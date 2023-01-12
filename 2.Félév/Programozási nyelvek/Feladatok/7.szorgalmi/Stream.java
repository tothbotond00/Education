import java.util.Random;

class Stream {
  private int maxStringLength;
  private int stringNumber;
  private Logger logger;
  private final String ABC ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  private String randomString (int maxStringLength){
    Random random = new Random();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i< maxStringLength; i++){
      int num = random.nextInt(26);
      sb.append(ABC.charAt(num));
    }
    return sb.toString();
  }

  Stream (int maxStringLength, int stringNumber, Logger logger){
    if (logger == null || maxStringLength <=0 || maxStringLength <= 0){
      throw new IllegalArgumentException();
    }
    this.maxStringLength = maxStringLength;
    this.stringNumber = stringNumber;
    this.logger = logger;
  } 

  public void startStreaming(){
    for (int i =0; i< stringNumber; i++){
      String rand = randomString(maxStringLength);
      logger.log(rand);
    }
  }
}