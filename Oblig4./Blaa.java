
public class Blaa extends Resept {
    
    public Blaa(Legemiddel legemiddel, Lege lege, Pasient pasient, int reit) {
        super(legemiddel, lege, pasient,reit);
    }

    
    @Override
    public String type(){
        return "blaa";
    }
    @Override
    public String farge(){
        return "Blaa";
    }
    @Override
    public int prisAaBetale(){
        int pris = (int) Math.round(this.legemiddel.hentPris()*0.25);
        return pris;
    }
    @Override
    public String toString() {
        String linje = super.toString();
        linje += "\n Pris som pasienten maa betale: " + prisAaBetale();
        linje += "\n Rabattordning: Blaa - 75% rabatt (allerede lagt til)";
        return linje;

    
        
    }
    
}
