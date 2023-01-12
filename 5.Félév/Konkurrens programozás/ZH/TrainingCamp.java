import java.util.*;
import java.util.concurrent.*;
import java.util.logging.*;

public class TrainingCamp {
    private static final List<String> CLASSES = List.of("Mage", "Paladin", "Warrior", "Shaman", "Hunter");
    private static final int NUMBER_OF_TRAINEES = 50;
    private static final int NUMBER_OF_VOLUNTEERS = 5;
    private static final int QUEUE_CAPACITY = 5;
    private static final int WAIT_TIME = 2000;
    private static final List<String> TRAINEES = new ArrayList<>();
    private static final Map<String, Integer> SORTED_TRAINEES = new ConcurrentHashMap<>();
    private static final BlockingQueue<String> TRAINEE_QUEUE = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
    private static int TRAINEES_SORTED = 0;
    private static final Object TRAINEES_SORTED_LOCK = new Object();
    
    public static void main(String[] args) {
        setupTrainees();

        for(int i = 0; i < NUMBER_OF_VOLUNTEERS; ++i){
            (new Thread(){
                @Override
                public void run(){
                    putTraineesInLine();
                }
            }).start();
        }

        for(int i = 0; i < QUEUE_CAPACITY; ++i){
            (new Thread(){
                @Override
                public void run(){
                    sortTrainees();
                }
            }).start();
        }
        
        ExecutorService exec = Executors.newSingleThreadExecutor();
        Callable<String> task = () -> selfCheck();
        Future<String> checkMessage = exec.submit(task);
        try{
           System.out.println(checkMessage.get());
        }catch(InterruptedException | ExecutionException e){
            Logger.getLogger(TrainingCamp.class.getName()).log(Level.SEVERE, null, e);
        }
        
        try {
            Thread.sleep(WAIT_TIME);
        } catch (InterruptedException ex) {
            Logger.getLogger(TrainingCamp.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(42);
    }

    /**
     * Setting up trainees randomly
     */
    private static void setupTrainees(){
        Random rand = new Random();
        for(int i = 0; i < NUMBER_OF_TRAINEES; ++i) {
            String traineeClass = CLASSES.get(rand.nextInt(0, CLASSES.size()));
            TRAINEES.add(traineeClass);
        }
        CLASSES.forEach(str -> SORTED_TRAINEES.put(str, 0));
    }

    /**
     * The volunteers put the trainees in a queue for further sorting
     */
    private static void putTraineesInLine(){
        while(true){
            String applier;
            int random;
            synchronized(TRAINEES){
                if(TRAINEES.isEmpty()) break;
                random = ThreadLocalRandom.current().nextInt(0, TRAINEES.size());
                applier = TRAINEES.remove(random);
            }
            boolean success = false;
            while(!success){
                try {
                    success = TRAINEE_QUEUE.offer(applier,WAIT_TIME,TimeUnit.MILLISECONDS);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TrainingCamp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Taking a trainee from the queue and determining where it belongs (map)
     */
    private static void sortTrainees(){
        while(TRAINEES_SORTED != NUMBER_OF_TRAINEES && !TRAINEE_QUEUE.isEmpty()){
            String trainee = null;
            while(trainee == null){
                try {
                    trainee = TRAINEE_QUEUE.poll(WAIT_TIME,TimeUnit.MILLISECONDS);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TrainingCamp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            synchronized(SORTED_TRAINEES){
                int num = SORTED_TRAINEES.get(trainee);
                SORTED_TRAINEES.put(trainee, num + 1);
            }
            
            synchronized(TRAINEES_SORTED_LOCK){
                TRAINEES_SORTED++;
            }
            
            try {
                Thread.sleep(WAIT_TIME);
            } catch (InterruptedException ex) {
                Logger.getLogger(TrainingCamp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Checking at the end of the simulation if we indeed sorted as many as we had to
     * @return String based on result
     */
    private static String selfCheck(){
        while(true){
            try {
                Thread.sleep(WAIT_TIME);
            } catch (InterruptedException ex) {
                Logger.getLogger(TrainingCamp.class.getName()).log(Level.SEVERE, null, ex);
            }
            boolean equals = TRAINEES_SORTED == NUMBER_OF_TRAINEES;
            if(equals){
                int counter = 0;
                for (int i = 0; i < CLASSES.size(); i++) {
                    counter += SORTED_TRAINEES.get(CLASSES.get(i));
                }
                if (counter == TRAINEES_SORTED) return "Everything adds up";
                else return "Something's wrong";
            }
        }
    }

}