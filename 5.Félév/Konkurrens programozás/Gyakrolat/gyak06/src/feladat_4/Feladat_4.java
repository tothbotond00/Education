package feladat_4;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Feladat_4 {
    public static void main(String[] args){
        InefficientSorter feladat = new InefficientSorter ();
        feladat.startSorting();
    }
}

class InefficientSorter 
{
    static final int NUM_OF_THREADS = 10;
    static final int NUM_OF_ELEMS = 100; 
    ExecutorService exec;
    final int array [];
    int sum;
    
    InefficientSorter (){
        exec = Executors.newFixedThreadPool(NUM_OF_THREADS);
        array = new int[NUM_OF_ELEMS];
        Random rand = new Random();
        sum = 0;
        for (int i = 0; i < NUM_OF_ELEMS; i++) {
            array[i] = rand.nextInt();
            sum += array[i];
        }
    }
    
    public void startSorting(){
        for (int i = 0; i < NUM_OF_THREADS; i++) {
            exec.submit(new Runnable(){
                @Override
                public void run()
                {
                    Random rand = new Random();
                    for (int j = 0; j < 10_000 ; j++) {
                        synchronized(array){
                            sort(rand);
                        }
                    }
                }
                private void sort(Random rand){
                    int x = rand.nextInt(NUM_OF_ELEMS);
                    int y = rand.nextInt(NUM_OF_ELEMS);
                    if (array[x] > array[y] && x < y){
                        int tmp = array[y];
                        array[y] = array[x];
                        array[x] = tmp;
                    }
                    else if (array [y] > array[x] && y < x){
                        int tmp = array[y];
                        array[y] = array[x];
                        array[x] = tmp;
                    }
                }
            });
        }
        exec.shutdown();
        try{
            exec.awaitTermination(1, TimeUnit.DAYS);
        }catch(InterruptedException e){

        }

        int sum2 = 0;
        boolean allGood = true;
        for (int j = 0; j < NUM_OF_ELEMS; j++) {
            sum2 += array[j];
            allGood = allGood && ( j + 1 == NUM_OF_ELEMS || array[j] < array[j+1]);
        }
        if(allGood){
            System.out.println("The sorting was successfull");
        }else System.out.println("This sort didn't work out. We have to SORT this out ;)");
        System.out.println(this.sum + "    " + sum2);
    }
    
}
