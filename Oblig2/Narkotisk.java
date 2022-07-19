public class Narkotisk extends Legemiddel {
    protected int styrke;

    public Narkotisk(String navn, int pris, float virkeStoffmg, int styrke){
        super(navn,pris,virkeStoffmg);
        this.styrke = styrke;
    }

    public int hentNarkotiskStyrke(){
        return styrke;
    }

    @Override
    public String toString(){
        String string = super.toString();
        string += "\n         Narkotisk Styrke: "+ styrke;
        return string;
    }
    
}
