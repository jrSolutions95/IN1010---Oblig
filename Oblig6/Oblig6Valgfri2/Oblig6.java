import java.util.ArrayList;
import java.util.Scanner;

public class Oblig6 {
    public static void main(String[] args) {
        String filnavn = args[0];
        Labyrint laby = new Labyrint(filnavn);
        System.out.println(laby);
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Oppgi koordinater <rad> <kolonne> ( '-1' for aa avslutte):");
        String linje = "";
        while(!(linje.equals("-1"))){
            linje = sc.nextLine();
            String[] koordinater = linje.split(" ");
            if(koordinater.length == 2){
                System.out.println("Utveier funnet: ");
                try {
                    ArrayList<ArrayList<Tuppel>> utveier = laby.finnUtvei(Integer.parseInt(koordinater[0]),Integer.parseInt(koordinater[1]));
                    if(utveier.size() == 0){
                        System.out.println("0 loesninger funnet");
                    }else {
                        System.out.println(String.valueOf(utveier.size() + " loesninger funnet"));

                        int minst = utveier.get(0).size();
                        for(ArrayList<Tuppel> list : utveier){
                            if(list.size() <= minst){
                                System.out.println("Dette er den korteste losningen:");
                            }
                            System.out.println("Lengde på losningen: "+ list.size());
                            System.out.println("Her kommer en losning:");
                
                            for(Tuppel tup : list){
                                System.out.println(tup);
                            }
                            System.out.println("");
                        }


                    }
                } catch (NumberFormatException e) {
                    System.out.println("Feil med inputen -> " + e);
                } 

                System.out.println("\nOppgi koordinater <rad> <kolonne> ( '-1' for aa avslutte):");
            } else if(koordinater.length != 2 && !(koordinater[0].equals("-1"))) {
            System.out.println("Vennligst oppgi 2 tall:");
            }
        }
        sc.close();
        System.exit(0);
        
    }
}
