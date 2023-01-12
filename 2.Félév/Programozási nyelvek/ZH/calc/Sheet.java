package calc;

import calc.util.CellName;
import calc.util.SheetException;

public class Sheet {
  private int numOfRows;
  private int numOfCols;
  private Evaluable [][] sheet;

  public Sheet (int numOfRows, int numOfCols){
    CellName c = new CellName();
    if (numOfRows < 0 || numOfCols < 0 || numOfCols > c.ABC().length()){
      throw new IllegalArgumentException();
    }
    this.numOfRows = numOfRows;
    this.numOfCols = numOfCols;
  }

  public void insertToSheet(String cellName, Evaluable ev)throws SheetException{
    CellName c = new CellName();
    sheet[c.getRowIndexFromCellName(cellName)][c.getColIndexFromCellName(cellName)] = ev;
  }

  public Evaluable getFromSheet(String cellName) throws SheetException{
    CellName c = new CellName();
    return sheet[c.getRowIndexFromCellName(cellName)][c.getColIndexFromCellName(cellName)];
  }

  public static int constructIntFromOperandStr(String operandStr, Sheet s) throws SheetException {
    CellName c = new CellName();
    if (c.ABC().contains(operandStr.substring(0,1) )  ){
      return s.getFromSheet(operandStr).eval(s);
    }
    else {
      return Integer.parseInt(operandStr);
    }
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < numOfRows; i++){
      for (int j = 0; j <numOfCols; j++){
        try {
          if (sheet[i][j] == null){
            sb.append("null");
          }
         else{
           sb.append(sheet[i][j].eval(this) );
          } 
          if (j != numOfCols-1) sb.append(" ");
        }
        catch (Exception e){}
        }
        if (i != numOfRows-1) sb.append(System.lineSeparator());
      }
      return sb.toString();
    }
  
}