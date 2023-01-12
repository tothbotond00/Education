{-# language DeriveFunctor, InstanceSigs #-}
{-# options_ghc -Wincomplete-patterns #-}

import Control.Applicative
import Control.Monad
import Data.Char
import Data.Foldable
import Debug.Trace

-- State monad
--------------------------------------------------------------------------------

newtype State s a = State {runState :: s -> (a, s)} deriving Functor

instance Applicative (State s) where
  pure  = return
  (<*>) = ap

instance Monad (State s) where
  return a = State (\s -> (a, s))
  State f >>= g = State (\s -> case f s of (a, s') -> runState (g a) s')

get :: State s s
get = State (\s -> (s, s))

put :: s -> State s ()
put s = State (\_ -> ((), s))

modify :: (s -> s) -> State s ()
modify f = do {s <- get; put (f s)}

evalState :: State s a -> s -> a
evalState ma = fst . runState ma

execState :: State s a -> s -> s
execState ma = snd . runState ma

--1.rész

data MaybeTree a
  = Node (MaybeTree a) (Maybe a) (MaybeTree a)
  | Leaf a
  deriving (Show)

mt :: MaybeTree Int
mt =
  Node
    (Node
      (Node (Leaf 2) Nothing (Leaf 4))
      (Nothing)
      (Leaf 10)
    )
    (Just 7)
    (Node (Leaf 0) (Just 5) (Leaf 3))

mt' :: MaybeTree (Either Bool Char)
mt' =
  Node
    (Node
      (Leaf $ Right 'a')
      (Nothing)
      (Node (Leaf $ Left True) Nothing (Leaf $ Right 'b')
      )
    )
    (Just $ Right 'c')
    (Node (Leaf $ Left False) Nothing (Leaf $ Left True))

--kész
instance (Eq a) => Eq (MaybeTree a) where
  (==) (Node mt1 (Just a) mt2 ) (Node mt1' (Just a') mt2' ) =  mt1 == mt1' && a == a' && mt2 == mt2'
  (==) (Node mt1 Nothing  mt2 ) (Node mt1' Nothing  mt2' )  =  mt1 == mt1' && mt2 == mt2'
  (==) (Node mt1 _  mt2 ) (Node mt1' _  mt2' )              =  False 
  (==) (Leaf l) (Leaf l')                                   =  l == l'
  (==) _ _ = False 

--kész
instance Functor MaybeTree where
  fmap f (Node mt1 (Just a) mt2 ) = Node (fmap f mt1) (Just (f a)) (fmap f mt2)
  fmap f (Node mt1 Nothing  mt2 ) = Node (fmap f mt1) Nothing  (fmap f mt2)
  fmap f (Leaf l)                 = Leaf (f l) 

--kész
instance Foldable MaybeTree where
  foldr f b ta = foldr f b (foldMap (\a -> [a]) ta)

  foldMap f (Node mt1 (Just a) mt2 ) = foldMap f mt1 <> f a <> foldMap f mt2 
  foldMap f (Node mt1 Nothing  mt2 ) = foldMap f mt1 <> mempty  <> foldMap f mt2 
  foldMap f (Leaf l)                 = f l

--kész
instance Traversable MaybeTree where
  traverse f (Node mt1 (Just a) mt2 ) = Node <$> traverse f mt1 <*> (Just <$> f a) <*> traverse f mt2
  traverse f (Node mt1 Nothing  mt2 ) = Node <$> traverse f mt1 <*> pure Nothing  <*> traverse f mt2
  traverse f (Leaf l)                 = Leaf <$> f l

--kész
mirror :: MaybeTree a -> MaybeTree a
mirror (Node mt1 m mt2 ) = Node (mirror mt2) m (mirror mt1)
mirror (Leaf f)          = Leaf f

--kész
countNothings :: MaybeTree a -> Int
countNothings (Node mt1 (Just a) mt2 )  = countNothings mt1 + countNothings mt2
countNothings (Node mt1 Nothing  mt2 )  = 1 + countNothings mt1 + countNothings mt2
countNothings (Leaf l)                  = 0

--kész
alternateSigns :: MaybeTree Int -> MaybeTree Int
alternateSigns t = evalState (traverse go t) 0 where
  go :: Int -> State Int Int
  go n = do
    num <- get
    put (num + 1) 
    if odd num
        then pure (negate n)
        else pure n

--kész
bools :: MaybeTree (Either Bool Char) -> [Bool]
bools (Node mt1 (Just (Left b)) mt2 )     = [b] ++ bools mt1 ++ bools mt2 
bools (Node mt1 (Just (Right b)) mt2 )    = bools mt1 ++ bools mt2 
bools (Node mt1 Nothing  mt2 )            = bools mt1 ++ bools mt2 
bools (Leaf (Left b) )                    = [b]
bools (Leaf (Right b) )                   = []

--kész
lastChar :: MaybeTree (Either Bool Char) -> Maybe Char
lastChar t = case mirror t of
    (Node mt1 (Just (Right a)) mt2 ) -> case lastChar mt1 of
        Just c  -> Just c
        Nothing -> Just a
    (Node mt1 (Just (Left a)) mt2 ) -> case lastChar mt1 of
        Just c  -> Just c
        Nothing -> case lastChar mt2 of
            Just c -> Just c 
            Nothing -> Nothing
    (Node mt1 Nothing  mt2 ) -> case lastChar mt1 of
        Just c  -> Just c
        Nothing -> case lastChar mt2 of
            Just c -> Just c 
            Nothing -> Nothing
    (Leaf (Right c)) -> Just c
    (Leaf (Left  a)) -> Nothing 

tests1 :: [Bool]
tests1 = [
    Leaf 10 == Leaf 10
  , Leaf 'a' /= Leaf 'b'
  , Node (Leaf 'c') Nothing (Leaf 'd') == Node (Leaf 'c') Nothing (Leaf 'd')
  , Node (Leaf 5) (Just 7) (Leaf 9) /= Node (Leaf 5) Nothing (Leaf 9)
  , Node (Leaf 5) Nothing (Leaf 9) /= Node (Leaf 5) Nothing (Leaf 3)
  , fmap (+10) mt == Node (Node (Node (Leaf 12) Nothing (Leaf 14)) Nothing (Leaf 20)) (Just 17) (Node (Leaf 10) (Just 15) (Leaf 13))
  , fmap (fmap succ) mt' == Node (Node (Leaf (Right 'b')) Nothing (Node (Leaf (Left True)) Nothing (Leaf (Right 'c')))) (Just (Right 'd')) (Node (Leaf (Left False)) Nothing (Leaf (Left True)))
  , sum mt == 31
  , foldr (:) [] mt == [2,4,10,7,0,5,3]
  , foldr (:) [] mt' == [Right 'a',Left True,Right 'b',Right 'c',Left False,Left True]
  , traverse (const (Nothing :: Maybe ())) mt == Nothing
  , evalState (traverse (\x -> modify (+x) >> get) mt) 0 == Node (Node (Node (Leaf 2) Nothing (Leaf 6)) Nothing (Leaf 16)) (Just 23) (Node (Leaf 23) (Just 28) (Leaf 31))
  , mirror mt == Node (Node (Leaf 3) (Just 5) (Leaf 0)) (Just 7) (Node (Leaf 10) (Nothing) (Node (Leaf 4) Nothing (Leaf 2)))
  , mirror mt' == Node (Node (Leaf $ Left True) (Nothing) (Leaf $ Left False)) (Just $ Right 'c') (Node (Node (Leaf $ Right 'b') Nothing (Leaf $ Left True)) Nothing (Leaf $ Right 'a'))
  , countNothings mt == 2
  , countNothings mt' == 3
  , alternateSigns mt == Node (Node (Node (Leaf 2) Nothing (Leaf (-4))) Nothing (Leaf 10)) (Just (-7)) (Node (Leaf 0) (Just (-5)) (Leaf 3))
  , alternateSigns (Node (Leaf 8) (Just 1) (Node (Leaf 9) Nothing (Leaf 6))) == Node (Leaf 8) (Just (-1)) (Node (Leaf 9) Nothing (Leaf (-6)))
  , bools mt' == [True, False, True]
  , bools (Node (Leaf $ Right 'a') Nothing (Leaf $ Right 'b')) == []
  , lastChar mt' == Just 'c'
  , lastChar (Node (Leaf $ Left True) Nothing (Leaf $ Left False)) == Nothing
  ]

--2.rész
-- Parser monad
--------------------------------------------------------------------------------

newtype Parser a = Parser {runParser :: String -> Maybe (a, String)}
  deriving Functor

instance Applicative Parser where
  pure  = return
  (<*>) = ap

instance Monad Parser where

  -- nem dob hibát + nem fogyaszt inputot
  return :: a -> Parser a
  return a = Parser $ \s -> Just (a, s)

  -- egymás után két parsert hívunk
  (>>=) :: Parser a -> (a -> Parser b) -> Parser b
  Parser f >>= g = Parser $ \s -> case f s of
    Nothing      -> Nothing
    Just (a, s') -> runParser (g a) s'

-- parserek közötti választás
instance Alternative Parser where
  empty :: Parser a
  empty = Parser $ \_ -> Nothing

  (<|>) :: Parser a -> Parser a -> Parser a
  Parser f <|> Parser g = Parser $ \s -> case f s of
    Nothing -> g s
    x       -> x

-- üres String olvasása
eof :: Parser ()
eof = Parser $ \s -> case s of
  [] -> Just ((), [])
  _  -> Nothing

-- Char olvasása, amire egy feltétel teljesül
satisfy :: (Char -> Bool) -> Parser Char
satisfy f = Parser $ \s -> case s of
  c:s | f c -> Just (c, s)   -- output String 1-el rövidebb!
  _         -> Nothing

satisfy_ :: (Char -> Bool) -> Parser ()
satisfy_ f = () <$ satisfy f

-- konkrét Char olvasása
char :: Char -> Parser ()
char c = () <$ satisfy (==c)

-- parser hívásakor kiír egy String üzenetet
debug :: String -> Parser a -> Parser a
debug msg pa = Parser $ \s -> trace (msg ++ " : " ++ s) (runParser pa s)

-- bármilyen Char olvasása
anyChar :: Parser Char
anyChar = satisfy (\_ -> True)

-- konkrét String-et próbál olvasni
string :: String -> Parser ()
string = traverse_ char

-- Control.Applicative-ból (iterálás):
-- many :: Parser a -> Parser [a]        -- 0-szor vagy többször olvasás
-- some :: Parser a -> Parser [a]        -- 1-szer vagy többször olvasás

many_ :: Parser a -> Parser ()
many_ pa = some_ pa <|> pure ()

some_ :: Parser a -> Parser ()
some_ pa = pa >> many_ pa

   -- Functor/Applicative operátorok
   --   (<$)       kicserélni parser végeredményét adott értékre
   --   (<$>)      fmap
   --   (<*)       két parser-t futtat, az első értékét visszaadja
   --   (*>)       két parser-t futtat, a második értékét visszaadja

-- whitespace elfogyasztása
ws :: Parser ()
ws = many_ (char ' ')

-- Olvassuk pa-t 0-szor vagy többször, psep-el elválasztva
sepBy :: Parser a -> Parser sep -> Parser [a]
sepBy pa psep = sepBy1 pa psep <|> pure []

-- Olvassuk pa-t 1-szor vagy többször, psep-el elválasztva
sepBy1 :: Parser a -> Parser sep -> Parser [a]
sepBy1 pa psep = (:) <$> pa <*> many (psep *> pa)

-- egy számjegy olvasása
digit :: Parser Int
digit = digitToInt <$> satisfy isDigit

topLevel :: Parser a -> Parser a
topLevel pa = ws *> pa <* eof

intLit' :: Parser Int
intLit' = do
  isNeg <- (True <$ char '-') <|> pure False
  n <- read <$> some (satisfy isDigit)
  ws
  if isNeg then pure (n * (-1))
           else pure n

char' :: Char -> Parser ()
char' c = char c <* ws

string' :: String -> Parser ()
string' str = string str <* ws

-- Min 1 pa olvasás, psep-el elválasztva, "combine" fv-el kombinálva
-- balra asszociáltan
infixLeft :: Parser a -> Parser sep -> (a -> a -> a) -> Parser a
infixLeft pa psep combine = foldl1 combine <$> sepBy1 pa psep

-- Min 1 pa olvasás, psep-el elválasztva, "combine" fv-el kombinálva
-- jobbra asszociáltan
infixRight :: Parser a -> Parser sep -> (a -> a -> a) -> Parser a
infixRight pa psep combine = foldr1 combine <$> sepBy1 pa psep

infixNonAssoc :: Parser a -> Parser sep -> (a -> a -> a) -> Parser a
infixNonAssoc pa psep combine = do
  exps <- sepBy1 pa psep
  case exps of
    [exp]        -> pure exp                  -- 1 db pa kifejezés
    [exp1, exp2] -> pure $ combine exp1 exp2  -- exp1 `psep` exp2
    _            -> empty



-- WHILE nyelv
--------------------------------------------------------------------------------

{-
Precedenciák:
  - literál (bool/int), zárójelezés
  - not
  - *  (jobb asszoc)
  - +  (jobb asszoc)
  - == (nem asszoc)
-}

data Exp
  = IntLit Int
  | BoolLit Bool          -- true vagy false
  | Add Exp Exp           -- e1 + e2
  | Mul Exp Exp           -- e1 * e2
  | Eq Exp Exp            -- e1 == e2
  | Not Exp               -- not e
  | Var String            -- változónév
  --kész
  | ListLit [Exp]         -- lista []
  | Append Exp Exp        -- ++
  | Length Exp            -- length
  | Head Exp              -- head
  | Tail Exp              -- tail
  deriving (Eq, Show)

type Program = [Statement]

data Statement
  = Assign String Exp               --   x := exp
  | IfThenElse Exp Program Program  --   if e1 then p1 else p2 end
  | While Exp Program               --   while e do p end
  deriving (Eq, Show)

--kész
keywords :: [String]
keywords = ["if", "then", "else", "while", "do", "end", "not","length", "tail", "head","++"]

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

-- kifejezések
--------------------------------------------------------------------------------

--ezt sajnos nem tudtam beilleszteni a programba de az interpretert és miegymást megcsináltam
list :: Parser [Exp]
list = char' '[' *> sepBy eqExp (char' ',')  <* char' ']'

atom :: Parser Exp
atom = (IntLit <$> intLit')
   <|> (char' '(' *> eqExp <* char' ')')
   <|> (BoolLit <$> (True  <$ keyword' "true"))
   <|> (BoolLit <$> (False <$ keyword' "false"))
   <|> (Var <$> ident')

notExp :: Parser Exp
notExp = (Not <$> (keyword' "not" *> atom))
--kész
      <|>(Length <$> (pLength *> atom))
      <|>(Tail <$> (pTail  *> atom))
      <|>(Head <$> (pHead  *> atom))
      <|> atom

--kész
appendExp :: Parser Exp
appendExp = infixRight notExp (keyword' "++") Append

mulExp :: Parser Exp
mulExp = infixRight appendExp (char' '*') Mul

addExp :: Parser Exp
addExp = infixRight mulExp (char' '+') Add

eqExp :: Parser Exp
eqExp = infixNonAssoc addExp (string' "==") Eq

-- statement olvasás
--------------------------------------------------------------------------------

pIf     = keyword' "if"
pThen   = keyword' "then"
pElse   = keyword' "else"
pWhile  = keyword' "while"
pDo     = keyword' "do"
pEnd    = keyword' "end"
pAssign = string' ":="
pLength = keyword' "length"
pTail   = keyword' "tail"
pHead   = keyword' "head"

statement :: Parser Statement
statement =
      (Assign <$> (ident' <* pAssign) <*> eqExp)
  <|> (IfThenElse <$> (pIf *> eqExp)
                  <*> (pThen *> program)
                  <*> (pElse *> program <* pEnd))
  <|> (While <$> (pWhile *> eqExp <* pDo)
             <*> (program <* pEnd))

program :: Parser Program
program = sepBy statement (char' ';')

parseProgram :: Parser Program
parseProgram = topLevel program

-- Interpreter
--------------------------------------------------------------------------------

-- értékek
data Val = VInt Int | VBool Bool | VList [Val]
  deriving (Eq, Show)

-- környezet
type Env = [(String, Val)]

evalExp :: Env -> Exp -> Val
evalExp env exp = case exp of
  IntLit n  -> VInt n
  BoolLit b -> VBool b
  Add e1 e2 -> case (evalExp env e1, evalExp env e2) of
    (VInt n1, VInt n2) -> VInt (n1 + n2)
    _                  -> error "type error"
  Mul e1 e2 -> case (evalExp env e1, evalExp env e2) of
    (VInt n1, VInt n2) -> VInt (n1 * n2)
    _                  -> error "type error"
  Eq e1 e2  -> case (evalExp env e1, evalExp env e2) of
    (VInt n1, VInt n2)   -> VBool (n1 == n2)
    (VBool b1, VBool b2) -> VBool (b1 == b2)
    _                    -> error "type error"
  Not e -> case evalExp env e of
    VBool b -> VBool (not b)
    _       -> error "type error"
  Var x -> case lookup x env of
    Nothing -> error $ "name not in scope: " ++ x
    Just v  -> v
--kész
  ListLit x -> VList (fmap (evalExp env) x )
  Append x y ->  case (evalExp env x, evalExp env y) of
      (VList list1, VList list2) -> VList (list1 ++ list2)
      _                          -> error "type error"
  Length x ->  case evalExp env x of
      VList vs     -> VInt (length vs)
      _            -> error "type error"
  Head y ->  case evalExp env y of
      VList vs     -> case vs of
          []       -> error "empty list"
          list     -> head list
      _            -> error "type error"
  Tail y  ->  case evalExp env y of
      VList vs     -> case vs of
          []       -> error "empty list"
          list     -> VList (tail list)
      _            -> error "type error"

updateEnv :: String -> Val -> Env -> Env
updateEnv x v [] = [(x, v)]
updateEnv x v ((x', v'):env)
   | x == x'   = (x, v):env
   | otherwise = (x', v'): updateEnv x v env

evalStatement :: Statement -> State Env ()
evalStatement st = case st of
  Assign x e -> do
    env <- get
    let v = evalExp env e
    put $ updateEnv x v env
  IfThenElse e p1 p2 -> do
    env <- get
    case evalExp env e of
      VBool b -> if b then evalProgram p1
                      else evalProgram p2
      _       -> error "type error"
  While e p -> do
    env <- get
    case evalExp env e of
      VBool b -> if b then evalProgram p >> evalStatement (While e p)
                      else pure ()
      _       -> error "type error"

evalProgram :: Program -> State Env ()
evalProgram = traverse_ evalStatement

run :: String -> Env
run str = case runParser parseProgram str of
  Nothing     -> error "parse error"
  Just (p, _) -> execState (evalProgram p) []


tests2 :: [Bool]
tests2 = [
    runParser parseProgram "xs := tail xs" == Just ([Assign "xs" (Tail (Var "xs"))],"")
  , runParser parseProgram "xs := head xs" == Just ([Assign "xs" (Head (Var "xs"))],"")
  , runParser parseProgram "x := length xs + 10" == Just ([Assign "x" (Add (Length (Var "xs")) (IntLit 10))],"")
  , runParser parseProgram "x := xs ++ ys ++ tail (head zs)" == Just ([Assign "x" (Append (Var "xs") (Append (Var "ys") (Tail (Head (Var "zs")))))],"")
  , runParser parseProgram "xs := [10 + 20, 30]" == Just ([Assign "xs" (ListLit [Add (IntLit 10) (IntLit 20),IntLit 30])],"")
  , runParser parseProgram "xs := [10, ys ++ []]" == Just ([Assign "xs" (ListLit [IntLit 10,Append (Var "ys") (ListLit [])])],"")
  , run "x := [10]; y := head x" == [("x",VList [VInt 10]), ("y",VInt 10)]
  , run "x := [10, 20]; y := tail x" == [("x",VList [VInt 10,VInt 20]), ("y",VList [VInt 20])]
  , run "xs := [0]; ys := [3]; zs := xs ++ ys" == [("xs",VList [VInt 0]), ("ys",VList [VInt 3]), ("zs",VList [VInt 0,VInt 3])]
  , run "xs := [1, 2, 3]; ys := []; while not (length xs == 0) do ys := [head xs] ++ ys; xs := tail xs end" == [("xs",VList []),("ys",VList [VInt 3,VInt 2,VInt 1])]
  ]