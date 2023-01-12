class Main {
  public static void main(String[] args) {
    if (args.length < 4){
      System.err.println("Not enough arguments!");
    }
    else {
    int length = args.length / 4;
    Rectangle r [] = new Rectangle[length];
    int j = 0;
    for (int i = 0; i< r.length; ++i){
      double x = Double.parseDouble(args[j]);
      double y = Double.parseDouble(args[j+1]);
      double width = Double.parseDouble(args[j+2]);
      double height = Double.parseDouble(args[j+3]);
      r[i] = new Rectangle(x,y,width,height);
      j +=4;
    }
    double minx = r[0].bottomLeft().x;
    double miny = r[0].bottomLeft().y;
    double maxx = r[0].topRight().x;
    double maxy = r[0].topRight().y;
    for (int i = 1; i< r.length; ++i){
      if (r[i].bottomLeft().x < minx){
        minx = r[i].bottomLeft().x;
      }
      if (r[i].bottomLeft().x < miny){
        miny = r[i].bottomLeft().y;
      }
      if (r[i].topRight().x > maxx){
        maxx = r[i].topRight().x;
      }
      if (r[i].topRight().y > maxy) {
        maxy = r[i].topRight().y;
      }
    }
    System.out.println ("Bounding rectangle: "+ minx + ";" + miny + " - " + maxx + ";" + maxy);
    }
  }
}