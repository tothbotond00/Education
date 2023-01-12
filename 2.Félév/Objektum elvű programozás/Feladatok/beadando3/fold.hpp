#ifndef FOLD_H_INCLUDED
#define FOLD_H_INCLUDED

#include <string>
#include "tipus.hpp"
#include "ido.hpp"

class Fold
{
private:
    ///ADATTAAGOK
    std::string nev;
    Tipus* tipus;
    int viz;

    ///VALTOZASOK
    void pusztul() {
        tipus = Puszta::peldany();
    }
    void zoldul() {
        tipus = Zold::peldany();
    }
    void tavasodik() {
        tipus = Tavas::peldany();
    }
    void addToViz(int value){
        viz +=value;
        if (viz < 0) viz = 0;
        if (tipus->also() > viz ){
            if (tipus->isTavas()) zoldul();
            else pusztul();
        }
        else if (tipus->felso() <viz){
            if (tipus->isPuszta()) zoldul();
            else tavasodik();
        }
    }

public:
    ///ERRORS
    enum FoldErrors {IncorrectViz};
    ///KONSTRUKTOR
    Fold(std::string n, char t, int v) : nev(n), viz(v) {
        if (viz < 0){
            throw IncorrectViz;
        }
        if (t == 't'){
            tipus = Tavas::peldany();
        }
        else if (t == 'z'){
            tipus = Zold::peldany();
        }
        else {
            tipus = Puszta::peldany();
        }
    }

    ///GETTEREK
    std::string getNev() const { return nev; }
    int getViz() const { return viz; }
    Tipus* getTipus() const { return tipus; }


    ///FO FUGGVENY
    void valtozik(Ido& i){
        addToViz(tipus->ido(i) );
        i.addToPara(tipus->para());
    }
};

#endif // FOLD_H_INCLUDED
