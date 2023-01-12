#ifndef BASE__H
#define BASE__H

#include <iostream>

class Base
{
public:
    virtual void f() const { std::cout << "Base" <<std::endl;}
    virtual ~Base() {}
};

class Der:public Base
{
public:
    virtual void f() const { std::cout << "Der" <<std::endl; }
    virtual ~Der(){};
};

#endif
