[10:15] PATAKI Norbert
 - 3 darab +/- (gyak)

[10:18] PATAKI Norbert
beadando: felev vegen

[10:18] PATAKI Norbert
szobeli vizsga, Teams-en.

[10:19] PATAKI Norbert
Irodalom:Bjarne Stroustrup: A C++ programozasi nyelvScott Meyers: Hatekony C++

[10:33] PATAKI Norbert
C/C++ programok forditasa

[10:46] PATAKI Norbert
0: begepeles
1: preprocesszoralas
2: nyelvi forditas
3: linkeles
----
4: futtatas

Linkelési hiba:
/tmp/ccEHAIBp.o: in function `main':
recmain.cpp:(.text+0xe): undefined reference to `fac(int)'
collect2: error: ld returned 1 exit status

1: cpp -> cpp
2: cpp -> obj
3: objs -> a.out

Lineklési hiba 2:
usr/bin/ld: fac.o: in function `fac(int)':
fac.cpp:(.text+0x0): multiple definition of `fac(int)'; faciter.o:faciter.cpp:(.text+0x0): first defined here
collect2: error: ld returned 1 exit status