--1.Feladat
szum'::[Int]->Int
szum' [] = 0
szum' (x:[])= x
szum' (x:xs) = x + szum' xs

--2.Feladat
last'::[Int]->Int
last' (x:[]) = x
last' (_:xs) = last' xs

--3.Feladat
and':: [Bool]->Bool
and' [] =True
and' (x:xs)
  |x==True =and' xs
  |otherwise = False

--4.Feladat
or'::[Bool]->Bool
or' [] =False
or' (x:xs)
  |x==True =True
  |otherwise  = or' xs

--5.Feladat
repeat'::Char->[Char]
repeat' x = x:repeat' x

--6.Feladat
replicate'::Int->Char->[Char]
replicate' y x = take y (repeat' x)

--7.Feladat
format::Int->[Char]->[Char]
format 0 _  =[]
format x y
   |length y >x = y
   |otherwise = replicate' (x-length y) ' ' ++ y

--8.Feladat
insert::Int->[Int]->[Int]
insert y []= y:[]
insert y (x:xs)
  |x>y =y:x:xs
  |otherwise = x:[] ++ insert y xs

--9.Feladat
sort::[Int]->[Int]
sort [] =[]
sort (x:[])=x:[]

--10.Feladat

--11.Feladat

--12.Feladat

--13.Feladat

--14.Feladat

--Beadando:
weight::[(String,Double,Int)]->Bool
weight [] =False
weight ((_,y,_):xs)
   | y>=50.0    = True
   |otherwise = weight xs

cost::[(String,Double,Int)]->Int
cost [] =0
cost ((_,_,z):xs) = z + cost xs

deliveryCost::[(String,Double,Int)]->Int
deliveryCost [] = 0
deliveryCost xs
    |weight xs=0
    |cost (xs)>30000 =5000
    |otherwise = 10000