#include "megfigyelesEnor.h"

using namespace std;

istream& operator>>(istream& in, Meres& m)
{
    return in >>m.datum>>m.tomeg>>m.tavolsag;
}

MegfigyelesEnor::MegfigyelesEnor(const string& filename) : file(filename)
{
    if(file.fail()){
        throw FILE_ERROR;
    }
}

void MegfigyelesEnor::first()
{
    next();
}

void MegfigyelesEnor::next()
{
    string sor;
    getline(file,sor);
    _end = file.fail();
    if( ! _end){
        istringstream in_sor(sor);
        in_sor >> _akt.nev >> _akt.allomas;

        Meres m;
        _akt.voltKozel = false;
        while(in_sor >> m){
            _akt.utolsoTomeg = m.tomeg;
            _akt.voltKozel = _akt.voltKozel || m.tavolsag < 2700;
        }
    }
}
