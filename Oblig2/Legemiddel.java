public abstract class Legemiddel{
    protected static int id = 1;
    protected int current;
    protected String navn;
    protected int pris;
    protected float virkeStoffmg;

    public Legemiddel(String navn, int pris, float virkeStoffmg) {
        this.navn = navn;
        this.pris = pris;
        this.virkeStoffmg = virkeStoffmg;
        this.current = id;
        id+=1;
    }

    public int hentID(){
        return current;        
    }

    public String hentNavn() {
        return navn;
    }

    public int hentPris(){
        return pris;
    }

    public float hentVirkestoff() {
        return virkeStoffmg;
    }

    public void settNyPris(int nyPris){
        this.pris = nyPris;
    }

    @Override
    public String toString(){
        String string = "\n         Navn: "+ navn;
        string += "\n         Pris: " + pris;
        string += "\n         mg: " + virkeStoffmg;
        string += "\n         ID: "+ current; 
        return string;
    }


}