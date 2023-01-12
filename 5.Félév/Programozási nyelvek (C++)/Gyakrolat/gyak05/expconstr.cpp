#include <iostream>

struct X
{
    explicit X(int i) {std::cout<< i << std::endl; }
};

void f (X x)
{
    std::cout<< &x << std::endl;
}

int main()
{
    //f(7);
    f(X(7));
}