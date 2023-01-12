#include <iostream>
#include <vector>
#include <map>

static int i = 0;

class Foo
{
public:
    Foo(){std::cout << "Def ctor " << ++i << std::endl;}
};

class A
{
public:
    A(int i) { std::cout << "Ctor " << i << std::endl; }
};

void f()
{
    //A b;
    A b(10);
    //Foo a(); <- függvény deklaráció
    Foo a;
    Foo ar[3];
    std::vector<Foo> vec(3);
    std::map<int,Foo> m;
    m[4];
}

int main()
{
    f();
}