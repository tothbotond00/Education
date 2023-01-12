#include "outfile.h"
#include <iostream>

using namespace std;

OutFile::OutFile(const std::string& filename)
{
    file.open(filename);
    if (file.fail()){
        cerr<<filename<< "nem jott letre" << endl;
        throw FILE_ERROR;
    }
}


void OutFile::write(const Kaktusz& e)
{
    file<< e.nev <<" "
        <<e.haza <<" "
        <<e.szin <<" "
        <<e.meret <<endl;
}
