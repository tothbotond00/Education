--1.Feladat:
fact :: Integer-> Integer
fact 0 = 1
fact n = n*fact(n-1)
--2.Feladat:
fib::Int->Int
fib 0 = 0
fib 1 = 1
fib n=fib(n-1)+fib(n-2)
--3.Feladat:
pow::Int->Int->Int
pow 0 _ = 0
pow _ 0 = 1
pow x 1 = x
pow x y = x * pow x (y-1)
--5.Feladat:
range::Int->Int->[Int]
range x y
    |x==y =[x]
range x y=x:range (x+1) y
--6.Feladat:
range'::Int->Int->[Int]
range' x y
    |x==y =[x]
range' x y=x:range' (x+1) y
--7.Feladat:
length'::[Int]->Int
length' [] = 0
length' (_:[]) = 1
length' (_:xs) = 1 + length' xs
--8.Feladat:

--9.Feladat:
everySecond::String->String
everySecond ""=""
everySecond (_:[])=""
everySecond (_:y:xs)=y:everySecond(xs)
--10.Feladat:
elem'::Char->String->Bool
elem' _ ""=False
elem' y (x:xs)
   |y==x =True
   |otherwise = elem' y xs
--11.Feladat:
--value::Integer->[(Integer,String)]->String
--value _ [] ="error"
--value z ((x,y):xs)
-- |z==x = y
-- |otherwise =value z xs
--12.Feladat:
value::Integer->String->[(Integer,String)]->String
value _ x [] =x
value z r ((x,y):xs) 
  |z==x = y
  |otherwise =value z r xs 