class Main {
  public static void main(String[] args) {
    ///A ÉS B FELADAT
    IntList a = new IntList(20);
    a.add(1);a.add(2);a.add(3);a.add(4);
    a.add(5);a.add(6);a.add(7);a.add(8);
    a.add(9);a.add(10);a.add(11);a.add(12);
    System.out.println(a);
    a.removeItemsGreaterThan(5);
    System.out.println(a);
    a.add(1);a.add(2);a.add(3);a.add(4);
    a.add(5);a.add(6);a.add(7);a.add(8);
    a.add(9);a.add(10);a.add(11);a.add(12);
    ///a.add(9);a.add(10);a.add(11);a.add(12); /itt illegal
    a.removeItemsGreaterThan(10);
    System.out.println(a);
    int [] array = {30,31,32};
    IntList b = new IntList(array,5);
    a.concat(b);
    System.out.println(a);
    ///a.concat(b); /itt illegal

    ///C FELADAT
    NamedIntList c = new NamedIntList("Próba",20);
    c.add(1);c.add(11);c.add(12);c.add(13);c.add(14);
    c.add(15);c.add(16);c.add(17);c.add(18);c.add(19);
    System.out.println(c);
    NamedIntList d = new NamedIntList("Próba2",array,20);
    System.out.println(d);
  }
}