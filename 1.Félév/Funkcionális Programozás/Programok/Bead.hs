--Szotar
type Dictionary = [(Char, Integer)]
dictionary :: [Char] -> Dictionary
dictionary x = zip x [1..]
dictionary_az :: Dictionary
dictionary_az = dictionary ['a'..'z']
dictionary_az_AZ :: Dictionary
dictionary_az_AZ = dictionary (['a'..'z']  ++ ['A'..'Z'])

--Kereses oda vissza!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
charToNum::Dictionary->Char->Integer
charToNum [] _=0
charToNum ((x,y):xs) h 
    |h==x = y
    |otherwise = charToNum xs h
numToChar :: Dictionary -> Integer -> Char
numToChar [] _='*'
numToChar ((x,y):xs) h 
    |h==y = x
    |otherwise = numToChar xs h

--Szöveg számmá!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
translate :: Dictionary -> String -> [Integer]
translate _ "" =[]
translate p (x:[]) = charToNum p x:[]
translate p (x:xs) = charToNum p x:translate p xs

--Prim
isPrime :: Integer -> Bool
isPrime 0 = False
isPrime 1 = False
isPrime 2 = True
isPrime n = odd n && null [d | d <- [3,5..squareRoot n], n `mod` d == 0] where
  squareRoot :: Integer -> Integer
  squareRoot n = floor (sqrt (fromIntegral n))
primeList :: [Integer]
primeList = 2:[ x | x <- [3,5..], isPrime x]

--Gödelszámmá alakítás!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
encode :: Dictionary -> String -> Integer
encode [] _ = 1
encode _ [] = 1
encode d (x) = (primeList!! (length (x)-1)^ charToNum d (last x))*encode d (init x)

--Prímfaktorizáció!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
seged::Integer ->[Integer]->Integer
seged p (x:xs) 
    |p`mod`x==0 = x
    |otherwise = seged p xs
primeFactorization :: Integer -> [Integer]
primeFactorization 1 = []
primeFactorization x = seged x primeList:primeFactorization (x`div`seged x primeList)