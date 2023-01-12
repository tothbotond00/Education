import Data.Char ( isUpper )
import Data.List 
--1.Feladat:
map'::(a->b)->[a]->[b]
map' _ [] =[]
map' f (x:xs) = f x : map' f xs

--2.Feladat:
filter'::(a->Bool)->[a]->[a]
filter' _ [] =[]
filter' f (x:xs)
   |f x = x:filter' f xs
   |otherwise = filter' f xs

--3.Feladat:
upperToLower::String->String
upperToLower "" = ""
upperToLower x = filter' isUpper x

--4.Feladat:
all' :: (a->Bool) -> [a]->Bool
all' _ [] =True
all' f  (x:xs)
    |f x =all' f xs
    |otherwise=False

--5.Feladat:
any' :: (a->Bool) -> [a]->Bool
any' _ [] =False
any' f  (x:xs)
    |f x = True
    |otherwise = any' f xs

--6.Feladat:
hasLongLines::String->Bool
hasLongLines "" = False
hasLongLines x =  any' (\n -> length (words n)>=3) (lines x)

--7.Feladat:
elem' :: Eq a => a -> [a] -> Bool
elem' _ [] =False
elem' a (x:xs) 
   |a==x =True
   |otherwise = elem' a xs

--8.Feladat:
hasAny :: Eq a => [a] -> [a] -> Bool
hasAny [] _ =False
hasAny _ [] =False
hasAny (x:xs) ys
   |elem' x ys =True
   |otherwise = hasAny xs ys

--9.Feladat:
takeWhile':: (a->Bool)->[a]->[a]
takeWhile' _ [] =[]
takeWhile' f (x:xs)
   |f x = x:takeWhile' f xs
   |otherwise =[]

--10.Feladat:
dropWhile' :: (a->Bool)->[a]->[a]
dropWhile' _ [] =[]
dropWhile' f (x:xs)
   |f x =dropWhile f xs
   |otherwise = x:xs

--11.Feladat:
--dropWord::[Char]->[Char]
--dropWord x = dropWhile' (\n -> n/= ' ') x

--12.Feladat:
users :: [(String, String)]
users = [ ("mrbean", "4321"), ("admin", "s3cr3t"), ("finn", "algebraic")]

--BEADANDO:
board ::[(Int,Int)]
board = [ (x,y) | x <-[0..7], y <-[0..7]]

rook::(Int,Int)->[(Int,Int)]
rook (x,y) = [ (x+m,y)| m<-[-7..7],elem (x+m,y) board && m /=0] ++ [ (x,y+n)| n<-[-7..7],elem (x,y+n) board && n /= 0]

knight :: (Int, Int) -> [(Int, Int)]
knight (x,y) = [(x+m,y+n) | m <- [-2..2], n <- [-2..2], elem (x+m,y+n) board && m/=0 && n/=0 && abs m + abs n == 3 ]

attacks :: ((Int, Int) -> [(Int, Int)]) -> (Int, Int) -> [(Int, Int)] -> Bool
attacks _ _ [] = False
attacks rule place (babu:babuk)
    |babu `elem` rule place =True
    | otherwise = attacks rule place babuk

remove :: Eq a => a -> [a] -> [a]
remove element list = filter (\e -> e/=element) list

moves :: ((Int, Int) -> [(Int, Int)]) -> (Int, Int) -> [(Int, Int)] -> [[(Int, Int)]]
moves rule place babuk = [ x : remove place babuk | x<-rule place, x `notElem`babuk]