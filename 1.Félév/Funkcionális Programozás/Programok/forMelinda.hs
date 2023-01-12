--1.Feladat
--Add meg három szám közül a legnagyobbat:
--(max3 1 2 3) == 3
--(max3 4 1000 5) == 1000
--(max3 -1 -5 -9) == -1 
max3 :: Ord a => a -> a -> a -> a

--2.Feladat
--Mondd meg hogy két tuple-nek van-e közös tagja
--(matches (1,2) (0,1)) == True
--(matches (1,2) (2,0)) == True
--(matches (1,1) (2,1)) == True
--(matches (1,0) (2,3)) == False
matches::(Int,Int)->(Int,Int)->Bool

--3.Feladat
--Csinálj egyszerű számológépet! Használd a tuple közelén lévő
--operátort a számításhoz!
--calc (1,'+',2) == 3
--calc (6,'-',4) == 2
--calc (3,'*',2) == 6
--calc (6,'/',2) == 3
calc :: (Int,Char,Int)->Int

--4.Feladat
--Mondd meg, hogy egy lista minden eleme pozitív-e
--segítség(null fv. vagy length függvény-t használhatsz)
--allPositive [1,2,3,4,5] == True
--allPositive [1,2,3,4,5,-1] == False
--allPositive [1,2,3,4,5,1000000] == True
--allPositive [] == True
allPositive::[Integer]->Bool

--5.Feladat
--Használd a számológéped egy lista minden elemére!
--calcAll[(1,'+',2),(6,'-',4)] == [3,2]
--calcAll[(3,'*',2),(6,'/',2)] == [6,3]
--calcAll[] == []
calcAll :: [(Int,Char,Int)] -> Int

--6.Feladat
--Ugyanaz mint az 5. feladat, viszont ha a számítás eredménye páros, ne kerüljön bele a listába az eredmény!
--calcOdd[(1,'+',2),(6,'-',4)] == [3]
--calcOdd[(3,'*',2),(6,'/',2)] == [3]
--calcOdd[(3,'*',2),(6,'-',4)] == []
--calcAll[] == []
calcOdd :: [(Int,Char,Int)] -> Int