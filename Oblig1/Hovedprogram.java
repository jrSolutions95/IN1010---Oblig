import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hovedprogram {
    public static void lesFraFil(String filnavn) {//brukt regneøvelsen til marlen jarholt som inspirasjon for filinnlesning
        Scanner sc = null;

        try{
            sc = new Scanner(new File(filnavn));
        } catch(FileNotFoundException e){
            System.out.println(" En feil oppstod! Fant ikke filen: " + filnavn);
            System.exit(0);
        }
        Dataklynge dk = new Dataklynge();
        while(sc.hasNextLine()){
            String linje = sc.nextLine();
            String[] data = linje.split(" ");

            int antallNoder = Integer.parseInt(data[0]);
            int antallProsessorer = Integer.parseInt(data[1]);
            int minne = Integer.parseInt(data[2]);

            if(antallProsessorer > 16|| minne > 4096){
              System.out.println("Maksimalt antall prosessorer eller minne er overskrevet i filen: "+ filnavn);
              System.exit(0);
            }


            for(int i = 0; i < antallNoder; i++){
                Node n = new Node(antallProsessorer,minne);
                dk.leggTilNode(n);
            }
        }
        System.out.println("Skriver nå ut informasjon om Dataklyngen som baserer seg på: "+ filnavn);

        System.out.println(("Antall prosessorer i:"+ filnavn + dk.antProssesorer()));
        System.out.println(("Antall noder i: " +filnavn + dk.antallNoder()));
        System.out.println(("Antall racks i: " +filnavn + dk.antallRacks()));
        System.out.println(("Antall noder med over 128 GB minne: " + dk.noderMedNokMinne(128)));
        System.out.println(("Antall noder med over 1024 GB minne: " + dk.noderMedNokMinne(1024)));
    }
    public static void main(String[] args) {
        lesFraFil("dataklynge.txt");
        lesFraFil("dataklynge2.txt");
        lesFraFil("dataklynge3.txt");
        lesFraFil("dataklynge4.txt");
        /* Brukt til testing tidligere
        //SAGA
        Dataklynge saga = new Dataklynge();

        for(int i = 0; i <450; i++){
            Node n = new Node(4,512);
            saga.leggTilNode(n);
        }

        for (int i = 0; i < 16; i++) {
            Node n = new Node(8,1024);
            saga.leggTilNode(n);
        }

        System.out.println("Informasjon om saga følger: \n");
        System.out.println(("Antall prosessorer i saga: " + saga.antProssesorer()));
        System.out.println(("Antall noder i saga: " + saga.antallNoder()));
        System.out.println(("Antall racks i saga: " + saga.antallRacks()));
        System.out.println(("Antall noder med over 128 GB minne: " + saga.noderMedNokMinne(128)));
        System.out.println(("Antall noder med over 1024 GB minne: " + saga.noderMedNokMinne(1024)));*/
    }

}
