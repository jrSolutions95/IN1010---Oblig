import java.util.ArrayList;

public class Dataklynge{
    private ArrayList<Rack> racks = new ArrayList<>();

public Dataklynge() {
}

public void leggTilNode(Node n) {

    if(racks.size() == 0){
        Rack r = new Rack();
        racks.add(r);
        r.leggTilNoder(n);

    } else if (racks.get(racks.size()-1).erFullt()){
            Rack r1 = new Rack();
            r1.leggTilNoder(n);
            racks.add(r1);
        }
     else {
        racks.get(racks.size()-1).leggTilNoder(n);
    }
}

public int antallNoder(){
    int antallNoder = 0;
    for(Rack r: racks){
        antallNoder += r.antallNoderIRack();
    }
    return antallNoder;
}


public int antProssesorer(){
    int antPros = 0;
    for(Rack r: racks){
        antPros += r.antProsIRack();
    }
    return antPros;

}
public int antallRacks(){
    return racks.size();
}

public int noderMedNokMinne(int paakrevdMinne) {
    int antallNoderMedNokMinne = 0;
    for(Rack r: racks){
        antallNoderMedNokMinne += r.noderMedNokMinneIRack(paakrevdMinne);
    }
    return antallNoderMedNokMinne;

}

}
