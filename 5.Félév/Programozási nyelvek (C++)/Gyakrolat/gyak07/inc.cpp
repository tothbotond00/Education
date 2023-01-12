#include <iostream>

void inc(int* n)
{
    if (n)
    {
        ++(*n);   
    }
}

void inc2(int& r)
{
    ++r;
}

int main()
{
    int x = 3;

        //inc(x); ha inc(int n) -> nem változik
        //inc(0); Segmentation fault ha nincs if guard
    inc(&x);

        //inc2(0);
    inc2(x);
    std::cout << x << std::endl;
}