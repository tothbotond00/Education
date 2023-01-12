#ifndef TOLTOHELY_H_INCLUDED
#define TOLTOHELY_H_INCLUDED

#include "penztar.h"

class Toltohely
{
public:
    Toltohely() : _kijelzo(0) {}

    void tolt(int liter) { _kijelzo = liter; }
    int getKijelzo() const { return _kijelzo; }
    friend Penztar;
private:
    int _kijelzo;
    void lenullaz() { _kijelzo = 0; }
};

#endif