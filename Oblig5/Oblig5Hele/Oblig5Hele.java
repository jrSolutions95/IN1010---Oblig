import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Oblig5Hele {

    public static void main(String[] args) {
        final int antallFletteTraader = 8;

        brukerStyring(antallFletteTraader);//kjører programmet med brukerstyring


        //brukt til testing:
        //ferdig("data/metadata.csv",7,antallFletteTraader);
        //ferdig("testdata/metadata.csv",5,antallFletteTraader);
        //ferdig("testdataliten/metadata.csv",2,antallFletteTraader);
    }   
        private static void brukerStyring(int antallFletteTraader) {
            System.out.println("Oppgi mappenavn paa mappen du vil aapne:");
            int terskel = 0;
            Scanner sc = new Scanner(System.in);
             while(sc.hasNextLine()){
                String linje = sc.nextLine();
                String navn = linje + "/metadata.csv";
                System.out.println("Oppgi differanse/terskel:");
                try {
                    int t = sc.nextInt();
                    ferdig(navn, t, antallFletteTraader);
                    System.exit(0);
                } catch (Exception e) {
                    System.out.println("Oops du ma oppgi et tall, avslutter programmet");
                    System.exit(1);
                }   
            }

            
        }

        private static void ferdig(String filnavn, int maxdiff,int antallFletteTraader){
            SubsekvensRegister pos = new SubsekvensRegister();
            SubsekvensRegister neg = new SubsekvensRegister();

            Monitor2 positiv = new Monitor2(pos);
            Monitor2 negativ = new Monitor2(neg);
    
            ArrayList<String> positivefiler =  filnavn(filnavn, true);
            ArrayList<String> negativefiler = filnavn(filnavn, false);
            
            try {
                filogFlettTraader(positivefiler,positiv,antallFletteTraader);
                filogFlettTraader(negativefiler,negativ,antallFletteTraader);
        
                HashMap<String,Subsekvens> positivMap = positiv.taUtHashMap();
                HashMap<String,Subsekvens> negativMap = negativ.taUtHashMap();
    
                 int antallPos = 0;
                 int antallNeg = 0;
                 int diff = 0;
            System.out.println("\nDominante Subsekvenser i mappen: " + filnavn);
            for(String key : positivMap.keySet()){
                if(negativMap.containsKey(key)){
                    //System.out.println(key);
                    antallPos = positivMap.get(key).hentAntall();
                    antallNeg = negativMap.get(key).hentAntall();
                    diff = antallPos - antallNeg;
                    if(diff >= maxdiff){
                        System.out.println(key +" "+ diff);
    
                    }
                } else{
                    if(positivMap.get(key).hentAntall() >= maxdiff){
                        System.out.println(key + " "+ positivMap.get(key).hentAntall());;
                    }
                }
            }
            System.out.println("");
         
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        
        private static ArrayList<String> filnavn(String filnavn, Boolean b){
            ArrayList<String> filoversikt = new ArrayList<>();
            String[] mappeinfo = filnavn.split("/");

            try {
                Scanner sc = new Scanner(new File(filnavn));
                while(sc.hasNextLine()){
                String linje = sc.nextLine();
                String[] info = linje.split(",");
                    if(b == Boolean.valueOf(info[1])){
                        String tmpnavn = mappeinfo[0]+"/" + info[0];
                        filoversikt.add(tmpnavn);    
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Kunne ikke finne mappen: "+ filnavn );
                System.exit(0);
            }
            return filoversikt;

        }
    
        private static void filogFlettTraader(ArrayList<String> filer, Monitor2 m1,int antallFletteTraader){
            CountDownLatch cdl = new CountDownLatch(filer.size());
            try {
                for(String filnavn : filer){
                    Thread lt = new Thread(new LeseTrad(m1,filnavn,cdl));
                    lt.start();
                }
                if(Thread.interrupted()){
                   throw new InterruptedException();
                }
                cdl.await(); 
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            
            System.out.println("Antall Hashmap etter filinnlesning: "+m1.antallHashMap());
               
            try {
                Thread[] flettetraader = new Thread[antallFletteTraader];
                for (int i = 0; i< antallFletteTraader; i++) {
                    flettetraader[i] = new Thread(new FletteTrad(m1));
                    flettetraader[i].start();
                }
                //System.out.println("Venter paa at all flettingen skal bli ferdig");
                for(Thread t : flettetraader){
                    t.join();
                }
                //System.out.println("Her skal alle vare ferdige");
    
            } catch (Exception e) {
                System.out.println(e);
            }
    
            System.out.println("Antall Hashmap etter fletting: " +m1.antallHashMap());
        
      }
}
