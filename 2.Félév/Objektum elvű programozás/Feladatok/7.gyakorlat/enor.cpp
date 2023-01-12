#include "enor.h"

Enor::Enor(const std::string& filename)
{
    file.open(filename.c_str());
    if(file.fail()){
        throw FILE_ERROR;
    }
}

void Enor::next()
{
    if (!(_end = (abnorm == sx))){
        std::string nev = dx.nev;
        akt = false;
        while( nev == dx.nev && sx==norm ){
            akt = akt || dx.fajta=="MEDVE" ;
            read();
        }
    }
}

void Enor::read()
{
    file >> dx.nev >> dx.datum >> dx.fajta >> dx.suly;
    sx = file.fail() ? abnorm : norm;
}
