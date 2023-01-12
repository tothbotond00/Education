#include <iostream>

namespace a
{
    void f()
    {
        std::cout << "Hello from namespace"
                  << std::endl;
    }
}

namespace b
{
    void f()
    {
        std::cout << "Hello from namespace 2"
                  << std::endl;
    }
}

namespace x
{
    namespace y
    {
        void f()
        {
            std::cout << "Hello from namespace 3"
                      << std::endl;
        }
    }
}

//Különböző "mangled" nevük lesz így a fv-eknek

using namespace a;
//nem lehet using namespace b ezután!!

int main()
{
    f();
    b::f();
    x::y::f();
}

/*
nspace.cpp: In function ‘int main()’:
nspace.cpp:29:6: error: call of overloaded ‘f()’ is ambiguous
   29 |     f();
      |     ~^~
nspace.cpp:5:10: note: candidate: ‘void a::f()’
    5 |     void f()
      |          ^
nspace.cpp:14:10: note: candidate: ‘void b::f()’
   14 |     void f()
      |          ^
*/