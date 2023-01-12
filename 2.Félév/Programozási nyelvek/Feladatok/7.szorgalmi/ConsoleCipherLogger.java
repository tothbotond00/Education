import java.util.Random;

class ConsoleCipherLogger extends Logger {
  private final String ABC ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  @Override
  void log (String str){
    Random random = new Random();
    int rand = random.nextInt(25)+1;
    ///Ez itt csupán az átláthatóság miatt van 
    System.out.println(str);
    System.out.println("Ennyi betuvel van eltolva: "+ rand);
    ///Ennyi
    StringBuilder sb = new StringBuilder(); 
    for (int i = 0; i < str.length(); i++){
      int num = ABC.indexOf(str.charAt(i))+rand;
      if ( num > 25) num = num-26;
      sb.append(ABC.charAt(num));
    }
    System.out.println(sb.toString());
  }
}