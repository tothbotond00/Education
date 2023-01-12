class Main {
  public static void main(String[] args) {
    try {
      System.out.println("CheckedSet Tests:");
      CheckedSet<Integer> ch = new CheckedSet<>();
      ch.add(2);
      System.out.println(ch.size());
      if (ch.contains(2)) System.out.println("Benne van.");
      ch.add(2);
    }
    catch(AlreadyContainedException e){
      System.err.println("Already contains");
    }
  }
}