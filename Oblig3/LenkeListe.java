public abstract class LenkeListe<T> implements Liste<T> {
    protected Node start = null;

    protected class Node{
        Node neste = null;
        T data;
        Node(T x){
            data = x;
        }
    }

    @Override
    public int stoerrelse() {
        int teller = 0;
        Node tmp = start;
        while(tmp != null){
            teller++;
            tmp = tmp.neste;
        }
        return teller;
    }

    @Override
    public void leggTil(T x) {
        Node ny = new Node(x);

        if(start == null){
            start = ny;
            return;
        }

        Node tmp = start;
        while(tmp.neste != null){
            tmp = tmp.neste;
        }
        tmp.neste = ny;
    }

    @Override
    public T hent() {
        return start.data;
    }

    @Override
    public T fjern() throws UgyldigListeindeks{
        if(stoerrelse() == 0){
            throw new UgyldigListeindeks(0);
        }
        T data = start.data;
        Node tmp = start.neste;
        start = tmp;
        return data;
    }
    @Override
    public String toString() {
        String linje = "";
        Node tmp = start;
        while(tmp.neste != null){
            linje += "\n" + tmp.data;
            tmp = tmp.neste;
        }
        linje += "\n" +tmp.data;
        return linje;
        
    }
    
    protected T hentPaIndex(int index) throws UgyldigListeindeks {
        if(stoerrelse() == 0){
            throw new UgyldigListeindeks(0);
        }
        else if(index == 0){
        return start.data;
        }
        Node tmp = start;
        for(int i = 0; i< index; i++){
            tmp = tmp.neste;
        }
        return tmp.data;

    }
    protected void leggTilIndex (int pos, T x)throws UgyldigListeindeks{
        Node ny = new Node(x);
        if(stoerrelse() == 0){ 
            start = ny;
           
        }else if(pos == 0){
            ny.neste = start;
            start = ny;
            
        }else if((pos > 0) && (pos <= stoerrelse())){
            Node forrige = start;
            Node etter = start.neste;
            Node tmp = start;
            
            for(int i = 1; i < pos;i++){
                forrige = tmp.neste;
                tmp = forrige;
            }
    
            etter = forrige.neste;
            forrige.neste = ny;
            ny.neste = etter;
            
        }
        else{
            throw new UgyldigListeindeks(pos);
    
        }
   
    
    
}
}
