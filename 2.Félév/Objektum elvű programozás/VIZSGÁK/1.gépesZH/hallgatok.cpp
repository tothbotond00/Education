#include "hallgatok.h"

using namespace std;

HallgatokEnor::HallgatokEnor(const string& filename) : enor(filename){}

void HallgatokEnor::first()
{
    enor.first();
    next();
}

void HallgatokEnor::next()
{
    _end = enor.end();
    if (!_end){
        _cur.nev = enor.current().nev;
        _cur.hanyszor = 0;
        while (!enor.end() &&_cur.nev == enor.current().nev){
            ///�rja ki azoknak a hallgat�knak az azonos�t�j�t, akik legfeljebb 8 gyakorlaton �rtek el 2,5 pontot!
            ///(mivel nem volt itt leagalabb vagy legfeljebb ugy ertelmeztem a feladatot hogy legalabb kell elerni a 2,5 pontot hiszen igy van ertelme)
            if (enor.current().pont >= 2.5){
                _cur.hanyszor++;
            }
            enor.next();
        }
    }
}
