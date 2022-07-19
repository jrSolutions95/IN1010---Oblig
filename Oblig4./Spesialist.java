public class Spesialist extends Lege implements Godkjenningsfritak {

    public String kontrollID;
        

    public Spesialist(String navn,String kontrollID) {
        super(navn);
        this.kontrollID = kontrollID;

    }

    @Override
    public String hentKontrollID() {
        return kontrollID;
    }
    
    @Override
    public String toString(){
        String linje = super.toString();
        linje += " - Spesialist KontrollID: " + hentKontrollID();
        return linje;
    }
    @Override
    public Blaa skrivBlaaResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        Blaa r =  new Blaa(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(r);
        return r;
    }

}
