#ifndef ENOR_H_INCLUDED
#define ENOR_H_INCLUDED

#include <fstream>
#include <string>

///Megoldashoz szukseges struktura
struct NumAmount
{
    int number;
    int amount;
};

class Enor
{
public:
    enum Errors {FILE_OPEN_ERROR};

    Enor(const std::string& filename);
    void first();
    void next();
    NumAmount current()const { return _cur; }
    bool end() const {return _end;}
private:
    enum Status {norm,abnorm};

    std::ifstream file;
    Status st;

    int num; //seged az elore olvasashoz
    bool _end;
    NumAmount _cur;
    void read();
};

#endif // ENOR_H_INCLUDED
