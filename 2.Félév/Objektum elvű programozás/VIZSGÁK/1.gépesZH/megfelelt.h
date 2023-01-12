#ifndef MEGFELELT_H_INCLUDED
#define MEGFELELT_H_INCLUDED

#include <string>
#include <sstream>
#include <fstream>

struct Vizsga
{
    std::string nev;
    unsigned int kerdesek;
    double pont;
};

class MegfeleltEnor
{
private:
    std::ifstream file;
    bool _end;
    Vizsga _cur;
public:
    enum Errors { FILE_OPEN_ERROR};

    MegfeleltEnor(const std::string& filename);
    void first() { next(); }
    void next();
    Vizsga current() const { return _cur; }
    bool end() const { return _end; }
};

#endif // MEGFELELT_H_INCLUDED

