package calc;

import calc.util.SheetException;

interface Evaluable {
  int eval(Sheet s) throws SheetException; 
}