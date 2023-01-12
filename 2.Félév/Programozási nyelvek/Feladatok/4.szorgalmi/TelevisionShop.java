enum TelevisionShop{
  SAMSUNG (21,43,85) ,
  LG (14,40,70), 
  SKYWORTH (0, 0, 0), 
  SONY (22, 32, 75), 
  SHARP (5, 50, 80) ;

  private int amount;
  private final int max;
  private final int min;

  TelevisionShop(int amount, int min, int max){
    this.amount =amount;
    this.max = max;
    this.min = min;
  }

  static int getMin (){
    int min = 0;
    for (TelevisionShop tv : TelevisionShop.values()){
      if ( (tv.min < min || min == 0) && tv.min != 0){
        min = tv.min;
      }
    }
    return min;
  }

  static int getMax () {
    int max = 0;
    for (TelevisionShop tv : TelevisionShop.values()){
      if ( (tv.max > max || max == 0) && tv.max != 0){
        max = tv.max;
      }
    }
    return max;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (this.amount == 0) {
      sb.append(this.name());
      sb.append(": We are out of stock");
    }
    else {
      sb.append(this.name());
      sb.append(": ");
      sb.append(this.min);
      sb.append("\" is the smallest and ");
      sb.append(this.max);
      sb.append("\" is the biggest diameter from this type.");
    }
    return sb.toString();
  }

  static void getStock() {
    for (TelevisionShop tv : TelevisionShop.values()){
      System.out.print(tv.amount);
      System.out.print(" amount is available from ");
      System.out.println(tv);
    }
  }

}