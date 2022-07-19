import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.DimensionUIResource;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI {
    private JFrame vindu;//husk static
    private JPanel panel;
    private JPanel brett;
    private JLabel lengde;
    public JLabel[][] ruter;
    private Controller kontroller;
    public String[][] stringBrett;
    


    public GUI(Controller kontroller){
        this.kontroller = kontroller;
        stringBrett = new String[12][12];
        ruter = new JLabel[12][12];

        vindu = new JFrame("Slangespillet");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.add(lengdeogavslutt(), BorderLayout.NORTH);
        
        brett = lagBrett();
        vindu.addKeyListener(new TrykkBehandler());
        vindu.add(panel); 
        vindu.add(brett,BorderLayout.SOUTH);   
        
        vindu.setFocusable(true);
        vindu.requestFocusInWindow();
        vindu.pack();
        vindu.setVisible(true);
        

    }

    class TrykkBehandler implements KeyListener{
        @Override
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();

            if(key == KeyEvent.VK_LEFT){
                kontroller.settRetning(Retning.VEST);
            }
            if(key == KeyEvent.VK_UP){
                kontroller.settRetning(Retning.NORD);
            }
            if(key == KeyEvent.VK_DOWN){
                kontroller.settRetning(Retning.SOR);
            }
            if(key == KeyEvent.VK_RIGHT){
                kontroller.settRetning(Retning.OST);
            }
            
        }



        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    private JPanel lengdeogavslutt(){
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());

        lengde = new JLabel("Lengde : 0");
        p.add(lengde,BorderLayout.LINE_START);

        JButton slutt = new JButton("Slutt");
        slutt.addActionListener(new SluttBehandler());
        p.add(slutt,BorderLayout.LINE_END); 

        return p;
    }

    static int trekk (int a, int b) {
        // Trekk et tilfeldig heltall i intervallet [a..b];
        return (int)(Math.random()*(b-a+1))+a;
    }

    private JPanel lagBrett(){

        JPanel brett = new JPanel();
        brett.setLayout(new GridLayout(12,12));
    
        for (int i= 0; i < 12 ; i++) {      
            for(int j = 0; j< 12; j++){
                int tmpL = trekk(0, 11); 
                int tmpB = trekk(0, 11);
                JLabel rute;
                    if(i == tmpL || j == tmpB){
                        rute = new JLabel("$",SwingConstants.CENTER);
                        rute.setForeground(Color.RED);
                        stringBrett[tmpL][tmpB] = "$";
                       
                    } else{
                        rute = new JLabel("",SwingConstants.CENTER);
                        stringBrett[i][j] = "";
                    } 
                    rute.setOpaque(true);
                    rute.setPreferredSize(new DimensionUIResource(40, 40));
                    rute.setBorder(BorderFactory.createLineBorder(Color.black));
                    ruter[i][j] = rute;
                    brett.add(rute); 
                 
            }   
        }
        return brett;
    }

    public void tegnSlangeHodet(int posL, int posB){
        JLabel rute = ruter[posL][posB];
        rute.setText("O");
        rute.setBackground(Color.BLUE);
    }

    public void tegnKropp(int posL,int posB) {
        JLabel rute = ruter[posL][posB];
        rute.setText("+");
        rute.setBackground(Color.GREEN);
    }

    public void tegnHvit(int posL, int posB){
        JLabel rute = ruter[posL][posB];
        rute.setText("");
        rute.setBackground(Color.WHITE);
    }

    public void settLengde(int l){
        lengde.setText("Lengde: " +l);
    }

    public void taper(){//få denne meldingen til å vise seg lengre
        System.out.println("Du tapte");//brukt som sjekk
        JOptionPane.showMessageDialog(vindu, "Oops du tapte!", "Tap", JOptionPane.CLOSED_OPTION);
    }

    public void seier(){
        System.out.println("Du vant");//brukt som sjekk
        JOptionPane.showMessageDialog(vindu, "Gratulerer du vant!", "Seier", JOptionPane.CLOSED_OPTION);
    }
       

    static class SluttBehandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);;
            
        }
    }
}
