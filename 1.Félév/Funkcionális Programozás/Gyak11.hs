import Data.Char

-- takeWhile' :: (a -> Bool) -> [a] -> [a]

-- dropWhile' :: (a -> Bool) -> [a] -> [a] / a = Char
-- dropWhile' :: (Char -> Bool) -> [Char] -> [Char]

-- dropSpaces
-- jól jön a dropWhile

dropSpaces :: String -> String
dropSpaces s = dropWhile (\c -> c == ' ') s

-- trim
-- jól jön a dropSpaces és a reverse

trim :: String -> String
-- trim a = reverse(dropSpaces(reverse(dropSpaces a)))

-- trim a = (reverse . dropSpaces . reverse . dropSpaces) a

-- trim a = reverse . dropSpaces . reverse . dropSpaces $ a

trim a = iterate f a !! 2
  where
    f :: String -> String
    f = reverse . dropSpaces

-- iterate f a = [a, f a, f (f a), f (f (f a)), ...]

{-
(f . g) x = f (g x)

f     ::      b -> c
g     :: a -> b
f . g :: a   ->    c

(.) :: (b -> c) -> (a -> b) -> (a -> c)
f . g = \x -> f (g x)
-}

f & g = \x -> f (g x)

sqr n = n ^ 2
double n = n * 2

{-
($) :: (a -> b) -> a -> b
f $ x = f x
-}

-- monogram
-- segédfüggvény: "Selymes" -> 'S' -> "S."
--                String       Char  String
-- unwords