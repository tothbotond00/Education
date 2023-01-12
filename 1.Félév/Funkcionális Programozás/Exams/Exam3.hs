--1.Feladat
idModDiv :: Int -> Int -> ((Int, Int), Int, Int)
idModDiv x y = ((x,y),x `mod` y, x `div` y)

--2.Feladat
isAmazing :: String -> Bool
isAmazing x
  | take 5 x =="Clean"   || take 7 x =="Haskell" =True
  |otherwise = False

--3.Feladat
mcCarthy91 :: Int -> Int
mcCarthy91 n
  | n>100 = n-10
  |otherwise = mcCarthy91(mcCarthy91 (n+11))

--4.Feladat
truthTable :: (Bool -> Bool -> Bool) -> [[Bool]]
truthTable a = [[True,True,a True True ],[True,False,a True False],[False,True,a False True],[False,False,a False False ]]

--5.Feladat
countEmpties :: [[a]] -> Int
countEmpties [] = 0
countEmpties ([]: xs) = 1+ countEmpties xs
countEmpties (x:xs) = countEmpties xs

--6.Feladat???

--7.Feladat
freq :: Eq a => a -> [a] -> Int
freq _ [] = 0
freq a (x:xs)
  | a==x = 1+freq a xs
  | otherwise =0+freq a xs

--8.Feladat???

--9.Feladat???

--10.Feladat
extraSpacing :: String -> String
extraSpacing [] = []
extraSpacing [x]
  | x==' ' = "   "
  |otherwise =[x]
extraSpacing (x:xs)
  |x==' ' ="   "++extraSpacing xs
  |head xs==' ' || xs==[]=x:extraSpacing xs
  |otherwise =x:' ':extraSpacing xs

--11.Feladat
maximumByWhen :: Ord b => (a -> Bool) -> (a -> b) -> [a] -> Maybe b
maximumByWhen a b x 
   |not (any a x)= Nothing
   |otherwise = Just (maximum (map b (filter a x ) ) )

--12.Feladat
arriveAt1 :: Int -> Int
arriveAt1 x 
   |x==1 = 1
   |otherwise = arrive x +arriveAt1 (x-1)
   where arrive x 
           | x==1 = 1
           | x==89 = 0
           |otherwise = arrive (toDigits x)
           where toDigits x 
                  |x==0 = 0
                  |otherwise = (x `mod ` 10)*(x `mod ` 10) + toDigits (x `div` 10) 
                     
--13.Feladat
data Vector3 = V Int Int Int
   deriving(Show,Eq)

--14.Feladat
componentSum :: Vector3 -> Int
componentSum (V x y z) =x+y+z

--15.Feladat
crossProduct :: Vector3 -> Vector3 -> Vector3
crossProduct (V a1 a2 a3) (V b1 b2 b3) = (V ((a2*b3)-(a3*b2)) (a3*b1-a1*b3) (a1*b2-a2*b1)) 

--16.Feladat
vectorListSum :: [Vector3] -> Vector3
vectorListSum xs= V (acom xs) (bcom xs) (ccom xs)
    where acom []= 0
          acom ((V a b c):xs) = a + acom xs
          bcom []= 0
          bcom ((V a b c):xs) = b + bcom xs
          ccom []= 0
          ccom ((V a b c):xs) = c + ccom xs