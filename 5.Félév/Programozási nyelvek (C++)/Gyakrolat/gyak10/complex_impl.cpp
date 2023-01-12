#include "complex_impl.h"
#include <iostream>
#include <cmath>

complex_impl::complex_impl(double r, double i)
{
    re = r;
    im = i;
}

void complex_impl::print()
{
    std::cout<< re << "+" << im << "i"<<std::endl;
}

double complex_impl::abs()
{
    return std::sqrt(re * re + im * im); 
}

void complex_impl::set_re(double d)
{
    re = d;
}