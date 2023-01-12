n :: Integer
n=42
s :: [Char]
s="Haskell"
inc :: Num a => a -> a
inc n =n+1
ev :: Integral a => a -> Bool
ev x=x`mod`2==0
od :: Integral a => a -> Bool
od x=x`mod`2==1
od2 :: Integral a => a -> Bool
od2 x=x`mod`2 /=0
od3 :: Integral a => a -> Bool
od3 x = not (ev x)
divides :: Integral a => a -> a -> Bool
divides x y = x`mod`y==0
area :: Num a => a -> a -> a
area x y=x*y
triangle :: (Ord a, Num a) => a -> a -> a -> Bool
triangle a b c= (a<b+c) && (b<c+a)&&(c<b+a)
pitag :: (Eq a, Num a) => a -> a -> a -> Bool
pitag a b c = a2+b2==c2
    where a2=a*a
          b2=b*b
          c2=c*c
szoko :: Integral a => a -> Bool
szoko a = (a`mod`4==0)&&(a`mod`100/=0)||(a`mod`400==0)
a :: Integer
a =32
--itt lesz a házi feladat:
max3 :: Ord a => a -> a -> a -> a
max3 a b c = max x y
    where x = max a b
          y= max a c