#include <iostream>

class X
{
    int i;
};

class Foo
{
public:
    Foo(int i) { std::cout<<"ctor: "<<i<<std::endl;}
};

class Y
{
    int j;

public:

    operator bool()
    {
        return true;
    }
};

void f(Foo f)
{
    std::cout << "f(Foo f)" << std::endl;
}

int main()
{
    int x = 5;
    // x = 0
    if (x = 8)
    {
        std::cout << "T"
                  << std::endl;
    }
    else
    {
        std::cout << "F"
                  << std::endl;
    }

    X x2;
    //!
    /* if (x2)
    {
        std::cout << "OK"
                  << std::endl;
    }*/

    f(7); //?

    Y y2;
    if (y2)
    {
        std::cout << "OK"
                  << std::endl;
    }
}