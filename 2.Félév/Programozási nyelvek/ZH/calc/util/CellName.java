package calc.util;

import calc.util.SheetException;

public class CellName {
  protected final String colIndexes = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  public String ABC() { return colIndexes; }

  public boolean isCellNameValid(String cellName){
    try {
    if (cellName.contains(" ")) return false;
    String a = cellName.substring(0,1);
    if (colIndexes.contains(a) && (Integer.parseInt(cellName.substring(1,cellName.length())) >= 0) ){
      return true;
    }
    return false;
    }
    catch (NumberFormatException e){
      return false;
    }
  }

  public int getRowIndexFromCellName(String cellName) throws SheetException{
    if (isCellNameValid(cellName)){
      return Integer.parseInt(cellName.substring(1,cellName.length()));
    }else {
      throw new SheetException("Nem megfelelő cellanév!");
    }
  }

  public int getColIndexFromCellName(String cellName) throws SheetException{
    if (isCellNameValid(cellName)){
      String a = cellName.substring(0, 1);
      return colIndexes.indexOf(a);
    }else {
      throw new SheetException("Nem megfelelő cellanév!");
    }
  }
}