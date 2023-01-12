#include "megfelelt.h"

using namespace std;

MegfeleltEnor::MegfeleltEnor(const std::string& filename) : file(filename)
{
    if (file.fail()){
        throw FILE_OPEN_ERROR;
    }
}

void MegfeleltEnor::next()
{
    string sor;
    getline(file,sor);
    _end = file.fail();
    if (!_end){
        istringstream is(sor);
        is >> _cur.nev;
        _cur.kerdesek = 0;
        _cur.pont = 0.0;
        unsigned int sorszam;
        double pont;
        while( is >> sorszam >> pont){
            _cur.kerdesek++;
            _cur.pont+=pont;
        }
    }
}
