#include <iostream>
#include "kertesz.hpp"
#include "noveny.h"

using namespace std;

int main()
{
    Kert* kert = new Kert(5);
    Kertesz* kertesz = new Kertesz(kert);

    kertesz->ultet(1,Burgonya::peldany(), 3);
    kertesz->ultet(2,Burgonya::peldany(), 3);
    kertesz->ultet(4,Burgonya::peldany(), 3);

    cout<<"betakarithato: "<<endl;
    for(int i : kert->beerett(10)){
        cout<<i <<", ";
    }
    cout<< endl;

    delete kert;
    delete kertesz;

    return 0;
}
