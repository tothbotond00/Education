/**
 * Kísérletezz szálakkal.

    Nevezd el a szálakat (setName).
    Készíts egy ThreadGroup példányt, és add azt át a szálak első paraméterének.
    Az osztály activeCount() és list() metódusait használva figyeld meg a szálak futását.
    ➿ Opcionális: IDE segítségével szüneteltesd a szálak futását debug módban (suspend).
    Vizsgáld meg a változóik tartalmát a Debugger segítségével.
    Írd át a változók értékét, és folytasd a futtatást.
 */

package feladat_2;

public class Feladat_2 {
    public static void main(String[] args){
        ThreadGroup tg = new ThreadGroup("MyThreads");
        Thread t = new MyThread(tg,"T1");
        Thread t2 = new Thread(tg, "T2");
        t.setName("MyThread");
        
        t.start();
        t2.start();
        tg.activeCount();
        tg.list();
    }
}

class MyThread extends Thread{
    public MyThread(ThreadGroup tg, String str){
        super(tg,str);
    }
    
    @Override
    public void run(){
        System.out.print("Hello");
    }
}
