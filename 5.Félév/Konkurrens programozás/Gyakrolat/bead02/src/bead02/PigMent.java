
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class PigMent {

  /* Global Constants */
  static final int TIC_MIN   = 50;  // Tickrate minimum time (ms)
  static final int TIC_MAX   = 200; // Tickrate maximum time (ms)
  static final int FEED      = 20;  // Mass gained through photosynthesis
  static final int BMR       = 10;  // Mass lost due to basal metabolic rate
  static final int MAX_POP   = 10;  // Maximum number of concurent pigs
  static final int INIT_POP  = 3;   // size of initial pig population
  static final int INIT_MASS = 20;  // starting mass of initial pigs
  
  static final AtomicInteger globalId = new AtomicInteger(0);
  static final ExecutorService pigPool = Executors.newFixedThreadPool(MAX_POP);
  // TODO: explicit locks and conditions also go here (Task2)

  /* Implementing Awesomeness (a.k.a. the pigs) */
  static class PhotoPig implements Runnable {
    
    /* Take this, USA! */
    final int id;

    /* Watch your lines, piggie! */
    int mass;

    /* Sweet dreams (are made of this) */
    void pigSleep() {
        int msec = ThreadLocalRandom.current().nextInt(TIC_MIN, TIC_MAX);
        try {
            Thread.sleep(msec);
        } catch (InterruptedException e) {
            pigSay("Oh i got awaken!");
        }
    }

    /* Ensuring free speech */
    synchronized void pigSay(String msg) {
        System.out.println(id + " id " + mass + " mass piglet: " + msg);
    }

    /* Here comes the esoteric stuff */
    boolean eatLight() {
        pigSleep();
        mass += FEED;
        mass -= mass / BMR;
        pigSay("Holy crap, I just ate light!");
        if(mass / BMR > FEED / 2){
            pigSay("This vessel can no longer hold the both of us!");
            pigPool.submit(new PhotoPig(mass/2));
            mass = mass / 2;
        }
        return true;
    }

    /* Hey, this ain't vegan! */
    boolean aTerribleThingToDo() {
      return true; // TODO: replace this placeholder (task2)
    }

    /* Every story has a beginning */
    public PhotoPig(int mass) {
      this.mass = mass;
      this.id   = globalId.addAndGet(1);
    }

    /* Live your life, piggie! */
    @Override public void run() {
        pigSay("Beware world, for I'm here now!");
        if(MAX_POP == globalId.get()) pigPool.shutdownNow();
        while(eatLight()){}
        pigSay("I have endured unspeakable horrors. Farewell, world!");
    }
  }

    /* Running the simulation */
    public static void main(String[] args) {
          System.out.println("Hello PigMent!");
          for (int i = 0; i < INIT_POP; i++) {
              pigPool.submit(new PhotoPig(INIT_MASS));
          }
    }
}
