public abstract class Rute {

    protected int rad;
    protected int kolonne;
    protected Rute nord;
    protected Rute syd;
    protected Rute vest;
    protected Rute oest;

    public Rute(int rad, int kolonne){
        this.rad = rad;
        this.kolonne = kolonne;
    }

    public void settInnNaboer(Rute nord,Rute syd, Rute vest, Rute oest){
        this.nord = nord;
        this.syd = syd;
        this.vest = vest;
        this.oest = oest;
    }

    public abstract void finn(Rute fra);


    

    
}
