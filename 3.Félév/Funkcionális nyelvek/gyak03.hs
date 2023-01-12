
{-# language InstanceSigs #-}

--részleges minatillesztésre figyelmeztet
{-# options_ghc -Wincomplete-patterns #-}

import Prelude hiding (Either(..), Functor(..), Semigroup(..), Monoid(..))
import System.Win32.File (WIN32_FILE_ATTRIBUTE_DATA(fadFileSize))


data Tree a = Leaf a | Node (Tree a) (Tree a)

class Eq' a where
  eq :: a -> a -> Bool

class Show' a where
  show' :: a -> String

instance Eq' a => Eq' (Tree a) where
  eq (Leaf a) (Node b c)  = False 
  eq (Node b c) (Leaf a)  = False
  eq (Leaf a) (Leaf b)    = eq a b
  eq (Node a b) (Node c d) = eq a b && eq c d 

instance Show' a => Show' (Tree a) where
  show' (Leaf a) = "(Leaf "++ show' a ++")"
  show' (Node a b) = "(Node "++ show' a ++ show' b++")"

-- Semigroup, Monoid, Functor
------------------------------------------------------------

infixr 6 <>
class Semigroup a where
  (<>) :: a -> a -> a   -- asszociatív bináris művelet
                        -- x <> (y <> z) = (x <> y) <> z

class Semigroup a => Monoid a where
  mempty :: a           -- Semigroup + egységelem
                        -- mempty <> x = x
                        -- x <> mempty = x

class Functor f where
  fmap :: (a -> b) -> f a -> f b

-- Feladat: írd meg a következő instance-okat! (+feleljen meg az osztály törvényeknek!)
-- Tipp: ugorjuk át a nehezebb feladatokat.

instance Semigroup [a] where
  (<>) = (++)

instance Monoid [a] where
  mempty = []

instance (Semigroup a, Semigroup b) => Semigroup (a, b) where
  (<>) :: (a, b) -> (a, b) -> (a, b)
  (<>) (x,y) (a,b) = (x <> a , y <> b)

instance (Monoid a, Monoid b) => Monoid (a, b) where
  mempty :: (a, b)
  mempty = (mempty,mempty)

instance Semigroup b => Semigroup (a -> b) where

  (<>) :: (a -> b) -> (a -> b) -> a -> b
  (<>) f g a = f a <> g a

instance Monoid b => Monoid (a -> b) where
  mempty :: a -> b
  mempty f = mempty

--------------------------------------------------------------------------------

-- map :: (a -> b) -> [a] -> [b]

-- általánosítás:
-- class Functor f where
--   fmap :: (a -> b) -> f a -> f b

-- instance Functor [] where
--    fmap :: (a -> b) -> [] a -> [] b
--    fmap = map

--    fmap :: (a -> b) -> [a] -> [b]    -- szintaktikus cukorka lista típusra!
--    fmap = map

-- min 1 paraméter
--  utolsú típusparaméter fölött map-elünk (Functor instance-al)

data    Foo1 a      = Foo1 Int a a a deriving Show
data    Foo2 a      = Foo2 Bool a Bool
data    Foo3 a      = Foo3 a a a a a
data    Tree1 a     = Leaf1 a | Node1 (Tree1 a) (Tree1 a) deriving Show
data    Tree2 a     = Node2 a [Tree2 a] deriving Show
data    Pair a b    = Pair a b
data    Either a b  = Left a | Right b
data    Tree3 i a   = Leaf3 a | Node3 (i -> Tree3 i a)  -- i-szeres elágazás
newtype Id a        = Id a
newtype Const a b   = Const a
newtype Fun a b     = Fun (a -> b)

instance Functor Foo1 where
  fmap :: (a -> b) -> Foo1 a -> Foo1 b
  fmap f (Foo1 n x y z) = Foo1 n (f x) (f y) (f z)

instance Functor Foo2 where
  fmap :: (a -> b) -> Foo2 a -> Foo2 b
  fmap f (Foo2 x y z) = Foo2 x (f y) z 

instance Functor Foo3 where
  fmap :: (a -> b) -> Foo3 a -> Foo3 b 
  fmap f (Foo3 a b c d e)= Foo3 (f a) (f b) (f c) (f d) (f e)

instance Functor Tree1 where
  fmap :: (a -> b) -> Tree1 a -> Tree1 b 
  fmap f (Node1 x y) = Node1 (fmap f x) (fmap f y)
  fmap f (Leaf1 a) = Leaf1 (f a) 

-- data Pair a b = Pair a b        (,)
-- Pair-t parciálisan alkalmazzuk 1 darab típusra

instance Functor (Pair c) where
  fmap :: (a -> b) -> Pair c a -> Pair c b
  fmap f (Pair c a) = Pair c (f a)

instance Functor Tree2 where
  fmap ::  (a -> b) -> Tree2 a -> Tree2 b
  fmap f (Node2 x xs)  = Node2 (f x) (map (fmap f) xs) 

instance Functor (Tree3 i) where
  fmap :: (a -> b) -> Tree3 i a -> Tree3 i b
  fmap f (Leaf3 a) = Leaf3 (f a)
  fmap f (Node3 g) = Node3 (\i -> fmap f (g i)) -- fmap f.g

instance Functor (Either a) where
  fmap :: (c -> b) -> Either a c -> Either a b
  fmap f (Left a) = Left a
  fmap f (Right a) = Right (f a)

instance Functor Id where
  fmap = fmap

instance Functor (Const a) where
  fmap :: (c -> b) -> Const a c -> Const a b
  fmap f (Const a) = Const a 

instance Functor (Fun a) where
  fmap :: (c -> b) -> Fun a c -> Fun a b
  fmap f (Fun a) = Fun (f . a)

data F a = A Int a
         | B a (F a)
         | C [a]
         deriving (Eq, Show)
--Quiz

instance Functor F where
  fmap :: (a -> b) -> F a -> F b
  fmap x (A a b) = A a (x b)
  fmap x (B b y) = B (x b) (fmap x y)
  fmap x (C xs) = C (map x xs)

tests :: [Bool]
tests = [ fmap (+1) (A 0 1) == A 0 2
        , fmap (+1) (B 0 (B 1 (A 2 3))) == B 1 (B 2 (A 2 4))
        , fmap (+1) (B 10 (C [20, 30])) == B 11 (C [21, 31])
        ]