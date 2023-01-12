--1.Feladat:
repeat'::a->[a]
repeat' x =x:repeat' x

--2.Feladat:
take'::Integer->[a]->[a]
take' _ [] =[]
take' 0 _=[]
take' y (x:xs) 
    | y>0 =x:take' (y-1) xs
    |otherwise=[]

--3.Feladat:
drop'::Integer->[a]->[a]
drop' _ [] = []
drop' 0 xs  = xs
drop' y (x:xs)
   |y<0 = x:xs
   |otherwise =drop' (y-1) xs

--4.Feladat:
langAndRegion::[Char]->(String,String)
langAndRegion x =(take 2 x,drop 3 x)

--5.Feladat:
zip'::[a]->[b]->[(a,b)]
zip' [] _ =[]
zip' _ [] =[]
zip' (x:xs) (y:ys) = (x,y):zip' xs ys

--6.Feladat:
unzip'::[(a,b)]->([a],[b])
unzip' [] =([],[])
unzip' (p:ps)=(fst p:fst (unzip' ps) ,snd p :snd (unzip' ps))

--7.Feladat:
empty::[Char]->[Integer]
empty [] = []
empty x = [ a | (a,b) <-zip [1..] (lines x), null b ]

--8.Feladat:
splitAt'::Int->[a]->([a],[a])
splitAt' y x =(take y x,drop y x)

--9.Feladat:
nub' :: Eq a => [a] -> [a]
nub' [] = []

--10.Feladat:

--Beadando:
isLonger :: [a] -> Int -> Bool
isLonger [] 0 = False
isLonger _ 0  = True
isLonger [] _ = False
isLonger (_:xs) y = isLonger xs (y-1)

runs::Int->[a]->[[a]]
runs _ [] = []
runs 0 x  = [x]
runs y (x:xs) = take y (x:xs):runs y (seged y (x:xs))
   where seged::Int ->[a]->[a]
         seged _ [] =[]
         seged 0 xs = xs
         seged y (_:xs) = seged (y-1) xs

join :: [a] -> [[a]] -> [a]
join _ [] = []
join _ [x]= x
join y (x:xs) = x++y++join y xs 
