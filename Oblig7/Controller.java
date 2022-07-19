
public class Controller{
    private Retning retning;
    private GUI gui;
    private Thread traad;
    private Slange slange;


    public Controller(){
        retning = Retning.SOR;
        gui = new GUI(this);
      
        slange = new Slange(gui,this);
        traad = new Thread(new Teller());
        traad.start();
    }

    public void settRetning(Retning r){
        this.retning = r;
        
    }

    class Teller implements Runnable{

        @Override
        public void run() {
           while(true){
               if(slange.hentSlangeLengde() < 3){
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    return;
                }
                flytt();       
               } if(slange.hentSlangeLengde() >= 3){
                 try {
                     Thread.sleep(1000);
                } catch (Exception e) {
                        return;
                    
                }
                flytt();

                }
                if(slange.hentSlangeLengde() >= 7){
                    try {
                        Thread.sleep(500);
                } catch (Exception e) {
                        return;
                    
                }
                flytt();
        }   }
    }
        
    }

    private void flytt(){
        slange.flytt(retning);
    }

    public void tapt(){
        traad.interrupt();
        gui.taper();
        System.exit(0);;
    }

    public void seier(){
        traad.interrupt();
        gui.seier();
        System.exit(0);;
    }

    
}