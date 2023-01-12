#include <iostream>
///fac.h után
#include "fac.h"
///

int fac(int);

int main ()
{
    std::cout << fac(5)
              << std::endl;
    return 0;
}

//g++ recmain.cpp 
//Linkelési hiba! - nincs fac