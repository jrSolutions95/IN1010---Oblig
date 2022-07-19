
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.*;

public class Monitor2 {
    private SubsekvensRegister sr;
    private Lock laas;
    private Condition ikkeTomt; 
   

    public Monitor2(SubsekvensRegister sr){
        this.sr = sr;
        laas = new ReentrantLock();
        ikkeTomt = laas.newCondition(); 
    }

    public void settInn(HashMap<String,Subsekvens> hm){
        laas.lock();
        try {
            sr.settInnHashMap(hm);
            //ikkeTomt.signalAll();fikk ikke conditons til å fungere
        } finally{
            laas.unlock();
        }
    }

    public HashMap<String,Subsekvens> taUtHashMap() throws InterruptedException {
        laas.lock();
        try {
            /*fikk ikke conditons til å fungere
            while(antallHashMap() == 0){
                System.out.println(Thread.currentThread().getName() + " : SubsekvensRegistere er tomt for hashmaps, venter");
                ikkeTomt.await();
            }*/
            return sr.taUtHashMap();

            }
            finally{
            laas.unlock();
        }
        
    }

    public void flett(){
        try {
            while(antallHashMap() > 1){
                HashMap<String,Subsekvens> hm1 = taUtHashMap();
                HashMap<String,Subsekvens> hm2 = taUtHashMap();
                 if(hm1 == null && hm2 != null){
                     settInn(hm2);
                 }
                if(hm2 == null && hm1 != null){
                    settInn(hm1);
                } else{
                    HashMap<String,Subsekvens> hm = SubsekvensRegister.flettHashMap(hm1,hm2);
                    settInn(hm);
                }
            }

        } catch (InterruptedException e) {
            System.out.println("Det oppstod en feil under fletting" + e);
        }
    }
  
    public int antallHashMap(){
        laas.lock();
       try {
           return sr.antallHashMap();
       } finally {
           laas.unlock();
       }
       
    }

    public HashMap<String,Subsekvens> filLeserMonitor(String filnavn){
        HashMap<String,Subsekvens> hm = SubsekvensRegister.filLeser(filnavn);
        return hm;
    }

    public HashMap<String,Subsekvens> flettHashMap(HashMap<String,Subsekvens> hm1, HashMap<String,Subsekvens> hm2){
        return flettHashMap(hm1, hm2);
    }
}
