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
    int osszido;
    bool kiesett;
};
istream& operator>>(stringstream& in, Versenyzo& v)
{
    in>>v.nev;
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

int main()
{
    try{
        SeqInFileEnumerator<Versenyzo> in("in3.txt");
        NyeroKereses ker;
        ker.addEnumerator(&in);
        ker.run();
        if(ker.found()){
            cout<<ker.optElem().nev <<" nyerte a versenyt"<<endl;
        }else{
            cout<<"Nem volt nyertes"<<endl;
        }
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
