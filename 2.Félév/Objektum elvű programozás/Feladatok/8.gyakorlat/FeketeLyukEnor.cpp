#include "FeketeLyukEnor.h"

using namespace std;

FeketeLyukEnor::FeketeLyukEnor(const string& filename ) : enor(filename)
{

}

void FeketeLyukEnor::first()
{
    enor.first();
    next();
}

void FeketeLyukEnor::next()
{
    _end = enor.end();
    if (!_end){
        _akt.nev = enor.current().nev;
        unsigned int s = 0;
        unsigned int c = 0;
        _akt.mindigKozel = true;
        while (!enor.end() && enor.current().nev == _akt.nev){
            s = s + enor.current().utolsoTomeg;
            c++;
            _akt.mindigKozel = _akt.mindigKozel && enor.current().voltKozel;
            enor.next();
        }
        _akt.atlagTomeg = (double)s / (double)c;
    }
}
