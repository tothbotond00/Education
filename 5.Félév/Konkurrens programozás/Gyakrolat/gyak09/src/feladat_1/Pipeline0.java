import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Pipeline0 {
    public static void main(String[] args) throws Exception {
        var NO_FURTHER_INPUT = "";

        var bq = new ArrayBlockingQueue<String>(10);

        var pool = Executors.newCachedThreadPool();

        pool.submit(() -> {
            bq.addAll(List.of("a", "bb", "ccccccc", "ddd", "eeee", NO_FURTHER_INPUT));
        });

        pool.submit(() -> {
            try {
                while (true) {
                    String s = bq.take();
                    if (s.equals(NO_FURTHER_INPUT)) return ;
                    System.out.println(s);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);
    }
}
