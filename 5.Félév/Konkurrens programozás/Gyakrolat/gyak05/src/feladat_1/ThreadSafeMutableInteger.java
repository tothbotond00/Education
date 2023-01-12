package feladat_1;

public class ThreadSafeMutableInteger {
    private int value;
    
    ThreadSafeMutableInteger(int value ){
        this.value = value;
    }
    
    ThreadSafeMutableInteger(){
        this.value = 0;
    }
    
    public synchronized int get(){
        return this.value;
    }
    
    public synchronized void set(int value){
        this.value = value;
    }
    
    public synchronized int getAndIncrement(){
        int sol = get();
        this.value = value + 1;
        return sol;
    }
    
    public synchronized int getAndDecrement(){
        int sol = get();
        this.value = value - 1;
        return sol;
    }
    
    public synchronized int getAndAdd(int v){
        int sol = get();
        this.value = value + v;
        return sol;
    }
    
    public synchronized int incrementAndGet(){
        this.value = value + 1;
        return value;
    }
    
    public synchronized int decrementAndGet(){
        this.value = value - 1;
        return value;
    }
    
    public synchronized int addAndGet(int v){
        this.value = value + v;
        return value;
    }
    
}
