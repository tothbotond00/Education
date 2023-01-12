#include <iostream>
#include "kaktusz.h"
#include "infile.h"
#include "outfile.h"

using namespace std;

void Feladat (const string& inputFileName, const string& honnan, const string& szin, int meret)
{
    InFile x(inputFileName);
    OutFile y ("orszag.txt");
    OutFile z ("viragszin.txt");

    bool l = true;
    Kaktusz e;
    Status sx;
    x.read(sx,e);
    while (sx ==norm){
        if (e.haza == honnan){
            y.write(e);
        }
        if (e.szin == szin){
            z.write(e);
        }
        l = l && (e.meret >= meret);
        x.read(sx,e);
    }
    if (l){
        cout<<"Minden kaktusz legalabb "<<meret<<" meretu"<<endl;
    }
    else {
        cout<<"Nem minden kaktusz legalabb "<<meret<<" meretu"<<endl;
    }
}

int main()
{
    string filename, oshaza, viragszin;
    int minmeret;

    try {
        cout<<"Oshaza: ";cin>>oshaza;
        cout<<"Virag szine: ";cin>>viragszin;
        cout<<"Vizsgalt meret: ";cin>>minmeret;
        cout<<"Input-fajl neve: ";cin>>filename;
        Feladat(filename,oshaza,viragszin,minmeret);
    }

    catch(InFile::Exception err){
        if (err==InFile::FILE_ERROR){
            cerr<<"Problema van az input fajlall"<<endl;
        }
        else {
            cerr<<"Unhandeled exception"<<endl;
        }
    }

    catch(OutFile::Exception err){
        if (err==OutFile::FILE_ERROR){
            cerr<<"Problema van az output fajlall"<<endl;
        }
        else {
            cerr<<"Unhandeled exception"<<endl;
        }
    }

    return 0;
}
