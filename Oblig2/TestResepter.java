

public class TestResepter {
    //skrev ingen egen integrasjonstest, utvidet heller TestResepter med Leger

    
    public static void main(String[] args) {
        //Oppretter legemidlene
        Narkotisk n = new Narkotisk("Morfin",99 , 1.0f, 50);
        Narkotisk n2 = new Narkotisk("Morfin",109 , 1.0f, 50);
        Vanlig v = new Vanlig("Paracet", 49, 2);
        Vanedannende vd = new Vanedannende("Heroin", 999, 10, 100);

        //Oppretter lege og spesialist                  
        Lege lege = new Lege("Hans");
        Spesialist spes = new Spesialist("Nina", "081295");

        //Oppretter reseptene med referanser til legemidlende og legene
        Hvit hvitresept = new Hvit(v, lege,1,10);
        Blaa blaresept = new Blaa(n, lege, 2, 3);
        Militarresept militarresept = new Militarresept(vd, lege, 0);
        Presept presept = new Presept(n2, spes, 4, 200);


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
        System.out.println(spes);

       


        
    }
}
