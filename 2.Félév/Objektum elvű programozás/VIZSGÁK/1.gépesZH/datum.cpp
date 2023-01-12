#include "datum.h"

using namespace std;
DatumEnor::DatumEnor(const std::string& filename) : file(filename)
{
    if (file.fail()){
        throw FILE_OPEN_ERROR;
    }
}

void DatumEnor::next()
{
    string sor;
    getline(file,sor);
    _end = file.fail();
    if (!_end){
        istringstream is(sor);
        is >> _cur.nev >> _cur.datum;
        _cur.pont = 0.0;
        unsigned int sorszam;
        double pont;
        while( is >> sorszam >> pont){
            _cur.pont+=pont;
        }
    }
}

