package feladat_3;

public class Feladat_3 {
    
    static long measure1;
    static long measure2;
    
    public static void main(String[] args){
        //Just One thread
        Thread c1 = new CounterThread1();
        long start1 = System.nanoTime();
        c1.start();
        while(Thread.activeCount() != 1){}
        measure1 = measure1 - start1;
        System.out.println( "One thread: " + measure1 + " nanosecs");
        
        //Ten threads
        long start2 = System.nanoTime();
        for (int i = 1; i <= 10; i++) {
            (new CounterThread2(1, i*1_000_000)).start();
        }
        while(Thread.activeCount() != 1){}
        measure2 = measure2 - start2;
        System.out.println("Ten threads: " + measure2 + " nanosecs");
        
        //Difference
        measure1 = measure1 * 10;
        double difference = ((double)measure1 / measure2) * 100;
        System.out.println("Acceleration for ten threads: " +difference + " %");
    }
}

class CounterThread1 extends Thread{
    
    @Override
    public void run()
    {
        int sum = 0;
        for (int i = 1; i <= 1_000_000; i++) {
            sum += i;
        }
        Feladat_3.measure1 = System.nanoTime();
    }
}

class CounterThread2 extends Thread{
    
    private final int start;
    private final int finish;
    
    public CounterThread2(int start, int finish){
        super();
        this.start = start;
        this.finish = finish;
    }
    
    @Override
    public void run()
    {
        int sum = 0;
        for (int i = this.start; i <= this.finish; i++) {
            sum += i;
        }
        Feladat_3.measure2 = System.nanoTime();
    }
}
