 class Oppbrukt extends Exception{
     Oppbrukt(Pasient p, Resept r){
         super("Pasienten " + p.hentNavn() +" har brukt opp respeten paa: " + r.hentLegemiddel().hentNavn());
     }
    
}
