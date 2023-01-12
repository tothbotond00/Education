package feladat_1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Feladat_1 {
    public static void main(String[] args){
        Collection<Integer> first = new ArrayList<>();
        Collection<Integer> second = new LinkedList<>();
        Collection<Integer> third = new Vector<>();
        Collection<Integer> fourth = Collections.synchronizedCollection(new Vector<>());
        Collection<Integer> fifth = Collections.synchronizedList(new Vector<>());
        for (int i = 0; i < 10_000; i++) {
            first.add(i);
            second.add(i);
            third.add(i);
            fourth.add(i);
            fifth.add(i);
        }
        
        snycIterate(first, 1);
        snycIterate(second, 2);
        snycIterate(third, 3);
        snycIterate(fourth, 4);
        snycIterate(fifth, 5);
        
        ExecutorService exec = Executors.newFixedThreadPool(4);
        exec.submit(() -> {
            snycIterate(first, 1);
            snycIterate(second, 2);
            snycIterate(third, 3);
            snycIterate(fourth, 4);
            snycIterate(fifth, 5);
        });
        exec.submit(() -> {
            snycIterate(first, 1);
            snycIterate(second, 2);
            snycIterate(third, 3);
            snycIterate(fourth, 4);
            snycIterate(fifth, 5);
        });
        exec.submit(() -> {
            nonSyncIterate(first, 998);
            nonSyncIterate(fourth, 4);
            nonSyncIterate(fifth, 5);
        });
        exec.submit(() -> {
            nonSyncIterate(first, 999);
            nonSyncIterate(fourth, 4);
            nonSyncIterate(fifth, 5);
        });
        exec.shutdown();
        try{
            exec.awaitTermination(1, TimeUnit.DAYS);
        }catch(InterruptedException e){
            
        }
        
    }
    
    public static void nonSyncIterate(Collection<Integer> col, int num){
        Iterator<Integer> it = col.iterator();
        while(it.hasNext()){
            System.out.println(num + ". " + it.next());
        }
    }
    
    public static  void snycIterate(Collection<Integer> col, int num){
        synchronized(col){
            nonSyncIterate(col, num);
        }
    }
}
