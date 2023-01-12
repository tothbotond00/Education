
#include <iostream>
#include <string>
#include "./library/summation.hpp"
#include "./library/maxsearch.hpp"
#include "./library/seqinfileenumerator.hpp"
#include "./library/stringstreamenumerator.hpp"

using namespace std;

struct Kor
{
    int ido;
    bool kiesett;
    Kor(){}
    Kor(int i, bool k) : ido(i),kiesett(k){}
};
istream& operator>>(istream& in, Kor& k){
    int perc, mp; char ch;
    in >> perc>>ch>>mp>>ch;
    k.ido = perc*60+mp;
    k.kiesett = ch =='K';
    return in;
}


class KorOsszegzes : public Summation<Kor, Kor>
{
    Kor neutral() const override { return Kor(0, false);}
    Kor add(const Kor& a, const Kor& b) const override
    {
        return Kor(a.ido + b.ido,a.kiesett || b.kiesett);
    }
    Kor func(const Kor& k) const override { return k; }
};

struct Versenyzo
{
    string nev;
    string verseny; //ötöshöz
    int osszido;
    bool kiesett;
};
istream& operator>>(stringstream& in, Versenyzo& v)
{
    in>>v.nev;
    in>>v.verseny; //ötöshöz
    StringStreamEnumerator<Kor> enor(in);
    KorOsszegzes ossz;
    ossz.addEnumerator(&enor);
    ossz.run();
    v.osszido = ossz.result().ido;
    v.kiesett = ossz.result().kiesett;
    return in;
}

class NyeroKereses : public MaxSearch<Versenyzo,int,Less<int>>
{
    int func(const Versenyzo& v) const override { return v.osszido; }
    bool cond(const Versenyzo& v) const override { return !v.kiesett; }
};

//ötös

struct Verseny
{
    string nev;
    bool voltNyertes;
    Versenyzo nyertes;
};

class VersenyenNyeroKeres : public NyeroKereses
{
private:
    string verseny;
public:
    VersenyenNyeroKeres(string verseny) : verseny(verseny) {}

    void first() override {}
    bool whileCond(const Versenyzo& v) const override
    {
        return v.verseny==verseny;
    }
};

class VersenyEnor : public Enumerator<Verseny>
{
private:
    bool vege;
    Verseny akt;
    SeqInFileEnumerator<Versenyzo> x;
public:
    VersenyEnor(const string& nev) : x(nev) {}

    void first() override
    {
        x.first();
        next();
    }
    void next() override
    {
        vege = x.end();
        if(!vege)
        {
            akt.nev = x.current().verseny;
            VersenyenNyeroKeres ker(akt.nev);
            ker.addEnumerator(&x);
            ker.run();
            akt.voltNyertes = ker.found();
            if(akt.voltNyertes)
            {
                akt.nyertes = ker.optElem();
            }
        }
    }
    Verseny current() const override { return akt; }
    bool end() const override { return vege; }
};

class Kiiratas : public Summation<Verseny, ostream>
{
public:
    Kiiratas(ostream* o) : Summation(o) {}

    string func(const Verseny& v) const override
    {
        if(v.voltNyertes)
        {
            return v.nev + "versenyen" + v.nyertes.nev + "nyert\n";
        }
        return v.nev + "versenyen nem nyert senki\n";
    }
};

int main()
{
    try{
        VersenyEnor in ("in5.txt");
        Kiiratas kiir(&cout);
        kiir.addEnumerator(&in);
        kiir.run();
    }
    catch(SeqInFileEnumerator<Versenyzo>::Exceptions e)
    {
        if(e == SeqInFileEnumerator<Versenyzo>::OPEN_ERROR){
            cerr<<"Nem lehet megniytni a file-t"<<endl;
        }else{
            cerr<<"error";
        }
    }

    return 0;
}
