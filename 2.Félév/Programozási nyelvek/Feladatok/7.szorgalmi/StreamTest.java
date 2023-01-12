import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

class StreamTest {
  @Test(expected = IllegalArgumentException.class)
  public void IllegalTest(){
    Logger l = new Logger();
    Stream(-1,-1,l);
  }
  
}