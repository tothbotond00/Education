import Data.Char 
--head (lsita első eleme)
--1.FELADAT:
head'::[Int]->Int
head' [] = 0
head' (x :_) = x
--2.FELADAT:
tail' :: [Int]->[Int]
tail' (_:xs)=xs
--3.FELADAT:
null' :: [Int]->Bool
null' [] =True
null' _ =False
--4.FELADAT:
isSingletonInt::[Int]->Bool
isSingletonInt (_:[]) =True
isSingletonInt _ =False
--5.FELADAT:
toUpperFirst::[Char]->[Char]
toUpperFirst "" = ""
toUpperFirst (x:karakter) = toUpper(x):karakter
--6.FELADAT:
--import Data.Char hiding (isLetter)
isLetter'::Char ->Bool
isLetter' x = elem x ['a'..'z'] || elem x ['A'..'Z']
--7.FELADAT:
isDigit':: Char ->Bool
isDigit' x = elem x ['0'..'9']
--8.FELADAT:
mountain:: Int -> [Int]
mountain x = [1..x-1]++[x,x-1..1]
--9.FELADAT:
divisors::Int->[Int]
divisors 0 =[1..]

--10.FELADAT:

--11.FELADAT:

--12.FELADAT:

--BEAD
--tricky :: Int -> String
--tricky 12 = "ez" -- 1. függvényág
--tricky w  = "ezi" -- 2. függvényág
--tricky 10 = "ezf" -- 3. függvényág
--tricky _  = "ez" -- 4. függvényág
isValid::String->Bool
isValid x = take 3 (x)=="TA:" 
isValid x =  take 3 (x)=="SM:" 
isValid x = take 3 (x)=="MA:"
isValid _ = False
firstTwo:: [Bool]->Bool 
firstTwo [] = False
firstTwo (x:[]) = x==True
firstTwo (_:[]) = False
firstTwo (x:y:_) = x==True && y==False || x==False && y ==True