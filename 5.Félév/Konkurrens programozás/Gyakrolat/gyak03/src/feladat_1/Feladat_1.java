/**
 * Oldd meg a feladatot úgy is, hogy a Thread objektumokat különféle módokon készíted el. Gondold meg mindegyik esetben, hogy le lehet-e úgy írni a kódot, hogy a két szál tevékenységének közös része (ki kell írni valamit) nem ismétlődik a kódban, hanem csak egyszer jelenik meg, és a szálak ezt paraméterezik azzal, hogy pontosan milyen szöveget kell kiírni.
    Thread leszármazott osztállyal
    Runnable interfészt megvalósító osztállyal
    Thread-ből származó névtelen osztállyal
    Runnable-ből származó névtelen osztállyal
    Thread konstruktorának lambda átadásával
 */
package feladat_1;

public class Feladat_1 {
    public static void main(String[] args) {
        //1
        (new ExtendThread()).start();
        //2
        (new Thread(new ExtendRunnable())).start();
        //3
        (new Thread() {
                @Override public void run() {
                for (int i = 0; i < 100000; i++) {
                    System.out.println("3Unnamed" + i);
                }
        }}).start();
        //4
        (new Thread(new Runnable(){
            @Override public void run() {
                for (int i = 0; i < 100000; i++) {
                    System.out.println("4UnnamedRun" + i);
                }
        }})).start();
        //5
        new Thread(() ->{
            for (int i = 0; i < 100000; i++) {
                    System.out.println("5Lambda" + i);
            }
        }).start();
    }
}

class ExtendThread extends Thread{
    @Override 
    public void run(){
        for (int i = 0; i < 100000; i++) {
            System.out.println("1Thread " + i);
        }
    }
}

class ExtendRunnable implements Runnable{
    @Override 
    public void run(){
        for (int i = 0; i < 100000; i++) {
            System.out.println("2Runnable" + i);
        }
    }
}
