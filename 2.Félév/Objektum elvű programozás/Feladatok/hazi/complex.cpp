#include <iostream>
#include "complex.h"


Complex Complex::operator+ (const Complex& c)
{
Complex a (c.x+x,c.y+y);
return a;
}

Complex Complex::operator- (const Complex& c)
{
Complex a (x-c.x,y-c.y) ;
return a;
}

Complex Complex::operator* (const Complex& c)
{
Complex a ( (x*c.x)-(y*c.y) , (x*c.y)+(y*c.x) ) ;
return a;
}

std::ostream& operator<<(std::ostream& s, const Complex& c)
{
    s<<"z = "<<c.x<<" + i * "<<c.y<<std::endl;
    return s;
}

Complex Complex::operator/ (const Complex& c)
{
if (c.x == 0 && c.y == 0){
    throw DIV_BY_ZERO;
}
double r = (( (x*c.x) + (y*c.y) ) / ( (c.x*c.x) + (c.y *c.y) ));
double i = (( (y*c.x) - (x*c.y) ) / ( (c.x*c.x) + (c.y *c.y) ));
Complex a (r,i);
return a;
}

std::istream& operator>>(std::istream& is, Complex& c)
{
    c.x = read <double>("Valos resz: ","Valos szamot kell megadni!",valid);
    c.y = read <double>("Imaginarius resz: ","Valos szamot kell megadni!",valid);
    return is;
}
