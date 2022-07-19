import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Labyrint{
    private Rute[][] lab;
    private int rader;
    private int kolonner;


    public Labyrint(String filnavn){
        filLeser(filnavn);

    }
    public void finnUtvei(int rad, int kolonne){
        if(rad >= rader){
            System.out.println("Oops kan ikke starte utenfor Labyrinten, Index: " + String.valueOf(rad) + " er utenfor! " + "Ovre Index er: " + String.valueOf(rader-1));
        }

        if(kolonne >= kolonner){
            System.out.println("Oops kan ikke starte utenfor Labyrinten, Index: " + String.valueOf(kolonne) + " er utenfor! "+ "Ovre Index er: "+ String.valueOf(kolonner-1));
        } else if(rad < rader && kolonne < kolonner){
             Rute tmp = lab[rad][kolonne];
             if(tmp instanceof SortRute){
                  System.out.println("Du kan ikke starte i en svart rute");
            }
            tmp.finn(null);
        }

    }
    private void filLeser(String filnavn){
        try {
            Scanner sc = new Scanner(new File(filnavn));
            String dimensjoner = sc.nextLine();
            String [] dim = dimensjoner.split(" ");
            rader = Integer.parseInt(dim[0]);
            kolonner = Integer.parseInt(dim[1]);
            lab = new Rute[rader][kolonner];

            int rad = 0;

            while(sc.hasNextLine()){
                int kol = 0;
                String linje = sc.nextLine();
                String[] tegnpaaLinje = linje.split("");

                for(String tegn : tegnpaaLinje){
                    Rute tmp;
                    if(tegn.equals("#")){
                        tmp = new SortRute(rad, kol);
                        lab[rad][kol] = tmp;
                    } else if(tegn.equals(".")){
                        if(rad == 0 || rad == rader -1 || kol == 0 || kol == kolonner -1){//sjekker om vi er i utkanten av labyrinten -> da er dette en åpning som må legges inn
                            tmp = new Aapning(rad, kol);
                            lab[rad][kol] = tmp;
                        } else{
                            tmp = new HvitRute(rad, kol);
                            lab[rad][kol] = tmp;
                        }
                    } else{
                        System.out.println(" Noe feil med symbolene i filen på rad:" + rad + " og kolonne:" + kol);
                        System.exit(1);
                    }
                kol++;
                }
            rad++;
            }
            sc.close();
            lesInnNaboer();
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke filen " + filnavn + "Avslutter programmet");
            System.exit(1);
        }
       
        
    }

    private void lesInnNaboer(){
        for(int i = 0; i < rader; i++){
            for(int y = 0; y < kolonner; y++){
                Rute nord = null;
                Rute syd = null;
                Rute vest = null; 
                Rute oest = null;

                Rute tmp = lab[i][y];

                if(!(i==0)){//her gjor vi sjekker om vi befinner oss i kantene av labyrinten, hvis vi befinner oss i kanten blir naboen i retningen vi sjekker staaende som null
                    nord = lab[i-1][y];
                }
                if(!(i == rader -1)){
                    syd = lab[i+1][y];
                }
                if(!(y == 0)){
                    vest = lab[i][y-1];
                }
                if(!(y == kolonner-1)){
                    oest = lab[i][y+1];
                }

                tmp.settInnNaboer(nord, syd, vest, oest);
            }
        }
    }

    @Override
    public String toString(){
        String linje = "";
        for(Rute[] rad : lab){
            for(Rute kol : rad){
                linje += kol;
            }
            linje += "\n";
        }
        return linje;
    }
}