import Data.List ()
import Data.Char ( digitToInt )
--1.Feladat
kroeneckerDelta :: Eq a => a -> a -> Int
kroeneckerDelta x y
   | x==y = 1
   | otherwise = 0

--2.Feladat
safeDivMod :: Int -> Int -> Maybe (Int, Int)
safeDivMod _ 0 = Nothing 
safeDivMod x y = Just (x `div` y,x `mod` y)

--3.Feladat
c :: ((a, b) -> int -> c) -> a -> int -> b -> c
c f a int b = f (a,b) int

--4.Feladat
sumOddIndices :: [Int] -> Int
sumOddIndices [] = 0
sumOddIndices [x]= x
sumOddIndices (x:y:xs)  = x + sumOddIndices xs

--5.Feladat
replace :: Int -> a -> [a] -> [a]
replace 0 a (x:xs) = a:xs
replace _ a [] = [a]
replace num a (x:xs)
   | num < 0 = a:x:xs
   | num > length (x:xs) = x:xs++[a]
   |otherwise = x:replace (num-1) a xs 

--6.Feladat
applyNTimes :: Int -> (a -> a) -> [a] -> [a]
applyNTimes 0 f x = x
applyNTimes _ _ [] = []
applyNTimes num f x = applyNTimes (num-1) f (map f x)

--7.Feladat
binaryToDecimal :: String -> Int
binaryToDecimal "" =0
binaryToDecimal x 
    |digitToInt ( head x ) ==1 = 2^ ((length x)-1) + binaryToDecimal (tail x)
    |otherwise =binaryToDecimal (tail x)

--8.Feladat
functionsComposition :: [(a -> a)] -> (a -> a)
functionsComposition [] x = x
functionsComposition f x = functionsComposition (init f) ((last f) x)

--9.Feladat
isSquareNumber :: Int -> Bool
isSquareNumber 0 = True
isSquareNumber 1 = True 
isSquareNumber x
   | x<0 = False 
   | otherwise = square x 2
          where square x y
                  |x == y = False 
                  |y*y == x = True 
                  |otherwise = square x (y+1)

--10.Feladat
deleteAll :: Eq a => a -> [a] -> [a]
deleteAll _ [] = []
deleteAll a (x:xs)
   |x==a = deleteAll a xs
   |otherwise = x:deleteAll a xs

--11.Feladat???

--12.Feladat
data RPS = Rock | Paper | Scissors
   deriving (Show,Eq)

beats :: RPS -> RPS
beats Rock = Scissors
beats Paper = Rock
beats Scissors = Paper

--13.Feladat
firstBeats :: [RPS] -> [RPS] -> Int
firstBeats [] [] = 0
firstBeats (x:xs) (y:ys) 
   | beats x == y = 1 + firstBeats xs ys
   | otherwise = firstBeats xs ys

--14.Feladat
data Temperature = Daytime Int | Night Int
    deriving (Show,Eq,Ord)

isDaytime :: Temperature -> Bool
isDaytime (Daytime _) =True 
isDaytime (Night _) = False 

--15.Feladat
toint:: Temperature -> Int
toint (Daytime x) = x
toint (Night x) = x

extremes :: [Temperature] -> (Int, Int)
extremes x =( maximum ( map toint (filter isDaytime x) ) , minimum ( map toint (filter (\x-> isDaytime x ==False) x) ))