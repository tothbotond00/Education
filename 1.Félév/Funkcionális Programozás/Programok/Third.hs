--Példa
not' ::Bool->Bool
not' True =False
not' False=True
--1.Feladat:
and'::Bool->Bool->Bool
and' True True =True
and' _ _ =False
--2.Feladat:
or'::Bool->Bool->Bool
or' False False =False
or' _ _=True
--3.Feladat:
xor :: Bool -> Bool->Bool
xor False False = False
xor True True =False
xor _ _ =True
--4.Feladat:
add2::Int ->Int->(Int,Int)
add2 1 1 = (0,1)
add2 a b = (a,b)
--5.Feladat:
paren :: Char-> Char->Bool
paren '(' ')' =True
paren '[' ']' =True
paren '{' '}' =True
paren _ _ = False
--6.Feladat:
calc :: (Int,Char,Int)->Int
calc (a,'+',b)=(a+b)
calc (a,'-',b)=(a-b)
calc (a,'*',b)=(a*b)
calc (a,'/',b)=(a `div` b)
--7.Feladat:
isSpace::Char->Bool
isSpace ' '=True
isSpace _=False
--Beadandó
nand::Bool->Bool->Bool
nand True True =False
nand _ _ =True

onAxis::(Int,Int)->Bool
onAxis (0,_)=True
onAxis(_,0)=True
onAxis(_,_)=False

punctuation::Char->Bool
punctuation '!'= True
punctuation '?'= True
punctuation '.'= True
punctuation _ =False