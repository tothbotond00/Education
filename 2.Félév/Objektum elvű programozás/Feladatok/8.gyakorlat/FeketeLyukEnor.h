#ifndef FEKETLYUKENOR_H_INCLUDED
#define FEKETLYUKENOR_H_INCLUDED

#include <string>
#include <iostream>
#include <sstream>
#include <fstream>
#include "megfigyelesEnor.h"

struct FeketeLyuk
{
    std::string nev;
    double atlagTomeg;
    bool mindigKozel;
};

class FeketeLyukEnor
{
private:
    MegfigyelesEnor enor;
    bool _end;
    FeketeLyuk _akt;
public:
    FeketeLyukEnor(const std::string& filename);

    void first();
    void next();
    FeketeLyuk current() const { return _akt; }
    bool end() const { return _end; }
};

#endif // FEKETLYUKENOR_H_INCLUDED
