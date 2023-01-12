package library;

class Main {
  public static void main (String[] args){
    //toString
    Book hp = new Book("Harry Potter","JK. Rowling",1998,534,true);
    Book lor = new Book("Lord of the Rings","Tolkien",1963,876,false);
    System.out.println(hp.toString()+"\n");
    System.out.println(lor.toString()+"\n");
    //equals without null
    Book hp2 = new Book(hp);
    if(hp2.equals(lor)){
      System.out.println("They are equals.\n");
    }
    else{
      System.out.println("They are not equals.\n");
    }
    if (hp.equals(hp2)){
      System.out.println("They are equals.\n");
    }
    else{
      System.out.println("They are not equals.\n");
    }
    //constructor with null
    Book bad = null;
    Book good = new Book (bad);
  }
}