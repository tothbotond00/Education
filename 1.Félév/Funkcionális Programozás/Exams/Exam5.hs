import Data.List ( group )
--1.Feladat
type Mat2 = ((Double, Double), (Double, Double))

transpose' :: Mat2 -> Mat2
transpose' ((a,b),(c,d)) = ((a,c),(b,d))

--2.Feladat
sameParity :: [Int] -> Bool
sameParity [] = True 
sameParity [x] = True 
sameParity (x:y:xs)
   | x `mod` 2 == y `mod` 2 =sameParity (y:xs)
   | otherwise = False

--3.Feladat
clamp :: Ord a => a -> a -> a -> a
clamp a b c 
   | a<c && b>c = c
   | a>c = a
   |otherwise =b

--4.Feladat
longestChain :: String -> Int
longestChain "" = 0
longestChain x = maximum (map (\x->length x) (group x )) 

--5.Feladat
countSingletons :: [[a]] -> Int
countSingletons [] = 0
countSingletons ((x:[]):xs) = 1 + countSingletons xs
countSingletons (x:xs) = countSingletons xs

--6.Feladat
type Vec2 = (Double, Double)

vectorLength :: Vec2 -> Double
vectorLength (a,b) = sqrt (a*a+b*b)

--7.Feladat???
lengthOfPath :: [Vec2] -> Double
lengthOfPath [] = 0
lengthOfPath [x] = 0

--8.Feladat

--9.Feladat
--10.Feladat
--11.Feladat
congruent :: Int -> Int -> Int -> Maybe Int
congruent a b m 
   | f a b 1 m  /= -1 = Just (f a b 1 m)
   | otherwise = Nothing 
       where f a b x m
               | a*x `mod` m == b `mod` m = x 
               | x==100 = -1 
               | otherwise = f a b (x+1) m

--12.Feladat
--13.Feladat
data LogEntry = Error String | Message String | ResultCode Int
   deriving (Eq)
   
testLog :: [LogEntry]
testLog = [
  Message "Spawning worker thread.",
  Message "Worker thread finished.",
  ResultCode 0,
  Message "Opening input file.", 
  Message "Reading 32 bytes.", 
  Error "Not enough bytes",
  Message "Connecting to 127.0.0.1.",
  Message "Sending packets.",
  Message "Packets sent.",
  ResultCode 100,
  Message "Compiling shaders.",
  Message "Linking.",
  Message "Allocating VAO and VBO",
  Message "Drawing cube",
  ResultCode 23,
  Message "Connecting to 213.32.12.2",
  Error "Connection failed"
  ]

getResultCodes :: [LogEntry] -> [Int]
getResultCodes [] = []
getResultCodes ((ResultCode x):xs) = x:getResultCodes xs
getResultCodes ((Error x) : xs) = (-1):getResultCodes xs
getResultCodes (x:xs) = getResultCodes xs

--14.Feladat
expectedOutput1 :: [[LogEntry]]
expectedOutput1 = [
  [Message "Spawning worker thread.",Message "Worker thread finished.",ResultCode 0],
  [Message "Opening input file.",Message "Reading 32 bytes.",Error "Not enough bytes"],
  [Message "Connecting to 127.0.0.1.",Message "Sending packets.",Message "Packets sent.",ResultCode 100],
  [Message "Compiling shaders.",Message "Linking.",Message "Allocating VAO and VBO",Message "Drawing cube",ResultCode 23],
  [Message "Connecting to 213.32.12.2",Error "Connection failed"]
  ]

--15.Feladat