#include <iostream>
#include "benzinkut.h"
#include "autos.h"

using namespace std;

int main()
{
    Benzinkut b;
    Autos* a = new Autos("Autos");
    try
    {
        a->tankol(&b, 3, 25);
    }
    catch(Errors e) {
        cerr << "hiba tortent" << endl;
    }

    delete a;

    return 0;
}