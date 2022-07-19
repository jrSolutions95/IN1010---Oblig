public class Aapning extends HvitRute{

    public Aapning(int rad, int kolonne){
        super(rad,kolonne);
    }

    @Override
    public void finn(Rute fra){
        System.out.println("("+ rad + "," +  kolonne+")");//med en gang vi har kalt finn i Aapningklassen vet vi at vi har funnet en vei ut(skriver ut hvor vi fant utveien) og bare returnerer
        return;
    }
    
}
