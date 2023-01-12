#include <iostream>
#include <string>

using namespace std;

int *find(int i) { return &i; }
int index(int i) { return i; }

int main()
{
    long a = (long)index(3);
    short s = (short)index(2 ^ 32);
    // C -t nem zavarná ez a cast
    // long l  = static_cast<long>(find(3));

    long l = reinterpret_cast<long>(find(3));

    //////////////////////////////////////////
    int i = 5;
    long b = reinterpret_cast<long>(&i);
    string* str = reinterpret_cast<string*>(&i); 
    std::cout << &i << std::endl;
    std::cout << b << std::endl;
    std::cout << str << std::endl;
}