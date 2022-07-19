import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SubsekvensRegister {
    private ArrayList<HashMap<String,Subsekvens>> beholder = new ArrayList<>();


    public void settInnHashMap(HashMap<String,Subsekvens> hm){
        beholder.add(hm);
    }

    public HashMap<String,Subsekvens> taUtHashMap() throws IndexOutOfBoundsException{
        return beholder.remove(0);
    }

    public int antallHashMap(){
        return beholder.size();
    }

    public static HashMap<String,Subsekvens> filLeser(String filnavn){
        Scanner sc = null;
        try {
            sc = new Scanner(new File(filnavn));
        } catch (FileNotFoundException e) {
            System.out.println("Kunne ikke finne filen: " + filnavn);
        }
        HashMap<String,Subsekvens> hm = new HashMap<>();
        String linje;

        int SubsekvensStrl = 3;

        while(sc.hasNextLine()){
            linje = sc.nextLine();
            if(linje.length() < 3){
                System.out.println("Feil i filformatet: Kan ikke lese linje med mindre enn 3 bokstaver");
                System.exit(1);
            } else{
                for(int i = 0; i <= linje.length()-SubsekvensStrl; i++){
                     String subsekvens = linje.substring(i,i+SubsekvensStrl);
                     hm.put(subsekvens, new Subsekvens(subsekvens,1));
                }
            }
        }
        sc.close();
        return hm;
    }

    public static HashMap<String,Subsekvens> flettHashMap(HashMap<String,Subsekvens> hm1, HashMap<String,Subsekvens> hm2){
        HashMap<String,Subsekvens> ferdig = new HashMap<>();

        Subsekvens tmp;
        for(Subsekvens s1: hm1.values()){// i denne loopet så legger vi til alle subsekvensene fra det forste hashmapet samt de som overlapper med det andre hashmappet
            tmp = hm2.remove(s1.sekvens);//prover å fjerne subsekvensen fra det andre hashmapet
            if(tmp == null){//hvis vi ikke klarer å fjerne det betyr det at det ikke finnes i det andre hashmappet og vi legger det til i den ferdige hashmapen
                ferdig.put(s1.sekvens,s1);
            } else{//hvis vi finner subsekvensen i det andre hashmapet, så øker vi bare antallet og legger det til det ferdige hashmapet
                int antall = tmp.hentAntall();
                s1.oekAntallForekomster(antall);
                ferdig.put(s1.sekvens,s1);
            }
        }
        //så legger vi til de subsekvensene som bare finnes i det andre hashmappet
        for(Subsekvens s2: hm2.values()){
            ferdig.put(s2.sekvens,s2);
        }

        return ferdig;
    }
}
