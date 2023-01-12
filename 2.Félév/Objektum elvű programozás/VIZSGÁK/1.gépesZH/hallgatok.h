#ifndef HALLGATOK_H_INCLUDED
#define HALLGATOK_H_INCLUDED

#include <string>
#include <fstream>
#include "datum.h"

struct Hallgato
{
    std::string nev;
    unsigned int hanyszor;
};

class HallgatokEnor
{
private:
    DatumEnor enor;
    bool _end;
    Hallgato _cur;
public:
    HallgatokEnor (const std::string& filename);
    void first();
    void next();
    Hallgato current() const { return _cur; }
    bool end() const { return _end; }
};

#endif // HALLGATOK_H_INCLUDED
