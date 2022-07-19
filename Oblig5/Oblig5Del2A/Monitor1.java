import java.util.HashMap;
import java.util.concurrent.locks.*;


public class Monitor1 {
    private SubsekvensRegister sr;
    private Lock laas;

    public Monitor1(SubsekvensRegister sr){
        this.sr = sr;
        laas = new ReentrantLock();
    }

    public void settInn(HashMap<String,Subsekvens> hm){
        laas.lock();
        try {
            sr.settInnHashMap(hm);
        } finally{
            laas.unlock();
        }
    }

    public HashMap<String,Subsekvens> taUtHashMap(){
        laas.lock();
        try {
            return sr.taUtHashMap();
        } finally{
            laas.unlock();
        }

    }

    public int antallHashMap(){
        return sr.antallHashMap();
    }

    public HashMap<String,Subsekvens> filLeserMonitor(String filnavn){
        HashMap<String,Subsekvens> hm = SubsekvensRegister.filLeser(filnavn);
        return hm;
    }

    public HashMap<String,Subsekvens> flettHashMap(HashMap<String,Subsekvens> hm1, HashMap<String,Subsekvens> hm2){
        return flettHashMap(hm1, hm2);
    }
}
