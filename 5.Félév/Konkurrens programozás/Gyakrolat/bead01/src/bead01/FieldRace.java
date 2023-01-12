package bead01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FieldRace {
    static AtomicBoolean isOn;
    static ConcurrentHashMap<Integer,Integer> scores;
    static AtomicInteger[] checkpointScores;
    static List<ArrayBlockingQueue<AtomicInteger>> checkpointQueues;
    static final int PLAYER_COUNT = 10;
    static final int CHECKPOINT_COUNT = 5;
    
    public static void main(String[] args) {
        ///INITIALISATION
        isOn = new AtomicBoolean(true);
        scores = new ConcurrentHashMap<>();
        checkpointQueues = Collections.synchronizedList(new ArrayList<>(CHECKPOINT_COUNT));
        checkpointScores = new AtomicInteger[PLAYER_COUNT];
        for (int i = 0; i < PLAYER_COUNT; i++) {
            scores.put(i, 0);
            checkpointScores[i] = new AtomicInteger(0);
        }
        for (int i = 0; i < CHECKPOINT_COUNT; i++) {
            checkpointQueues.add(new ArrayBlockingQueue<>(10));
        }
        ExecutorService exec = Executors.newFixedThreadPool(PLAYER_COUNT + PLAYER_COUNT + 1);
        
        ///THREAD MAKING
        for (int i = 0; i < PLAYER_COUNT; i++) {
            int index = i;
            exec.submit(new Player(index));
        }
        for (int i = 0; i < CHECKPOINT_COUNT; i++) {
            int index = i;
            exec.submit(new Checkpoint(index));
        }
        exec.submit(new PrinterClass());
        
        
        ///WAINTING 10 SECS FOR TERMINATION
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FieldRace.class.getName()).log(Level.SEVERE, null, ex);
        }
        isOn.set(false);
        
        ///SHUTDOWN OF THE EXECUTOR SERVICE
        exec.shutdown();
        try {
            exec.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            Logger.getLogger(FieldRace.class.getName()).log(Level.SEVERE, null, ex);
        }
        exec.shutdownNow();
        
        ///FINAL RESULT
        System.out.print("FINAL RESULT: ");
        (new PrinterClass()).printScores();
    }
    
    static class PrinterClass implements Runnable{
        @Override
        public void run(){
            while(isOn.get()){
                try {
                    printScores();
                    Thread.sleep(1_000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FieldRace.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        synchronized public void printScores(){
            //Sort the hashmap as the task says
            List<Integer> values = new ArrayList<>(scores.keySet());
            Collections.sort(values, (Integer a, Integer b) -> scores.get(b) - scores.get(a)
            );
            System.out.print("Scores: [ ");
            values.forEach(val -> {
                System.out.print(val + "=" + scores.get(val)+ ", ");
            });
            System.out.println("]");
        }
    }
    
    static class Player implements Runnable{
        
        private final int index;
        
        Player(int index){
            this.index = index; 
        }
        
        @Override
        public void run() {
            while(isOn.get()){
                try {
                    int destination = ThreadLocalRandom.current().nextInt(0, CHECKPOINT_COUNT);
                    int timeDuration = ThreadLocalRandom.current().nextInt(500, 2_001);
                    Thread.sleep(timeDuration);
                    checkpointQueues.get(destination).add(checkpointScores[index]);
                    while(checkpointScores[index].get() == 0 && isOn.get()){
                        synchronized(checkpointScores[index]){checkpointScores[index].wait(3);}
                    }
                    if(!isOn.get()) break;
                    int newScore = checkpointScores[index].get();
                    checkpointScores[index].set(0);
                    System.out.println("Player "+ index +" got "+ newScore +" points at checkpoint " + destination);
                    scores.put(index, scores.get(index) + newScore); 
                } catch (InterruptedException ex) {
                    Logger.getLogger(FieldRace.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
    static class Checkpoint implements Runnable{
        
        private final int index;
        
        Checkpoint(int index){
            this.index = index; 
        }
        
        @Override
        public void run() {
            while(isOn.get()){
                try {
                    AtomicInteger point = checkpointQueues.get(index).poll(2, TimeUnit.SECONDS);
                    if (point == null) continue;
                    point.addAndGet(ThreadLocalRandom.current().nextInt(10, 100));
                    synchronized(point) {point.notifyAll();}
                } catch (InterruptedException ex) {
                    Logger.getLogger(FieldRace.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
}
