public class Tuppel {
    private int rad;
    private int kolonne;

    public Tuppel(int rad,int kolonne){
        this.rad = rad;
        this.kolonne = kolonne;
    }

    public int hentRad(){
        return rad;
    }

    public int hentKolonne(){
        return kolonne;
    }

    @Override
    public String toString(){
        return "{"+rad+","+kolonne+"}";
    }
    
}
