#ifndef MENU_H_INCLUDED
#define MENU_H_INCLUDED

#include "priorsor.h"

class Menu
{
public:
    Menu() {}
    void run();
private:
    PrQueue pq;
    int getMenuPoint();
    void putIn();
    void removeMax();
    void getMax();
    void checkEmpty();
    void write();

};

#endif // MENU_H_INCLUDED
