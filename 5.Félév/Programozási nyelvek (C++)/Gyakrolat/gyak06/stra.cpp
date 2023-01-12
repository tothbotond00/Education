#include <cstdlib>
#include <iostream>
#include <string>

void g()
{
    int* p = (int*)std::malloc(sizeof (int));
    *p = 11;
    std::cout << *p << std::endl;
    std::free(p);
}

void f()
{
    std::string s = "Hello";
    std::string* p = (std::string*) std::malloc(sizeof(std::string));
    *p = s;
    p->push_back('a');
    std::free(p);
    //Szegmentálási hiba :( nincs alapértelmezett érték new nincs
}

int main()
{
    g();
    f();
}