#include <iostream>
#include <fstream>
#include "megfigyelesEnor.h"
#include "FeketeLyukEnor.h"

using namespace std;

int elso (const string& filename, ostream& out)
{
    try {
        MegfigyelesEnor t(filename);
        Megfigyeles maxelem;
        bool l = false;
        for (t.first();!t.end();t.next()){
            if(l && t.current().voltKozel){
                if (t.current().utolsoTomeg > maxelem.utolsoTomeg){
                    maxelem = t.current();
                }
            }
            else if (!l && t.current().voltKozel){
                maxelem = t.current();
                l = true;
            }
        }
        if (l){
            out << maxelem.nev <<endl;
        }
        else {
            out<<"Nem volt a feltetelnek megfelelo elem."<<endl;
        }
        return 0;
    }
    catch(MegfigyelesEnor::Errors err){
        if(err== MegfigyelesEnor::FILE_ERROR){
            cerr<<"fajl hiba"<<endl;
            return 1;
        }
        else {
            cerr <<"ismeretlen hiba"<<endl;
            return 2;
        }
    }
}

int masodik(const string& filename, ostream& out)
{
    try{
        out<< "Mindig 2.7M FENYEVNEL kozelebb mert fekete lyuk atlagos utolso tomege:" << endl;
        FeketeLyukEnor t (filename);
        for (t.first(); !t.end();t.next()){
            if (t.current().mindigKozel){
                out<< t.current().atlagTomeg<<endl;
            }
        }
        return 0;
    }
    catch(MegfigyelesEnor::Errors err){
        if(err== MegfigyelesEnor::FILE_ERROR){
            cerr<<"fajl hiba"<<endl;
            return 1;
        }
        else {
            cerr <<"ismeretlen hiba"<<endl;
            return 2;
        }
    }
}

int main()
{
    //return elso("input.txt",cout);
    return masodik("input.txt",cout);
}
