public class Lege {
    protected String navn;

    public Lege(String navn) {
        this.navn = navn;
        
    }

    public String hentNavn(){
        return navn;
    }

    public String toString(){
        return this.navn;
    }
    
}
