#ifndef POINT_H_INCLUDED
#define POINT_H_INCLUDED

#include <iostream>
#include <cmath>

class Point
{
public:
    Point (double a = 0, double b = 0) : x(a), y(b) {}
    double distance (const Point& p) const
    {
        return sqrt( pow( x-p.x, 2 )+ pow( y-p.y, 2 ) );
    }
    double getX() const { return x; }
    double getY() const { return y; }
    void setPoint (double a = 0, double b = 0)
    {
        x = a;
        y = b;
    }
    Point& operator+ (const Point& p)
    {
        x +=p.x;
        y +=p.y;
        return *this;
    }
    friend std::istream& operator>>(std::istream& is, Point& p);
private:
    double x;
    double y;
};

std::istream& operator>> (std::istream& is, Point& p)
{
    if ( !(is >> p.x >> p.y) ){
        //hiba kezelés
    }
    return is;
}

#endif // POINT_H_INCLUDED
