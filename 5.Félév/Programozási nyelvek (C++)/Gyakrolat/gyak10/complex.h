#ifndef COMPLEX__H
#define COMPLEX__H

class complex_impl;

class complex
{
    complex_impl* p;
public:
    complex(double , double );
    complex(const complex& );
    complex& operator=( const complex& );
    ~complex();

    void print();
    double abs();
    void set_re(double );
};

#endif