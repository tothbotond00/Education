#ifndef OSZTALY_H_DEFINED
#define OSZTALY_H_DEFINED

#include <string>
#include "papir.h"

//A feladat megoldasahoz szukseges struktura, tartalmaz minden fontos informaciot
struct Osztaly
{
    std::string osztaly;
    bool szorgos;
};

class OsztalyEnor
{
private:
    PapirEnor tt;
    Osztaly _cur;
    bool _end;
public:
    OsztalyEnor(const std::string& str) : tt(str) { } ;
    void first() {tt.first();}
    void next();
    Osztaly current() const { return _cur; }
    bool end() const { return _end; }
};

#endif // OSZTALY_H_DEFINED
