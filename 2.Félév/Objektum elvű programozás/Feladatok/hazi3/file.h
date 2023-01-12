#ifndef FILE_H_INCLUDED
#define FILE_H_INCLUDED

#include "log.h"

class File : public Log
{
public:
    File(int i) : size(i) {}
    ~File() {}
    int getSize() override { return size ; }
protected:
    int size;
};


#endif // FILE_H_INCLUDED
