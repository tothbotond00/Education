{-# LANGUAGE DeriveFunctor, DeriveFoldable, DeriveTraversable #-}
{-# OPTIONS_GHC -Wincomplete-patterns #-}

--------------------------------------------------------------------------------
-- 1 : 0-9
-- 2 : 10-11
-- 3 : 12-14
-- 4 : 15-16
-- 5 : 17-20
--------------------------------------------------------------------------------

import Control.Applicative
import Control.Monad
import Data.Char

-- State monad
--------------------------------------------------------------------------------

newtype State s a = State {runState :: s -> (a, s)}
  deriving Functor

instance Applicative (State s) where
  pure  = return
  (<*>) = ap

instance Monad (State s) where
  return a = State $ \s -> (a, s)
  State f >>= g = State $ \s -> case f s of
    (a, s') -> runState (g a) s'

get :: State s s
get = State $ \s -> (s, s)

put :: s -> State s ()
put s = State $ \_ -> ((), s)

modify :: (s -> s) -> State s ()
modify f = get >>= \s -> put (f s)

evalState :: State s a -> s -> a
evalState ma = fst . runState ma

execState :: State s a -> s -> s
execState ma = snd . runState ma

--------------------------------------------------------------------------------
--                              Feladatok
--------------------------------------------------------------------------------

data Tree a = Leaf1 a | Leaf2 a a | Node (Tree a) (Maybe (Tree a))
  deriving (Eq, Ord, Show, Functor, Foldable, Traversable)

ex1 :: Tree Int
ex1 =
  Node
    (Leaf2 2 1)
    (Just (Node
      (Leaf1 10)
      (Just (Node
         (Leaf2 5 6)
         Nothing))))

-- 1
-- instance Functor Tree where

-- 2
-- instance Foldable Tree where

-- 1
leftmost :: Tree a -> a
leftmost = head . foldr (:) []

-- 1
findInLeaf2 :: (a -> Bool) -> Tree a -> Maybe a
findInLeaf2 f (Leaf1 _) = Nothing
findInLeaf2 f (Leaf2 x y)
 | f x       = Just x
 | f y       = Just y
 | otherwise = Nothing
findInLeaf2 f (Node l r) =
  findInLeaf2 f l <|> join (findInLeaf2 f <$> r)

-- 1
-- instance Traversable Tree where

-- 2
countLeaf1s :: Tree a -> Tree (a, Int)
countLeaf1s t = evalState (go t) 0 where
  goElem a = do
    n <- get
    put (n + 1)
    pure (a, n)

  go (Leaf1 x) = Leaf1 <$> goElem x
  go (Leaf2 x y) = do
    n <- get
    pure (Leaf2 (x, n) (y, n))
  go (Node l r) = Node <$> go l <*> traverse go r

-- 3
replaceLeaf2s :: [a] -> Tree a -> Tree a
replaceLeaf2s as t = evalState (go t) as where

  goElem a = do
    as <- get
    case as of
      []    -> pure a
      a':as -> put as >> pure a'

  go (Leaf1 x) = pure (Leaf1 x)
  go (Leaf2 x y) = Leaf2 <$> goElem x <*> goElem y
  go (Node l r) = Node <$> go l <*> traverse go r

--------------------------------------------------------------------------------
--                  While nyelv parser + interpreter
--------------------------------------------------------------------------------

newtype Parser a = Parser {runParser :: String -> Maybe (a, String)}
  deriving Functor

instance Applicative Parser where
  pure = return
  (<*>) = ap

instance Monad Parser where
  return a = Parser $ \s -> Just (a, s)
  Parser f >>= g = Parser $ \s ->
    case f s of
      Nothing     -> Nothing
      Just(a, s') -> runParser (g a) s'

instance Alternative Parser where
  empty = Parser $ \_ -> Nothing
  (<|>) (Parser f) (Parser g) = Parser $ \s -> case f s of
    Nothing -> g s
    x       -> x

satisfy :: (Char -> Bool) -> Parser Char
satisfy f = Parser $ \s -> case s of
  c:cs | f c -> Just (c, cs)
  _          -> Nothing

eof :: Parser ()
eof = Parser $ \s -> case s of
  [] -> Just ((), [])
  _  -> Nothing

char :: Char -> Parser ()
char c = () <$ satisfy (==c)

string :: String -> Parser ()
string = mapM_ char

ws :: Parser ()
ws = () <$ many (char ' ' <|> char '\n')

sepBy1 :: Parser a -> Parser b -> Parser [a]
sepBy1 pa pb = (:) <$> pa <*> many (pb *> pa)

sepBy :: Parser a -> Parser b -> Parser [a]
sepBy pa pb = sepBy1 pa pb <|> pure []

anyChar :: Parser Char
anyChar = satisfy (const True)

-- While nyelv
------------------------------------------------------------

data Exp
  = Add Exp Exp    -- a + b
  | Mul Exp Exp    -- a * b
  | Var Name       -- x
  | IntLit Int
  | BoolLit Bool   -- true|false
  | Not Exp        -- not e
  | And Exp Exp    -- a && b
  | Or Exp Exp     -- a || b
  | Eq Exp Exp     -- a == b
  | Lt Exp Exp     -- a < b

  -- 1
  | ReadInt
  | ReadBool
  deriving Show

type Program = [Statement]
type Name    = String

data Statement
  = Assign Name Exp         -- x := e
  | While Exp Program       -- while e do p1 end
  | If Exp Program Program  -- if e then p1 else p2 end
  | Block Program           -- {p1}       (lok??lis scope)

  deriving Show


-- While parser
--------------------------------------------------------------------------------

{-
Parser a While nyelvhez. A szintaxist az Exp ??s Statement defin??ci??n??l l??taht??
fenti kommentek ??sszegzik, tov??bb??:

  - mindenhol lehet whitespace tokenek k??z??tt
  - a Statement-eket egy Program-ban v??lassza el ';'
  - Az oper??torok er??ss??ge ??s assszociativit??sa a k??vetkez??:
      infixr 2 ||
      infixr 3 &&
      infix  4 ==
      infix  4 <
      infixl 6 +
      infixl 7 *
  - "not" er??sebben k??t minden oper??torn??l.
  - A kulcsszavak: not, and, while, do, if, end, true, false.
  - A v??ltoz??nevek legyenek bet??k olyan nem??res sorozatai, amelyek *nem* kulcsszavak.
    Pl. "while" nem azonos??t??, viszont "whilefoo" m??r az!

P??lda szintaktikilag helyes programra:

  x := 10;
  y := x * x + 10;
  while (x == 0) do
    x := x + 1;
    b := true && false || not true
  end;
  z := x
-}

char' :: Char -> Parser ()
char' c = char c <* ws

string' :: String -> Parser ()
string' s = string s <* ws

keywords :: [String]
keywords = [
  "not", "and", "while", "do", "if", "end", "true", "false",
  -- 1
  "readInt", "readBool"]

pIdent :: Parser String
pIdent = do
  x <- some (satisfy isLetter) <* ws
  if elem x keywords
    then empty
    else pure x

pBoolLit :: Parser Bool
pBoolLit = (True  <$ string' "true")
       <|> (False <$ string' "false")

pIntLit :: Parser Int
pIntLit = read <$> (some (satisfy isDigit) <* ws)

pAtom :: Parser Exp
pAtom = (BoolLit <$> pBoolLit)
    <|> (IntLit <$> pIntLit)

    -- 1
    <|> (ReadInt <$ string' "readInt")
    <|> (ReadBool <$ string' "readBool")

    <|> (Var <$> pIdent)
    <|> (char' '(' *> pExp <* char' ')')

pNot :: Parser Exp
pNot =
      (Not <$> (string' "not" *> pAtom))
  <|> pAtom

pMul :: Parser Exp
pMul = foldl1 Mul <$> sepBy1 pNot (char' '*')

pAdd :: Parser Exp
pAdd = foldl1 Add <$> sepBy1 pMul (char' '+')

pEqOrLt :: Parser Exp
pEqOrLt =
  pAdd >>= \e ->
        (Eq e <$> (string' "==" *> pAdd))
    <|> (Lt e <$> (string' "<"  *> pAdd))
    <|> pure e

pAnd :: Parser Exp
pAnd = foldr1 And <$> sepBy1 pEqOrLt (string' "&&")

pOr :: Parser Exp
pOr = foldr1 Or <$> sepBy1 pAnd (string' "||")

pExp :: Parser Exp
pExp = pOr

pProgram :: Parser Program
pProgram = sepBy pStatement (char' ';')

pStatement :: Parser Statement
pStatement =
        (Assign <$> pIdent <*> (string' ":=" *> pExp))
    <|> (While <$> (string' "while" *> pExp)
               <*> (string' "do" *> pProgram <* string' "end"))
    <|> (If <$> (string' "if"   *> pExp)
            <*> (string' "then" *> pProgram)
            <*> (string' "else" *> pProgram <* string' "end"))
    <|> (Block <$> (char' '{' *> pProgram <* char' '}'))

pSrc :: Parser Program
pSrc = ws *> pProgram <* eof


-- Interpreter
------------------------------------------------------------

{-
Interpreter a While nyelvhez.

Kifejez??sek:
  - A logikai ??s artimetikai m??veletek ki??rt??kel??se ??rtelemszer??. Ha nem
    megfelel?? t??pus?? ??rt??ket kapunk argumentumokra, dobjunk "error"-al hib??t.
  - Az == oper??tor m??k??dik, ha mindk??t argumentum Bool, vagy ha mindk??t argumentum
    Int, az eredm??ny mindig Bool.

V??ltoz?? scope ??s ??rt??kad??s kezel??se:
  - ??j scope-nak sz??m??t:
    - minden "while" kifejez??s teste
    - minden "if" kifejez??s k??t ??ga
    - minden ??j Block (a szintaxisban pl "x := 0; {y := x; x := x}"-n??l
      a kapcsos z??r??jeles utas??t??ssorozat ??j blokkban van).

  - ha egy ??j v??ltoz??nak ??rt??ket adunk, akkor felvessz??k a k??rnyezet elej??re
  - ha egy megl??v?? v??ltoz??nak ??rt??ket adunk, akkor update-elj??k a v??ltoz?? ??rt??k??t
  - amikor az interpreter v??gez egy scope ki??rt??kel??s??val, eldobja az ??sszes,
    scope-ban ??jonnan felvett v??ltoz??t a k??rnyezetb??l.
-}

type Val  = Either Int Bool
type Env  = [(Name, Val)]

-- 1 (+ adjusting elsewhere)
type Eval = State (Env, [String])

binOp :: String
      -> Exp
      -> Exp
      -> Either (Int -> Int -> Int) (Bool -> Bool -> Bool)
      -> Eval Val
binOp opName e1 e2 f = do
  v1 <- evalExp e1
  v2 <- evalExp e2
  case (f, v1, v2) of
    (Left f , Left n1 , Left n2 ) -> pure (Left  (f n1 n2))
    (Right f, Right b1, Right b2) -> pure (Right (f b1 b2))
    _                             -> error ("type error in " ++ opName ++ " argument")

evalExp :: Exp -> Eval Val
evalExp e = case e of
  Add e1 e2 -> binOp "+"  e1 e2 (Left (+))
  Mul e1 e2 -> binOp "*"  e1 e2 (Left (*))
  And e1 e2 -> binOp "&&" e1 e2 (Right (&&))
  Or  e1 e2 -> binOp "||" e1 e2 (Right (||))
  Eq  e1 e2 -> do
    v1 <- evalExp e1
    v2 <- evalExp e2
    case (v1, v2) of
      (Left  n1, Left  n2) -> pure (Right (n1 == n2))
      (Right b1, Right b2) -> pure (Right (b1 == b2))
      _                    -> error "type error in == arguments"
  Lt  e1 e2 -> do
    v1 <- evalExp e1
    v2 <- evalExp e2
    case (v1, v2) of
      (Left  n1, Left  n2) -> pure (Right (n1 < n2))
      _                    -> error "type error in < arguments"
  Var x -> do
    (env, _) <- get
    case lookup x env of
      Nothing -> error ("variable not in scope: " ++ x)
      Just v  -> pure v
  IntLit n -> pure (Left n)
  BoolLit b -> pure (Right b)
  Not e -> do
    v <- evalExp e
    case v of
      Right b -> pure (Right (not b))
      _       -> error "type error in \"not\" argument"

  -- 2
  ReadInt -> do
    (env, inp) <- get
    s <- case inp of
      []    -> error "empty input"
      s:inp -> put (env, inp) >> pure s
    case runParser (pIntLit <* eof) s of
      Nothing     -> error "readInt: parse error"
      Just (n, _) -> pure (Left n)
  ReadBool -> do
    (env, inp) <- get
    s <- case inp of
      []    -> error "empty input"
      s:inp -> put (env, inp) >> pure s
    case runParser (pBoolLit <* eof) s of
      Nothing     -> error "readBool: parse error"
      Just (b, _) -> pure (Right b)

newScope :: Eval a -> Eval a
newScope ma = do
  (env, _) <- get
  a <- ma
  modify (\(env', inp) -> (drop (length env' - length env) env', inp))
  pure a

updateEnv :: Name -> Val -> Env -> Env
updateEnv x v env =
  case go env of
    Nothing   -> (x, v):env
    Just env' -> env'
  where
    go :: Env -> Maybe Env
    go [] = Nothing
    go ((x', v'):env)
      | x == x'   = Just ((x, v):env)
      | otherwise = ((x', v'):) <$> go env

evalSt :: Statement -> Eval ()
evalSt s = case s of
  Assign x e -> do
    v <- evalExp e
    modify (\(env, inp) -> (updateEnv x v env, inp))
  While e p -> do
    v <- evalExp e
    case v of
      Right b -> if b then newScope (evalProg p) >> evalSt (While e p)
                      else pure ()
      _       -> error "type error: expected a Bool condition in \"while\" expression"
  If e p1 p2 -> do
    v <- evalExp e
    case v of
      Right b -> if b then newScope (evalProg p1)
                      else newScope (evalProg p2)
      _       -> error "type error: expected a Bool condition in \"if\" expression"
  Block p ->
    newScope (evalProg p)

evalProg :: Program -> Eval ()
evalProg = mapM_ evalSt


-- interpreter
--------------------------------------------------------------------------------

runProg :: String -> [String] -> (Env, [String])
runProg str inp = case runParser pSrc str of
  Nothing     -> error "parse error"
  Just (p, _) -> execState (evalProg p) ([], inp)

p1 :: String
p1 = "x := 10; y := 20; {z := x + y}; x := x + 100"
