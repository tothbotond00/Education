# include "infile.h"
# include <iostream>
# include <sstream>

using namespace std;

InFile::InFile(const std::string& filename)
{
    file.open(filename);
    if (file.fail()){
        cerr<<filename<<" nem nyithato meg" << endl;
        throw FILE_ERROR;
    }
}

void InFile::read (Status& sx, Kaktusz& e)
{
    string sor;
    getline(file,sor);
    if (!file.fail()&& sor != ""){
        sx = norm;
        stringstream inp(sor);
        inp>> e.nev;
        inp>> e.haza;
        inp>> e.szin;
        inp>> e.meret;
    }
    else {
        sx = abnorm;
    }
}
