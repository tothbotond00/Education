#include <iostream>
#include <map>

class complex
{
    double re;
    double im;
public:
    complex(double re = 0.0, double im = 0.0)
    {
        this->im = im;
        this->re = re;
    }
    void print() const
    {
        std::cout << re << '+' << im << 'i' << std::endl;
    }
};

int main()
{
    //DEF CTOR
    complex c(2.3,3.5);
    complex c2;
    std::map<int,complex> map;
    map[6];
    complex d(4.1);
    c.print();
    d.print();

    //COPY CTOR
    complex cc = c;
    complex dd(d);
    cc.print();
    dd.print();

    //= CTOR
    complex s(3.4, 123.1);
    s = d;
    s.print();
}