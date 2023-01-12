#pragma once

#include "noveny.h"

class Parcella
{
protected:
    int azon;
    Noveny* noveny;
    int ultetesiDatum;
public:
    Parcella(int az) : azon(az), noveny(nullptr), ultetesiDatum(0){}
    void ultet(Noveny* n, int d){ noveny = n; ultetesiDatum = d;}
    int getAzon() const { return azon; }
    Noveny* getNoveny() const { return noveny; }
    int getUltetesiDatum() const { return ultetesiDatum; }
};
