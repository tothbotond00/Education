--Példa
--pelda :: [Integer]
--pelda =[x ^ y |  (x, y) <- (zip [1, 2, 3] [4, 5, 6])]

--1.Feladat:
isPrime::Integer->Bool
isPrime x 
    | length [d |d <-[1..x],x `mod` d ==0 ]==2 =True
    |otherwise=False
isPrime'::Integer->Bool
isPrime' 1 =False
isPrime' x = check [d |d <-[1..truncate(sqrt (fromIntegral x))],x `mod` d ==0 ]
   where check [_]=True
         check _     =False

--2.Feladat:
primes::[Integer]
primes =[ x|x<-[1..],isPrime' x]

--3.Feladat:
allPositive::[Integer]->Bool
allPositive [] =True
allPositive x = null [ z | z <- x ,z<=0 ] 

--4.Feladat:
dominoes::[(Integer,Integer)]
dominoes = [(x,y)|x<-[0..6] ,y<-[x..6]]

--5.Feladat:

--6.Feladat:
alphabet::[(Char,Integer)]
alphabet = [(x,y)|(x,y)<-zip ['a'..'z'] [1..]]

--7.Feladat:
--Megoldottam:De itt baszart a program
--8.Feladat:
courses :: [([Char], [([Char], [Char], [Char])])]
courses =[("Programozasi nyelvek II.", [("Horvath", "Istvan", "BDE91E"), ("Fodros", "Aniko", "DDA3KX")]), ("Imperativ programozas", [("Nemeth", "Eniko", "ALX1K0"), ("Horvath", "Istvan", "BDE91E")]), ("Funkcionalis programozas", [("Kiss", "Elemer", "ABCDE6"), ("Nagy", "Jakab", "CDE560")])]
students::[String]
students = [z | (x,[(_,_,z)])<-courses,x=="Funkcionalis programozas"]

--9.Feladat:

--10.Feladat:

--11.Feladat:
