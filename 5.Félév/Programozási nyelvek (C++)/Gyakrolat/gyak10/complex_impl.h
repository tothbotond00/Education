#ifndef COMPLEX_IMPL__H
#define COMPLEX_IMPL__H

class complex_impl
{
    double re, im;

public:
    complex_impl(double, double);
    
    void print();
    double abs();
    void set_re(double );
};

#endif