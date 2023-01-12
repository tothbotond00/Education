#include <iostream>

int a = 4;

void f()
{
    const char* s = "blalabalbal";
    int x = 3;
    int* p = &a;
    // int* p = &x;
    std::cout << p << std::endl;
    std::free(p);
    /*
        free(): invalid pointer
        Aborted
    */
    delete (s);
    /*
        free(): invalid pointer
        Aborted
    */
}

int main()
{
    f();
}