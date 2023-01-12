#include "complex.h"
#include "complex_impl.h"
#include <iostream>

complex::complex(double r, double i)
{
    p = new complex_impl(r, i);
}

complex::complex(const complex& rhs)
{
    p = new complex_impl( *(rhs.p) );
}

complex& complex::operator=( const complex& rhs)
{
    *p = *( rhs.p );
    return *this;
}

complex::~complex()
{
    delete p;
}

void complex::print()
{
    p->print();
}

double complex::abs()
{
    p->abs();
}

void complex::set_re(double d)
{
    p->set_re(d);
}