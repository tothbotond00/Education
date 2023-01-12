#include <iostream>
#include <vector>
#include <list>

class X
{
    int i;
public:
    X(int j = 1)
    {   
        i = j;
        std::cout << "Def ctor" << i << std::endl;
    }

    ~X()
    {
        std::cout << "Dtor" << i << std::endl;
    }

    X(const X& rhs) 
    {
        std::cout << "Copy ctor " << i << std::endl;
    }

    X& operator=(const X& rhs)
    {
        std::cout << "Op = " << i << std::endl;
        return *this;
    }

    void print() const
    {
        std::cout<< i <<std::endl;
    }
};

void f()
{
    int max = 40;
    X a;
    X b = a;
    X c(b);
    a = c; //a.operator=(c)
    b.operator=(a);
    a.print();
    b.print();
    c.print();
    std::vector<X> v;
    std::list<X> l;
    for (size_t i = 0; i < max; i++)
    {
        v.push_back(a);
        l.push_back(b);
    }
}

int main()
{
    f();
}