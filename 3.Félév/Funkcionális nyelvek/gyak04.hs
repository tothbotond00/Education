{-# OPTIONS -Wincomplete-patterns #-}
{-# LANGUAGE InstanceSigs, DeriveFunctor #-}
module Tut04 where

-- Maybe monad

-- Monad operations and combinators:

returnMaybe :: a -> Maybe a
returnMaybe  = Just 

bindMaybe :: (a -> Maybe b) -> Maybe a -> Maybe b
bindMaybe f (Just a) = f a
bindMaybe _ _ = Nothing 

joinMaybe :: Maybe (Maybe a) -> Maybe a
joinMaybe (Just (Just a)) = Just a
joinMaybe _ = Nothing

---apMaybe' xss = bindMaybe id xss
apMaybe :: Maybe (a -> b) -> Maybe a -> Maybe b
apMaybe (Just f) (Just a) = Just (f a) 
apMaybe _ _ = Nothing 

kleisli :: (a -> Maybe b) -> (b -> Maybe c) -> (a -> Maybe c)
kleisli = undefined

-- Exercises: 
--  1) Define the functions sequenceMaybe, mapMaybe, mapMaybeTree, filterMaybe, zipWithMaybe
--  2) Redefine these functions using the Maybe monad (bindMaybe)

sequenceId :: [a] -> [a]
sequenceId []     = []
sequenceId (x:xs) = x : sequenceId xs

sequenceMaybe :: [Maybe a] -> Maybe [a]
sequenceMaybe [] = Just []
sequenceMaybe  (x:xs) = case (x, sequenceMaybe xs) of
    (Just x', Just xs') -> Just(x':xs') 
    _                    -> Nothing

sequenceMaybe' :: [Maybe a] -> Maybe [a]
sequenceMaybe' [] = returnMaybe []
sequenceMaybe' (x:xs) = x >>= (\x' -> 
                        sequenceMaybe' xs >>= (\xs' ->
                            returnMaybe (x':xs')))

sequenceMaybe'' :: [Maybe a] -> Maybe [a]
sequenceMaybe'' [] = returnMaybe []
sequenceMaybe'' (x:xs) = do
    x' <- x
    xs' <- sequenceMaybe'' xs
    return (x':xs')

-- examples:
--  sequenceMaybe [Just 0, Nothing, Just 1] = Nothing
--  sequenceMaybe [Just 0, Just 2, Just 1] = [0,2,1]


-- map :: (a -> b) -> [a] -> [b]
mapMaybe :: (a -> Maybe b) -> [a] -> Maybe [b]
mapMaybe _ [] = Just []
mapMaybe f (x:xs) = case (f x,mapMaybe f xs) of
    (Just x',Just xs') -> Just (x' :  xs')
    _                  -> Nothing

mapMaybe' :: (a -> Maybe b) -> [a] -> Maybe [b]
mapMaybe' _ [] = returnMaybe []
mapMaybe' f (x:xs) = do
      x'  <- f x
      xs' <- mapMaybe' f xs
      return (x':xs')


-- filter :: (a -> Bool) -> [a] -> [a]
filterMaybe :: (a -> Maybe Bool) -> [a] -> Maybe [a]
filterMaybe _ [] = Just []
filterMaybe f (x:xs) = case (f x, filterMaybe f xs) of
    (Just b ,Just  xs') -> Just (if b then x:xs else xs)
    _                 -> Nothing

filterMaybe' :: (a -> Maybe Bool) -> [a] -> Maybe [a]
filterMaybe' _ [] = returnMaybe []
filterMaybe' f (x:xs) = do
    x' <- f x
    xs' <- filterMaybe' f xs
    returnMaybe (if x' then x:xs' else xs')

-- examples:
--  filterMaybe (\x -> if x > 0 then Just (even x) else Nothing) [2, -1, 3] == Nothing
--  filterMaybe (\x -> if x > 0 then Just (even x) else Nothing) [2, 1, 3] == [2]

data Tree a = Leaf a | Node (Tree a) (Tree a) 
            deriving (Eq, Show)

-- fmap :: (a -> b) -> Tree a -> Tree b
mapMaybeTree :: (a -> Maybe b) -> Tree a -> Maybe (Tree b)
mapMaybeTree f (Leaf a) = do
    y <- f a
    return (Leaf y)
mapMaybeTree f (Node l r) = do
    x' <- mapMaybeTree f l
    y' <- mapMaybeTree f r
    return (Node x' y')
    
-- examples:
--  mapMaybeTree (\x -> if x > 0 then Just (even x) else Nothing) (Leaf (-1)) 
--    == Nothing
--  mapMaybeTree (\x -> if x > 0 then Just (even x) else Nothing) (Node (Leaf 2) (Leaf 3)) 
--    == Just (Node (Leaf True) (Leaf False))


-- zipWith :: (a -> b -> c) -> ([a] -> [b] -> [c])
zipWithMaybe :: (a -> b -> Maybe c) -> [a] -> [b] -> Maybe [c]
zipWithMaybe = undefined
-- examples:
--  zipWithMaybe (\x y -> if x + y > 0 then Just (x + y) else Nothing) [-1,2] [1, 3] 
--    == Nothing
--  zipWithMaybe (\x y -> if x + y > 0 then Just (x + y) else Nothing) [-1,2] [2, 3] 
--    == [1, 5]
