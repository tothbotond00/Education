import java.util.NoSuchElementException;

public class Stack {
  private int [] array;
  private int size;
  private int length;

  public Stack(int size){
    this.size = size;
    this.array = new int [this.size];
    this.length = 0;
  }

  public Stack(){
    this.size = 10;
    this.array = new int [this.size];
    this.length = 0;
  }

  public int pop(){
    if (empty()){
      throw new NoSuchElementException();
    }
    else {
      length--;
      return array[length];
    }
  }

  public void push(int n){
    if(length == size){
      throw new ArrayIndexOutOfBoundsException();
    }
      array[length] = n;
      length++;
  }

  public boolean empty(){
    return length==0;
  }

  public int size() {
    return length;
  }
}