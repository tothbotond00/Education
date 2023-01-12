#include "autos.h"
#include "benzinkut.h"
#include "toltohely.h"
#include "penztar.h"

#include <iostream>
#include <sstream>

using namespace std;

void Autos::tankol(Benzinkut* b, unsigned int sorszam, int liter)
{  
    cout << getNev() << " starts to fuel" << endl;

    Toltohely* toltohely = b->getTolto(sorszam);
    if( nullptr == toltohely ) throw WRONG_PUMP;

    toltohely->tolt(liter);
    cout << getNev() << " is fueling " << liter << " liter petrol" << endl;

    int n = b->getPenztar()->fizet(sorszam);
    cout << getNev() << " has paid " << n << " Fts" << endl;
}