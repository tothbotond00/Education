#include <string>
#include <iostream>
#include <cstdlib>

void f(int cnt)
{
    std::string s = "Hello";
    std::string *p = new std::string("Szia");
    for (int i = 0; i < cnt; ++i)
    {
        s.push_back('!');
        p->push_back('!');
    }
    std::cout << s << std::endl;
    std::cout << p << std::endl;
    delete p;
    //std::free(p);
}

int main()
{
    f(100);
    //növeljük az cnt -t akkor nő az allocok száma
}