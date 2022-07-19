public class Stabel<T> extends LenkeListe<T> {
    @Override
    public void leggTil(T x) {
        Node ny = new Node(x);
        ny.neste = start;
        start = ny; //kunne også brukt leggTilIndex(0,x)
      
    }
    
}
