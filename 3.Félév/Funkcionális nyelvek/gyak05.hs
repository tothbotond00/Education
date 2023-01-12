{-# OPTIONS -Wincomplete-patterns #-}
{-# LANGUAGE InstanceSigs, DeriveFunctor #-}
module Tut05 where
import Prelude hiding (mapM)
import Distribution.Simple.Utils (xargs)

-- Evaluation of expressions
data IntExpr = Value Int
             | Plus  IntExpr IntExpr
             | Div   IntExpr IntExpr
             deriving (Show, Eq, Ord)

expr1 :: IntExpr
expr1 = Value 10 `Plus` Value 5 -- (10 + 5)

expr2 :: IntExpr
expr2 = Value 10 `Div` Value 5 -- (10 `div` 5)

expr3 :: IntExpr
expr3 = Value 10 `Div` Value 0 -- (10 `div` 0)

expr4 :: IntExpr
expr4 = Div (Value 1) (Plus (Value 10) (Value (-10))) -- 1 / (10 - 10)

-- Define an evaluation function `evalIntExpr :: IntExpr -> Int`
-- Examples: 
--   evalIntExpr expr1 == 15
--   evalIntExpr expr2 == 2
--   evalIntExpr expr3 == ???
--   evalIntExpr expr4 == ???

evalIntExpr :: IntExpr -> Int
evalIntExpr (Value x) = x
evalIntExpr (Plus x y) = evalIntExpr x + evalIntExpr y
evalIntExpr (Div x y) = evalIntExpr x `div` evalIntExpr y

-- Define a "safe" evaluation function `evalIntExprMaybe :: IntExpr -> Maybe Int`
-- Examples: 
--   evalIntExprMaybe expr1 == Just 15
--   evalIntExprMaybe expr2 == Just 2
--   evalIntExprMaybe expr3 == Nothing
--   evalIntExprMaybe expr4 == Nothing

-- Hint: first define
safeDiv :: Maybe Int -> Maybe Int -> Maybe Int
safeDiv (Just x) (Just y) 
    |y == 0 = Nothing
    |otherwise = Just (x `div` y)
safeDiv _ _ = Nothing 

add'::Maybe Int ->Maybe Int -> Maybe Int
add' (Just x) (Just y) = Just (x+y)
add' _ _ = Nothing

evalIntExprMaybe :: IntExpr -> Maybe Int
evalIntExprMaybe (Value x) = Just x
evalIntExprMaybe (Plus x y) = do add' mx my 
    where mx = evalIntExprMaybe x 
          my = evalIntExprMaybe y
evalIntExprMaybe (Div x y) = do safeDiv mx my
    where mx = evalIntExprMaybe x 
          my = evalIntExprMaybe y   

-- General monadic operations (in Control.Monad)

liftM :: Monad m => (a -> b) -> m a -> m b
liftM f x = do fmap f x 

liftM2 :: Monad m => (a -> b -> c) -> m a -> m b -> m c
liftM2 f x y = do
    x' <- x
    y' <- y
    return (f x' y')

liftM3 :: Monad m => (a -> b -> c -> d) -> m a -> m b -> m c -> m d
liftM3 f x y z = do
    x' <- x
    y' <- y
    z' <- z
    return (f x' y' z')


mapM :: Monad m => (a -> m b) -> [a] -> m [b]
mapM f []    = return []
mapM f (x:xs) = do
    x' <- f x
    xs' <- mapM f xs
    return (x': xs')

-- Other monadic functions:

f2 :: Monad m => m a -> m b -> m (a, b)
f2 x y = do
    x' <- x
    y' <- y
    return (x',y')

f3 :: Monad m => m (m a) -> m a
f3 x = do
    x' <- x
    x'' <- x'
    return x'' 

f4 :: Monad m => m (a -> b) -> m a -> m b
f4 x y = do
    y' <- y 
    x' <- x 
    return (x' y')  

f5 :: Monad m => (a -> m b) -> m a -> m b
f5 x y = do
    y' <- y
    s' <- x y'
    return s'

f8 :: Monad m => (a -> m b) -> (b -> m c) -> a -> m c
f8 f g x = do 
    s' <- f x
    t' <- g s' 
    return t' 

-- forever ma  in Haskell   ~    while(true) { ma } 
forever :: Monad m => m a -> m b
forever ma = do
  ma
  forever ma

-- whileM cond ma        ~    while(cond) { ma }
whileM :: Monad m => m Bool -> m a -> m [a]
whileM cond ma = undefined 

-- forM xs f       ~    for(x in xs) { f x }
forM :: Monad m => [a] -> (a -> m b) -> m [b]
forM = flip mapM

foldr' :: (a -> b -> b) -> b -> [a] -> b
foldr' f e [] = e
foldr' f e (x:xs) = f x (foldr' f e xs)

foldrM :: Monad m => (a -> b -> m b) -> b -> [a] -> m b
foldrM = undefined

---QUIZ

data Expr = ETrue         -- True
          | EFalse        -- False
          | Not Expr      -- not
          | Or Expr Expr  -- (||)
          | Error
-- Define the function `evalExpr :: Expr -> Maybe Bool`.
-- It should evaluate the boolean expression in the Maybe monad. 

-- It should fail (and return Nothing) if the expression contains Error
-- It should succeed (and return (Just ...)) otherwise.

negate':: Maybe Bool -> Maybe Bool
negate' (Just True) = Just False
negate' (Just False) = Just True
negate' _ = Nothing

or' :: Maybe Bool -> Maybe Bool -> Maybe Bool
or' Nothing Nothing = Nothing 
or' (Just False) (Just False ) = Just False
or' _ _ = Just True 

evalExpr :: Expr -> Maybe Bool
evalExpr Error = Nothing 
evalExpr ETrue = Just True
evalExpr EFalse = Just False
evalExpr (Not a) = do
    a' <- negate' (evalExpr a)
    return a'
evalExpr (Or a b) = do
    a' <- or' (evalExpr a) (evalExpr b)
    return a'

tests :: [Bool]
tests = [ evalExpr ETrue                        == Just True
        , evalExpr EFalse                       == Just False
        , evalExpr Error                        == Nothing
        , evalExpr (Not EFalse)                 == Just True
        , evalExpr (Not Error)                  == Nothing
        , evalExpr (Or ETrue EFalse)            == Just True
        , evalExpr (Or EFalse EFalse)           == Just False
        , evalExpr (Or ETrue Error)             == Nothing
        , evalExpr (Or (Or ETrue Error) EFalse) == Nothing
        ]