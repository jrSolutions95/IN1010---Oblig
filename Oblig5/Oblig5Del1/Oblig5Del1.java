import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Oblig5Del1 {

    public static void main(String[] args) {
        SubsekvensRegister r = new SubsekvensRegister();
        brukerStyring(r);
        /* Brukt til testing
        SubsekvensRegister sr = new SubsekvensRegister();
        SubsekvensRegister r = new SubsekvensRegister();
        filogFlett("testdatalike/metadata.csv",sr);
        filogFlett("testdatalitenlike/metadata.csv",r);*/

    }
    
    private static void brukerStyring(SubsekvensRegister r) {
        System.out.println("Oppgi mappenavn paa mappen du vil aapne:");
        Scanner sc = new Scanner(System.in);
         while(sc.hasNextLine()){
            String linje = sc.nextLine();
            String navn = linje + "/metadata.csv";
            filogFlett(navn,r);
            System.exit(0);
        }

        
    }
    private static void filogFlett(String filnavn, SubsekvensRegister sr){
        try {
            Scanner sc = new Scanner(new File(filnavn));
            String[] info = filnavn.split("/");
            while(sc.hasNextLine()){
                String tmpnavn = info[0]+"/"+sc.nextLine();
                sr.settInnHashMap(SubsekvensRegister.filLeser(tmpnavn));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke mappen eller filen med metadata");
            System.exit(0);
        }


        System.out.println("Antall HashMap for fletting: "+sr.antallHashMap());
        try {
            while(sr.antallHashMap() > 1){
                HashMap<String,Subsekvens> hm1 = sr.taUtHashMap();
                HashMap<String,Subsekvens> hm2 = sr.taUtHashMap();
    
                HashMap<String,Subsekvens> f = sr.flettHashMap(hm1, hm2);
    
                sr.settInnHashMap(f);            
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Kan ikke hente ut fra beholderen da den er tom");
        }

       
        Subsekvens flest = null;
        for(Subsekvens s : sr.taUtHashMap().values()){
            if(flest == null){
                flest = s;
            }
            else if(s.hentAntall() > flest.hentAntall()){
                flest = s;
            }
        }

        System.out.println("Subsekvens med flest forekomster: "+ flest);
        

    }
}
