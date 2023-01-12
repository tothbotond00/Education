import Data.Char
--Maybe 
-- data Maybe a = Nothing | Just a

safeDiv :: Int -> Int -> Maybe Int 
safeDiv _ 0 = Nothing
safeDiv x y = Just (x `div` y)

showResult::Maybe Int -> String
showResult Nothing = "Itt nincs semmi"
showResult (Just n) ="Az eredmény: " ++show n

safeHead :: [a] ->Maybe a 
safeHead [] = Nothing
safeHead (x:_) = Just x

--Akasztófa játék

--típusszinonímák
type ABC = [Char]
type Riddle = String
type RightGuesses = [Char]
type WrongGuesses = [Char]

abc::ABC
abc = ['A'..'Z']

data State = St Riddle RightGuesses WrongGuesses
  deriving (Show,Eq,Ord)

--Érvényes-e a tipp
isValidLetter:: Char -> ABC ->Bool
isValidLetter _ [] = False
isValidLetter x (y:ys)
   |toUpper x ==y || toLower x ==y =True
   |otherwise = isValidLetter x ys  

--Kezdeti állapot
startState::ABC->String-> Maybe State
startState _ "" = Just (St [] [] [])
startState x y 
   |ok x (up y) = Just (St (up y) [] [])
   |otherwise = Nothing

ok::ABC->String->Bool
ok _ "" = True
ok abc (y:ys)
  |isValidLetter y abc  || y== ' '= ok abc ys
  |otherwise = False

up::String->String
up "" =""
up (x:xs) =toUpper x :up xs

--Betűk tippelése