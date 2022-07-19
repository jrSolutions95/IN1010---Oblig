import java.util.ArrayList;

public class SortRute extends Rute {
    public SortRute(int rad, int kolonne, Labyrint l){
        super(rad,kolonne, l);
    }

    @Override
    public String toString(){
        return "#";
    }

    @Override
    public void finn(Rute fra,ArrayList<Tuppel> veien) {//her returnerer vi bare da det ikke finnes en utvei denne veien
        besoekt = true;
        return;
    }
}
