public abstract class Resept {
    protected static int id = 1;
    protected Legemiddel legemiddel;
    protected Lege lege;
    protected Pasient pasient;
    protected int reit;
    protected int reseptID;

    public Resept(Legemiddel legemiddel, Lege lege,Pasient pasient,int reit) {
        this.legemiddel = legemiddel;
        this.lege = lege;
        this.pasient = pasient;
        this.reseptID = id;
        this.reit = reit;
        id+=1;
    }

    public int hentID(){
        return reseptID;

    }

    public Legemiddel hentLegemiddel(){
        return legemiddel;
    }

    public Lege hentLege(){
        return lege;
    }

    public String hentPasientID() {
        return pasient.pasientId();
    }

    public int hentReit(){
        return reit;
    }
    
    public boolean bruk(){
        if(this.hentReit()== 0 ){
            return false;
        } else{
            this.reit -= 1;
            return true;
        }

    }

    public abstract String farge();
    public abstract int prisAaBetale();
    public abstract String type();
    
        
        
    @Override
    public String toString(){
        String linje = "\n ----- Resept ---- ";
        linje += "\n Farge: " + farge();
        linje += "\n PasientID: " + hentPasientID();
        linje += "\n Reit: " + hentReit();
        linje += "\n Lege: " + hentLege() +"\n";
        linje += "\n Legemiddel: "+ hentLegemiddel();
        return linje;
    }

        
    



    

}
