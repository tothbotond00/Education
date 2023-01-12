#include <iostream>

int main()
{
    //!POINTER
    int i = 1;
    int* p = &i;
    std::cout << *p << std::endl;
    *p = 2;
    std::cout << *p << std::endl;

    //REFERENCIA
    int& r = i;
    i = 3;
    std::cout << r << std::endl;
    r = 4;
    std::cout << i << std::endl;

    //KÜLÖNBSÉG
    int k = 5;
    p = &k;
    std::cout << *p << " " << i << std::endl;
    r = k;
    std::cout << k << " " << i << std::endl;
    {
        int r = 6;
        std::cout << r << " " << i << std::endl;
    }

    //NULL
    int* n = 0;
        //int& nr = 0; -> referencia nem lehet nulla!

    int* up;
        //int& ur; -> semmihez nem lehet referencia

    //KONSTANS REF
    const int& cr = i;
    r = 17;
    std::cout << cr << std::endl;
        //cr = 18;
}