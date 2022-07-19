public class Prioritetskoe<T extends Comparable<T>> extends LenkeListe<T>{
    @Override
    public void leggTil(T x) {
        if(stoerrelse() == 0){
            leggTilIndex(0, x);// her kunne vi også brukt leggTil(x), men da måtte vi ha brukt super.leggTil(x)
            return;
        } else if(stoerrelse() > 0){
            for(int i =0; i < stoerrelse();i++){
                if(hentPaIndex(i).compareTo(x) > 0){
                    leggTilIndex(i, x);
                    return;
                }
            }
        }
        leggTilIndex(stoerrelse(),x);// her kunne vi også brukt leggTil(x), men da måtte vi ha brukt super.leggTil(x)
    }

   
}
