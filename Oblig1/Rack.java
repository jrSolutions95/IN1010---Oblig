public class Rack{
    private Node[] rack;
    private static int maxAntallNoder = 12;


public Rack() {
    rack  = new Node[maxAntallNoder];
}

public boolean erFullt(){
    if(rack.length == antallNoderIRack()){
        return true;
    } return false;

}
public void leggTilNoder(Node n){
    this.rack[antallNoderIRack()] = n;
}
public int antallNoderIRack(){
   int teller = 0;
   while(rack[teller] instanceof Node){
       if(teller +1 == maxAntallNoder){
           return teller+1;
       }
       teller += 1;
   }
   return teller;

}
public int antProsIRack() {
    int ant = 0;
    for(Node n : rack){
        if(n == null){
            return ant;
        }
        ant += n.getProsessorer();
    }
    return ant;

}

public int noderMedNokMinneIRack(int paakrevdMinne){
    int antallNoderMedNokMinne = 0;
    for(int i = 0; i< antallNoderIRack(); i++){
        if(rack[i].getMinne() >= paakrevdMinne){
            antallNoderMedNokMinne +=1;
        }
    }
    return  antallNoderMedNokMinne;
}
}
