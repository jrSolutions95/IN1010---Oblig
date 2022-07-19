public class Subsekvens{
    public final String sekvens;
    private int antForekomster;

    public Subsekvens(String sub, int antall){
        sekvens = sub;
        antForekomster = antall;
    }

    public int hentAntall(){
        return antForekomster;
    }

    public void oekAntallForekomster(int t){
        antForekomster +=t;
    }

    public String toString(){
        String linje = "("+ sekvens + ","+ antForekomster+ ")";
        return linje;
    }
}


