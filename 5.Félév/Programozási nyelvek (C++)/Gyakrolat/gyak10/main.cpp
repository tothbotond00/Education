#include "complex.h"
#include <iostream>

int main()
{
    complex c(1.2,3.1);
    c.print();
    std::cout << c.abs() << std::endl;
    complex d = c;
    d.set_re(5.5);
    std::cout << c.abs() << std::endl;
    complex e(5.6,7.5);
    e = d;
    d.set_re(6.2);
    e.print();
}