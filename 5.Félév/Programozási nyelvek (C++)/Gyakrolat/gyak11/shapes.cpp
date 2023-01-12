#include <iostream>

class Shape
{
    int color;
public:
    virtual double get_area() const = 0;
    int get_color() const { return color; }
    virtual ~Shape() {}
    Shape(int c): color(c) {}
};

class Circle: public Shape
{
    double rad;
public:
    Circle(int c, double r): Shape(c),rad(r){}
    virtual double get_area() const
    {
        return rad * rad * 3.14;
    }
};

class Rectangle: public Shape
{
    double a,b;
public:
    Rectangle(int c, double r, double p): Shape(c),a(r), b(p){}
    virtual double get_area() const
    {
        return a*b;
    }
};

void print(Shape* p)
{
    std::cout << p->get_area() << p->get_color() << std::endl;
}

int main()
{
    Shape* p = new Circle(4,8.3);
    print(p);
    delete p;
    p = new Rectangle(2,3.1,23);
    print(p);
    delete p;
}