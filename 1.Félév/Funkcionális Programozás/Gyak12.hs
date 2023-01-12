data Day = Mon | Tue | Wed | Thu | Fri | Sat | Sun
--   ^^^   ^^^   ^^^   ^^^   ^^^   ^^^   ^^^   ^^^
--   |     adatkonstruktorok
--   |
--   új adattípus neve
  deriving (Show, Eq) -- opcionális. Csak akkor kell,
                      -- ha ki akarjuk íratni a napokat.
intToDay :: Int -> Day
intToDay 0 = Mon
intToDay 1 = Tue
intToDay 2 = Wed
intToDay 3 = Thu
intToDay 4 = Fri
intToDay 5 = Sat
intToDay 6 = Sun

dayToInt :: Day -> Int
dayToInt Mon = 0
dayToInt Tue = 1
dayToInt Wed = 2
dayToInt Thu = 3
dayToInt Fri = 4
dayToInt Sat = 5
dayToInt Sun = 6

instance Enum Day where
  -- a kulcs a végtelen listához ([Mon .. ]) a maradékos osztás
  -- mely leképezi a számokat a 0..6 intervallumra
  toEnum n = intToDay (n `mod` 7)
  fromEnum d = dayToInt d

isWeekend :: Day -> Bool
isWeekend Sat = True
isWeekend Sun = True
isWeekend _   = False

isWeekend' :: Day -> Bool
isWeekend' d = d == Sat || d == Sun

nextDay :: Day -> Day
nextDay Mon = Tue
nextDay Tue = Wed
nextDay Wed = Thu
nextDay Thu = Fri
nextDay Fri = Sat
nextDay Sat = Sun
nextDay Sun = Mon

-- T :: Int -> Int -> Time
data Time = T Int Int
  deriving (Show, Eq, Ord)

isEarlier :: Time -> Time -> Bool
isEarlier t1 t2 = t1 < t2

-- deriving Show hatása:
-- instance Show Time where
--   show (T h m) = "T " ++ show h ++ " " ++ show m

instance Show Time where
  show (T h m) = show h ++ ":" ++ show m

showTime :: Time -> String
showTime (T h m) = show h ++ ":" ++ show m

-- isBetween: jól jön az isEarlier

time :: Int -> Int -> Time
time h m
 | h < 0 || h > 23 = error ("time: invalid hour: " ++ show h)

data USTime = AM Int Int | PM Int Int

