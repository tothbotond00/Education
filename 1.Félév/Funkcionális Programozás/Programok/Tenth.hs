import Data.Char ( isUpper )
import Data.List ( group, sort )
--1.Feladat:
dropSpaces::String->String
dropSpaces x =dropWhile (\x->x==' ') x

--2.Feladat:
trim::String->String
trim x= takeWhile (\x->x/=' ') (dropWhile (\x->x==' ') x)

trim' ::String->String
trim' x = reverse. dropSpaces . reverse .dropSpaces $ x
--3.Feladat:
monogram::String->String
monogram [] =[]
monogram x =   takeWhile isUpper (head (words x))++". "++monogram (unwords(tail (words x)))

--4.Feladat:
uniq :: Ord a => [a] -> [a] 
uniq x = (map (\(x:xs) -> x) (group(sort x)))

--5.Feladat:
zipWith' ::(a->a->b)->[a]->[a]->[b]
zipWith' _ [] _ = []
zipWith' _ _ [] = []
zipWith' f (x:xs) (y:ys) = f x y :zipWith' f xs ys

--6.Feladat:
repeated:: Ord a => [a]->[a]
repeated x = map (\(x:xs) -> x)(filter (\x -> length x >=2) (group(sort x)))

--7.Feladat:
dotProduct ::[Int]->[Int]->Int
dotProduct x y = sum (zipWith' (*) x y) 

--8.Feladat:
--9.Feladat:
--10.Feladat:
--11.Feladat:
--BEAD:

compress::String->[(Int,Char)]
compress x = map (\x -> (length x, head x)) (group x)

decompress::[(Int,Char)]->String
decompress x = concat (map (\(x,y)-> replicate x y) x)