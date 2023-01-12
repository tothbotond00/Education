package tests;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import calc.util.CellName;
import calc.util.SheetException;
import calc.Num;
import calc.Equation;

public class Tests{
  @Test
  public void isCellNameValidTest(){
    CellName c = new CellName();
    assertEquals(false, c.isCellNameValid("A 120"));
    assertEquals(false, c.isCellNameValid("a120"));
    assertEquals(false, c.isCellNameValid("A+"));
    assertEquals(true, c.isCellNameValid("A9"));
    assertEquals(true, c.isCellNameValid("A120"));
  }

  @Test
  public void getRowIndexFromCellNameTest() throws SheetException{
    CellName c = new CellName();
    assertEquals(8,c.getRowIndexFromCellName("A8"));
    assertEquals(120,c.getRowIndexFromCellName("A120"));
  }

  @Test
  public void getColIndexFromCellNameTest() throws SheetException{
    CellName c = new CellName();
    assertEquals(0,c.getColIndexFromCellName("A8"));
    assertEquals(3,c.getColIndexFromCellName("D120"));
  }

  @Test
  public void NumTest() throws SheetException{
    Num number = new Num(8);
    assertEquals(8,number.eval(null));
  }

  @Test(expected = IllegalArgumentException.class)
  public void EquationTest1() throws SheetException {
    Equation eq = new Equation ("A1+ A3");
  }

  @Test(expected = IllegalArgumentException.class)
  public void EquationTest2() throws SheetException {
    Equation eq = new Equation ("A1+a3");
  }

  @Test(expected = IllegalArgumentException.class)
  public void EquationTest3() throws SheetException {
    Equation eq = new Equation ("A1++A3");
  }

  ///maradék junit tesztre kifutottam az időböl

}