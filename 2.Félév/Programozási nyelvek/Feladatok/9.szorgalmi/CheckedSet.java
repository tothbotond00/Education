import java.util.HashSet;

public class CheckedSet<T> {
  private HashSet<T> set = new HashSet<T>();

  public int size() {
    return set.size();
  } 

  public void add (T element) throws AlreadyContainedException{
    if (contains(element)){
      throw new AlreadyContainedException("");
    }
    else {
      set.add(element);
    }
  }

  public boolean contains(T element){
    return set.contains(element);
  }

}