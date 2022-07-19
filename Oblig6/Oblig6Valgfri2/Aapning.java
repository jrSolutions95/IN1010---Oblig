import java.util.ArrayList;

public class Aapning extends HvitRute{

    public Aapning(int rad, int kolonne, Labyrint l){
        super(rad,kolonne, l);
    }

    @Override
    public void finn(Rute fra, ArrayList<Tuppel> veien){
        current = nr;
        Rute.nr++;
        besoekt = true;

        ArrayList<Tuppel> nySti = new ArrayList<>(veien);
        nySti.add(new Tuppel(rad, kolonne));
        l.leggTilVei(nySti);
        
        //system.out.println("("+ rad + "," +  kolonne+")");//med en gang vi har kalt finn i Aapningklassen vet vi at vi har funnet en vei ut(skriver ut hvor vi fant utveien) og bare returnerer
        return;
    }
    
}
