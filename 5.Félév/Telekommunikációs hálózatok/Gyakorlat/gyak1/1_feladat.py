#!/usr/bin/env python

f = open("evszamok.txt", "r")

szokoev = "Szökőév: "
nem_szokoev = "Nem szökőév: "

for x in f:
    ev = int(x)
    if ev % 400 == 0 or (ev % 4 == 0 and not ev % 100 == 0) :
        szokoev += str(ev) + ','
    else :
        nem_szokoev += str(ev) + ','

f.close()

print(szokoev)
print(nem_szokoev)