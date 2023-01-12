--1.Feladat:
add::(Int,Int)->(Int,Int)->(Int,Int)
add (szam1,nev1) (szam2,nev2) = (szam1*nev1+szam2*nev1,nev1*nev2)
--2.Feladat:
mult::(Int,Int)->(Int,Int)->(Int,Int)
mult (szam1,nev1) (szam2,nev2)=(szam1*szam2,nev1*nev2)
--3.Feladat:
divide::Int->Int->(Int,Int)
divide a b = (a`mod`b,a`div`b)
--4.Feladat:
masod::Double->Double->Double->(Double,Double)
masod a b c = ((-b+d)/(2*a),(-b-d)/(2*a))
    where d =sqrt(b*b-4*a*c)
--5.Feladat:
matches::(Int,Int)->(Int,Int)->Bool
matches (a,b) (c,d)= a==c||a==d||b==c||b==d
--6.Feladat:
len :: Floating a => (a, a) -> a
len (a,b) = sqrt(a^2+b^2)
--7.Feladat:
strech::(Int,Int)->Int->(Int,Int)
strech (a,b) c =(a*c,b*c)
--8.Feladat:
distance::Floating a => (a, a)->(a,a) -> a
distance (a,b) (c,d) = sqrt((c-a)^2+(d-b)^2)
--Beadandó
shift :: (Int,Int) -> Int -> (Int, Int)
shift (a,b) c = ((a+((b+c) `div` 60))`mod`24 ,(b+c) `mod` 60)
isEarlier :: (Int, Int)-> (Int, Int)->Bool
isEarlier (a,b) (c,d) =a<c || b<d && a==c
createEvent::(Int,Int)->Int->String->((Int,Int),(Int,Int),String)
createEvent (a,b) c d =((a,b),shift (a,b) c,d)