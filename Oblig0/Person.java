public class Person{
  Bil3 bil;

  public Person(Bil3 b){
    bil = b;
  }

  public void skrivUtBilNummer(){
    System.out.println(bil.hentNummer());
  }
}
