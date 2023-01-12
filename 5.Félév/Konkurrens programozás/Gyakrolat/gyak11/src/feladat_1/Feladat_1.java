
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Feladat_1 {
    public static void main(String[] args){
        
        AtomicInteger[] forks = new AtomicInteger[5];
        ExecutorService exec = Executors.newFixedThreadPool(5);
        
        for (int i = 0; i < 5; i++) {
            int ind = i;
            exec.submit(new Runnable(){
                @Override
                public void run() {
                    System.out.println(ind + "is waiting");
                    synchronized(forks[(ind +1) % 5]){
                        synchronized(forks[ind % 5]){
                            System.out.println(ind + "is eating");
                        }
                    }
                }
            });
        }
        
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException ex) {
        }
        exec.shutdown();
        try {
            exec.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
        }
        
    }
}

