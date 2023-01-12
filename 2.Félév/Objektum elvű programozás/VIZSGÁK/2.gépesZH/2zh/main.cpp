#include <iostream>
#include <string>
#include "./library/summation.hpp"
#include "./library/counting.hpp"
#include "./library/seqinfileenumerator.hpp"
#include "./library/stringstreamenumerator.hpp"

using namespace std;

///Strukturak
struct Beszallitas
{
    string datum;
    int db;
};

struct Bolt
{
    string nev;
    bool sok;
};

///Egyedi prog tetelek
class KonyvSum : public Summation<Beszallitas, int>
{
    int neutral() const override { return 0; }
    int add(const int& a,const int& b) const override
    {
        return a+b;
    }
    int func(const Beszallitas& b) const override { return b.db; }
};

class CoutSok : public Counting<Bolt>
{
    bool cond(const Bolt& b) const { return b.sok; }
};

///>> operatorok
istream& operator>>(stringstream& i, Bolt& b)
{
    i>>b.nev;
    StringStreamEnumerator<Beszallitas> enor(i);
    KonyvSum sum;
    sum.addEnumerator(&enor);
    sum.run();
    b.sok = sum.result() > 400;
    return i;
}

istream& operator>>(istream& i, Beszallitas& b)
{
    i>>b.datum>>b.db;
    return i;
}

///MAIN
int main()
{
    try{
        SeqInFileEnumerator<Bolt> i("in.txt");
        CoutSok counting;
        counting.addEnumerator(&i);
        counting.run();
        cout<<counting.result()<<" db bolt volt ahova tobb mint 400 konyvet szallitottak be."<<endl;
        return 0;
    }
    ///Hibakezeles
    catch(SeqInFileEnumerator<Bolt>::Exceptions err)
    {
        if(err == SeqInFileEnumerator<Bolt>::OPEN_ERROR){
            cerr<<"A file-t nem lehet megnyitni!"<<endl;
            return 1;
        }else{
            cerr<<"Ismeretlen hiba!";
            return 2;
        }
    }
}
