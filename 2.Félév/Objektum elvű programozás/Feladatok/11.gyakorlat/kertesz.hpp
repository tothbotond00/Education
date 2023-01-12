#pragma once

#include "kert.hpp"

class Kertesz
{
public:
    Kertesz(Kert* k) : kert(k) {}
    void ultet(int azon,Noveny* noveny, int datum)
    {
        kert->getParcella(azon)->ultet(noveny,datum);
    }
    void arat(int datum)
    {
        for(int i = 0; i<kert->getParcellaCount(); i++){
                if(nullptr != kert->getParcella(i) && nullptr !=kert->getParcella(i)->getNoveny() &&
               datum - kert->getParcella(i)->getUltetesiDatum() >= kert->getParcella(i)->getNoveny()->getEresiIdo()){
                   std::cout<<i<<", ";
               }
        }
    }
private:
    Kert* kert;
};
