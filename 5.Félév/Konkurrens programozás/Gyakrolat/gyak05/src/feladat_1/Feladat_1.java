package feladat_1;

import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.List;

public class Feladat_1 {
    public static void main(String[] argv){
        
        ///WITH NO ATOMIC OPERATION
        ThreadSafeMutableInteger solution = new ThreadSafeMutableInteger();
        List<Thread> threads = new ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
           threads.add(new Thread(){
               @Override
               public void run(){
                   for (int j = 0; j < 10_000_000; j++) {
                       solution.set(solution.get() + 1);
                   }
               }
            });
        }
        
        for (int i = 0; i < 10; i++) {
            threads.get(i).start();
        }
        
        for (int i = 0; i < 10; i++) {
            try{
                threads.get(i).join();   
            }catch (InterruptedException e) {
            exit(0);
            }
        }
        
        System.out.println(solution.get());
        
        //WITH ATOMIC OPERATION
        
        ThreadSafeMutableInteger solution2 = new ThreadSafeMutableInteger();
        List<Thread> threads2 = new ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
           if (i % 2 == 0){
                threads2.add(new Thread(){
                    @Override
                    public void run(){
                        for (int j = 0; j < 10_000_000; j++) {
                            solution2.incrementAndGet();
                        }
                    }
                 });
           }else {
               threads2.add(new Thread(){
                    @Override
                    public void run(){
                        for (int j = 0; j < 10_000_000; j++) {
                            solution2.decrementAndGet();
                        }
                    }
                 });
           }
        }
        
        for (int i = 0; i < 10; i++) {
            threads2.get(i).start();
        }
        
        for (int i = 0; i < 10; i++) {
            try{
                threads2.get(i).join();   
            }catch (InterruptedException e) {
            exit(0);
            }
        }
        
        System.out.println(solution2.get());
    }
}
