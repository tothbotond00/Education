package calc;

import calc.util.SheetException;

public class Num implements Evaluable {
  private int number;

  public Num (int number ){
    if (number < 0){
      throw new IllegalArgumentException();
    }
    this.number = number;
  }

  public int eval (Sheet s) throws SheetException {
    return number;
  }

}