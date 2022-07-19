public class Vanlig extends Legemiddel {

    public Vanlig(String navn, int pris,float virkeStoffmg) {
        super(navn, pris, virkeStoffmg);
        
    }
    @Override
    public String type(){
        return "vanlig";

    }
    
}
