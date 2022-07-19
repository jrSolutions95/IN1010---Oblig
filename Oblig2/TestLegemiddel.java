import java.util.ArrayList;
import java.util.List;

public class TestLegemiddel  {
    public static void main(String[] args) {
        Narkotisk n = new Narkotisk("Morfin",99 , 1.0f, 50);
        Vanlig v = new Vanlig("Paracet", 49, 2);
        Vanedannende vd = new Vanedannende("Heroin", 999, 10, 100);

        List<Legemiddel> oversikt = new ArrayList<>();
        oversikt.add(n);
        oversikt.add(v);
        oversikt.add(vd);


        System.out.println("Test av toString-metodene:");
        for(Legemiddel l : oversikt){
            System.out.println(l.toString());
        }
        
        System.out.println("\nTest av testLegemiddel-metodene");
        testV(v,"Paracet", 49,2.0f);
        testN(n, "Morfin",99, 1.0f, 50);
        testVd(vd, "Heroin",999, 10, 100);


        



    }

    public static boolean testLegemiddelID(Legemiddel legemiddel, int forventetID) {
        return legemiddel.hentID() == forventetID;
        
    }

    public static boolean testLegemiddelNavn(Legemiddel legemiddel, String forventetNavn) {
        return (legemiddel.hentNavn().toLowerCase()).equals(forventetNavn.toLowerCase());

    }

    public static boolean testLegemiddelPris(Legemiddel legemiddel, int forventetPris) {
        return legemiddel.hentPris() == forventetPris;
        
    }

    public static boolean testLegemiddelVirkemiddelmg(Legemiddel legemiddel, float forventetVirkemiddelmg){
        return legemiddel.hentVirkestoff() == forventetVirkemiddelmg;
    }

    public static boolean testLegemiddelNarkotiskStyrke(Narkotisk legemiddel, int forventetNarkotiskStyrke) {
            return legemiddel.hentNarkotiskStyrke() == forventetNarkotiskStyrke;
        
    }

    public static boolean testLegemiddelVanedannendeStyrke(Vanedannende legemiddel, int forventetVanedannendeStyrke) {
        return legemiddel.hentVanedannendeStyrke() == forventetVanedannendeStyrke;
        
    }

    public static void testV(Vanlig legemiddel, String forventetNavn, int forventetPris, float forventetVirkemiddelmg){

         System.out.println("\n--- Kjorer test for: "+ legemiddel.hentNavn());
         System.out.println(" Navn: "+ legemiddel.hentNavn() + "  Forventet navn: " + forventetNavn + "  Resultat: " + testLegemiddelNavn(legemiddel, forventetNavn));
         System.out.println((" Pris: "+ legemiddel.hentPris() +  "  Forventet pris: "+ forventetPris + "  Resultat: " + testLegemiddelPris(legemiddel, forventetPris)));
         System.out.println((" Virkemiddel mg: "+ legemiddel.hentVirkestoff() +  "  Forventet virkemiddel mg: "+ forventetVirkemiddelmg + "  Resultat: " + testLegemiddelVirkemiddelmg(legemiddel, forventetVirkemiddelmg)+"\n"));
      
    }
        
    public static void testN(Narkotisk legemiddel, String forventetNavn, int forventetPris, float forventetVirkemiddelmg, int forventetNarkotiskStyrke){
        System.out.println("--- Kjorer test for: "+ legemiddel.hentNavn());
         System.out.println(" Navn: "+ legemiddel.hentNavn() + "  Forventet navn: " + forventetNavn + "  Resultat: " + testLegemiddelNavn(legemiddel, forventetNavn));
         System.out.println((" Pris: "+ legemiddel.hentPris() +  "  Forventet pris: "+ forventetPris + "  Resultat: " + testLegemiddelPris(legemiddel, forventetPris)));
         System.out.println((" Virkemiddel mg: "+ legemiddel.hentVirkestoff() +  "  Forventet virkemiddel mg: "+ forventetVirkemiddelmg + "  Resultat: " + testLegemiddelVirkemiddelmg(legemiddel, forventetVirkemiddelmg)));
         System.out.println((" Narkotisk Styrke: "+ legemiddel.hentNarkotiskStyrke() +  "  Forventet Narkotisk Styrke: "+ forventetNarkotiskStyrke + "  Resultat: " + testLegemiddelNarkotiskStyrke(legemiddel, forventetNarkotiskStyrke)+"\n"));
        
    }

    public static void testVd(Vanedannende legemiddel, String forventetNavn, int forventetPris, float forventetVirkemiddelmg, int forventetVanedannendeStyrke){
        System.out.println("--- Kjorer test for: "+ legemiddel.hentNavn());
         System.out.println(" Navn: "+ legemiddel.hentNavn() + "  Forventet navn: " + forventetNavn + "  Resultat: " + testLegemiddelNavn(legemiddel, forventetNavn));
         System.out.println((" Pris: "+ legemiddel.hentPris() +  "  Forventet pris: "+ forventetPris + "  Resultat: " + testLegemiddelPris(legemiddel, forventetPris)));
         System.out.println((" Virkemiddel mg: "+ legemiddel.hentVirkestoff() +  "  Forventet virkemiddel mg: "+ forventetVirkemiddelmg + "  Resultat: " + testLegemiddelVirkemiddelmg(legemiddel, forventetVirkemiddelmg)));
         System.out.println((" Vanedannende Styrke: "+ legemiddel.hentVanedannendeStyrke() +  "  Forventet Vanedannende Styrke: "+ forventetVanedannendeStyrke + "  Resultat: " + testLegemiddelVanedannendeStyrke(legemiddel, forventetVanedannendeStyrke)+"\n"));
      
    }





    
}
