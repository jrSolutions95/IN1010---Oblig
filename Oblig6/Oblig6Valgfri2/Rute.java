import java.util.ArrayList;

public abstract class Rute {

    protected int rad;
    protected int kolonne;
    protected Rute nord;
    protected Rute syd;
    protected Rute vest;
    protected Rute oest;
    protected static int nr;
    protected int current;
    protected Labyrint l;
    protected Boolean besoekt;

    public Rute(int rad, int kolonne, Labyrint l){
        this.rad = rad;
        this.kolonne = kolonne;
        nr = 1;
        this.l = l;
        besoekt = false;

    }

    public void settInnNaboer(Rute nord,Rute syd, Rute vest, Rute oest){
        this.nord = nord;
        this.syd = syd;
        this.vest = vest;
        this.oest = oest;
    }

    public abstract void finn(Rute fra,ArrayList<Tuppel> al);


    

    
}
