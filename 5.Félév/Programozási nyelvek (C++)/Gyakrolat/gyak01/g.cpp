#include "fac.h"

void g ()
{
    fac(6);
}

//g++ -E g.cpp
//Linkelési hiba g++ g.cpp recmain.cpp
//fac.h függvény törzse több helyre is kerülhet
