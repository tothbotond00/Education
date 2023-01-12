package feladat_3;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Feladat_3 {
    
    
    public static void main(String[] args){
        Switcheroo feladat = new Switcheroo();
        feladat.startSwitching();
    }
}

class Switcheroo
{
    static final int NUM_OF_THREADS = 10;
    static final int NUM_OF_ELEMS = 100; 
    ExecutorService exec;
    final int array [];
    
    Switcheroo(){
        exec = Executors.newFixedThreadPool(NUM_OF_THREADS);
        array = new int[NUM_OF_ELEMS];
        for (int i = 0; i < NUM_OF_ELEMS; i++) {
            array[i] = 1000;
        }
    }
    
    public void startSwitching(){
        
        int sum = 0;
        for (int i = 0; i < NUM_OF_ELEMS; i++) {
            sum += array[i];
        }
        System.out.println("At start : " + sum);
        
        for (int i = 0; i < NUM_OF_THREADS; i++) {
            exec.submit(new Runnable(){
                @Override
                public void run()
                {
                    Random rand = new Random();
                    for (int j = 0; j < 10_000 ; j++) {
                        synchronized(array){
                            switcheroo(rand);
                        }
                    }
                }
                
                private void switcheroo(Random rand){
                    int x = rand.nextInt(NUM_OF_ELEMS);
                    int y = rand.nextInt(NUM_OF_ELEMS);
                    int lowest = array[x];
                    int value = rand.nextInt(lowest);
                    array[x] -= value;
                    array[y] += value;
                }
            });
        }
        
        exec.shutdown();
        try{
            exec.awaitTermination(1, TimeUnit.DAYS);
        }catch(InterruptedException e){
            
        }
        
        sum = 0;
        for (int i = 0; i < NUM_OF_ELEMS; i++) {
            sum += array[i];
        }
        System.out.println("At the end : " + sum);
    }
    
}
