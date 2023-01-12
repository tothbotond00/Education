#include <iostream>

#include "tipus.hpp"
#include "fold.hpp"
#include "ido.hpp"
#include <string>
#include <sstream>
#include <fstream>
#include <vector>

#define KOR 10

using namespace std;

enum FileErrors {IncorrectInputFile,ShortInputFile};

///VEKTOR KIIRASA
void writeVec (const vector<Fold*> kornyezet,const int i)
{
    cout<<kornyezet[i]->getNev()<<" "<<kornyezet[i]->getTipus()->nev()<<" "<<kornyezet[i]->getViz()<<endl;
}

///BEOLVASAS
void read ( const string nev, int& index, vector<Fold*>& kornyezet,Ido& idojaras)
{
    cout<<"Input file tartalma: "<<endl;
    ifstream file;
    file.open(nev);
    if (file.fail()){
        throw IncorrectInputFile;
    }
    file>>index; cout<<index<<endl;
    string n; char t; int v;
    for (int i = 0; i < index; i++){
        file>>n>>t>>v;
        kornyezet.push_back( new Fold(n,t,v) );
        writeVec(kornyezet,i);
    }
    int para; file>>para;
    cout<<para<<endl<<endl;
    idojaras.addToPara(para);
}

///KOROK LEJATSZASA (EZ ESETBEN 10 KOR)
void run (const int index, vector<Fold*>& kornyezet,Ido& idojaras,const int kor)
{
    for (int j = 0; j < kor; j++){
        cout<<j+1<<".kor:"<<endl;
        for(int i = 0; i<index; i++){
            kornyezet[i]->valtozik(idojaras);
            writeVec(kornyezet,i);
        }
        cout<<endl;
    }
}
///MAXKIVALASZTAS
int findMax(const vector<Fold*> kornyezet, const int index)
{
    if (index == 0) throw ShortInputFile;
    int max = kornyezet[0]->getViz();
    int ind = 0;
    for (int i = 0; i < index; i++){
        if (max < kornyezet[i]->getViz()){
            max = kornyezet[i]->getViz(); ind = i;
        }
    }
    return ind;
}

#define NORMAL_MODE
#ifdef NORMAL_MODE


int main()
{
    cout<<"3.beadando/1.feladat (MQH41V)"<<endl<<endl;

    try{
        ///ADATOK BEOLVASASA
        cout<<"Input .txt fajl neve: ";
        string nev;
        vector<Fold*> kornyezet;
        Ido idojaras(0);
        int index;
        cin>>nev;cout<<endl;
        read(nev,index,kornyezet,idojaras);

        ///KOROK LEJATSZASA
        run(index,kornyezet,idojaras,KOR);

        ///EGYSZERU MAXIMUM KIVALASZTAS
        int ind = findMax(kornyezet,index);
        cout<<endl<<"A legvizesebb terulet: "; writeVec(kornyezet,ind);

        ///DELETE
        for (int i=0; i<index; i++){
            delete kornyezet[i];
        }
        return 0;
    }
    catch(FileErrors e){
        if(e == IncorrectInputFile){
            cerr<<"Hiba a file megnyitasa kozben"<<endl;
            return 1;
        }
        else if (e == ShortInputFile){
            cerr<<"A maximum kivalasztashoz minimum 1 db adat kell!"<<endl;
            return 5;
        }
        else{
            cerr<<"Ismeretlen hiba!"<<endl;
            return 2;
        }
    }
    catch(Fold::FoldErrors e){
        if (e == Fold::IncorrectViz){
            cerr<<"Hibas viz ertek! 0-nal kissebb ertek nem lehet!"<<endl;
            return 3;
        }
        else{
            cerr<<"Ismeretlen hiba!"<<endl;
            return 2;
        }
    }
    catch(Ido::IdoErrors e){
        if (e == Ido::IncorrectPara){
            cerr<<"Negativ parat erteket nem lehet az idohoz adni!"<<endl;
            return 4;
        }
        else{
            cerr<<"Ismeretlen hiba!"<<endl;
            return 2;
        }
    }
}

#else

#define CATCH_CONFIG_MAIN
#include "catch.hpp"

///OSZTALYOK TESZTESETI
TEST_CASE("i.addToPara()", "Ido") {
    Ido i(30);
    CHECK_THROWS(i.addToPara(-3));
    i.addToPara(41);
    CHECK(i.getIdo() == ESO);
    CHECK(i.getPara() == 30); //Mivel esett visszaesik a para 30-ra a feladat szerint
}

TEST_CASE("ii.pusztul() / zoldul() / tavasodik()", "Fold"){
    Fold f("test1",'p',14);
    Ido i(71);
    f.valtozik(i);
    CHECK(f.getTipus()->nev() == 'z');
    Fold f2("test2",'z',49);
    Ido i2(71);
    f2.valtozik(i2);
    CHECK(f2.getTipus()->nev() == 't');
    Fold f3("test3",'t',52);
    Ido i3(30);
    f3.valtozik(i3);
    CHECK(f3.getTipus()->nev() == 'z');
    Fold f4("test4",'z',16);
    Ido i4(30);
    f4.valtozik(i4);
    CHECK(f4.getTipus()->nev() == 'p');
}

TEST_CASE("iii.addToViz()","Fold"){
    Fold f("test1",'p',0);
    Ido i(30);
    f.valtozik(i);
    CHECK(f.getViz() >= 0);
    Fold f2("test2",'p',14);
    Ido i2(71);
    f2.valtozik(i2);
    CHECK(f2.getViz() == 19);
    Fold f3("test3",'p',14);
    Ido i3(30);
    f3.valtozik(i3);
    CHECK(f3.getViz() == 11);
    Fold f4("test4",'z',16);
    Ido i4(71);
    f4.valtozik(i4);
    CHECK(f4.getViz() == 26);
    Fold f5("test5",'z',16);
    Ido i5(30);
    f5.valtozik(i5);
    CHECK(f5.getViz() == 10);
    Fold f6("test6",'t',52);
    Ido i6(71);
    f6.valtozik(i6);
    CHECK(f6.getViz() == 67);
    Fold f7("test7",'t',52);
    Ido i7(30);
    f7.valtozik(i7);
    CHECK(f7.getViz() == 42);
    //Megj. FELHO ertekkel nehez tesztelni a random ertek miatt
}

///MAIN TESZTESETEI
TEST_CASE("Beolvasas","Main"){
    vector<Fold*> test1;
    Ido idojaras(0);
    int index1;
    read("test1.txt",index1,test1,idojaras); //ha ures a file akkor automatikus 0 ertekre inicializal minden valtozot
    CHECK(index1 == 0);
    vector<Fold*> test2;
    int index2;
    read("test2.txt",index2,test2,idojaras);
    CHECK(index2 == 1);
    vector<Fold*> test3;
    int index3;
    read("test3.txt",index3,test3,idojaras);
    CHECK(index3 == 10);
    vector<Fold*> test4;
    int index4;
    CHECK_THROWS(read("test4.txt",index4,test4,idojaras));
}

TEST_CASE("findMax() intervallum","Main"){
    vector<Fold*> test1;
    int index1 = 0;
    CHECK_THROWS(findMax(test1,index1) );
    vector<Fold*> test2;
    test2.push_back(new Fold("a",'p',13)); //ez a max(0.index)
    int index2 = test2.size();
    CHECK(findMax(test2,index2) == 0);
    vector<Fold*> test3;
    test3.push_back(new Fold("a",'p',13));
    test3.push_back(new Fold("b",'z',17));
    test3.push_back(new Fold("c",'z',48));
    test3.push_back(new Fold("d",'t',52));
    test3.push_back(new Fold("e",'t',100)); //ez a max (4.index)
    int index3 = test3.size();
    CHECK(findMax(test3,index3) == 4);
}

TEST_CASE("findMax() hely szerint","Main"){
    vector<Fold*> test1;
    test1.push_back(new Fold("a",'p',13));
    test1.push_back(new Fold("b",'z',17));
    test1.push_back(new Fold("c",'z',48));
    test1.push_back(new Fold("d",'t',52));
    test1.push_back(new Fold("e",'t',100)); //ez a max (4.index)
    int index1 = test1.size();
    CHECK(findMax(test1,index1) == 4);
    vector<Fold*> test2;
    test2.push_back(new Fold("e",'t',100)); //ez a max (0.index)
    test2.push_back(new Fold("a",'p',13));
    test2.push_back(new Fold("b",'z',17));
    test2.push_back(new Fold("c",'z',48));
    test2.push_back(new Fold("d",'t',52));
    int index2 = test2.size();
    CHECK(findMax(test2,index2) == 0);
    vector<Fold*> test3;
    test3.push_back(new Fold("a",'p',13));
    test3.push_back(new Fold("b",'z',17));
    test3.push_back(new Fold("e",'t',100)); //ez a max (2.index)
    test3.push_back(new Fold("c",'z',48));
    test3.push_back(new Fold("d",'t',52));
    int index3 = test3.size();
    CHECK(findMax(test3,index3) == 2);
}

TEST_CASE("findMax() maximumok szama szerint", "Main"){
    vector<Fold*> test1;
    test1.push_back(new Fold("a",'p',13));
    test1.push_back(new Fold("b",'z',17));
    test1.push_back(new Fold("c",'z',48));
    test1.push_back(new Fold("e",'t',100)); //ez a max (3.index)
    test1.push_back(new Fold("d",'t',52));
    int index1 = test1.size();
    CHECK(findMax(test1,index1) == 3);
    vector<Fold*> test2;
    test2.push_back(new Fold("a",'p',13));
    test2.push_back(new Fold("b",'z',17));
    test2.push_back(new Fold("e2",'t',100)); //ez a max (2.index)
    test2.push_back(new Fold("c",'z',48));
    test2.push_back(new Fold("e",'t',100)); //ez is a max (4.index)
    test2.push_back(new Fold("d",'t',52));
    int index2 = test2.size();
    int ind = findMax(test2,index2);
    CHECK(ind == 2);
    CHECK(test2[ind]->getNev() == "e2");
}

#endif
