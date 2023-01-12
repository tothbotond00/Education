import Data.Char ( toUpper, toLower )
--1.Feladat
sortTuple :: Ord a => (a, a) -> (a, a)
sortTuple (x,y)
    |x < y = (x,y)
    |otherwise =(y,x)

--2.Feladat
caseSwap :: Char -> Char
caseSwap x
   | x `elem`  ['a'..'z'] = toUpper x
   | x `elem`  ['A'..'Z'] = toLower x
   |otherwise = x
   
--3.Feladat
count :: Eq a => a -> [a] -> Int
count a [] = 0
count a (x:xs)
   | a==x = 1 + count a xs
   | otherwise = count a xs

--4.Feladat
listMul :: [Int] -> [Int] -> Int
listMul [] [] =0
listMul (x:xs) (y:ys) = x*y + listMul xs ys

--5.Feladat!!!
sameSign :: [Int] -> Bool
sameSign [] =True 
sameSign [x] = True 
sameSign x 
   |filter (\a->a>=0) x == x = True 
   |filter (\a->a<=0) x == x = True 
   |otherwise =False

--6.Feladat
isCorrect :: [(Int, Int)] -> Bool
isCorrect [] =True 
isCorrect [x] =True
isCorrect ( (a,b):(c,d):xs)
   | b==c =isCorrect  ( (c,d):xs)
   |otherwise = False 

--7.Feladat
filterMany :: [a -> Bool] -> [a] -> [a]
filterMany [] x = x
filterMany (x:xs) a = filterMany xs (filter x a)

--8.Feladat
conditionalMax :: Ord a => (a -> Bool) -> [a] -> Maybe a
conditionalMax _ [] = Nothing 
conditionalMax x a  
  |filter x a == [] =Nothing 
  |otherwise  = Just (maximum (filter x a) )

--9.Feladat
data Season = Winter | Spring | Summer | Autumn
  deriving (Eq)

nextSeason :: Season ->  Season
nextSeason Winter = Spring
nextSeason Spring = Summer
nextSeason Summer = Autumn
nextSeason Autumn = Winter

--10.Feladat
seasonAfterMonths :: Int -> Season
seasonAfterMonths x
   | x==0 || x==1 ||x==11 = Winter
   | x>=2 && x<=4 = Spring
   | x>=5 && x<=7 = Summer
   | x>=8 && x<=10 = Autumn
   |otherwise =seasonAfterMonths (x-12)

--11.Feladat
removeSpecial :: String -> String
removeSpecial [] = []
removeSpecial (x:xs)
  | x `elem` ['a'..'z'] || x `elem` ['A'..'Z'] || x `elem` ['0'..'9'] || x==' ' = x:removeSpecial xs
  |otherwise =removeSpecial xs

--12.Feladat
isSublist :: Eq a => [a] -> [a] -> Bool
isSublist [] _ = True 
isSublist (x:xs) y
  | x `elem` y =isSublist xs y
  |otherwise =False 

--13.Feladat
multipleElems :: Eq a => [a] -> [a]
multipleElems [] = []
multipleElems (x:xs)
  | x `elem` xs = x: multipleElems (filter (\a-> a /= x) xs) 
  | otherwise = multipleElems (filter (\a-> a /= x) xs)

--14.Feladat
maxTempChange :: [(Int, Int)] -> Int
maxTempChange (x:xs) 
    |(snd x - fst x) == maximum (map (\a -> (snd a - fst a) ) (x:xs)) = 1
    | otherwise  = 1 + maxTempChange xs

--15.Feladat
isPrime :: Integer -> Bool
isPrime 0 = False
isPrime 1 = False
isPrime 2 = True
isPrime n = odd n && null [d | d <- [3,5..squareRoot n], n `mod` d == 0] where
  squareRoot :: Integer -> Integer
  squareRoot n = floor (sqrt (fromIntegral n))
primeList :: [Integer]
primeList = 2:[ x | x <- [3,5..], isPrime x]
