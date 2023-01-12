#ifndef DATUM_H_INCLUDED
#define DATUM_H_INCLUDED

#include <string>
#include <sstream>
#include <fstream>

struct Kviz
{
    std::string nev;
    std::string datum;
    double pont;
};

class DatumEnor
{
private:
    std::ifstream file;
    bool _end;
    Kviz _cur;
public:
    enum Errors { FILE_OPEN_ERROR};

    DatumEnor(const std::string& filename);
    void first() { next(); }
    void next();
    Kviz current() const { return _cur; }
    bool end() const { return _end; }
};

#endif // MEGFELELT_H_INCLUDED


