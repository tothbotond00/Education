class ConsoleLogger extends Logger {
  
  @Override
  void log (String str){
    System.out.println(str); 
  }
}