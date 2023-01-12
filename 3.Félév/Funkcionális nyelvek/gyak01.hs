module Tut01 where
import Prelude hiding (Eq(..), Ord(..))

-- This file: https://github.com/AndrasKovacs/ELTE-func-lang/tree/master/2021-22-1/gyak_3/Notes01.hs

----- General information:
-- https://github.com/AndrasKovacs/ELTE-func-lang/tree/master/2021-22-1

----- Holes:

ex0 :: a -> b -> a
ex0  x y = x

----- Common Algebraic Datatypes: (,), Either, Maybe

-- data Maybe a = Nothing 
--              | Just a

-- data (,) a b = (,) a b

-- data Either a b = Left a
--                 | Right b

data BinTree a = Leaf a
               | Node (BinTree a) (BinTree a)
               deriving (Show) -- <-- this defines `show :: Show a => BinTree a -> String` automatically. 

data Tree1 a = Leaf1 a
             | Node1 [Tree1 a]
             deriving (Show)

----- Exercises: define functions f1 .. f11 with the given types.

f1 :: (a, (b, (c, d))) -> (b, c)
f1 (x,(y,(z,w))) = (y, z)

f2 :: (a -> b) -> a -> b
f2 x = x                  -- f2 = id

f3 :: (b -> c) -> (a -> b) -> a -> c
f3 f1 f2 z = f1 (f2 z)    -- f3  = (.)

f4 :: (a -> b -> c) -> (b -> a -> c)
f4 g x y = g y x          -- f4 = flip

f5 :: ((a, b) -> c) -> (a -> b -> c)
f5 g x y = g (x,y)        -- f5 = curry

f6 :: (a -> (b, c)) -> (a -> b, a -> c)
f6 f = (h1,h2)
    where h1 x = fst (f x)
          h2 x = snd (f x)

f7 :: (a -> b, a -> c) -> (a -> (b, c))
f7 (f, h) x = (f x, h x) 

f8 :: (Either a b -> c) -> (a -> c, b -> c)
f8 f = (f . Left ,f . Right )

f9 :: (a -> c, b -> c) -> (Either a b -> c)
f9 (f, g) (Left a)  = f a
f9 (f,g) (Right b) = g b

f10 :: Either (a, b) (a, c) -> (a, Either b c)
f10 (Left (a,b))  = (a, Left b)
f10 (Right (a,c)) = (a, Right c)

f11 :: (a, Either b c) -> Either (a, b) (a, c)
f11 (a,Left b)= Left (a,b)
f11 (a,Right c) = Right (a,c)

----- The Eq and Ord typeclasses:

-- ghci> :i Eq
class Eq a where
  (==) :: a -> a -> Bool

instance Eq Bool where
  (==) True True = True
  (==) False False = True 
  (==) _ _ = False  

instance Eq a => Eq (Maybe a) where
  (==) Nothing Nothing  = True
  (==) (Just a) (Just b) = a == b
  (==) _ _ = False 

instance (Eq a, Eq b) => Eq (Either a b) where
  (==) (Left a) (Left b) = a == b
  (==) (Right a) (Right b) = a == b
  (==) _ _ = False  

instance (Eq a, Eq b) => Eq (a, b) where
  (==) (a,b) (c,d) = (a==c) && (b==d)


-- ghci> :i Ord
class Ord a where
  (<=) :: a -> a -> Bool

instance Ord Bool where
  (<=) True True = True
  (<=) False False = True
  (<=) False True = True 
  (<=) _ _ = False
instance Ord a => Ord (Maybe a) where
  (<=) Nothing Nothing = True 
  (<=) Nothing (Just a) = True 
  (<=) (Just a) (Just b) = a <= b
  (<=) _ _  = False 

-- 1) (1pt) Define a function `g` with the following signature:
g :: (a -> a -> b) -> (a -> b)
g x y = x y y

-- 2) (1pt) Define a function `h` with the following signature:
h :: (b -> a) -> Either a b -> a
h x (Left a) = a 
h x (Right b) = x b