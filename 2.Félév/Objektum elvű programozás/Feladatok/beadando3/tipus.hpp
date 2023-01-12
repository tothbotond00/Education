#ifndef TIPUS_H_INCLUDED
#define TIPUS_H_INCLUDED

#include "ido.hpp"

///TIPUS
class Tipus
{
public:
    virtual ~Tipus() {}
    virtual bool isTavas() const { return false;}
    virtual bool isZold() const { return false;}
    virtual bool isPuszta() const { return false;}
    virtual int ido(Ido i) const { return 0;}
    virtual int para() const { return 0; }
    virtual int also() const { return 0; }
    virtual int felso() const { return 0; }
    virtual char nev() const { return 'a';}
};

///TAVAS
class Tavas : public Tipus
{
public:
    static Tavas* peldany(){
        if(nullptr == _peldany){
        _peldany = new Tavas();
        }
        return _peldany;
    }
    bool isTavas()  const override { return true;}
    int ido (Ido i) const override {
        if (i.getIdo() == ESO){
            return 15;
        }
        else if (i.getIdo() == FELHO){
            return 3;
        }
        else {
            return -10;
        }
    }
    int para() const override { return 10; };
    virtual int also() const { return 51; }
    virtual int felso() const { return 10000; }
    virtual char nev() const { return 't';}

private:
    static Tavas* _peldany ;
};

///ZOLD
class Zold : public Tipus
{
public:
    static Zold* peldany(){
        if(nullptr == _peldany){
        _peldany = new Zold();
        }
        return _peldany;
    }
    bool isZold() const override { return true;}
    int ido(Ido i) const override {
        if (i.getIdo() == ESO){
            return 10;
        }
        else if (i.getIdo() == FELHO){
            return 2;
        }
        else {
            return -6;
        }
    }
    int para() const override { return 7; };
    virtual int also() const { return 16; }
    virtual int felso() const { return 50; }
    virtual char nev() const { return 'z';}

private:
    static Zold* _peldany;
};

///PUSZTA
class Puszta : public Tipus
{
public:
    bool isPuszta() const override { return true;}
    static Puszta* peldany(){
        if(nullptr == _peldany){
        _peldany = new Puszta();
        }
        return _peldany;
    }
    int ido(Ido i) const override {
        if (i.getIdo() == ESO){
            return 5;
        }
        else if (i.getIdo() == FELHO){
            return 1;
        }
        else {
            return -3;
        }
    }
    int para() const override { return 3; };
    virtual int also() const { return 0; }
    virtual int felso() const { return 15; }
    virtual char nev() const { return 'p';}

private:
    static Puszta* _peldany ;
};

///SINGLETON
Puszta* Puszta::_peldany = nullptr;
Zold* Zold::_peldany = nullptr;
Tavas* Tavas::_peldany = nullptr;

#endif // TIPUS_H_INCLUDED
