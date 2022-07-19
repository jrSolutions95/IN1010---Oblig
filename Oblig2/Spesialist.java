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
        linje += " - Spesialist \nKontrollID: " + hentKontrollID();
        return linje;
    }
}
