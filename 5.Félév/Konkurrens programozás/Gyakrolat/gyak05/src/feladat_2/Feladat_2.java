package feladat_2;

import java.util.ArrayList;
import java.util.List;

public class Feladat_2 {
    public static void main(String[] argv) {
        List<Thread> threads = new ArrayList<>();
        ThreadSafeMutableIntArray array = new ThreadSafeMutableIntArray(2);
        
        for (int i = 0; i < 10; i++) {
            int num = i % 2;
            threads.add(new Thread(){
                @Override
                public void run(){
                    while(array.addOne(num, 10_000_000))
                        ;
                }
            });
        }
        
        for (int i = 0; i < 10; i++) {
            threads.get(i).start();
        }
        
        for (int i = 0; i < 10; i++) {
            try{
                threads.get(i).join();
            }catch(InterruptedException e){
                
            }
        }
        
        System.out.println(array.get(0));
        System.out.println(array.get(1));
    }
}
