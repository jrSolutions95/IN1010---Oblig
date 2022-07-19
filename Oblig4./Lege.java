public class Lege implements Comparable<Lege> {
    protected String navn;
    protected IndeksertListe<Resept> utskrevneResepter = new IndeksertListe<>();

    public Lege(String navn) {
        this.navn = navn;
        
    }

    public String hentNavn(){
        return navn;
    }

    public String toString(){
        return this.navn;
    }

    public IndeksertListe<Resept> hentUtSkrevneResepter(){
        return utskrevneResepter;
    }

    public int compareTo(Lege l) {
        if((this.hentNavn()).compareToIgnoreCase(l.hentNavn()) > 0){
            return 1;
        } else if((this.hentNavn()).compareToIgnoreCase(l.hentNavn())< 0){
            return -1;
        } else {
            return 0;
        }
    }

    public Hvit skrivHvitResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        if(legemiddel instanceof Narkotisk){
            throw new UlovligUtskrift(this,legemiddel);
        } else{
            Hvit r = new Hvit(legemiddel,this, pasient, reit);
            utskrevneResepter.leggTil(r);
            return r;
        }
    }
    public Militarresept skrivMilResept (Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift{
        if(legemiddel instanceof Narkotisk){
            throw new UlovligUtskrift(this,legemiddel);
        } else{
            Militarresept r =  new Militarresept(legemiddel, this, pasient);
            utskrevneResepter.leggTil(r);
            return r;
        }
    }
    public Presept skrivPResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        if(legemiddel instanceof Narkotisk){
            throw new UlovligUtskrift(this,legemiddel);
        } else{
            Presept r =  new Presept(legemiddel,this, pasient, reit);
            utskrevneResepter.leggTil(r);
            return r;
        }
    }
    public Blaa skrivBlaaResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        if(legemiddel instanceof Narkotisk){
            throw new UlovligUtskrift(this,legemiddel);
        } else{
            Blaa r =  new Blaa(legemiddel, this, pasient, reit);
            utskrevneResepter.leggTil(r);
            return r;
        }   
    }
    
}
