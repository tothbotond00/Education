#ifndef MEGFIGYELESENOR_H_INCLUDED
#define MEGFIGYELESENOR_H_INCLUDED

#include <string>
#include <iostream>
#include <fstream>
#include <sstream>

struct Megfigyeles
{
    std::string nev; // azonositoja
    std::string allomas;
    bool voltKozel;
    unsigned int utolsoTomeg;
};

struct Meres
{
    std::string datum;
    unsigned int tomeg;
    unsigned int tavolsag;
};

class MegfigyelesEnor
{
private:
    std::ifstream file;
    bool _end;
    Megfigyeles _akt;

    void read();

public:
    enum Errors {FILE_ERROR};

    MegfigyelesEnor(const std::string& filename);
    void first();
    void next();
    bool end() const { return _end; }
    Megfigyeles current() { return _akt; }
};

#endif // MEGFIGYELESENOR_H_INCLUDED
