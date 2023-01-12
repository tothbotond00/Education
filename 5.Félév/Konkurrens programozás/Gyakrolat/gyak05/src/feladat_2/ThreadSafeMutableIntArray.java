package feladat_2;

public class ThreadSafeMutableIntArray {
    
    private final int capacity;
    private final int[] array;
    private final Object[] locks;
    
    ThreadSafeMutableIntArray(int capacity){
        this.capacity = capacity;
        array = new int[capacity];
        locks = new Object[capacity];
        for (int i = 0; i < capacity; i++) {
            locks[i] = new Object();
        }
    }
    
    public int get(int idx){
        synchronized(locks[idx]){
            return array[idx];
        }
    }
    
    void set(int idx, int newValue){
        synchronized(locks[idx]){
            array[idx] = newValue;
        }
    }
    
    boolean addOne(int idx, int limit){
        synchronized(locks[idx]){
            if (get(idx) < limit){
                set(idx,get(idx) + 1);
                return true;
            }else return false;
        }
    }
    
}
