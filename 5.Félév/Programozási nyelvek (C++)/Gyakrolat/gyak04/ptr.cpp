#include <iostream>

int main()
{
    //!SIMA POINTER
    int i = 5;
    int* p = &i;
    std::cout<<  p << std::endl;
    std::cout<< *p << std::endl;
    *p = 2;
    std::cout<<  i << std::endl;

    //!CONST POINTER
    const int s = 25;
    //int* q = &s; --- constra nem szabad pointert állítani!

    const int* q = &s;
    //-*q = 23; const miatt nem lehet átírni pointeren keresztül az értéket
    std::cout<< *q << std::endl;

    q = &i;
    //-*q = 23; const miatt nem lehet átírni pointeren keresztül az értéket
    std::cout<< *q << std::endl;

    //! FIX POINTER
    int* const cp = &i;
    *cp = 15;

    std::cout<< *cp << std::endl;
    //-cp = &s; Nem mutathat más memóriaterületre a ponter
    //-cp = &i; Lényegében nem szerepelhet bal oldalon

    //! CONST FIX POINTER
    const int* const ccp = &i;
    std::cout<< *ccp << std::endl;
}