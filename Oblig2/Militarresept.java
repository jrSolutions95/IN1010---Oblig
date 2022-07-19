public class Militarresept extends Hvit {   
    //husk at denne skal ha 100% rabatt
    
    
    public Militarresept(Legemiddel legemiddel,Lege lege, int pasientID) {
        super(legemiddel, lege, pasientID,3);
        
        
    }
    @Override
    public int prisAaBetale() {
        return 0;

        
    }

    @Override
    public String toString() {
        String linje = super.toString();
        linje += "\n Pris som pasient maa betale: "+ prisAaBetale();
        linje+= "\n Rabattordning: Militarresept - 100% rabatt (allerede lagt til)";
        return linje;
        
    }
    
}
