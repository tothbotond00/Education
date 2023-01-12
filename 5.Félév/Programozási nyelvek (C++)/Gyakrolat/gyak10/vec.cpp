#include <iostream>

class Vector
{
    int* p;
    int cap, s;

public:
    Vector(int i)
    {
        cap = i;
        s = i;
        p = new int[i];
        for (int i = 0; i < s; i++)
        {
            p[i] = 0;
        }
        
    }

    ~Vector()
    {
        delete [] p;
    }

    Vector(const Vector& rhs)
    {
        cap = rhs.cap;
        s = rhs.s;
        p = new int [s];
        for (int i = 0; i < s; i++)
        {
            p[i] = rhs.p[i];
        }
    }

    int size() const
    {
        return s;
    }

    int operator[] (int idx) const
    {
        return p[idx];
    }

    int& operator[] (int idx)
    {
        return p[idx];
    }

};

void f()
{
    Vector v(8);
    const Vector cv = v; //DOUBLE FREE ha nincs copy construktor 
}

int main()
{
    f();
}