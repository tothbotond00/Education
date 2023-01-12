package feladat_1;

import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.List;

public class Feladat_1 {
    public static void main(String[] str){
        List<Integer> list = new ArrayList<>();
        Thread even = new Thread(){
            @Override
            public void run(){
                for (int i = 2; i <= 1_000_000; i+=2) {
                    while(!list.contains(i-1))
                        ;

                    System.out.println(i);
                    synchronized(list){
                            list.add(i);
                    }
                }
            }
        };
        Thread odd = new Thread(){
            @Override
            @SuppressWarnings("empty-statement")
            public void run(){
                for (int i = 1; i <= 1_000_000; i+=2) {
                    while(!(list.isEmpty() || list.contains(i-1)))
                        ;
                    System.out.println(i);
                    synchronized(list){
                            list.add(i);
                    }
                }
            }
        };
        odd.start();
        even.start();
        try{
            odd.join();
            even.join();
        }catch(InterruptedException e){
            exit(1);
        }
        System.out.println(list.toArray().length);
        for (int i = 0; i < 1_000_000; i++) {
            System.out.println(list.get(i));
        }
    }
}
