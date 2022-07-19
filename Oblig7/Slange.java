
import java.util.ArrayList;
import java.util.Stack;
import java.util.*;

public class Slange {
    private GUI gui;
    private Controller controller;
    private int slangePosLengde,slangePosBredde;
    private int lengde = 12;
    private int bredde = 12;
    private int slangeLengde = 0;
    Stack<int[]> stack;
    ArrayList<int[]> heleVeien;
    ArrayList<int[]> lista; 

    public Slange(GUI g, Controller c) {
        this.gui = g;
        this.controller = c;
        lista =  new ArrayList<>();
        heleVeien = new ArrayList<>();
        randomStart();
        
    }

    public int hentSlangeLengde(){
        return slangeLengde;
    }

    public void flytt(Retning retning){
        int tmpSlangePosLengde = slangePosLengde;
        int tmpSlangePosBredde = slangePosBredde;

        if(Retning.NORD == retning){
            tmpSlangePosLengde--;
        }
        if(Retning.SOR == retning){
            tmpSlangePosLengde++;
        }
        if(Retning.OST == retning){
            tmpSlangePosBredde++;
        }
        if(Retning.VEST == retning){
            tmpSlangePosBredde--;
        }

        if(gyldigPosisjon(tmpSlangePosLengde, tmpSlangePosBredde)){
            besoktRute(tmpSlangePosLengde, tmpSlangePosBredde);
            if(sjekkSeier()){
                controller.seier();
            }
        } else{
            controller.tapt();
        }
    }

    private boolean gyldigPosisjon(int posL, int posB){
        if(posL < 0 || posL >= lengde){
            return false;
        }
        if(posB < 0 || posB >= bredde){
            return false;
        }
        for(int[] i : lista){
            if(posL == i[0] && posB ==i[1]){
                System.out.println("Krasjet i seg selv!");
                return false;
            }
        }
        return true;
    }

    private void besoktRute(int nyLengde, int nyBredde){
        int[] koordinat = new int[2];
        koordinat[0] = nyLengde;
        koordinat[1] = nyBredde;


        ArrayList<int[]> heltNY = new ArrayList<int[]>(heleVeien);
        heltNY.add(koordinat);
        heleVeien = heltNY;

        ArrayList<int[]> kopi = new ArrayList<>(heleVeien);
        ArrayList<int[]> reverse = new ArrayList<>(kopi);
        Collections.reverse(reverse);

        if(gui.ruter[nyLengde][nyBredde].getText().equals("$")){
            slangeLengde++;
            gui.settLengde(slangeLengde);
        }
        
        ArrayList<int[]> halen = new ArrayList<>();

        for(int i = 0; i < slangeLengde; i++){
            int[] data = reverse.get(i);
            halen.add(data);
            gui.tegnKropp(data[0], data[1]);
        
        }
        lista = halen;

        for(int i = 0; i < kopi.size()-slangeLengde;i++){
            int[]data = kopi.get(i);
            gui.tegnHvit(data[0], data[1]);
        }
        
        slangePosLengde = nyLengde;
        slangePosBredde = nyBredde;
        gui.tegnSlangeHodet(slangePosLengde, slangePosBredde);

      
    }

    public boolean sjekkSeier(){
        for (int i= 0; i < 12 ; i++) {      
            for(int j = 0; j< 12; j++){
                if(gui.ruter[i][j].getText().equals("$")){
                    return false;
                }
            }

        }
        return true;
    }
        

    static int trekk (int a, int b) {
        // Trekk et tilfeldig heltall i intervallet [a..b];
        return (int)(Math.random()*(b-a+1))+a;
        }

    private void randomStart(){
        slangePosLengde = trekk(0, 11);
        slangePosBredde = trekk(0,11);

        while(!(gyldigPosisjon(slangePosLengde, slangePosBredde))){
            slangePosLengde = trekk(0, 11);
            slangePosBredde = trekk(0,11);
            
        }
        besoktRute(slangePosLengde, slangePosBredde);

    }
}

