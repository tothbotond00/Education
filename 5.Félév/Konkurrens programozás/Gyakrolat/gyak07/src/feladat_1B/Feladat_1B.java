package feladat_1B;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Feladat_1B {
    
    static final int THREAD_COUNT = 2;
    static final int ELEMS_PER_THREAD = 100_000;
    
    public static void main(String[] args){
        List<Integer> original = Collections.synchronizedList(new ArrayList<>());
        for (int i = 1; i <= THREAD_COUNT * ELEMS_PER_THREAD; i++) {
            original.add(i);
        }
        List<Integer> result = Collections.synchronizedList(new ArrayList<>());
        ExecutorService exec = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            exec.submit(new Runnable(){
                @Override
                public void run(){
                    for (int j = 0; j < ELEMS_PER_THREAD; j++) {
                        int num = original.remove(0);
                        result.add(num);
                    }
                }
            });
        }
        
        exec.shutdown();
        try{
            exec.awaitTermination(1, TimeUnit.DAYS);
        }catch(InterruptedException e){

        }
    }    
}
