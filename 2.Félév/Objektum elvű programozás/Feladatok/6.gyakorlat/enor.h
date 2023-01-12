#ifndef ENOR_H_INCLUDED
#define ENOR_H_INCLUDED

#include <string>
#include <fstream>

class Enor
{
public:
    enum Errors {FILE_OPEN_ERROR};

    Enor (const std::string& filename);
    void first();
    void next();
    int current() const { return cur; }
    bool end() const { return _end; }
private:
    enum Status { abnorm, norm };

    std::ifstream file;
    char ch;
    Status st;

    int cur;
    bool _end;
    void read();
} ;

#endif // ENOR_H_INCLUDED
