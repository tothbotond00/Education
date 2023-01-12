import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.util.NoSuchElementException;

public class StackTest {

  @Test
  public void popTest(){
    Stack s = new Stack();
    s.push(1);s.push(2);s.push(3); s.push(4); s.push(5);
    assertEquals(5,s.pop());
    assertEquals(4,s.pop());
    assertEquals(3,s.pop());
    assertEquals(2,s.pop());
    assertEquals(1,s.pop());
  }

  @Test
  public void emptyTrueTest(){
    Stack s = new Stack();
    assertEquals(true,s.empty());
    Stack x= new Stack(20);
    assertEquals(true,x.empty());
  }

  @Test
  public void emptyFalseTest(){
    Stack s = new Stack(); s.push(2);
    assertEquals(false,s.empty());
    Stack x = new Stack(20); x.push(3);
    assertNotEquals(true,x.empty());
  }

  @Test(expected = NoSuchElementException.class)
  public void popEmptyTest(){
    Stack s = new Stack();
    s.pop();
  }

  @Test
  public void sizeTest(){
    Stack s = new Stack();
    assertEquals(0,s.size());
    s.push(2);
    assertEquals(1,s.size());
  }

  @Test
  public void allTest(){
    Stack s = new Stack();
    s.push(1);s.push(2);s.push(3); s.push(4); s.push(5);
    s.pop();s.pop();s.pop();s.pop();s.pop();
    assertEquals(true,s.empty());
    assertEquals(0,s.size());
  }

}