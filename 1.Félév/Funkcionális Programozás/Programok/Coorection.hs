--1.Fealdat:
isValid::String->Bool
isValid [] = False 
isValid (x :[]) =False
isValid (x:y :[]) =False
isValid (z:y:x:_)
  | z=='S' && y=='M' && x==':' =True
  | z=='M' && y=='A' && x==':' =True
  | z=='T' && y=='A' && x==':' =True
  | otherwise =False
--Megjegyzés: Remélem így értetted, hogy válaszzam le az 1-feladatnál mint a headnél :D

--2.Feladat:
firstTwo:: [Bool]->Bool 
firstTwo [] = False
firstTwo (x:[]) = x==True
firstTwo (_:[]) = False
firstTwo (x:y:_) = x==True && y==False || x==False && y ==True