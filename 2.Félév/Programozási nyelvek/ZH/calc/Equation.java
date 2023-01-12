package calc;

import calc.util.SheetException;
import calc.util.CellName;
import calc.Sheet;

public class Equation implements Evaluable {
  private String operand1;
  private String operand2;
  private Character operator;

  public Equation (String s){
    boolean bad = true;
    int j = 0;
    for (int i = 0; i < s.length() ; i++){
      if (s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) =='*' || s.charAt(i)=='/'){
        operator = s.charAt(i);
        j = i;
        bad = false;
        break;
      }
    }
    if (bad) throw new IllegalArgumentException();
    String o1 =  s.substring(0,j); String o2 = s.substring(j+1,s.length());
    CellName c = new CellName();
    if (c.isCellNameValid(o1) && c.isCellNameValid(o2)){
      operand1 = o1; operand2 = o2;
    } else{
      throw new IllegalArgumentException();
    }
  }


  public int eval (Sheet s) throws SheetException {
    if (Sheet.constructIntFromOperandStr(operand1,s) == 0){
      throw new ArithmeticException();
    }
    switch(operator){
      case '+': return Sheet.constructIntFromOperandStr(operand1,s) + Sheet.constructIntFromOperandStr(operand2,s);
      case '-': if ( ( Sheet.constructIntFromOperandStr(operand1,s) - Sheet.constructIntFromOperandStr(operand2,s) )< 0) throw new ArithmeticException();
                else  return Sheet.constructIntFromOperandStr(operand1,s) - Sheet.constructIntFromOperandStr(operand2,s);
      case '*' : return Sheet.constructIntFromOperandStr(operand1,s) * Sheet.constructIntFromOperandStr(operand2,s);
      case '/' : return Sheet.constructIntFromOperandStr(operand1,s) - Sheet.constructIntFromOperandStr(operand2,s);
    }
    return 0;
  }

}