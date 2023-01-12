{-# LANGUAGE DeriveFunctor #-}
{-# OPTIONS_GHC -Wincomplete-patterns #-}

import Control.Applicative
import Control.Monad
import Data.Char
import Debug.Trace

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

data Either' a b = Left' a | Right' b | Both a b
  deriving (Eq, Show)

instance Functor (Either' a) where
  fmap f (Left' a)  = Left' a
  fmap f (Right' b) = Right' (f b)
  fmap f (Both a b) = Both a (f b)

instance Foldable (Either' a) where
  foldr f b (Left' a)   = b
  foldr f b (Right' b') = f b' b
  foldr f b (Both a b') = f b' b

  foldMap f (Left' a)  = mempty
  foldMap f (Right' b) = f b
  foldMap f (Both a b) = f b

instance Traversable (Either' a) where
  traverse f (Left' a)  = pure (Left' a)
  traverse f (Right' b) = Right' <$> f b
  traverse f (Both a b) = Both a <$> f b

partition :: [Either' a b] -> ([a], [b], [(a, b)])
partition []         = ([], [], [])
partition (eab:eabs) = case partition eabs of
  (as, bs, abs) -> case eab of
    Left' a  -> (a:as, bs, abs)
    Right' b -> (as, b:bs, abs)
    Both a b -> (as, bs, (a, b):abs)

zipWith' :: (Either' a b -> c) -> [a] -> [b] -> [c]
zipWith' f (a:as) (b:bs) = f (Both a b) : zipWith' f as bs
zipWith' f []     (b:bs) = f (Right' b) : zipWith' f [] bs
zipWith' f (a:as) []     = f (Left' a)  : zipWith' f as []
zipWith' f []     []     = []

mapMaybeLeft :: (a -> Maybe b) -> [Either' a c] -> Maybe [Either' b c]
mapMaybeLeft f = mapM $ \eab -> case eab of
  Left' a  -> Left' <$> f a
  Right' c -> pure (Right' c)
  Both a c -> Both <$> f a <*> pure c

-- Maybe monád nélkül ugyanez:
mapMaybeLeft' :: (a -> Maybe b) -> [Either' a c] -> Maybe [Either' b c]
mapMaybeLeft' f []         = Just []
mapMaybeLeft' f (eab:eabs) = case mapMaybeLeft' f eabs of
  Nothing   -> Nothing
  Just eabs -> case eab of
    Left' a  -> case f a of
                  Nothing -> Nothing
                  Just b  -> Just (Left' b : eabs)
    Right' c -> Just (Right' c:eabs)
    Both a c -> case f a of
                  Nothing -> Nothing
                  Just b  -> Just (Both b c : eabs)

data Tree a = Leaf a | Node (Tree a) (Tree a)
  deriving (Eq, Show)

instance Functor Tree where
  fmap f (Leaf a)   = Leaf (f a)
  fmap f (Node l r) = Node (fmap f l) (fmap f r)

instance Foldable Tree where
  foldr f b (Leaf a)   = f a b
  foldr f b (Node l r) = foldr f (foldr f b r) l

  -- foldMap f (Leaf a)   = f a
  -- foldMap f (Node l r) = foldMap f l <> foldMap f r

instance Traversable Tree where
  traverse f (Leaf a)   = Leaf <$> f a
  traverse f (Node l r) = Node <$> traverse f l <*> traverse f r

treeSums :: Tree Int -> Tree Int
treeSums t = evalState (traverse go t) 0 where
  go :: Int -> State Int Int
  go n = do
    sum <- get
    put (sum + n)
    pure sum

-- traverse nélkül ugyanez:
treeSums' :: Tree Int -> Tree Int
treeSums' t = evalState (go t) 0 where
  go :: Tree Int -> State Int (Tree Int)
  go (Leaf n) = do
    sum <- get
    put (sum + n)
    pure (Leaf sum)
  go (Node l r) =
    Node <$> go l <*> go r


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
  deriving Show

type Program = [Statement]
type Name    = String

data Statement
  = Assign Name Exp         -- x := e
  | While Exp Program       -- while e do p1 end
  | If Exp Program Program  -- if e then p1 else p2 end
  | Block Program           -- {p1}       (lokális scope)

  | LogStr String
  | LogInt Exp
  deriving Show


-- While parser
--------------------------------------------------------------------------------

{-
Parser a While nyelvhez. A szintaxist az Exp és Statement definíciónál látahtó
fenti kommentek összegzik, továbbá:

  - mindenhol lehet whitespace tokenek között
  - a Statement-eket egy Program-ban válassza el ';'
  - Az operátorok erőssége és assszociativitása a következő:
      infixr 2 ||
      infixr 3 &&
      infix  4 ==
      infix  4 <
      infixl 6 +
      infixl 7 *
  - "not" erősebben köt minden operátornál.
  - A kulcsszavak: not, and, while, do, if, end, true, false.
  - A változónevek legyenek betűk olyan nemüres sorozatai, amelyek *nem* kulcsszavak.
    Pl. "while" nem azonosító, viszont "whilefoo" már az!

Példa szintaktikilag helyes programra:

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
keywords = ["not", "and", "while", "do", "if", "end", "true", "false", "LogStr", "LogInt"]

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

pStringLit :: Parser String
pStringLit = char '\"' *> many (satisfy (/='\"')) <* char' '\"'

pAtom :: Parser Exp
pAtom = (BoolLit <$> pBoolLit)
    <|> (IntLit <$> pIntLit)
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
    <|> (LogStr <$> (string' "LogStr" *> pStringLit))
    <|> (LogInt <$> (string' "LogInt" *> pExp))

pSrc :: Parser Program
pSrc = ws *> pProgram <* eof


-- Interpreter
------------------------------------------------------------

{-
Interpreter a While nyelvhez.

Kifejezések:
  - A logikai és artimetikai műveletek kiértékelése értelemszerű. Ha nem
    megfelelő típusú értéket kapunk argumentumokra, dobjunk "error"-al hibát.
  - Az == operátor működik, ha mindkét argumentum Bool, vagy ha mindkét argumentum
    Int, az eredmény mindig Bool.

Változó scope és értékadás kezelése:
  - Új scope-nak számít:
    - minden "while" kifejezés teste
    - minden "if" kifejezés két ága
    - minden új Block (a szintaxisban pl "x := 0; {y := x; x := x}"-nél
      a kapcsos zárójeles utasítássorozat új blokkban van).

  - ha egy új változónak értéket adunk, akkor felvesszük a környezet elejére
  - ha egy meglévő változónak értéket adunk, akkor update-eljük a változó értékét
  - amikor az interpreter végez egy scope kiértékeléséval, eldobja az összes,
    scope-ban újonnan felvett változót a környezetből.
-}

type Val  = Either Int Bool
type Env  = [(Name, Val)]
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

newScope :: Eval a -> Eval a
newScope ma = do
  (env, _) <- get
  a <- ma
  modify (\(env', log) -> (drop (length env' - length env) env', log))
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
    modify (\(env, log) -> (updateEnv x v env, log))
  While e p -> do
    v <- evalExp e
    case v of
      Right b -> if b then newScope (evalProg p) >> evalSt (While e p)
                      else pure ()
      Left _  -> error "type error: expected a Bool condition in \"while\" expression"
  If e p1 p2 -> do
    v <- evalExp e
    case v of
      Right b -> if b then newScope (evalProg p1)
                      else newScope (evalProg p2)
      Left _ -> error "type error: expected a Bool condition in \"if\" expression"
  Block p ->
    newScope (evalProg p)
  LogStr str ->
    modify (\(env, log) -> (env, str:log))
  LogInt e -> do
    v <- evalExp e
    case v of
      Left n  -> modify (\(env, log) -> (env, show n:log))
      Right _ -> error "type error: expected an Int for LogInt"

evalProg :: Program -> Eval ()
evalProg = mapM_ evalSt


-- interpreter
--------------------------------------------------------------------------------

runProg :: String -> (Env, [String])
runProg str = case runParser pSrc str of
  Nothing     -> error "parse error"
  Just (p, _) -> execState (evalProg p) ([], [])

p1 :: String
p1 = "x := 10; y := 20; {z := x + y}; x := x + 100; LogStr \"foobar\"; LogInt (x * x + y)"
-- runProg p1 == ([("y",Left 20),("x",Left 110)],["12120","foobar"])

p2 :: String
p2 =
  "{" ++
  "  x := 0;" ++
  "  while not (x == 10) do" ++
  "    x := x + 1;"++
  "    LogInt x" ++
  "  end" ++
  "}"
-- runProg p2 == ([],["10","9","8","7","6","5","4","3","2","1"])
