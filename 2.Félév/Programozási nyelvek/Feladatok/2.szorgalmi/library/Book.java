package library;

class Book {
  private String name;
  private String author;
  private int relase;
  private int pagenum;
  private boolean rent;

  public Book (String name,String author, int relase, int pagenum, boolean rent){
    this.name = name;
    this.author = author;
    this.relase = relase;
    this.pagenum = pagenum;
    this.rent = rent;
  }

  public Book (Book b) {
    if (b == null){
      throw new IllegalArgumentException();
    }
    this.name = b.name;
    this.author = b.author;
    this. relase = b.relase;
    this.pagenum = b.pagenum;
    this.rent = b.rent;
  }

  public String toString(){
    String s;
    if (rent){
      s = name+"'s properties:"+"\n"+
                 "This book was written by: "+author+"\n"+
                 "It was relased in : "+ relase+"\n"+
                 "It has "+pagenum+" pages"+"\n"+
                 "The book is currently available."
               ;
    }
    else{
      s = name+"'s properties:"+"\n"+
                 "This book was written by: "+author+"\n"+
                 "It was relased in : "+ relase+"\n"+
                 "It has "+pagenum+" pages"+"\n"+
                 "The book is currently unavilable."
               ;
    }
    return s;
  }

  public boolean equals(Book b){
    if (b == null){
      throw new IllegalArgumentException();
    }
    return (this.name == b.name && this.author == b.author && this.relase ==b.relase && this.pagenum ==b.pagenum);
  }

}