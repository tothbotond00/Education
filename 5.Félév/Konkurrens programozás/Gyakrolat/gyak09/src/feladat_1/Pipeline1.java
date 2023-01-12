import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Pipeline1 {
    public static void main(String[] args) throws Exception {
        var NO_FURTHER_INPUT1 = "";
        var NO_FURTHER_INPUT2 = -1;

        var bq1 = new ArrayBlockingQueue<String>(10);
        var bq2 = new ArrayBlockingQueue<Integer>(10);

        var pool = Executors.newCachedThreadPool();

        pool.submit(() -> {
            bq1.addAll(List.of("a", "bb", "ccccccc", "ddd", "eeee", NO_FURTHER_INPUT1));
        });

        pool.submit(() -> {
            try {
                while (true) {
                    String s = bq1.take();
                    if (s.equals(NO_FURTHER_INPUT1)) {
                        bq2.add(NO_FURTHER_INPUT2);
                        return ;
                    }
                    bq2.add(s.length());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.submit(() -> {
            try {
                while (true) {
                    Integer s = bq2.take();
                    if (s.equals(NO_FURTHER_INPUT2)) return ;
                    else System.out.println(s);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);
    }
}
