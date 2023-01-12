#ifndef IDO_H_INCLUDED
#define IDO_H_INCLUDED

#include <stdlib.h>
#include <time.h>

enum Fajta {NAPOS, FELHO, ESO};

class Ido
{
public:
    ///KONSTRUKTOR
    enum IdoErrors {IncorrectPara};
    Ido (int ertek) : para(ertek) {
        if(ertek < 0){
            throw IncorrectPara;
        }
        else {
            valtozik();
        }
    }
    int getPara() const { return para; }
    Fajta getIdo() const { return idojaras; }
    void addToPara(int ertek){
        if(ertek < 0) throw IncorrectPara;
        para += ertek;
        valtozik();
    }

private:
    void valtozik(){
        if(para < 40){
            idojaras = NAPOS;
        }
        else if (para > 70){
            idojaras = ESO;
            para = 30;
        }
        else {
            srand(time(NULL));
            int random;
            random = rand() % 100;
            if ((para-40)*3.3 > random ){
                idojaras = ESO;
                para = 30;
            }
            else {
                idojaras = FELHO;
            }
        }
    }
    Fajta idojaras;
    int para;
} ;

#endif // IDO_H_INCLUDED
