public class Pasient {
    protected static int id = 1;
    private int current;
    private IndeksertListe<Resept> resepter = new IndeksertListe<Resept>();
    private String navn;
    private String fdslNummer;

    public Pasient(String navn, String fdslNummer) {
        this.navn = navn;
        this.fdslNummer = fdslNummer;
        this.current = id;
        id+=1;
    }

    public String hentNavn(){
        return navn;
    }
    public String pasientId(){
        return Integer.toString(current);
    }

    public void leggTilNyResept(Resept r){
        resepter.leggTil(r);
    }

    public IndeksertListe<Resept> hentResepterPasient(){
        return resepter;
    }

    public String hentFdslNummer(){
        return fdslNummer;
    }


    @Override
    public String toString(){
        String linje = " Informasjon om Pasienten: \n " + navn;
        linje += "\n ID: " + current + "\n Fodselsnummer: " + fdslNummer;
        return linje;
    }


}
