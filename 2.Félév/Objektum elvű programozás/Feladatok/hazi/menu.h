#ifndef MENU_H_INCLUDED
#define MENU_H_INCLUDED

#include "complex.h"

class Menu
{
public:
    Menu () {}
    void run();
private:
    int menuPoint();
    void plus();
    void minus();
    void multiplies();
    void divides();
};

#endif // MENU_H_INCLUDED
