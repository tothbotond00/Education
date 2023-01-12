#ifndef COMPLEX_H_INCLUDED
#define COMPLEX_H_INCLUDED

#include <iostream>
#include "read.hpp"

inline bool valid(double a) { return true; }

class Complex
{
public:
    enum ComplexError { DIV_BY_ZERO };
    Complex () {};

    double getX() { return x; }
    double getY() { return y; }

    Complex (double a , double b) : x(a),y(b) {}

    Complex operator+ (const Complex& c);
    Complex operator- (const Complex& c);
    Complex operator* (const Complex& c);
    Complex operator/ (const Complex& c);

    friend std::ostream& operator<<(std::ostream& s, const Complex& c);
    friend std::istream& operator>>(std::istream& is, Complex& c);

private:
    double x;
    double y;
};

#endif // COMPLEX_H_INCLUDED
