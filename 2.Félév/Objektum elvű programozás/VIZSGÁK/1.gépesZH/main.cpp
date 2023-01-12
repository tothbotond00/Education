#include <iostream>
#include <fstream>
#include "megfelelt.h"
#include "datum.h"
#include "hallgatok.h"

using namespace std;

///KÖZEPES SZINTÛ FELADAT
int elso (const string& filename)
{
    try {
        MegfeleltEnor t(filename);
        string nev ;
        bool l;
        for (t.first(); !t.end(); t.next()){
            if (t.current().kerdesek !=5 && t.current().pont >=(2.5) ){
                l = true;
                nev = t.current().nev;
            }
        }
        if (l){
            cout<<"Egy halgato kodja aki nem valaszolt minden kerdesre, de legalabb elert 2,5 pontot: "<<nev<<endl;
        }
        else {
            cout<<"Nem volt olyan halgato aki nem valaszolt minden kerdesre es elerert 2,5 pontot"<<endl;
        }
        return 0;
    }
    catch(MegfeleltEnor::Errors err){
        if (err==MegfeleltEnor::FILE_OPEN_ERROR){
            cerr<<"Fajl megnyitasa sikertelen!"<<endl;
            return 1;
        }
        else {
            cerr<<"Ismeretlen hiba!"<<endl;
            return 2;
        }
    }
}

///KIVÁLÓ SZINTÛ FELADAT
int masodik (const string& filename)
{
    try {
        HallgatokEnor t(filename);
        bool l = false;
        ostringstream os;
        for (t.first();!t.end();t.next()){
            if (t.current().hanyszor <= 8 ){
                l = true;
                os<<t.current().nev<<endl;
            }
        }
        if (!l){
            cout<<"Nem volt olyan hallgato aki legfeljebb 8 gyakorlaton erte el minimum a 2,5 pontot."<<endl;
        }
        else {
            cout<<"A hallgatok akik legfeljebb 8 gyakorlaton ertek el a minimum 2,5 pontot:"<<endl<<endl;
            string nevek = os.str();
            cout<<nevek;
        }
        return 0;
    }
    catch(DatumEnor::Errors err){
        if (err==DatumEnor::FILE_OPEN_ERROR){
            cerr<<"Fajl megnyitasa sikertelen!"<<endl;
            return 1;
        }
        else {
            cerr<<"Ismeretlen hiba!"<<endl;
            return 2;
        }
    }
}

int main()
{
    cout<<"OEP 1.GEPES ZH MEGOLDASA:"<<endl<<endl;
    //return elso("input2.txt");
    return masodik("input3.txt");
}
