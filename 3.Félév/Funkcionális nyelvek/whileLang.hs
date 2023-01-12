{-# LANGUAGE DeriveFunctor, InstanceSigs #-}
{-# options_ghc -Wincomplete-patterns #-}

import Data.Foldable
import Data.Traversable
import Control.Monad
import Control.Applicative
import Data.Char
import Debug.Trace

--------------------------------------------------------------------------------

newtype Parser a = Parser {runParser :: String -> Maybe (a, String)}
  deriving Functor

instance Applicative Parser where
  pure  = return
  (<*>) = ap

instance Monad Parser where
  return :: a -> Parser a
  return a = Parser $ \s -> Just (a, s)

  (>>=) :: Parser a -> (a -> Parser b) -> Parser b
  Parser f >>= g = Parser $ \s -> case f s of
    Nothing      -> Nothing
    Just (a, s') -> runParser (g a) s'

instance Alternative Parser where
  empty :: Parser a
  empty = Parser $ \_ -> Nothing

  (<|>) :: Parser a -> Parser a -> Parser a
  Parser f <|> Parser g = Parser $ \s -> case f s of
    Nothing -> g s
    x       -> x

eof :: Parser ()
eof = Parser $ \s -> case s of
  [] -> Just ((), [])
  _  -> Nothing

satisfy :: (Char -> Bool) -> Parser Char
satisfy f = Parser $ \s -> case s of
  c:s | f c -> Just (c, s)
  _         -> Nothing

satisfy_ :: (Char -> Bool) -> Parser ()
satisfy_ f = () <$ satisfy f

char :: Char -> Parser ()
char c = () <$ satisfy (==c)

debug :: String -> Parser a -> Parser a
debug msg pa = Parser $ \s -> trace (msg ++ " : " ++ s) (runParser pa s)

anyChar :: Parser Char
anyChar = satisfy (\_ -> True)

string :: String -> Parser ()
string = traverse_ char

many_ :: Parser a -> Parser ()
many_ pa = some_ pa <|> pure ()

some_ :: Parser a -> Parser ()
some_ pa = pa >> many_ pa

ws = many_ (satisfy isSpace)

sepBy :: Parser a -> Parser sep -> Parser [a]
sepBy pa psep = sepBy1 pa psep <|> pure []

sepBy1 :: Parser a -> Parser sep -> Parser [a]
sepBy1 pa psep = (:) <$> pa <*> many (psep *> pa)

digit :: Parser Int
digit = digitToInt <$> satisfy isDigit

topLevel :: Parser a -> Parser a
topLevel pa = ws *> pa <* eof

infixLeft :: Parser a -> Parser sep -> (a -> a -> a) -> Parser a
infixLeft pa psep combine = foldl1 combine <$> sepBy1 pa psep

infixRight :: Parser a -> Parser sep -> (a -> a -> a) -> Parser a
infixRight pa psep combine = foldr1 combine <$> sepBy1 pa psep

infixNonAssoc :: Parser a -> Parser sep -> (a -> a -> a) -> Parser a
infixNonAssoc pa psep combine = do
  exps <- sepBy1 pa psep
  case exps of
    [exp]        -> pure exp                  -- 1 db pa kifejezés
    [exp1, exp2] -> pure $ combine exp1 exp2  -- exp1 `psep` exp2
    _            -> empty

--------------------------------------------------------------------------------

{- Precedence:
  - literals, parentheses
  - *  (right associative)
  - +  (right associative)
  - == (non associative)
  - if e1 then e2 else e3  (prefix)
-}

data Exp
  = IntLit Int
  | BoolLit Bool
  | Not Exp        
  | Add Exp Exp
  | Mul Exp Exp
  | Eq Exp Exp
  | Var String
  | Pair Exp Exp
  | Fst Exp
  | Snd Exp
  deriving (Eq, Show)

type Program = [Statement]

data Statement
  = Assign String Exp
  | IfThenElse Exp Program Program
  | While Exp Program
  | ForFromTo String Exp Exp Program
  deriving (Eq, Show)

char' c = char c <* ws
string' s = string s <* ws

posInt' :: Parser Int
posInt' = read <$> (some (satisfy isDigit) <* ws)

keywords :: [String]
keywords = [ "if", "then", "else", "while", "do", "end", "not", "fst", "snd",
             "for","from","to"
           ]

keyword' :: String -> Parser ()
keyword' str = do
  string str
  x <- many (satisfy isLetter) <* ws
  case x of
    "" -> pure ()
    _  -> empty

ident' :: Parser String
ident' = do
  x <- some (satisfy isLetter) <* ws
  if elem x keywords then empty
                     else pure x

--------------------------------------------------------------------------------

parensOrPair :: Parser Exp
parensOrPair = do
  char' '('
  es <- sepBy1 eqExp (char' ',')
  char' ')'
  case es of
    []  -> empty
    [e] -> pure e
    es  -> pure $ foldr1 Pair es

atom :: Parser Exp
atom = (IntLit <$> posInt')
   <|> parensOrPair
   <|> (BoolLit <$> (True  <$ keyword' "true"))
   <|> (BoolLit <$> (False <$ keyword' "false"))
   <|> (Var <$> ident')

primFunExp :: Parser Exp
primFunExp =
      (Not <$> (keyword' "not" *> atom))
  <|> (Fst <$> (keyword' "fst" *> atom))
  <|> (Snd <$> (keyword' "snd" *> atom))
  <|> atom

mulExp :: Parser Exp
mulExp = infixRight primFunExp (char' '*') Mul

addExp :: Parser Exp
addExp = infixRight mulExp (char' '+') Add

eqExp :: Parser Exp
eqExp = infixNonAssoc addExp (string' "==") Eq

--------------------------------------------------------------------------------

pIf     = keyword' "if"
pThen   = keyword' "then"
pElse   = keyword' "else"
pWhile  = keyword' "while"
pDo     = keyword' "do"
pEnd    = keyword' "end"
pAssign = string' ":="
pFor = keyword' "for"
pFrom = keyword' "from"
pTo = keyword' "to"

--ForFromTo String Exp Exp Program

statement :: Parser Statement
statement =
      (Assign <$> (ident' <* pAssign) <*> eqExp)
  <|> (IfThenElse <$> (pIf *> eqExp)
                  <*> (pThen *> program)
                  <*> (pElse *> program <* pEnd))
  <|> (While <$> (pWhile *> eqExp <* pDo)
             <*> (program <* pEnd))
  <|> (ForFromTo <$> (pFor *> ident' <* pFrom )
             <*> (eqExp <* pTo)
             <*> (eqExp <* pDo)
             <*> (program <* pEnd))

program :: Parser Program
program = sepBy statement (char' ';')

parseProgram :: Parser Program
parseProgram = topLevel program

p1 :: String
p1 = unlines [
  "x := 100;",
  "y := 1000;",
  "while x == 0 do",
    "y := y + 1",
  "end"
  ]

p2 :: String
p2 = unlines [
  "i := 0;",
  "sum := 0;",
  "while not (i == 100) do",
  "  sum := sum + i;",
  "  i := i + 1",
  "end"
  ]

--------------------------------------------------------------------------------

-- Small test 10:

-- Above is the parser from ea/Notes10.hs

-- Extend the parser with a new statement
--   `for x from a to b do  p  end`

-- where `x` is an identifier,
--       `a` and `b` are expressions,
--       `p` is a program.

-- The corresponding constructor of `Statement` is:
--    `ForFromTo String Exp Exp Program` (see line 126)

p3 :: String
p3 = unlines [
  "n := 10;",
  "sum := 0;",
  "for i from n to n * n do",
  "  sum := sum + i",
  "end"
  ]

p4 :: String
p4 = unlines [
  "n := 10;",
  "m := 10;",
  "sum := 0;",
  "for i from 0 to n do",
  "  for j from 0 to n do",
  "    sum := sum + i * j",
  "  end",
  "end"
  ]

-- The new keywords shouldn't be valid identifiers.
-- The following program should not be accepted:
badp1 :: String
badp1 = unlines [
  "for := 1;",
  "from := 1;",
  "to := 1"
  ]

tests :: [Bool]
tests = [ runParser parseProgram p1
          == Just ([Assign "x" (IntLit 100),Assign "y" (IntLit 1000),While (Eq (Var "x") (IntLit 0)) [Assign "y" (Add (Var "y") (IntLit 1))]],"")
        , runParser parseProgram p2
          == Just ([Assign "i" (IntLit 0),Assign "sum" (IntLit 0),While (Not (Eq (Var "i") (IntLit 100))) [Assign "sum" (Add (Var "sum") (Var "i")),Assign "i" (Add (Var "i") (IntLit 1))]],"")
        , runParser parseProgram p3
          == Just ([Assign "n" (IntLit 10),Assign "sum" (IntLit 0),ForFromTo "i" (Var "n") (Mul (Var "n") (Var "n")) [Assign "sum" (Add (Var "sum") (Var "i"))]],"")
        , runParser parseProgram p4
          == Just ([Assign "n" (IntLit 10),Assign "m" (IntLit 10),Assign "sum" (IntLit 0),ForFromTo "i" (IntLit 0) (Var "n") [ForFromTo "j" (IntLit 0) (Var "n") [Assign "sum" (Add (Var "sum") (Mul (Var "i") (Var "j")))]]],"")
        , runParser parseProgram badp1 == Nothing
        ]