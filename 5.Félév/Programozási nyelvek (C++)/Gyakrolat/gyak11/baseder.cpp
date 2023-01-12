#include <iostream>
#include "base.h"

struct X: Base
{
    void f() const {std::cout <<"X" << std::endl;}
    ~X(){} 
};

void g(Base* b);

int main()
{
    Base* p = 0;
    p = new Base();
    g(p);
    delete p;
    p = new Der();
    g(p);
    delete p;
    p = new X();
    g(p);
    delete p;
}