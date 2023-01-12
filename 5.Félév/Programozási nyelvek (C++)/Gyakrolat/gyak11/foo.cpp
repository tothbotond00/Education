#include <iostream>

class Bar
{
public:
    void f() const {}
};

class Foo
{
public:
    virtual void f() const {}
};

int main()
{
    Foo f;
    Bar b;
    std::cout << sizeof(b) << std::endl; 
    std::cout << sizeof(f) << std::endl; 
}