#ifndef AUTOS_H_INCLUDED
#define AUTOS_H_INCLUDED

#include <string>

class Benzinkut;

enum Errors { WRONG_PUMP };

class Autos
{
public:
    Autos(const std::string& nev) : _nev(nev) {}
    std::string getNev() const { return _nev; }
    void tankol(Benzinkut* b, unsigned int sorszam, int liter);
private:
    std::string _nev;
};

#endif