#include "enor.h"
#include <iostream>

using namespace std;

Enor::Enor (const std::string& filename)
{
    file.open(filename);
    if (file.fail()){
        throw FILE_OPEN_ERROR;
    }
}

void Enor::read()
{
    file >> num;
    st = file.eof() ? abnorm : norm;
}

void Enor::first()
{
    read();
    next();
}

void Enor::next()
{
    _end = st==abnorm;
    if (!_end){
        _cur.number = num;
        _cur.amount = 1;
        while(st == norm && num == _cur.number ){
            _cur.amount++;
            read();
        }
    }
}
