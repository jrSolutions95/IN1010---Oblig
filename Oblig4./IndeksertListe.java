public class IndeksertListe<T> extends LenkeListe<T> {

public void leggTil (int pos, T x)throws UgyldigListeindeks{
    leggTilIndex(pos, x); 
}

public void sett (int pos, T x){
    if(pos >= stoerrelse()){
        throw new UgyldigListeindeks(pos);
    }


    Node tmp = start;
    for(int i =0; i< pos;i++){
        tmp = tmp.neste;
    }

    tmp.data = x;
    
}

public T hent(int pos){
    return hentPaIndex(pos);
}


public T fjern(int pos){
    if(pos>= stoerrelse()){
        throw new UgyldigListeindeks(pos);
    }

    Node forrige = start;
    Node tmp = start;
    
    for(int i = 0; i < pos;i++){
        forrige = tmp;
        tmp = forrige.neste;
    }

    forrige.neste = tmp.neste;
    return tmp.data;
}


}
