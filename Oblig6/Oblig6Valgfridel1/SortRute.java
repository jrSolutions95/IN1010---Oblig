import java.util.ArrayList;

public class SortRute extends Rute {
    public SortRute(int rad, int kolonne){
        super(rad,kolonne);
    }

    @Override
    public String toString(){
        return "#";
    }

    @Override
    public void finn(Rute fra) {//her returnerer vi bare da det ikke finnes en utvei denne veien
        return;
    }
}
