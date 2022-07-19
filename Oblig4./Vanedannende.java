public class Vanedannende extends Legemiddel{
    protected int vaneDannendeStyrke;
    
    public Vanedannende(String navn, int pris, float virkeStoffmg,int vaneDannendeStyrke){
        super(navn, pris, virkeStoffmg);
        this.vaneDannendeStyrke = vaneDannendeStyrke;
    }

    public int hentVanedannendeStyrke(){
        return vaneDannendeStyrke;
    }

    @Override
    public String type(){
        return "vanedannende";

    }

    @Override
    public String toString(){
        String string = super.toString();
        string += "\n         Vanedannende Styrke: "+ vaneDannendeStyrke;
        return string;
    }
}
