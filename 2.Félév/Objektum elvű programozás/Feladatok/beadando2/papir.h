#ifndef PAPIR_H_DEFINED
#define PAPIR_H_DEFINED

#include <string>
#include <sstream>
#include <fstream>

//A feladat megoldasahoz szukseges struktura, tartalmaz minden fontos informaciot
struct Papir
{
    std::string nev;
    std::string osztaly;
    double kilo;
};

class PapirEnor
{
private:
    std::ifstream f;
    Papir _cur;
    bool _end;
public:
    enum FileErrors {IncorrectInputFile, IncorrectFileSize};
    PapirEnor(const std::string &str);
    void first() { next(); }
    void next();
    Papir current() const { return _cur; }
    bool end() const { return _end; }

};

#endif // PAPIR_H_DEFINED
