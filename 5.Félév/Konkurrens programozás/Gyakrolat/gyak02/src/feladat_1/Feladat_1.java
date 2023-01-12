/**
 * Figyeld meg, hogy a kimenetek összefésülődnek.
    Írj olyan megoldást is, ahol karakterről karakterre írod ki a szövegeket, a System.out objektum print() műveletét használva, nem a println() segítségével.
    Próbáld ki, mi történik, ha szálindítás helyett a fenti két kiírást nem a start(), hanem a run() metódus meghívásával futtatod.
    Készíts olyan megoldást is, ahol a sztenderd kimenet helyett egy fájlba írod a kimenetet PrintWriter használatával.
 */

package feladat_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Feladat_1 {

    public static void main(String[] args) {
        (new HelloThread()).start();
        (new WorldThread()).start();
        (new OtherThread()).start();
        List<String> textArray = new ArrayList<>();
        textArray.add("hello");
        textArray.add("Boti");
        textArray.add("example");
        for(String text : textArray){
            (new Thread(){
                
                private String message;
                
                @Override
                public void run(){
                    this.message = text;
                    File file = new File("filename.txt");
                    PrintWriter pr = null;
                    try{
                        pr = new PrintWriter(file);
                    }catch(FileNotFoundException ex){
                        System.exit(1);
                    }
                    for (int i = 0; i < 100000; i++) {
                        pr.write(message + i + "\n");
                    }
                    pr.close();
                }
            }).start();
        }
    }
    
}

class HelloThread extends Thread{
    @Override 
    public void run(){
        for (int i = 0; i < 100000; i++) {
            System.out.println("hello " + i);
        }
    }
}

class WorldThread extends Thread {
    @Override 
    public void run(){
        for (int i = 0; i < 100000; i++) {
            System.out.println("world " + i);
        }
    }
}

class OtherThread extends Thread {
    @Override public void run(){
        for (int i = 0; i < 100000; i++) {
            System.out.println("other " + i);
        }
    }
}
