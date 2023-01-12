#include "penztar.h"
#include "benzinkut.h"
#include "toltohely.h"

int Penztar::fizet(unsigned int sorszam)
{
    int liter = _telephely->getTolto(sorszam)->getKijelzo();
    _telephely->getTolto(sorszam)->lenullaz();
    return liter * _telephely->getEgysegar();
}