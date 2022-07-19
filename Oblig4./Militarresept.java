public class Militarresept extends Hvit {   
    //husk at denne skal ha 100% rabatt
    
    
    public Militarresept(Legemiddel legemiddel,Lege lege, Pasient pasient) {
        super(legemiddel, lege, pasient,3);
        
        
    }

    @Override
    public String type() {
        return "militaer";
        
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
