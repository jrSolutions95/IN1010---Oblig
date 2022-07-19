import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Oblig5Del2B {

    public static void main(String[] args) {
        SubsekvensRegister sr = new SubsekvensRegister();
        Monitor2 m1 = new Monitor2(sr);

        brukerStyring(m1);

        /* Brukt til testing
        System.out.println("Skriver ut info om Monitor med mappen -> testdatalike");
        
        kjor8("testdatalike/metadata.csv",m1);

        
        System.out.println("\nSkriver ut info om Monitor med mappen -> testdatalitenlike");
        SubsekvensRegister r = new SubsekvensRegister();
        Monitor2 m2 = new Monitor2(r);
        kjor8("testdatalitenlike/metadata.csv",m2);*/
    }

    private static void brukerStyring(Monitor2 m1) {
        System.out.println("Oppgi mappenavn paa mappen du vil aapne:");
        Scanner sc = new Scanner(System.in);
         while(sc.hasNextLine()){
            String linje = sc.nextLine();
            String navn = linje + "/metadata.csv";
            try {
                kjor8(navn, m1);
                System.exit(0);;
            } catch (Exception e) {
                System.out.println(e);
                System.exit(0);
            }
         
         }
    }

    private static int antallLinjer(String filnavn){
        int antall = 0;
        try {
            Scanner sc = new Scanner(new File(filnavn));
            while(sc.hasNextLine()){
                sc.nextLine();
                antall+=1;
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return antall;
    }

    private static void kjor8(String filn, Monitor2 m1) throws InterruptedException{
        CountDownLatch cdl = new CountDownLatch(antallLinjer(filn));
        try {
            String filnavn = filn;
            Scanner sc = new Scanner(new File(filnavn));
            String[] info = filnavn.split("/");
            while(sc.hasNextLine()){
                String tmpnavn = info[0]+"/"+sc.nextLine();
                Thread lt = new Thread(new LeseTrad(m1,tmpnavn,cdl));
                lt.start();
                

                if(Thread.interrupted()){
                    throw new InterruptedException();
                }
                
            }
            cdl.await(); 
        } catch (FileNotFoundException | InterruptedException e) {
            System.out.println(e);
            System.exit(0);
        }

        System.out.println("Antall Hashmap etter filinnlesning: "+m1.antallHashMap());

        try {
            Thread[] flettetraader = new Thread[10];
            for (int i = 0; i<10 ; i++) {
                flettetraader[i] = new Thread(new FletteTrad(m1));
                flettetraader[i].start();
            }
            System.out.println("Venter paa at all flettingen skal bli ferdig");
            for(Thread t : flettetraader){
                t.join();
            }
            System.out.println("Her skal alle vare ferdige");

        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Antall Hashmap etter fletting: " +m1.antallHashMap());
        Subsekvens flest = null;
        for(Subsekvens s : m1.taUtHashMap().values()){
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
