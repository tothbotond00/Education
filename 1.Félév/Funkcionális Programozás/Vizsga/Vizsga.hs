import Data.Char
import Data.List
import Data.String
--1.Feladat
extract :: (Int, Int) -> (Int, Int) -> (Int, Int)
extract (a,b) (c,d) = (a-c,b-d)

--2.Feladat
hasDivisibleNum :: Integral i => [i] -> i -> Bool
hasDivisibleNum [] _ = False 
hasDivisibleNum (x:xs) a
    | x `mod` a == 0 =True 
    | otherwise = hasDivisibleNum xs a

--3.Feladat
caseSwap :: Char -> Char
caseSwap x
   | isUpper x = toLower x
   | isLower x = toUpper x
   | otherwise = x

--4.Feladat
semifactor :: Integer -> Integer
semifactor 1 = 1
semifactor 2 = 2
semifactor x
  | even x = x*semifactor (x-2)
  | otherwise = x*semifactor(x-2)

--5.Feladat
functionZip :: [a] -> [(a -> b)] -> [b]
functionZip [] _ = []
functionZip _ [] = []
functionZip (x:xs) (y:ys) = y x : functionZip xs ys

--6.Feladat
countNonEmpties :: [[a]] -> Int
countNonEmpties [] = 0
countNonEmpties ([]:xs) = countNonEmpties xs
countNonEmpties (x:xs) = 1 + countNonEmpties xs

--7.Feladat
combine :: [a] -> [(a,a)]
combine [] = []
combine [x] = []
combine (x:xs) = [(x,y) | y <-xs] ++ combine xs

--8.Feladat
ap2Maybe :: (a -> b -> c) -> (Maybe a -> Maybe b -> Maybe c)
ap2Maybe _ Nothing _ = Nothing 
ap2Maybe _ _ Nothing  = Nothing
ap2Maybe f (Just x) (Just y) = Just (f x y) 

--9.Feladat
lookupPair :: (a -> b -> Bool) -> [(a,b)] -> Maybe (a,b)
lookupPair _ [] = Nothing
lookupPair f ((x,y):xs)
   |f x y==True =Just (x,y)
   |otherwise = lookupPair f xs 

--10.Feladat
reverseWords :: String -> String
reverseWords "" = ""
reverseWords x = unwords (map reverse (words x) )

--11.Feladat
safeIndex :: [a] -> Int -> Maybe a
safeIndex [] _ =Nothing 
safeIndex (x:xs) a
  | a==0 = Just x
  | a<0 = Nothing 
  |otherwise =safeIndex xs (a-1)

--12.Feladat
caesarEncrypt :: String -> Int -> String
caesarEncrypt "" _ = ""
caesarEncrypt x num = map chr (map encrypt (map ord x) )  
       where encrypt x 
              | num + x>122 = (num+x)-26
              | otherwise = num+x

--13.Feladat
advancedTrim :: (a -> Bool) -> [a] -> [a]
advancedTrim _ [] = []
advancedTrim _ [x] =[x]
advancedTrim f (x:y:xs)
    |f x && f y = advancedTrim f (x:xs)
    |otherwise =x:advancedTrim f (y:xs)

--14.Feladat
data Currency = Forint Double | Euro Double
    deriving (Eq,Show)

swapCurrency :: Currency -> Currency
swapCurrency (Euro x) = Forint (x*360)
swapCurrency (Forint x) = Euro (x/360) 

--15.Feladat
--type Transactions = [Currency]

--toEuro::Currency->Currency
--toEuro (Euro x ) = Euro x
--toEuro (Forint x) = Forint (x/360)

--backToHuf::Currency->Currency
--backToHuf (Euro x) = Euro x
--backToHuf (Forint x) = Forint (x*360)

--sortedTransactions :: Transactions -> Transactions
--sortedTransactions [] = []