
public class FletteTrad implements Runnable {
    private Monitor2 m1;

    public FletteTrad(Monitor2 m1){
        this.m1 = m1;
    }
    @Override
    public void run() {
        m1.flett();
    }
}
