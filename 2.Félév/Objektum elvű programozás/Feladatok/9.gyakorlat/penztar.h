#ifndef PENZTAR_H_INCLUDED
#define PENZTAR_H_INCLUDED

class Benzinkut;

class Penztar
{
public: 
    Penztar(Benzinkut* b) : _telephely(b) {}
    int fizet(unsigned int sorszam);
private:
    Benzinkut* _telephely;
};

#endif