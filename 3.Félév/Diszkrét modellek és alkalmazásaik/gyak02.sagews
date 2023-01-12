︠5a5a9f6c-50e2-40c1-bd27-5d1c945771f9o︠

#1 Írassuk ki illetve tároljuk listában az első 100 db köbszámot.
#alapvető adatszerkezetek : lista

L= [1,2,3]
#print(L)
L = [1..10]
#print(L)
L = [x for x in [1..10] if x > 5]
#print(L)
L = [(x^3) for x in [1..100]]
print(L)
︡1a8f1da6-bbdb-4c65-b25f-38bff236b537︡{"stdout":"[1, 8, 27, 64, 125, 216, 343, 512, 729, 1000, 1331, 1728, 2197, 2744, 3375, 4096, 4913, 5832, 6859, 8000, 9261, 10648, 12167, 13824, 15625, 17576, 19683, 21952, 24389, 27000, 29791, 32768, 35937, 39304, 42875, 46656, 50653, 54872, 59319, 64000, 68921, 74088, 79507, 85184, 91125, 97336, 103823, 110592, 117649, 125000, 132651, 140608, 148877, 157464, 166375, 175616, 185193, 195112, 205379, 216000, 226981, 238328, 250047, 262144, 274625, 287496, 300763, 314432, 328509, 343000, 357911, 373248, 389017, 405224, 421875, 438976, 456533, 474552, 493039, 512000, 531441, 551368, 571787, 592704, 614125, 636056, 658503, 681472, 704969, 729000, 753571, 778688, 804357, 830584, 857375, 884736, 912673, 941192, 970299, 1000000]\n"}︡{"done":true}
︠f39ce6b9-6064-42a9-aca2-f784c5dde664o︠

#2 Írassuk ki illetve tároljuk listában az első 100 db természetes szám közül a 3-mal oszthatóak köbét.

L = [(x^3) for x in [1..100] if x % 3 == 0]
print(L)

︡05eea6b0-5e67-48d6-ba8c-a4b3edfa9783︡{"stdout":"[27, 216, 729, 1728, 3375, 5832, 9261, 13824, 19683, 27000, 35937, 46656, 59319, 74088, 91125, 110592, 132651, 157464, 185193, 216000, 250047, 287496, 328509, 373248, 421875, 474552, 531441, 592704, 658503, 729000, 804357, 884736, 970299]\n"}︡{"done":true}
︠5859a331-616d-4740-861e-19e32c3500a7so︠

#3 Írjon Python/SageMath függvényt, amely sztringet kap paraméterként, és visszatér a sztring egy olyan másolatával, ahol a páros indexű karakterek duplán szerepelnek. Például az alma sztringre a helyes eredmény aalmma.

#stringek
str = "Helló World"
str +='a'
#itt csinált egy harmadikat
print(str)
#függvények
def foo (p, x) :
    return print(p + x)
foo(1,2)

def harmas(s) :
    L= ""
    for idx in [0..len(s)-1]:
        L += s[idx]
        if(idx % 2 == 0):
            L += s[idx]
    return L
harmas("Hello World")

︡464a8ef7-e2ca-4c95-9908-95026db3af6f︡{"stdout":"Helló Worlda\n"}︡{"stdout":"3\n"}︡{"stdout":"'HHellloo WWorrldd'\n"}︡{"done":true}
︠8667592e-118c-4f41-88a0-2d6e7fb6c687so︠

#4 Írjon Python/SageMath függvényt, amely sztringet kap paraméterként, és visszatér a sztring Mocking Spongebob Squarepants egy véletlenszerű példányával. Írja meg a függvény olyan változatát is, amely garantálja, hogy a mondatkezdő betű mindig nagybetű.

def sponge(string) :
    L=""
    for (idx,ch) in enumerate(string):
        if randint(0,1) == 0 or idx == 0:
            L += ch.upper()
        else:
            L += ch.lower()
    return L
print(sponge("A Python jo"))

︡40aa7e67-5b0f-4c2e-812b-c384ca89ec09︡{"stdout":"A pyTHoN JO\n"}︡{"done":true}
︠a2b4a324-0ed8-4636-8842-558e0741b227s︠


#5 Egy X halmazon értelmezett binér relációt formálisan X-beli rendezett párok halmazaként szoktunk definiálni. Írjunk eljárásokat, melyek egy ilyen formában adott relációról megmondják, hogy az reflexív ill. tranzitív-e (két külön eljárást). A bemenetük az X halmaz és a reláció

def Is_Reflexive (X,L) :
    for i in X:
        if (i,i) not in L:
            return False
    return True
Is_Reflexive({1, 2, 3}, [(1, 1), (1, 2), (2, 2)])
Is_Reflexive({1, 2, 3, 4}, [(1, 1), (2, 2), (3, 3), (4, 4)])

def Is_Transitive(X,L) :
    for a in X:
        for b in X:
            for c in X:
                if (a,b) in L and (b,c) in L and (a,c) not in L:
                    return False
    return True
Is_Transitive({1, 2, 3}, [(1,1), (1,2), (1,3), (2,1), (2,2), (2,3), (3,1), (3,2), (3,3)])
Is_Transitive({1, 2, 3, 4}, [(1, 2), (2, 3), (1, 4), (2, 2)])

︡b7c81161-1bb5-4b13-8e61-4fec3fd3a8c6︡{"stdout":"False\n"}︡{"stdout":"True\n"}︡{"stdout":"True\n"}︡{"stdout":"False\n"}︡{"done":true}









