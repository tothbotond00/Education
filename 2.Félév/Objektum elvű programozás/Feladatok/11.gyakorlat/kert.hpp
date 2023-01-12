#pragma once

#include <vector>
#include "parcella.h"

class Kert
{
protected:
    std::vector<Parcella> parcellak;
public:
    Kert(int n)
    {
        parcellak.resize(n);
        for (int i = 0; i < n; ++i){
            parcellak[i] = new Parcella(i);
        }
    }
    ~Kert()
    {
        for (unsigned int i=0; i<parcellak.size(); ++i){
            delete parcellak[i];
        }
    }
    int getParcellaCount() const { return parcellak.size(); }
    Parcella getParcella(int i) const
    {
        if (0 <= i && i<(int)parcellak.size()){
            return parcellak[i];
        }
        return nullptr;
    }
    void ultet(int i, Noveny* n, int d)
    {
        Parcella* p = getParcella(i);
        if (nullptr != p){
            p->ultet(n,d);
        }
    }
    std::vector<int> beerett(int datum)
    {
        std::vector<int> _result;

        for (unsigned int i=0; i<parcellak.size(); ++i){
            if (nullptr != parcellak[i] && nullptr != parcellak[i]->getNoveny() && datum-parcellak[i]->getUltetesiDatum() >= parcellak[i]->getNoveny()->getEresiIdo()){
                _result.push_back(i);
            }
        }
        return _result;
    }
};
