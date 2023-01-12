#ifndef OUTFILE_H_INCLUDED
#define OUTFILE_H_INCLUDED

#include <fstream>
#include <string>
#include "kaktusz.h"

class OutFile
{
public:
    enum Exception {FILE_ERROR};

    OutFile(const std::string& filename);
    ~OutFile() {file.close();}
    void write(const Kaktusz& e);
private:
    std::ofstream file;
};

#endif // INFILE_H_INCLUDED
