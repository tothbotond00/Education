#ifndef BENZINKUT_H_INCLUDED
#define BENZINKUT_H_INCLUDED

class Toltohely;
class Penztar;

#include <vector>

class Benzinkut
{
public:
    Benzinkut();
    ~Benzinkut();

    int getEgysegar() const { return _egysegar; }
    Toltohely* getTolto(unsigned int sorszam) const { return _toltok[sorszam]; }
    Penztar* getPenztar() { return _penztar; }
private:
    const int _egysegar = 400;
    std::vector<Toltohely*> _toltok;
    Penztar* _penztar;
};

#endif