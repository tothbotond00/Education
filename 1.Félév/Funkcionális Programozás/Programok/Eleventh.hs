--1.Feladat:
data Day = Mon | Tue | Wed | Thu | Fri | Sat | Sun
 deriving (Show,Eq) -- Enum is mehet ide-mint improgba
 
--2.Feladat:
isFirstDayOfWeek::Day->Bool
isFirstDayOfWeek Mon = True
isFirstDayOfWeek _   = False

--3.Feladat:
isWeekend:: Day ->Bool
isWeekend Sat = True
isWeekend Sun = True
isWeekend _   = False

--4.Feladat:
data Time = T Int Int
  deriving Show
--instance Show Time where
     --show (T h m) = show h ++ "."++show m 

--5.Feladat:
showTime :: Time -> String
showTime ( T h m ) = show h ++ "."++show m 

--6.Feladat:
eqTime:: Time->Time->Bool
eqTime (T h m) (T x y) = h==x && y==m

--7.Feladat:
isEarlier::Time->Time->Bool
isEarlier (T h1 m1) (T h2 m2) = h1<h2 || h1==h2 && m1<m2

--8.Feladat:
isBetween::Time->Time->Time->Bool
isBetween t1 t2 t3 = ( isEarlier t1 t2 && isEarlier t2 t3 )|| ( isEarlier t3 t2 && isEarlier t2 t1 )

--9.Feladat:
time::Int->Int->Time
time h m 
  | h<0 || h>23 =error ("time:invalid hour: "++show h)
  | m<0 || m>59 =error ("time:invalid minute: "++show m)
  |otherwise = T h m

--10.Feladat:
data USTime = AM Int Int | PM Int Int
 deriving (Show,Eq)

--11.Feladat:
showUSTime:: USTime->String
showUSTime (AM h m) = show h++"."++show m++" am"
showUSTime (PM h m) = show h++"."++show m++" pm"

--12.Feladat:
usTimeToTime::USTime->Time
usTimeToTime (AM h m) 
   | h<12      = T h m
   | otherwise = T (h-12) m 
usTimeToTime (PM h m) 
   | h<12      = T (h+12) m
   | otherwise = T h m 

--13.Feladat:
timeToUSTime::Time->USTime
timeToUSTime (T h m)
   | h ==00 = AM 12 m
   | h<12   = AM h m 
   | h==12  = PM 12 m
   | otherwise = PM (h-12) m 

--BEAD:
data Month = Jan | Feb | Mar | Apr | May | Jun | Jul | Aug | Sep | Oct | Nov | Dec

type Year = Int

numberOfDays::Year -> Month ->Int
numberOfDays _ Jan = 31
numberOfDays year Feb
   | (year `mod` 4==0 && year `mod`100/=0) || year `mod` 400==0 = 29
   |otherwise =28
numberOfDays _ Mar = 31
numberOfDays _ Apr = 30
numberOfDays _ May = 31
numberOfDays _ Jun = 30
numberOfDays _ Jul = 31
numberOfDays _ Aug = 31
numberOfDays _ Sep = 30
numberOfDays _ Oct = 31
numberOfDays _ Nov = 30
numberOfDays _ Dec = 31