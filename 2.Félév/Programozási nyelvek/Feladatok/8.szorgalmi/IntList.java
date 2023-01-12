import java.util.ArrayList;
import java.lang.IllegalStateException;
import java.lang.IllegalArgumentException;

public class IntList {

  protected int maxSize ;
  protected ArrayList<Integer> array;

  public IntList (int maxSize){
    this.maxSize = maxSize;
    this.array = new ArrayList<Integer>(maxSize);
  }

  public IntList (int [] array, int maxSize){
    if (maxSize < array.length){
      throw new IllegalStateException();
    }
    this.maxSize = maxSize;
    this.array = new ArrayList<Integer>(maxSize);
    for (int i = 0; i < array.length; i ++){
      this.array.add(array[i]);
    }
  }

  void add (int num){
    if (array.size() == maxSize){
      throw new IllegalStateException();
    }
    else {
      array.add(num);
    }
  }

  void concat (IntList other){
    if (other.array.size() + this.array.size() > maxSize){
      throw new IllegalStateException();
    }
    else {
      for (int i = 0; i < other.array.size(); i++){
        this.array.add(other.array.get(i));
      }
    }
  }

  public String toString(){
    if (array.size() == 0 ){
      return "empty";
    }
    else {
      StringBuilder sb = new StringBuilder();
      sb.append(array.get(0));
      for (int i = 1; i < array.size(); i++){
        sb.append(" , ");
        sb.append(array.get(i));
      }
      return sb.toString();
    }
  }

  void removeItemsGreaterThan(int limit){
    for (int i = 0; i < array.size(); i++){
      if (array.get(i) > limit){
        array.remove(i);
        i -= 1;
      }
    }
  }

  ArrayList getData(){
    ArrayList<Integer> nums = new ArrayList<Integer>();
    for (int i = 0; i< array.size(); i++){
      nums.add(array.get(i));
    }
    return nums;
  }

}