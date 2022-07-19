

public class TestResepter {
    //skrev ingen egen integrasjonstest, utvidet heller TestResepter med Leger

    
    public static void main(String[] args) {
        /*//Oppretter legemidlene
        Narkotisk n = new Narkotisk("Morfin",99 , 1.0f, 50);
        Narkotisk n2 = new Narkotisk("Morfin",109 , 1.0f, 50);
        Vanlig v = new Vanlig("Paracet", 49, 2);
        Vanedannende vd = new Vanedannende("Heroin", 999, 10, 100);

        //Oppretter lege og spesialist                  
        Lege lege = new Lege("Hans");
        Spesialist spes = new Spesialist("Nina", "081295");
        Pasient pas = new Pasient("Lars", "081295");

        //Oppretter reseptene med referanser til legemidlende og legene
        Hvit hvitresept = new Hvit(v, lege,pas,10);
        Blaa blaresept = new Blaa(n, lege, pas, 3);
        Militarresept militarresept = new Militarresept(vd, lege, pas);
        Presept presept = new Presept(n2, spes, pas, 200);


        System.out.println(" Skriver ut info om Hvit:");
        System.out.println(hvitresept+ "\n");

        System.out.println(" Skriver ut info om Blaa:");
        System.out.println(blaresept + "\n");

        System.out.println(" Skriver ut info om Militarresept:");
        System.out.println(militarresept+ "\n");

        System.out.println("Skriver ut info om Presept:");
        System.out.println(presept + "\n");


        //Kort test av legeobjektene sine toString-metoder
        System.out.println("Test av legeobjektene:" );
        System.out.println(lege);
        System.out.println(spes);*/

        Narkotisk n = new Narkotisk("Morfin",99 , 1.0f, 50);
        Narkotisk n2 = new Narkotisk("Morfin",109 , 1.0f, 50);
        Vanlig v = new Vanlig("Paracet", 49, 2);
        Vanedannende vd = new Vanedannende("Heroin", 999, 10, 100);

        //Oppretter lege og spesialist                  
        Lege lege = new Lege("Hans");
        Spesialist spes = new Spesialist("Nina", "081295");
        Pasient pas = new Pasient("Lars", "081295");

        try {
            lege.skrivBlaaResept(n, pas, 10);
        } catch (UlovligUtskrift e) {
            e.printStackTrace();
        }
        try {
            lege.skrivHvitResept(n, pas, 10);
        } catch (UlovligUtskrift e) {
            e.printStackTrace();
        }
        try {
            lege.skrivMilResept(n, pas);
        } catch (UlovligUtskrift e) {
            e.printStackTrace();
        }
        try {
            lege.skrivPResept(n, pas,10);
        } catch (UlovligUtskrift e) {
            e.printStackTrace();
        }

        try {
            spes.skrivBlaaResept(n, pas,10);
        } catch (UlovligUtskrift e) {
            e.printStackTrace();
        }

        System.out.println("Skriver ut reseptene Nina har skrevet ut " + spes.hentUtSkrevneResepter());

        //Oppretter reseptene med referanser til legemidlende og legene
        Hvit hvitresept = new Hvit(v, lege,pas,10);
        Blaa blaresept = new Blaa(n, lege, pas, 3);
        Militarresept militarresept = new Militarresept(vd, lege, pas);
        Presept presept = new Presept(n2, spes, pas, 200);




        //Kort test av legeobjektene sine toString-metoder
        System.out.println("Test av legeobjektene:" );
        System.out.println(lege);
        System.out.println(spes);

       


        
    }
}
