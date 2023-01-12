{-# OPTIONS -Wincomplete-patterns #-}
{-# LANGUAGE InstanceSigs, DeriveFunctor, DeriveFoldable #-}
module Tut07 where

import Prelude hiding (Traversable(..))
import Control.Monad
import Data.Monoid

data State s a = State { runState :: s -> (a, s) }
  deriving (Functor)

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
modify f = do s <- get; put (f s)

evalState :: State s a -> s -> a
evalState sta s = fst (runState sta s)

execState :: State s a -> s -> s
execState sta s = snd (runState sta s)

--------------------------------------------------------------------------------

data Tree a = Leaf a 
            | Node (Tree a) (Tree a)
  deriving (Show, Eq, Functor, Foldable)

-- Define the function `labelTree :: Tree a -> Tree Int`.
--   `labelTree t` should label the leaves by the integers 0,1,...
-- Examples:
--   labelTree (Leaf ()) == Leaf 0
--   labelTree (Node (Leaf ()) (Leaf ())) 
--     == Node (Leaf 0) (Leaf 1)
--   labelTree (Node (Node (Leaf ()) (Leaf ())) (Leaf () ))
--     == Node (Node (Leaf 0) (Leaf 1)) (Leaf 2)

labelTree :: Tree a -> Tree Int
labelTree t = evalState (go t) 0
  where go :: Tree a -> State Int (Tree Int)
        go (Leaf a ) = do
          n    <- get
          put  (n + 1)
          pure (Leaf n)
        go (Node a b) = do
          a' <- go a
          b' <- go b
          pure (Node a' b')
        --go (Node l r) = Node <$> go l <*> go r

-- `relabelTree xs t` should label the leaves using the values of xs.
--  (You can assume that length xs >= length t)
-- Examples:
--   relabelTree [10] (Leaf ()) == Leaf 10
--   relabelTree [2,1] (Node (Leaf ()) (Leaf ())) 
--     == Node (Leaf 2) (Leaf 1)
--   relabelTree [9,2,7] (Node (Node (Leaf ())) (Leaf ()) (Leaf ()))
--     == Node (Node (Leaf 9) (Leaf 2)) (Leaf 7)

relabelTree :: [b] -> Tree a -> Tree b
relabelTree hs t = evalState (go t) hs   
  where go :: Tree a -> State [b] (Tree b)
        go (Leaf a) = do
          xs   <- get
          let z:zs = xs
          put  zs 
          pure (Leaf z)
        go (Node a b) = do
          a'   <- go a
          b'   <- go b
          pure (Node a' b')

--

-- In Prelude:
--   lookup :: Eq a => [(a,b)] -> a -> Maybe b
--   lookup [("key1", 0), ("key2", 1)] "key1" == Just 0
--   lookup [("key1", 0), ("key2", 1)] "key3" == Nothing

-- Examples:
--   mapLookup [("a", 0), ("b", 1)] (Node (Leaf "b") (Leaf "a"))
--     == Just (Node (Leaf 1) (Leaf 0))
--   mapLookup [("a", 0), ("b", 1)] (Node (Leaf "a") (Leaf "c"))
--     == Nothing

---ARGUMNETS SWAPPED!

-- `mapLookup xs t` should apply the function `lookup xs` to the
--   values at the leaves of `t`, and fail (return Nothing)
--   if any of the lookups fails.

mapLookup :: Eq a => [(a,b)] -> Tree a -> Maybe (Tree b)
mapLookup xs (Leaf x) = do
  b <- lookup x xs
  pure (Leaf b) 
mapLookup xs (Node l r) = Node <$> mapLookup xs l <*> mapLookup xs r

--------------------------------------------------------------------------------

class Foldable f => Traversable f where
  -- fmap    ::                (a ->   b) -> f a ->    f b
  -- foldMap :: Monoid m =>    (a -> m)   -> f a -> m
  traverse :: Applicative m => (a -> m b) -> f a -> m (f b)

instance Traversable [] where
  traverse f []     = pure []
  traverse f (x:xs) = (:) <$> f x <*> traverse f xs

relabel' :: Traversable f => [b] -> f a -> f b
relabel' xs t = evalState (go t) xs
    where go :: Traversable f => f a -> State [b] (f b)
          go = traverse $ \x -> do 
            ys <- get
            let z:zs = ys
            put zs
            pure z 

--mapLookup xs (Leaf x) = do
--  b <- lookup x xs
--  pure (Leaf b) 
--mapLookup xs (Node l r) = Node <$> mapLookup xs l <*> mapLookup xs r

mapLookup' :: (Eq a, Traversable f) => [(a,b)] -> f a -> Maybe (f b)
mapLookup' xs = traverse $ \a -> lookup a xs 

instance Traversable Maybe where
  traverse f Nothing  = pure Nothing
  traverse f (Just x) = Just <$> f x

instance Traversable (Either x) where
  traverse f (Left a) = pure (Left a)
  traverse f (Right a) = Right <$> f a

instance Traversable Tree where
  traverse f (Leaf a)   = Leaf <$> f a
  traverse f (Node l r) = Node <$> traverse f l <*> traverse f r

data Tree2 a = Leaf2 a 
             | Node2 [Tree2 a]
  deriving (Functor, Foldable)

-- fmap f (Node2 xs) = fmap (fmap f) xs
instance Traversable Tree2 where
  traverse f (Leaf2 a)   = Leaf2 <$> f a
  traverse f (Node2 xs)  = Node2 <$> traverse (traverse f) xs 

-- Bonus (fmapDefault and foldMapDefault in Data.Traversable):
fmapFromTraverse :: (Traversable f, Monoid m) => (a -> m) -> f a -> m
fmapFromTraverse = undefined

foldMapFromTraverse :: Traversable f => (a -> b) -> f a -> f b
foldMapFromTraverse = undefined

---Quiz
-- Examples:
--   sumLeft (Leaf 10) 
--         == Leaf 10
--   sumLeft (Node (Leaf 10) (Leaf 2))
--         == Node (Leaf 10) (Leaf 12)
--   sumLeft (Node (Node (Leaf 1000) (Leaf 200))  (Node (Leaf 3)   (Leaf 40)))
--         == Node (Node (Leaf 1000) (Leaf 1200)) (Node (Leaf 1203) (Leaf 1243))
 
sumLeft :: Tree Integer -> Tree Integer
sumLeft t = evalState (go t) 0 
    where go :: Tree Integer -> State Integer (Tree Integer)
          go (Leaf a) = do
            s <- get
            let z = s + a
            put z
            pure (Leaf z)
          go (Node a b) = do
            a' <- go a
            b' <- go b
            pure (Node a' b')