#include <iostream>
#include "papir.h"
#include "osztaly.h"

using namespace std;

///Az elso feladat megoldasahoz szukseges fuggveny
double firstMax(const string& nev, Papir& e)
{
    PapirEnor t(nev);
    t.first();
    double max = t.current().kilo;
    e = t.current();
    for (t.next(); !t.end();t.next() ) {
        if (t.current().kilo > max){
            max = t.current().kilo;
            e = t.current();
        }
    }
    return max;
}

///A masodik feladat megoldasahoz szukseges
bool secondSearch (const string& nev2, Osztaly& ee)
{
    OsztalyEnor t(nev2);
    bool l = false;
    for (t.first();!t.end() && !l;t.next()){
        l = t.current().szorgos;
        ee = t.current();
    }
    return l;
}

//#define NORMAL_MODE
#ifdef NORMAL_MODE

int main()
{
    ///OEP 2. NAGY BEADANDO - 22. feladat
    cout<<"2. Beadando: "<<endl;
    cout<<endl;

    ///1.Feladat:
    try{
        cout<<"Elso feladat:"<<endl;
        cout<<endl;
        string nev;
        //Fajl bekerese es egyedi felsorloval valo megoldas
        cout<<"Kerlek add meg a file nevet a feladat megoldasahoz (alapertelmezett: input.txt) : "; cin>>nev;
        Papir e;
        firstMax(nev,e);
        //Megoldas kiirasa
        cout<<endl;
        cout<<"A legtobb papirt osszegyujto: "<<e.nev<<" "<<e.osztaly<<endl;
        cout<<"O "<<e.kilo<<" kg papirt gyujtott ossze."<<endl;
    }

    //Hibakezeles hibas fajl eseten
    catch(PapirEnor::FileErrors err){
        if(err == PapirEnor::IncorrectInputFile){
            cerr<<"Ilyen nevu file nem letezik!";
        }
        else if (err == PapirEnor::IncorrectFileSize){
            cerr<<"Ures file-on nem ertelmezett a maximum kivalasztas!";
        }
        else {
            cerr<<"programhiba!";
        }
    }

    ///2.Feladat:
    try{
        cout<<endl;
        cout<<"Masodik feladat:"<<endl;
        cout<<endl;
        string nev2;
        //Fajl bekerese es egyedi felsorloval valo megoldas
        cout<<"Kerlek add meg a file nevet a masodik feladat megoldasahoz (alapertelmezett: input.txt) : "; cin>>nev2;
        Osztaly ee;
        //Megoldas kiirasa
        cout<<endl;
        if (secondSearch(nev2,ee) ){
            cout<<"Volt olyan osztaly melynek minden tanuloja min. 10 kg-ot vitt. "<<endl;
            cout<<"Ez az osztaly a(z) "<<ee.osztaly<<" volt."<<endl;
        }
        else {
            cout<<"Nem volt olyan osztaly melynek minden tanuloja min. 10kg-ot vitt."<<endl;
        }
    }

    //Hibakezeles hibas fajl eseten
    catch(PapirEnor::FileErrors err){
        if(err == PapirEnor::IncorrectInputFile){
            cerr<<"Ilyen nevu file nem letezik!";
        }
        else if (err == PapirEnor::IncorrectFileSize){
            cerr<<"Ures file! Egyik osztalybol sem indult ebben az esetben diak!";
        }
        else {
            cerr<<"programhiba!";
        }
    }

    return 0;
}
#else

#define CATCH_CONFIG_MAIN
#include "catch.hpp"

///1.	Elsõ feladat maximum keresésének tesztesetei:

TEST_CASE("i.	Üres állomány", "1a.txt") {
    Papir e;
    CHECK_THROWS(firstMax("1a.txt",e));
}

TEST_CASE("ii.	Egy elemet tartalmazó állomány", "1b.txt") {
    Papir e;
    CHECK(firstMax("1b.txt",e) == 22.0);
}

TEST_CASE("iii.	Több elemet tartalmazó állomány", "1c.txt") {
    Papir e;
    CHECK(firstMax("1c.txt",e) == 12.0);
}

TEST_CASE("i.	Elsõ helyen van a maximum", "1d.txt") {
    Papir e;
    CHECK(firstMax("1d.txt",e) == 12.0);
}

TEST_CASE("ii.	Végén van a maximum", "1e.txt") {
    Papir e;
    CHECK(firstMax("1e.txt",e) ==12.0);
}

TEST_CASE("iii.	Random helyen van a maximum", "1f.txt") {
    Papir e;
    CHECK(firstMax("1f.txt",e) == 12.0);
}

TEST_CASE("i.	Csak egy maximum van", "1c.txt") {
    Papir e;
    CHECK(firstMax("1c.txt",e) == 12.0);
}

TEST_CASE("ii.	Több maximum is van (hamarabb beolvasott elem lesz a maximum)", "1g.txt") {
    Papir e;
    CHECK(firstMax("1g.txt",e) == 12.0);
    CHECK (e.nev =="Toth Botond");
}

///2.	Egy diák a beadott papírjainak tömegének összegének (összegzés) tesztesetei:

TEST_CASE("i.	Sor amiben nincs számolandó adat", "1h.txt") {
    PapirEnor t("1h.txt");
    t.first();
    CHECK(t.current().kilo == 0.0);
}

TEST_CASE("ii.	Sor amiben egy számolandó adat", "1i.txt") {
    PapirEnor t("1i.txt");
    t.first();
    CHECK(t.current().kilo == 2.0);
}

TEST_CASE("iii.	Sor amiben sok összegzendő adat van", "1b.txt") {
    PapirEnor t("1b.txt");
    t.first();
    CHECK(t.current().kilo == 22.0);
}

///3.	Második feladat lineáris keresésének tesztesetei:

TEST_CASE("i.	Üres állomány2", "1a.txt") {
    Osztaly ee;
    CHECK_THROWS(secondSearch("1a.txt",ee));
}

TEST_CASE("ii.	Egyetlen osztály", "2a.txt") {
    Osztaly ee;
    CHECK(secondSearch("2a.txt",ee) == false );
}

TEST_CASE("iii.	Több osztály is", "2b.txt") {
    Osztaly ee;
    CHECK(secondSearch("2b.txt",ee) == true );
}

TEST_CASE("i.	Első osztály szorgos", "2c.txt") {
    Osztaly ee;
    CHECK(secondSearch("2c.txt",ee) == true );
    CHECK (ee.osztaly == "1a");
}

TEST_CASE("ii.	Csak az utolsó osztály szorgos", "2b.txt") {
    Osztaly ee;
    CHECK(secondSearch("2b.txt",ee) == true );
    CHECK(ee.osztaly == "4c");
}

TEST_CASE("i.	Van szorgos osztály", "2b.txt") {
    Osztaly ee;
    CHECK(secondSearch("2b.txt",ee) == true );
}

TEST_CASE("ii.	Nincs szorgos osztály", "2d.txt") {
    Osztaly ee;
    CHECK(secondSearch("2d.txt",ee) == false );
}

TEST_CASE("iii.	Több szorgos osztály is van", "2e.txt") {
    Osztaly ee;
    CHECK(secondSearch("2e.txt",ee) == true );
    CHECK (ee.osztaly == "1b");
}

///4.	Egy osztály szorgosságának ledöntése (optimista eldöntés) tesztesetei:

TEST_CASE("i.	Egy ember jár az osztályba", "1b.txt") {
    OsztalyEnor tt("1b.txt");
    tt.first();
    CHECK(tt.current().szorgos);
}

TEST_CASE("ii.	Több ember jár az osztályba", "2a.txt") {
    OsztalyEnor tt("2a.txt");
    tt.first();
    CHECK(!tt.current().szorgos);
}

TEST_CASE("i.	Első ember nem gyűjtött 10 kilót", "2f.txt") {
    OsztalyEnor tt("2f.txt");
    tt.first();
    CHECK(!tt.current().szorgos);
}

TEST_CASE("ii.	Utolsó ember nem gyűjtött 10 kilót", "2h.txt") {
    OsztalyEnor tt("2h.txt");
    tt.first();
    CHECK(!tt.current().szorgos);
}

TEST_CASE("iii.	Random ember nem gyűjtött 10 kilót", "2g.txt") {
    OsztalyEnor tt("2g.txt");
    tt.first();
    CHECK(!tt.current().szorgos);
}

TEST_CASE("i.	Egyik gyerek sem gyűjtött 10 kilót", "2i.txt") {
    OsztalyEnor tt("2i.txt");
    tt.first();
    CHECK(!tt.current().szorgos);
}

TEST_CASE("ii.	Egy gyerek nem gyűjtött 10 kilót", "2g.txt") {
    OsztalyEnor tt("2g.txt");
    tt.first();
    CHECK(!tt.current().szorgos);
}

TEST_CASE("iii.	Minden gyerek 10 kilót gyűjtött", "2j.txt") {
    OsztalyEnor tt("2j.txt");
    tt.first();
    CHECK(tt.current().szorgos);
}

#endif
