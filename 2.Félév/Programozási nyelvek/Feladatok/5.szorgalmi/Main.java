import auto.Auto;
import auto.utils.Color;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

class Main {
  public static void main(String[] args) {
    System.out.println("5.gyakorlo feladat");
    File file = new File("input.txt");

    try (Scanner sc = new Scanner(file)){
      int count = 0;
      while (sc.hasNextLine()) {
      count++;
      sc.nextLine();
      }
      Scanner sc2 = new Scanner(file);
      Auto[] auto = new Auto[count];
      int db = 0;
      while(sc2.hasNextLine()){
        String line = sc2.nextLine();
        String [] lines = line.split(",");
        //sor be van-e olvasva
        System.out.println(line);
        if (lines[1]== "RED"){
          auto[db] = new Auto(lines[0],Color.RED,Integer.parseInt( lines[2] ));
        }
        else if(lines[1]=="GREEN"){
          auto[db] = new Auto(lines[0],Color.GREEN,Integer.parseInt( lines[2] ));
        }
        else {
          auto[db] = new Auto(lines[0],Color.BLUE,Integer.parseInt( lines[2] ));
        }
        db++;
      }
      //hany sor volt
      System.out.println(count);
    }
    catch (FileNotFoundException e){
      System.out.println("Please provide a filename");
    }
  }
}