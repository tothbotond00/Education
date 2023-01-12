
import Prelude hiding (Eq(..), Ord(..), Functor(..))

----- The Eq and Ord typeclasses:

-- ghci> :i Eq
class Eq a where
  (==) :: a -> a -> Bool

instance Eq Bool where
  (==) True True     = True
  (==) False False   = True 
  (==) _ _           = False  

instance Eq a => Eq (Maybe a) where
  (==) Nothing Nothing   = True
  (==) (Just a) (Just b) = a == b
  (==) _ _               = False

instance Eq a => Eq [a] where
  (==) [] []            = True 
  (==) [] xs            = False
  (==) xs []            = False 
  (==) (x:xs) (y:ys)    = (x == y) && (==) xs ys
  --(==) _ _            = False         

instance (Eq a, Eq b) => Eq (Either a b) where
  (==) (Left a) (Left b)    = a == b
  (==) (Right a) (Right b)  = a == b
  (==) _ _                  = False 

instance (Eq a, Eq b) => Eq (a, b) where
  (==) (a,b) (c,d) = (a==c) && (b==d)

-- ghci> :i Ord
class Eq a => Ord a where
  (<=) :: a -> a -> Bool

instance Ord Bool where
  --(<=) True True    = True
  --(<=) False False  = True
  --(<=) False True   = True 
  --(<=) _ _          = False
  (<=) True False     = False
  (<=) _ _            = True

instance Ord a => Ord (Maybe a) where
  (<=) Nothing Nothing      = True 
  (<=) Nothing (Just a)     = True 
  (<=) (Just a) (Just b)    = a <= b
  (<=) _ _                  = False 

instance Ord a => Ord [a] where
  (<=) [] _          = True
  (<=) _ []          = False
  (<=) (x:xs) (y:ys)
        | x == y    = (<=) xs ys
        | x <= y    = True
        | otherwise = False 

----- The Functor typeclass:

map':: (a -> b) -> [a] -> [b]
map' f []       = []
map' f (x:xs)   = f x : map' f xs

-- ghci> :i Functor
class Functor f where
  fmap :: (a -> b) -> f a -> f b

instance Functor [] where
  --fmap :: (a -> b) -> [a] -> [b]
  fmap = map'

instance Functor Maybe where
  -- fmap :: (a -> b) -> Maybe a -> Maybe b
  fmap f Nothing   = Nothing 
  fmap f (Just a ) = Just (f a)

mapNested :: (a -> b) -> [[[a]]] -> [[[b]]]
mapNested = undefined

--helper functions...^
data Id a = Id a
          deriving (Show)

instance Functor Id where
  -- fmap :: (a -> b) -> Id a -> Id b
  fmap = undefined

data Const a b = Const a
               deriving (Show)

instance Functor (Const a) where
  -- fmap :: (a -> b) -> Const x a -> Const x b
  fmap = undefined

data BinTree a = BinLeaf a
               | BinNode (BinTree a) (BinTree a)
               deriving (Show)

instance Functor BinTree where
  -- fmap :: (a -> b) -> BinTree a -> BinTree b
  fmap = undefined

data Tree1 a = Leaf1 a
             | Node1 [Tree1 a]
             deriving (Show)

instance Functor Tree1 where
  -- fmap :: (a -> b) -> Tree1 a -> Tree1 b
  fmap = undefined

data Tree2 a = Leaf2 a
             | Node2 (Int -> Tree2 a)
-- Remark: it is not possible to define Show, Eq or Ord for `Tree2`

instance Functor Tree2 where
  -- fmap :: (a -> b) -> Tree2 a -> Tree2 b
  fmap = undefined

-- Írj egy függvényt, ami összeadja egy `[Maybe Int]` listában az össszes `Int`-et,
-- a `Nothing`-okat nullának vesszük.

sumMaybes :: [Maybe Int] -> Int
sumMaybes [] = 0
sumMaybes (Nothing : xs) = sumMaybes xs
sumMaybes (Just x : xs) = x + sumMaybes xs

-- Tesztesetek:
--tests :: [Bool]
--tests = [
  --sumMaybes [] == 0,
  --sumMaybes [Just 10, Just 20] == 30,
  --sumMaybes [Nothing, Just 20, Nothing] == 20
  --]