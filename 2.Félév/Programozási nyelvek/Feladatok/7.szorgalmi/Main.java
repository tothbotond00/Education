class Main {
  public static void main (String [] args){
    Logger cl = new ConsoleLogger();
    Stream s = new Stream(10,5,cl);
    s.startStreaming();

    System.out.println();
    Logger ccl = new ConsoleCipherLogger();
    Stream ss = new Stream(10,5,ccl);
    ss.startStreaming();

    System.out.println();
    Logger fl = new FileLogger("output.txt");
    Stream sss = new Stream(10,5,fl);
    sss.startStreaming();
    System.out.println("Fajlba ki lett irva!");
  } 
}