import java.util.ArrayList;

public class HvitRute extends Rute{
    //protected int tmp;

    public HvitRute(int rad, int kolonne){
        super(rad, kolonne);
    }

    @Override
    public void finn(Rute fra){//her kjorer vi de rekursive kallene og sorger for at vi ikke gaar tilbake samme vei som vi kom fra
        current = nr;
        Rute.nr++;

        if(this.nord != fra){
            nord.finn(this);
        }
        if(this.syd != fra){
            syd.finn(this);
        }
        if(this.vest != fra){
            vest.finn(this);
        }
        if(this.oest != fra){
            oest.finn(this);
        }
    }

    @Override
    public String toString(){
        return String.valueOf(current);
    }
    
}
