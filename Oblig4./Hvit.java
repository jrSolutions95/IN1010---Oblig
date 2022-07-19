public class Hvit extends Resept {

    public Hvit(Legemiddel legemiddel, Lege lege, Pasient pasient,int reit) {
        super(legemiddel, lege, pasient,reit);
        
        
    }

    @Override
    public String type(){
        return "hvit";
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
