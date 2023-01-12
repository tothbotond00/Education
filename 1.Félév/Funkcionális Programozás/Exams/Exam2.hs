import Data.List ()
--1.Feladat
elemQuartet :: Eq a => a -> (a, a, a, a) -> Bool
elemQuartet x (a,b,c,d) = x==a || x==b || x==c || x==d 

--2.Feladat
manhattanDistance :: (Integer, Integer) -> (Integer, Integer) -> Integer
manhattanDistance (a,b) (c,d) = abs (a-c) +abs (b-d)

--3.Feladat
mulMaybe :: Maybe Integer -> Maybe Integer -> Integer
mulMaybe Nothing Nothing = 1
mulMaybe Nothing (Just x) = x
mulMaybe (Just x) Nothing = x
mulMaybe (Just x) (Just y) = x*y

--4.Feladat
notNullProduct :: (Eq a, Num a) => [a] -> a
notNullProduct [] = 1
notNullProduct (x:xs)
   |x==0 = 1
   |otherwise =x*notNullProduct xs

--5.Feladat
isNum :: String -> Bool
isNum [] = False
isNum [x] =x `elem` ['0'..'9']
isNum (x:xs) =  (x `elem` ['0'..'9'] || x `elem` ['-','+'] ) && all (\x-> x `elem` ['0'..'9']) xs && xs /= []

--6.Feladat!!!

--7.Feladat
applies :: [a -> b] -> [a] -> [b]
applies [] _ = []
applies _ [] = []
applies (x:xs) a = map (x) a ++ applies xs a 

--8.Feladat
mapToSmaller :: Ord a => (a -> a) -> [a] -> [a]
mapToSmaller _ [] = []
mapToSmaller a (x:xs)
  | a x < x = a x : mapToSmaller a xs
  |otherwise = x : mapToSmaller a xs

--9.Feladat
productOfShorter :: Num a => [a] -> [a] -> a
productOfShorter [] _ = 1
productOfShorter _ [] = 1
productOfShorter x y
   | length x <= length y = multiply x
   | otherwise = multiply y
   where multiply [] = 1
         multiply x = (head x) * ( multiply (init x) )
   
--10.Feladat
triplets :: [a] -> [(Maybe a, Maybe a, Maybe a)]
triplets [] = []
triplets [x] =  [ ( Just x,Nothing,Nothing) ]
triplets [x,y] = [ (Just x,Just y,Nothing ) ]
triplets (x:y:z:xs) = (Just x,Just y, Just z) :triplets xs

--11.Feladat
trimBy :: (b -> Bool) -> [b] -> [b]
trimBy _ [] = []
trimBy a (x:xs)
 | a x = x : trimBy a (dropWhile a xs)
 | otherwise = x:trimBy a xs

--12.Feladat
replaceAt :: Int -> a -> [a] -> [a]
replaceAt _ a [] = [a]
replaceAt 0 a (x:xs) = a:xs
replaceAt num a (x:xs) 
   | num < 0 = a:x:xs
   | num == 0 = a:xs
   | otherwise = x: replaceAt (num-1) a xs

--13.Feladat
data NLString = N String  | L Int String 
    deriving (Eq,Show)

--14.Feladat
nlLength :: NLString -> Int
nlLength (L x _) = x
nlLength (N s) =(length s)-1

--15.Feladat
fromString :: String -> NLString
fromString x
  | '0' `elem` x = L (length x) x 
  |otherwise = N (x++['0'])

--16.Feladat
nlConcat :: NLString -> NLString -> NLString
nlConcat (N x) (N y) = (N ((init x)++y) )
nlConcat (N x) (L num y) = (L  (-1+num+length x)  ((init x)++y) )
nlConcat (L num y) (N x)  = (L  (-1+num+length x)  (y++(init x)) )
nlConcat (L num1 x) (L num2 y) = (L (num1+num2) (x++y))