package auto;

import auto.utils.Color;

public class Auto {
  private String regnum;
  private Color color;
  private int maxspeed;
  static int counter = 0;

  public Auto () {
    this.regnum = "AAA-000";
    this.color = Color.BLUE;
    this.maxspeed = 120;
    counter++;
  }

  public Auto (String regnum, Color color,int maxspeed){
    this.regnum = regnum;
    this.color = color;
    this.maxspeed = maxspeed;
    counter++;
  }

  static public boolean isFaster (Auto a1, Auto a2){
    return (a1.maxspeed > a2.maxspeed);
  }
  
}