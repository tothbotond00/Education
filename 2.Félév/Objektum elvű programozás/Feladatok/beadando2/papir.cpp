#include "papir.h"

using namespace std;

///Egyszeru input txt file megnyitasa, ha nem sikerul Error dobasa
PapirEnor::PapirEnor(const string& str)
{
    f.open(str);
    if (f.fail()){
        throw IncorrectInputFile;
    }
    else if(f.peek() == ifstream::traits_type::eof()){
        throw IncorrectFileSize;
    }
}

///Next() fuggveny kidologzasa
void PapirEnor::next()
{
    string line;
    getline(f,line);
    _end = f.fail();
    if (!_end){
        //Problemas eldonteni, hogy milyen hosszu egy ember neve azert seged valtozo hasznalata sokat segit
        istringstream is(line);
        string seged;
        is >> seged;
        _cur.nev = seged;
        is >>seged;
        while (seged[0] != '1' && seged[0] != '2' && seged[0] != '3' && seged[0] != '4' ){
            _cur.nev = _cur.nev + ' ' + seged;
            is >>seged;
        }
        //Ha a kovetkezo string egy szammal kezdodik az mar biztos az osztaly sorszama
        _cur.osztaly = seged;
        _cur.kilo = 0;
        //A datum es a mennyiseg beolvasasa, mennyiseg osszegzese a _cur.kilo-ban
        string datum;
        double mennyiseg;
        for (is>>datum>>mennyiseg;!is.fail();is>>datum>>mennyiseg){
            _cur.kilo += mennyiseg;
        }
    }
}
