#include <iostream>

class complex
{
    double re, im;
public:
    complex(double r, double i)
    {
        re = r;
        im = i;
    }

    void print() const
    {
        std::cout << re << " " << im << std::endl;
    }
};

template <class T>
void swap(T& a, T&b)
{
    T tmp = a;
    a = b;
    b = tmp;
}

int main()
{
    int a = 5;
    int b = 8;
    swap(a, b);
    std::cout << a << b << std::endl;

    double c = 5.5;
    double d = 12.301;
    swap(c,d);
    std::cout << c << d << std::endl;

    complex s(4.5, 7.8);
    complex t(2.1, 123.2);
    swap(s,t);
    t.print();

    //swap(std::cout, std::cerr); ez nem lehetséges
}