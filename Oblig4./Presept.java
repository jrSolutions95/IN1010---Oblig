public class Presept extends Hvit {
    protected static int rabatt = 108;

    public Presept(Legemiddel legemiddel, Lege lege, Pasient pasient,int reit){
        super(legemiddel, lege, pasient,reit);
       

    }


    @Override
    public String type() {
        return "p";
        
    }
   public int prisAaBetale() {
    if(this.legemiddel.hentPris()>= rabatt){
        return this.legemiddel.hentPris()-rabatt;
    } else{
       return 0;

    }
       
   }

    @Override
    public String toString() {
        String linje = super.toString();
        linje += "\n Pris som pasienten maa betale: "+ prisAaBetale();
        linje += "\n Rabattordning: Presept - 108kr rabatt (allerde lagt til)";
        return linje;
    }
    
}
