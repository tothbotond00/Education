/**
 * Join, sleep, interrupt

    Alakítsd át a Készíts két szálat… feladatot úgy, hogy miután mindkét szál végzett, írja ki a kész szót.
    Hogy gyorsabban végezzen a program, írd át 1000-re a terminálási feltételben szereplő konstanst.
    Használd a Thread osztály join metódusát egy szál bevárásához.
    Ne felejtsd el elkapni az InterruptedException kivételt.
    Várakozás szálon belül
    Alakítsd át az előző programot úgy, hogy minden két kiírás között 5 ezredmásodpercet várakozzon. Ehhez használd a Thread osztály sleep statikus metódusát.
    Ne felejtsd el elkapni az InterruptedException kivételt.
    Megjegyzés: a Javában van egy wait() nevű, szintén közismert metódus, de annak teljesen más a szerepe.
    Szálak futásának megszakítása
    Szakítsd meg az előző programban a szálakat egy másodperc futás után. Ehhez használd a Thread osztály interrupt.
    Megszakított szál újraindítása?
    Próbáld meg újraindítani a megszakított szálakat. Mi történik?
 */
package feladat_2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Feladat_2 {
    public static void main(String[] args) throws InterruptedException {
        Thread hello = new PrintThread("Hello");
        Thread world = new PrintThread("world");
        hello.start();
        world.start();
        try{
            Thread.sleep(1000);
            hello.interrupt();
            world.interrupt();
            hello.join();
            world.join();
            System.out.println("Kész!");
        }catch(InterruptedException e){
            System.out.println("Main InterruptedException");
        }
    }
}

class PrintThread extends Thread {
    public String text;
    
    PrintThread(String text){
        super();
        this.text = text;
    }
    
    @Override public void run(){
        for (int i = 0; i < 1000000; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(PrintThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(text + i);
        }
    }
}
