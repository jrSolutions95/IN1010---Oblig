
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class LeseTrad implements Runnable {
    private Monitor2 m1;
    private String filnavn;
    private CountDownLatch cdl;


    public LeseTrad(Monitor2 m1, String filnavn,CountDownLatch cdl){
        this.m1 = m1;
        this.filnavn = filnavn;
        this.cdl = cdl;

    }
    @Override
    public void run() {
        System.out.println("Leser naa inn fil: " + filnavn);
        HashMap<String,Subsekvens> hm = m1.filLeserMonitor(filnavn);
        m1.settInn(hm);
        cdl.countDown();
    }
    
}
