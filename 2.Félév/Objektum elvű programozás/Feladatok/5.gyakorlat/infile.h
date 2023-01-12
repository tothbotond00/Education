#ifndef INFILE_H_INCLUDED
#define INFILE_H_INCLUDED

#include <string>
#include <fstream>
#include "kaktusz.h"

enum Status {abnorm, norm};

class InFile
{
public:
    enum Exception {FILE_ERROR};

    InFile(const std::string& filename);
    ~InFile() { file.close(); }
    void read (Status& sx, Kaktusz& e);
private:
    std::ifstream file;
};

#endif // INFILE_H_INCLUDED
