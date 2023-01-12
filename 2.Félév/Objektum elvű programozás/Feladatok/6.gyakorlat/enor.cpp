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
    file.get(ch);
    st = file.eof() ? abnorm : norm;
}

void Enor::first()
{
    read();
    next();
}

void Enor::next()
{
    while(st == norm && ( ch == ' ' || ch == '\n') ){
        read();
    }
    _end = (st==abnorm);
    if(!_end) {
        cur = 0;
        while( st == norm && ( ch != ' ' && ch != '\n')){
            cur ++;
            read();
        }
    }
}
