import java.util.ArrayList;

public class HvitRute extends Rute{
    //protected int tmp;

    public HvitRute(int rad, int kolonne, Labyrint l){
        super(rad, kolonne, l);
    }

    @Override
    public void finn(Rute fra,ArrayList<Tuppel> veien){//her kjorer vi de rekursive kallene og sorger for at vi ikke gaar tilbake samme vei som vi kom fra
        current = nr;
        Rute.nr++;

        if (this.besoekt) {
            return;
        }

        this.besoekt = true;
        ArrayList<Tuppel> nySti = new ArrayList<>(veien);
        nySti.add(new Tuppel(rad, kolonne));

        if(this.nord != fra){
            nord.finn(this, nySti);
        }
        if(this.syd != fra){
            syd.finn(this, nySti);
        }
        if(this.vest != fra){
            vest.finn(this, nySti);
        }
        if(this.oest != fra){
            oest.finn(this, nySti);
        }
        this.besoekt = false;

    }

    @Override
    public String toString(){
        return String.valueOf(current);
    }

}
