#ifndef CIRCLE_H_INCLUDED
#define CIRCLE_H_INCLUDED

#include "point.h"

class Circle
{
public:
    Circle() {}
    Circle(const Point& p , double d ) : c(p), r(d) {}
    bool contains (const Point& p) const
    {
        return c.distance(p) <= r;
    }
    const Point& getc () const { return c; }
    double getr () const { return r; }
private:
    Point c;
    double r;
};

#endif // CIRCLE_H_INCLUDED
