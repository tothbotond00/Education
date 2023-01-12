#include "benzinkut.h"
#include "toltohely.h"
#include "penztar.h"

Benzinkut::Benzinkut()
{
    _toltok.resize(6);
    for(unsigned int i=0; i<_toltok.size(); i++) {
        _toltok[i] = new Toltohely();
    }
    _penztar = new Penztar(this);
}

Benzinkut::~Benzinkut()
{
    for(Toltohely* t : _toltok) {
        delete t;
    }
    delete _penztar;
}