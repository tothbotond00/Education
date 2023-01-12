import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CheckedSetTest {
  @Test
  public void setTest (){
    try {
      CheckedSet<Integer> ch = new CheckedSet<>();
      assertEquals(0, ch.size());
      ch.add(2);ch.add(3);ch.add(1);
      assertEquals(3, ch.size());
    }
    catch(AlreadyContainedException e){
    }
  }

  @Test(expected = AlreadyContainedException.class)
  public void exceptionTest() throws AlreadyContainedException{
      CheckedSet<String> ch = new CheckedSet<>();
      ch.add("--");ch.add("--");
  }

  @Test
  public void containsTest(){
    try {
      CheckedSet<Double> ch = new CheckedSet<>();
       ch.add(1.1);
       assertEquals(true, ch.contains(1.1));
       assertEquals(false, ch.contains(1.2));
    }
    catch(AlreadyContainedException e){
    }
  }

  ///A típusos tesztek lényegében benne vannak a 3 tesztesetben ...
  
}