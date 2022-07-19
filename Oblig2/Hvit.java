public class Hvit extends Resept {

    public Hvit(Legemiddel legemiddel, Lege lege, int pasientID,int reit) {
        super(legemiddel, lege, pasientID,reit);
        
        
    }

    @Override
    public String farge(){
        return "Hvit";
    }

    @Override
    public int prisAaBetale() {
        return this.legemiddel.hentPris();
        
    }
    
    
}
