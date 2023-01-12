#include <iostream>

int main()
{
    int n;
    std::cin >> n;
    int v[n];
    v[0] = 0;
    return 0;
}

//g++ -W -Wall -pedantic -ansi b.cpp

/*
    Temp fileok:
    g++ -save-temps a.cpp
    includeok -> a.ii
    object file -> a.o
    assembly -> a.s
*/

//g++ -o akarmi a.cpp -> akarmi.out
//-O3 optimalizáció állítgatás
//-c megáll az opject szinten
//-l külső könyvtár kell a fordításhoz
 