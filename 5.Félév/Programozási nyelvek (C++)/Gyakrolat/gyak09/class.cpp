#include <iostream>

class X
{

};

void f()
{
    std::cout << sizeof(X) << std::endl;
    X a; //default contruált objektum
    X b; //"ctor"
    std::cout << &a << std::endl;
    std::cout << &b << std::endl;
    X* p = new X(); //default contruált objektum
    std::cout << p << std::endl;
    delete p;
    X c = a; //copy construktor
    X d(a); //copy construktor
    std::cout << &c << std::endl;
    std::cout << &d << std::endl;
    b = d; //operátor= művelet 
    std::cout << &b << std::endl;

}

int main()
{
    f();
}