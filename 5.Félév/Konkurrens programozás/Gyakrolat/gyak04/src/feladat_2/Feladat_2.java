package feladat_2;

import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.List;

public class Feladat_2 {

    public static void main(String[] argv) {

        //Chaotic
        long measure1 = System.nanoTime();
        Thread t1 = new Thread(new useThreads(10, false, false));
        t1.start();
        try {
            t1.join();
            measure1 = System.nanoTime() - measure1;
        } catch (InterruptedException e) {
            exit(0);
        }

        ///Synchronized
        long measure2 = System.nanoTime();
        Thread t2 = new Thread(new useThreads(10, true, false));
        t2.start();
        try {
            t2.join();
            measure2 = System.nanoTime() - measure2;
        } catch (InterruptedException e) {
            exit(0);
        }

        ///In order and synchronized
        long measure3 = System.nanoTime();
        Thread t3 = new Thread(new useThreads(10, true, true));
        t3.start();
        try {
            t3.join();
            measure3 = System.nanoTime() - measure3;
        } catch (InterruptedException e) {
            exit(0);
        }

        System.out.println("CHAOTIC: " + measure1 + " nanosecs");
        System.out.println("SYNCHRONIZED: " + measure2 + " nanosecs");
        System.out.println("IN ORDER AND SYNCHRONIZED: " + measure3 + " nanosecs");
    }
}

class useThreads implements Runnable {

    private final int n;
    private final boolean isSynchronized;
    private final boolean isInOrder;
    private final List<Integer> demoList;
    private final List<Thread> counterThreads;

    public useThreads(int n, boolean isSynchronized, boolean isInOrder) {
        this.n = n;
        this.isSynchronized = isSynchronized;
        this.isInOrder = isInOrder;
        this.demoList = new ArrayList<>();
        this.counterThreads = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            this.counterThreads.add(new CounterThread(i, this.n, this.isSynchronized, this.isInOrder, this.demoList));
        }
    }

    @Override
    public void run() {
        this.counterThreads.forEach(t -> {
            t.start();
        });
        this.counterThreads.forEach(t -> {
            try {
            t.join();
            } catch (InterruptedException e) {
                exit(0);
            }
        });
        System.out.println("List size: " + this.demoList.size());
    }

}

class CounterThread extends Thread {
    final int INTEGER_LIMIT = 1000;
    private final int start;
    private final int n;
    private final boolean isSynchronized;
    private final boolean isInOrder;
    private final List<Integer> demoList;

    public CounterThread(int start,int n, boolean isSynchronized, boolean isInOrder, List<Integer> demoList) {
        this.start = start;
        this.n = n;
        this.isSynchronized = isSynchronized;
        this.isInOrder = isInOrder;
        this.demoList = demoList;
    }

    @Override
    public void run() {
        for (int i = start; i < INTEGER_LIMIT; i += n) {
            if(isInOrder){
                while (!(i == 0 || this.demoList.contains(i - 1)))
                ;
            }
            if(isSynchronized){
                synchronized (demoList) {
                demoList.add(i);
                }
            }else{
                demoList.add(i);
            }
        }
    }
}
