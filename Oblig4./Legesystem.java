import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.lang.NumberFormatException;
import java.lang.NullPointerException;
import java.lang.IllegalArgumentException;


public class Legesystem {
    private Stabel<Pasient> pasientListe = new Stabel<>();
    private Stabel<Legemiddel> legemiddelListe = new Stabel<>();
    private Prioritetskoe<Lege> legeListe = new Prioritetskoe<>(); 
    private Stabel<Resept> reseptListe = new Stabel<>();
   
   
    private Legemiddel hentRiktLegemiddel(int id, Stabel<Legemiddel> legeMliste){
        for(Legemiddel lm: legeMliste){
            if(lm.hentID() == id){
                return lm;
            }
        }
        return null;
    }

    private Lege hentRiktigLege(String navn, Prioritetskoe<Lege> legeList){
        for(Lege l : legeList){
            if(navn.equals(l.hentNavn())){
                return l;
            }
        }
        return null;
    }

    private Pasient hentRiktigPasient(String id, Stabel<Pasient> pasientList ) {
        for(Pasient p : pasientList){
            if(id.equals(p.pasientId())){
                return p;
            }
        }
        return null;
        
    }

    private void lesInnFil(String filnavn) throws FileNotFoundException{
        Scanner sc = null;

        try {
            sc = new Scanner(new File(filnavn));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();//kaster unntaket videre
        }
        int feil = 0;
        String linje = sc.nextLine();
        while(sc.hasNextLine()){
            
            if(linje.startsWith("# Pasienter")){
                while(sc.hasNextLine()){
                    linje = sc.nextLine();

                    if(linje.startsWith("#")){
                        break;
                    }
                String[] info = linje.split(",");
                Pasient pas = new Pasient(info[0],info[1]);
                pasientListe.leggTil(pas);

                }
            } 

            else if(linje.startsWith("# Legemidler")){
                while(sc.hasNextLine()){
                    linje = sc.nextLine();

                    if(linje.startsWith("#")){
                        break;
                    }
                    String[] info = linje.split(",");

                    try {
                        sjekkInputResept(info);

                        String navn = info[0];
                        String type = info[1];
                        int pris = Integer.parseInt(info[2]);
                        float virkeStoffmg = Float.parseFloat(info[3]);
                        if(type.equals("narkotisk")){
                            int narkotiskStyrke = Integer.parseInt(info[4]);
            
                            Narkotisk n = new Narkotisk(navn,pris,virkeStoffmg,narkotiskStyrke);
                            legemiddelListe.leggTil(n);
                                
                        }  else if(type.equals("vanedannende")){
                            int vaneDannendeStyrke = Integer.parseInt(info[4]);
                            Vanedannende vd = new Vanedannende(navn,pris,virkeStoffmg,vaneDannendeStyrke);
                            legemiddelListe.leggTil(vd);
            
                        } else if(type.equals("vanlig")){
                            Vanlig v = new Vanlig(navn,pris,virkeStoffmg);
                            legemiddelListe.leggTil(v);
                        } else{
                            System.out.println("En feil er oppdaget i formatet til: "+ navn + " || Feiltype: typen legemiddel kan ikke opprettes: " +type);
                            feil+=1;
                        }
                      
    
                    } catch (IllegalArgumentException| NullPointerException e ) {
                        System.out.println("En feil er oppdaget i formatet til: "+ info[0] + " || Feiltype: " +e);
                        feil +=1;
                    
                    }
                }
            
            }
        
            else if(linje.startsWith("# Leger")){
                while(sc.hasNextLine()){
                    linje = sc.nextLine();

                    if(linje.startsWith("#")){
                        break;
                    }
                String[] info = linje.split(",");
                try {
                    if(info[1].equals("0")){
                        Lege l = new Lege(info[0]);
                        legeListe.leggTil(l);
                    } else{
                        Spesialist spes = new Spesialist(info[0], info[1]);
                        legeListe.leggTil(spes);
                    }
                } catch (NumberFormatException |ArrayIndexOutOfBoundsException e) {
                    System.out.println("En feil er oppdaget i formatet til: " +info[0] + " || Feiltype: " + e);
                    feil+=1;
                }

               
                }
            }
            
            else if(linje.startsWith("# Resepter")){//forutsetter her at filene kan inneholde mer informasjon som er irrelevant for oss, hvis vi visste at dette var slutte av fila kunne vi bare brukt else
                while(sc.hasNextLine()){
                    linje = sc.nextLine();

                    if(linje.startsWith("#")){
                        break;
                    }
                    try {
                        String[] info = linje.split(",");
                        Legemiddel lm = hentRiktLegemiddel(Integer.parseInt(info[0]),legemiddelListe);
                        Lege l = hentRiktigLege(info[1],legeListe);
                        Pasient p = hentRiktigPasient(info[2], pasientListe);
                        String type = info[3];
                        
        
                        if(type.equals("hvit")){
                            int reit = Integer.valueOf(info[4]);
                            try {
                                Hvit res = l.skrivHvitResept(lm, p, reit);
                                reseptListe.leggTil(res);
                                p.leggTilNyResept(res);
                               
                             } catch (UlovligUtskrift| NullPointerException e) {
                                System.out.println(e);
                                System.out.println("Resepten ble dermed ikke opprettet" );
        
                            }   
                            
                        } else if(type.equals("blaa")){
                            int reit = Integer.valueOf(info[4]);
                            try {
                                Blaa res = l.skrivBlaaResept(lm, p, reit);
                                reseptListe.leggTil(res);
                                p.leggTilNyResept(res);
                             } catch (UlovligUtskrift| NullPointerException e) {
                                System.out.println(e);
                                System.out.println("Resepten ble dermed ikke opprettet" );
        
                            }   
                        } else if(type.equals("militaer")){
                            try {
                                Militarresept res = l.skrivMilResept(lm, p);
                                reseptListe.leggTil(res);
                                p.leggTilNyResept(res);
                             } catch (UlovligUtskrift| NullPointerException e) {
                                System.out.println(e);
                                System.out.println("Resepten ble dermed ikke opprettet" );
        
                            }   
                        } else if(type.equals("p")){
                            int reit = Integer.valueOf(info[4]);
                            try {
                                Presept res = l.skrivPResept(lm, p, reit);
                                reseptListe.leggTil(res);
                                p.leggTilNyResept(res);
                             } catch (UlovligUtskrift| NullPointerException e) {
                                System.out.println(e);
                                System.out.println("Resepten ble dermed ikke opprettet" );
        
                            }   
                        }  
                        
                    } catch (Exception e) {
                        System.out.println(e);
                    }
               
            }
            }
         }
         sc.close();
         if(feil > 0){
             System.out.println("Totalt antall feil ved filformatet: " + feil + "\n");
         }
    }

    private void sjekkInputResept(String[] data)throws NumberFormatException, IllegalArgumentException{
        if(data.length == 4 || data.length == 5){
            if(data.length == 4){
                try {
                    int pris = Integer.parseInt(data[2]);
                    Float f =  Float.parseFloat(data[3]);
                } catch (NumberFormatException e) {
                    throw e;
                } 
            } else {
                try {
                    int pris = Integer.parseInt(data[2]);
                    Float f =  Float.parseFloat(data[3]);
                    int styrke = Integer.parseInt(data[4]);
                } catch (NumberFormatException e) {
                    throw e;
                } 
            }
        } else{
            throw new IllegalArgumentException("Antall argumenter er feil");
        }
    }

    public void kjor(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Velkommen til Lars sitt legeystem: ");
        skrivUtHovedMeny();
        String input = sc.nextLine();

        while(!input.equals("0")){
            if(input.equals("1")){
                printAlleData();
                skrivUtHovedMeny();
                input = sc.nextLine();

            } else if(input.equals("2")){
                skrivUtUnderMeny2();
                
                String input2 = sc.nextLine();
                if(input2.equals("p")){
                    System.out.println("Oppgi navn paa pasienten ->");
                    String navn = sc.nextLine();
                    System.out.println("Oppgi fodselsnummer paa pasienten -> ");
                    String fdslN = sc.nextLine();

                    lagNyPasient(navn, fdslN);
                    
                }
                else if(input2.equals("lm")){
                    skrivUtUndermeny21();
                    String input21 = sc.nextLine();

                    while(!input21.equals("9")){
                     if(input21.equals("V")){

                        try {
                            System.out.println("Oppgi navn paa Legemiddelet ->");
                            String navn = sc.nextLine();
                            System.out.println("Oppgi Pris ->");
                            int pris = Integer.parseInt(sc.nextLine());
                            System.out.println("Oppgi virkestoff ->");
                            float virkeStoffmg = Float.parseFloat(sc.nextLine());
                            lagVanlig(navn,pris,virkeStoffmg);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Oops jeg skjonte ikke formatet || Feilen:" + e +  "\nProv paa nytt!");
                        }
                        
                        
                        
        
                    } else if(input21.equals("VD")){
                        try {
                            System.out.println("Oppgi navn paa Legemiddelet ->");
                            String navn = sc.nextLine();
                            System.out.println("Oppgi Pris ->");
                            int pris = Integer.parseInt(sc.nextLine());
                            System.out.println("Oppgi virkestoff ->");
                            float virkeStoffmg = Float.parseFloat(sc.nextLine());
                            System.out.println("Oppgi Vandedannene styrke ->");
                            int vaneDannendeStyrke = Integer.parseInt(sc.nextLine());

                            lagVanedannende(navn, pris, virkeStoffmg, vaneDannendeStyrke);
                            break;
                            
                        } catch (NumberFormatException e) {
                            System.out.println("Oops jeg skjonte ikke formatet || Feilen:" + e +  "\nProv paa nytt!");
                        }
                        
                        
                    } else if(input21.equals("N")){
                        try {
                            System.out.println("Oppgi navn paa Legemiddelet ->");
                            String navn = sc.nextLine();
                            System.out.println("Oppgi Pris ->");
                            int pris = Integer.parseInt(sc.nextLine());
                            System.out.println("Oppgi virkestoff ->");
                            float virkeStoffmg = Float.parseFloat(sc.nextLine());
                            System.out.println("Oppgi Narkotisk styrke ->");
                            int narkotiskStyrke = Integer.parseInt(sc.nextLine());

                            lagNarkotisk(navn, pris, virkeStoffmg, narkotiskStyrke);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Oops jeg skjonte ikke formatet || Feilen:" + e +  "\nProv paa nytt!");
                        }
                        
                        
                    } else {
                        System.out.println("Oops! jeg forstod ikke komandoen. Sorg for at kommandoen er gyldig");
                        skrivUtUndermeny21();
                        input21 = sc.nextLine();
                    }
                    }
                
               }
                else if(input2.equals("l")){
                    System.out.println("Oppgi navn paa legen ->");
                    String navn = sc.nextLine();
                    System.out.println("Hvis legen er spesialist oppgi S ellers oppgi V ->");
                    String type = sc.nextLine();
                    if(type.equals("S")){
                        System.out.println("Oppgi kontrollid ->");
                        String kID = sc.nextLine();
                        Spesialist spes = new Spesialist(navn, kID);
                        legeListe.leggTil(spes);
                        System.out.println("Ny spesialist opprettet: " + spes);
                    } else{
                        Lege l = new Lege(navn);
                        legeListe.leggTil(l);
                        System.out.println("Ny lege opprettet: " + l);
                    }

                }
                else if(input2.equals("r")){

                        skrivUtReseptTyper();
                        input2 = sc.nextLine();
                        while(!input2.equals("9")){
                        if(input2.equals("Hvit")){
                            int lmID;
                            int reit;
                            int index;
                            Lege l;

                            try {
                                System.out.println("Velg forst en pasient som skal bruke den hvite resepten");
                                System.out.println(pasientListe);
                                System.out.println("Oppgi ID pa pasienten ->");
                                String pasID = sc.nextLine();
                                Pasient p = hentRiktigPasient(pasID, pasientListe);

                                if(p == null){
                                    System.out.println("Det finnes ingen pasient i systemet med ID: " + pasID );
                                    break;
                                }
                            
                                System.out.println("Velg legemiddel du vil bruke i den hvite resepten");
                                System.out.println(legemiddelListe);
                                System.out.println("Oppgi ID pa legemiddelet");
                                try {
                                    lmID = Integer.parseInt(sc.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Oops! Du maa oppgi et heltall || Feilmelding: " + e);
                                    break;
                                }

                                Legemiddel lm = hentRiktLegemiddel(lmID,legemiddelListe);

                                if(lm == null){
                                    System.out.println("Det finnes ingen legemiddel i systemet med ID: " + lmID);
                                    break;
                                }
                                
                                System.out.println("Oppgi onsket reit");

                                try {
                                    reit = Integer.parseInt(sc.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Oops! Du maa oppgi et heltall || Feilmelding: " + e);
                                    break;
                                }
                            

                                System.out.println("Velg en lege som skal skrive ut den hvite resepten");
                                int teller = 0;
                                for(Lege ln : legeListe){
                                    System.out.println( teller + " " + ln);
                                    teller++;
                                }
                                System.out.println("Oppgi index pa legen");
                                try {
                                    index = Integer.parseInt(sc.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Oops! Du maa oppgi et heltall || Feilmelding: " + e);
                                    break;
                                }

                                try {
                                    l = legeListe.hentPaIndex(index);
                                } catch (NullPointerException e) {
                                    System.out.println("Fant ingen leger paa index: " + index);
                                    break;
                                }
                            
                            Hvit h =  l.skrivHvitResept(lm, p, reit);
                            reseptListe.leggTil(h);
                            System.out.println(" Ny resept lagt til: " + h);
                            break;
                                
                            } catch (UlovligUtskrift e) {
                                System.out.println(e + " paa Hvit resept");
                                break;
                            }
                            
                            
                        } else if(input2.equals("Blaa")){
                            int lmID;
                            int reit;
                            int index;
                            Lege l;

                            try {
                                System.out.println("Velg forst en pasient som skal bruke den blaa resepten");
                                System.out.println(pasientListe);
                                System.out.println("Oppgi ID pa pasienten ->");
                                String pasID = sc.nextLine();
                                Pasient p = hentRiktigPasient(pasID, pasientListe);

                                if(p == null){ 
                                    System.out.println("Det finnes ingen pasient i systemet med ID: " + pasID );
                                     break;
                                }
                           
                                System.out.println("Velg legemiddel du vil bruke i den blaa resepten");
                                System.out.println(legemiddelListe);
                                System.out.println("Oppgi ID pa legemiddelet");

                                try {
                                    lmID = Integer.parseInt(sc.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Oops! Du maa oppgi et heltall || Feilmelding: " + e);
                                    break;
                                }

                                Legemiddel lm = hentRiktLegemiddel(lmID,legemiddelListe);

                                if(lm == null){
                                    System.out.println("Det finnes ingen legemiddel i systemet med ID: " + lmID);
                                    break;
                                }
                                
                                System.out.println("Oppgi onsket reit");

                                try {
                                    reit = Integer.parseInt(sc.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Oops! Du maa oppgi et heltall || Feilmelding: " + e);
                                    break;
                                }
                            

                                System.out.println("Velg en lege som skal skrive ut den blaa resepten");
                                int teller = 0;
                                for(Lege ln : legeListe){
                                    System.out.println( teller + " " + ln);
                                    teller++;
                                }
                                System.out.println("Oppgi index pa legen");
                                try {
                                    index = Integer.parseInt(sc.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Oops! Du maa oppgi et heltall || Feilmelding: " + e);
                                    break;
                                }

                                try {
                                    l = legeListe.hentPaIndex(index);
                                } catch (NullPointerException e) {
                                    System.out.println("Fant ingen leger paa index: " + index);
                                    break;
                                }
                            
                            
                            Blaa b =  l.skrivBlaaResept(lm, p, reit);
                            reseptListe.leggTil(b);
                            System.out.println(" Ny resept lagt til: " + b);
                            break;
                            } catch (UlovligUtskrift e) {
                                System.out.println(e + " paa Blaa resept");
                                break;
                            }

                        
                        } else if(input2.equals("P")){
                            int lmID;
                            int reit;
                            int index;
                            Lege l;

                            try {
                                System.out.println("Velg forst en pasient som skal bruke P-resepten");
                                System.out.println(pasientListe);
                                System.out.println("Oppgi ID pa pasienten ->");
                                String pasID = sc.nextLine();
                                Pasient p = hentRiktigPasient(pasID, pasientListe);

                                if(p == null){ 
                                    System.out.println("Det finnes ingen pasient i systemet med ID: " + pasID );
                                     break;
                                }
                           
                                System.out.println("Velg legemiddel du vil bruke i P-resepten");
                                System.out.println(legemiddelListe);
                                System.out.println("Oppgi ID pa legemiddelet");

                                try {
                                    lmID = Integer.parseInt(sc.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Oops! Du maa oppgi et heltall || Feilmelding: " + e);
                                    break;
                                }

                                Legemiddel lm = hentRiktLegemiddel(lmID,legemiddelListe);

                                if(lm == null){
                                    System.out.println("Det finnes ingen legemiddel i systemet med ID: " + lmID);
                                    break;
                                }
                                
                                System.out.println("Oppgi onsket reit");

                                try {
                                    reit = Integer.parseInt(sc.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Oops! Du maa oppgi et heltall || Feilmelding: " + e);
                                    break;
                                }

                                System.out.println("Velg en lege som skal skrive ut P-resepten");                             
                                int teller = 0;
                                for(Lege ln : legeListe){
                                    System.out.println( teller + " " + ln);
                                    teller++;
                                }
                                System.out.println("Oppgi index pa legen");
                                try {
                                    index = Integer.parseInt(sc.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Oops! Du maa oppgi et heltall || Feilmelding: " + e);
                                    break;
                                }

                                try {
                                    l = legeListe.hentPaIndex(index);
                                } catch (NullPointerException e) {
                                    System.out.println("Fant ingen leger paa index: " + index);
                                    break;
                                }


                                Presept pr =  l.skrivPResept(lm, p, reit);
                                reseptListe.leggTil(pr);
                                System.out.println(" Ny resept lagt til: " + pr);
                                break;
                            } catch (UlovligUtskrift e) {
                                System.out.println(e + " paa P-resept");
                                break;
                            }

                        } else if(input2.equals("Mil")){
                            int lmID;
                            int index;
                            Lege l;

                            try {
                                System.out.println("Velg forst en pasient som skal bruke Militaerresepten");
                                System.out.println(pasientListe);
                                System.out.println("Oppgi ID pa pasienten ->");
                                String pasID = sc.nextLine();
                                Pasient p = hentRiktigPasient(pasID, pasientListe);

                                if(p == null){ 
                                    System.out.println("Det finnes ingen pasient i systemet med ID: " + pasID );
                                     break;
                                }
                           
                                System.out.println("Velg legemiddel du vil bruke i Militaerresepten");
                                System.out.println(legemiddelListe);
                                System.out.println("Oppgi ID pa legemiddelet");

                                try {
                                    lmID = Integer.parseInt(sc.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Oops! Du maa oppgi et heltall || Feilmelding: " + e);
                                    break;
                                }

                                Legemiddel lm = hentRiktLegemiddel(lmID,legemiddelListe);

                                if(lm == null){
                                    System.out.println("Det finnes ingen legemiddel i systemet med ID: " + lmID);
                                    break;
                                }
                                
                                System.out.println("Velg en lege som skal skrive ut Militarresepten");                          
                                int teller = 0;
                                for(Lege ln : legeListe){
                                    System.out.println( teller + " " + ln);
                                    teller++;
                                }
                                
                                System.out.println("Oppgi index pa legen");
                                try {
                                    index = Integer.parseInt(sc.nextLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Oops! Du maa oppgi et heltall || Feilmelding: " + e);
                                    break;
                                }


                                try {
                                    l = legeListe.hentPaIndex(index);
                                } catch (NullPointerException e) {
                                    System.out.println("Fant ingen leger paa index: " + index);
                                    break;
                                }

                                Militarresept mil =  l.skrivMilResept(lm, p);
                                reseptListe.leggTil(mil);
                                System.out.println(" Ny resept lagt til: " + mil);
                                break;
                            } catch (UlovligUtskrift e) {
                                System.out.println(e + " paa Militaerresept");
                                break;
                            }
                        } 
                        
                    }

                }
                else if(input2.equals("9")){
                    skrivUtHovedMeny();
                    input = sc.nextLine();
                } else {
                    System.out.println("Oops! jeg forstod ikke komanndoen. Sorg for at kommandoen er gyldig");
                }
            } else if(input.equals("3")){
                try {
                    brukResept(sc);
                } catch (NumberFormatException |UgyldigListeindeks e) {
                    System.out.println(e); 
                }
                skrivUtHovedMeny();
                input = sc.nextLine();
                
            } else if(input.equals("4")){
                skrivUtUnderMeny4();
                String input2 = sc.nextLine();
                if(input2.equals("Vane")){
                    totaltAntallVanedannendeResepter();;
                    
                } else if(input2.equals("Nark")){
                    totaltAntallNarkotiskeResepter();

                } else if(input2.equals("M")){
                    muligMisbrukNarkotisk();

                } else if(input2.equals("9")){
                    skrivUtHovedMeny();
                    input = sc.nextLine();
                } else{
                    System.out.println("Oops! jeg forstod ikke komanndoen oppgi en gyldig handling");
                    
                }

            } else if(input.equals("5")){
                System.out.println("Hva onsker du at navnet pa filen skal vare ->");
                String filnavn = sc.nextLine() + ".txt";
                skrivTilFil(filnavn);
                skrivUtHovedMeny();
                input = sc.nextLine();
            
            } else if(input.equals("6")){
                System.out.println("Skriv navnet pa filen du onsker aa aapne (uten .txt): ");
                String input6 = sc.nextLine();
                try {
                    lesInnFil(input6+".txt");
                    System.out.println("Suksess! Filen ble aapnet\n");
                } catch (FileNotFoundException e) {
                    System.out.println(" En feil oppstod! Fant ikke filen: " + input6+".txt");
                    
                }
                skrivUtHovedMeny();
                input = sc.nextLine();
                
                
            } else{
                System.out.println("Oops! jeg forstod ikke komanndoen skriv et tall mellom 0 og 5");
                skrivUtHovedMeny();
                input = sc.nextLine();
            }
        }
        sc.close();
    }

    private void skrivTilFil(String filnavn){
        
        PrintWriter nyFil = null;
        try {
            nyFil = new PrintWriter(filnavn);
        } catch (Exception e) {
            System.out.println(e);
        }

        nyFil.print("# Pasienter (navn, fnr)\n");
        for(Pasient p: pasientListe){
            nyFil.println(p.hentNavn()+ ","+p.hentFdslNummer() );
        }
        nyFil.print("# Legemidler (navn,type,pris,virkestoff,[styrke])");
        for(Legemiddel lmd: legemiddelListe){
            if(lmd instanceof Vanlig){
                nyFil.println(lmd.hentNavn() + "," + lmd.type()+ "," + lmd.hentPris() + "," + lmd.hentVirkestoff());
            } else if(lmd instanceof Vanedannende){
                Vanedannende lm = (Vanedannende) lmd;//legges til på riktig type(caster)
                nyFil.println(lm.hentNavn() + "," + lm.type()+ "," + lm.hentPris() + "," + lm.hentVirkestoff()+ "," + lm.hentVanedannendeStyrke());
            } else if(lmd instanceof Narkotisk){
                Narkotisk lm = (Narkotisk) lmd;
                nyFil.println(lm.hentNavn() + "," + lm.type()+ "," + lm.hentPris() + "," + lm.hentVirkestoff()+ "," + lm.hentNarkotiskStyrke());
            }
        }
                
        nyFil.print("# Leger (navn,kontrollid / 0 hvis vanlig lege)\n");
        for(Lege l : legeListe){
            if(l instanceof Spesialist){
                Spesialist spes = (Spesialist) l;
                nyFil.println(spes.hentNavn() +"," + spes.hentKontrollID());
            } else{
                nyFil.println(l.hentNavn() + "," + "0");
            }
            
        }

        nyFil.print("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])\n");
        for(Resept r : reseptListe){
            nyFil.println(r.hentLegemiddel().hentID() + "," + r.hentLege().hentNavn() + ", " + r.hentPasientID() + "," + r.type() +"," + r.hentReit());
        }
        nyFil.close();
        System.out.println("Data er naa skrevet til filen: "+ filnavn);
       
    }

    private void totaltAntallVanedannendeResepter() {
    
        int teller = 0;
        for(Legemiddel l : legemiddelListe){
            if(l instanceof Vanedannende){
                teller+=1;
            }
        }
        System.out.println("Totalt antall utskrevne resepter med vanedannende legemidler: "+ teller);
        
    }

    private void totaltAntallNarkotiskeResepter(){
        int teller = 0;
        for(Legemiddel l: legemiddelListe){
            if(l instanceof Narkotisk){
                teller+=1;
            }
        }
        System.out.println("Totalt antall utskrevne resepter med narkotiske legemidler: "+ teller);
    }

    private void muligMisbrukNarkotisk(){
        System.out.println("\nLeger som har skrevet ut minst en resept paa narkotisk legemiddel:");
        int total = 0;
        for(Lege l : legeListe){
            int teller = 0;
            IndeksertListe<Resept> r = l.hentUtSkrevneResepter();
            for(Resept res: r){
                if(res.hentLegemiddel() instanceof Narkotisk){
                    teller +=1;
                    total+=1;
                }
            }
            if(teller == 1){
                System.out.println(l.hentNavn() + " har skrevet ut " + teller + " resept med narkotisk legemiddel");
            } else if(teller > 1){
                System.out.println(l.hentNavn() + " har skrevet ut " + teller + " resepter med narkotisk legemiddel");
            }
        }
        if(total == 0){
            System.out.println("Ingen leger har skrevet ut resepter med narkotisk legemiddel");
        }
        System.out.println("\nPasienter med minst en gyldig resept paa narkotisk legemiddel:");
        
        int totalP = 0;
        for(Pasient p : pasientListe){
            int teller = 0;
            IndeksertListe<Resept> r= p.hentResepterPasient();
            
            for(Resept res: r){
                if(res instanceof Blaa);{
                    if(res.hentLegemiddel() instanceof Narkotisk){
                        if(res.hentReit() > 0){
                            teller +=1;
                            totalP +=1;
                        }
                    }
                }
            }
            if(teller == 1){
                System.out.println(p.hentNavn() + " har " + teller +"  gyldig resept");
            } else if(teller > 1){
                System.out.println(p.hentNavn() + " har " + teller +"  gyldige resepter");
            }
        }
        if(totalP == 0){
            System.out.println(" Ingen pasienter har en gyldig resept paa et narkotisk legemiddel");
        }

    }

    private void skrivUtUnderMeny4(){
        System.out.println("\nVelg handling:");
        System.out.println("Vane: Statistikk om totalt antall resepter med vanedannende legemidler");
        System.out.println("Nark: Statistikk om totalt antall resepter med narkotiske legemidler");
        System.out.println("M: Mulig misbruk av narkotiske legemidler");
        System.out.println("9: Returner til Hovedmeny");
        System.out.println("Tast inn handling ->");
    }

    private void brukResept(Scanner sc) throws UgyldigListeindeks, NumberFormatException{
        System.out.println("Hvilken pasient vil du se resept for?");
        System.out.println(pasientListe);
        System.out.println("Velg ID \n->");
        String id = sc.nextLine();
    
        int sjekk = Integer.parseInt(id);//kaster NumberFormatException hvis det ikke er mulig å konvertere til et tall
        if(sjekk > pasientListe.stoerrelse()){//sjekker om tallet er innenfor storrelsen til pasientlista
            throw new UgyldigListeindeks(sjekk);
        }

        Pasient p = hentRiktigPasient(id,pasientListe);

        System.out.println("Hvilken resept vil du bruke");
        IndeksertListe<Resept> resepter = p.hentResepterPasient();
        if(resepter.stoerrelse() == 0){
            System.out.println("Brukeren har ingen resepter som kan brukes");
            return;
        } else{
            int teller = 0;
            for(Resept r : resepter){
                System.out.println(teller +" " + r.hentLegemiddel().hentNavn() + " Reit: " + r.hentReit());
                teller++;
            }
            int index = Integer.parseInt(sc.nextLine());//kaster NumberFormatException hvis det ikke er mulig å konvertere til et tall
            if(index >= resepter.stoerrelse() || index < 0){
                
                throw new UgyldigListeindeks(index);
            }

            Resept r = resepter.hentPaIndex(index);

            if(r.bruk()){
               System.out.println("Brukte resept paa " + r.hentLegemiddel().hentNavn() + " Antall gjenvaerende reit: "+ r.hentReit()+ "\n");
            } else{
                 System.out.println("Kunne ikke bruke resept paa "+ r.hentLegemiddel().hentNavn() +  "(ingen gjenvarende reit)\n");
            } 
        }
    }

    private void printAlleData(){
        if(pasientListe.stoerrelse() == 0 && legeListe.stoerrelse() == 0 && legemiddelListe.stoerrelse() == 0 && reseptListe.stoerrelse() == 0){
            System.out.println("Pasientlisten er tom");
            System.out.println("Legelisten er tom");
            System.out.println("Legemiddellisten er tom");
            System.out.println("Reseptlisten er tom\n");
        } else{
        System.out.println("\nListe med Pasienter: \n" + pasientListe);
        System.out.println("\nListe med Leger: \n" + legeListe);
        System.out.println("\nListe med Legemidler: \n" + legemiddelListe);
        System.out.println("\nListe med Resepter: \n" + reseptListe);
        }
        
    }

    private void skrivUtUnderMeny2(){
        System.out.println("\n Velg handling:");
        System.out.println("p: Legg til ny Pasient");
        System.out.println("lm: Legg til nytt Legemiddel");
        System.out.println("l: Legg til ny Lege");
        System.out.println("r: Legg til ny Resept");//velg lege, velg legemiddel, og velg pasient er kanskje smart her?
        System.out.println("9: Returner til Hovedmeny" );
        System.out.println("Tast inn handling ->");
    }

    private void skrivUtHovedMeny(){
        System.out.println("Velg handling:");
        System.out.println("0: Avslutt");
        System.out.println("1: Print alle data i systemet");
        System.out.println("2: Lag og legg til nye elementer");
        System.out.println("3: Bruk en resept");
        System.out.println("4: Statistikk");
        System.out.println("5: Skriv til fil");
        System.out.println("6: Les fra fil");
        System.out.println("Tast inn handling ->");

    }

    private void skrivUtUndermeny21(){
        System.out.println("\nVelg handling:");
        System.out.println(("V: Lag et Vanlig legemiddel"));
        System.out.println(("VD: Lag et Vanedannende legemiddel"));
        System.out.println(("N: Lag et Narkotisk legemiddel"));
        System.out.println("9: Returner til Undermeny");
        System.out.println("Tast inn handling ->");
    }

    private void skrivUtReseptTyper(){
        System.out.println("\nVelg handling:");
        System.out.println("Hvit: Lag en Hvit resept");
        System.out.println("Blaa: Lag en Blaa resept");
        System.out.println("P: Lag en P-resept");
        System.out.println("Mil: Lag en Militaerresept");
        System.out.println("9: Returner til Undermeny");
        System.out.println("Tast inn handling ->");
    }

    private void lagNyPasient(String navn, String fdslNummer){
        Pasient p = new Pasient(navn, fdslNummer);
        pasientListe.leggTil(p);
        System.out.println("Ny pasient opprettet: " + p);
    }

    private void lagVanlig(String navn,int pris, float virkeStoffmg){
        Vanlig v = new Vanlig(navn, pris, virkeStoffmg);
        legemiddelListe.leggTil(v);
        System.out.println("Nytt legemiddel opprettet: " + v);
    }

    private void lagVanedannende(String navn, int pris, float virkeStoffmg, int vaneDannendeStyrke){
        Vanedannende vd = new Vanedannende(navn, pris, virkeStoffmg, vaneDannendeStyrke);
        legemiddelListe.leggTil(vd);
        System.out.println("Nytt legemiddel opprettet: " + vd);
    }

    private void lagNarkotisk(String navn, int pris, float virkeStoffmg, int styrke){
        Narkotisk n = new Narkotisk(navn, pris, virkeStoffmg, styrke);
        legemiddelListe.leggTil(n);
        System.out.println("Nytt legemiddel opprettet: " + n);
    }





}


