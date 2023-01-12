#ifndef MENU_H_INCLUDED
#define MENU_H_INCLUDED

#include "nums.h"

class Menu
{
public:
    Menu() {}
    void start ();
private:
    Nums n;
    int getMenuPoint();
    void add();
    void remove();
    void setEmpty();
    void getMax();
    void write();
};

#endif // MENU_H_INCLUDED
