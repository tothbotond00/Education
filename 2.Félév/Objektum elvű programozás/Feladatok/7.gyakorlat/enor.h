#ifndef ENOR_H_INCLUDED
#define ENOR_H_INCLUDED

#include <string>
#include <fstream>

struct Trofea
{
  std::string nev;
  std::string datum;
  std::string fajta;
  int suly;
};

class Enor
{
public:
    enum Errors { FILE_ERROR };
    Enor(const std::string& filename);

    void first() { read(); next(); }
    void next();
    bool current() const { return akt; }
    bool end() const { return _end; }

private:
    enum Status {abnorm, norm};

    std::ifstream file;
    Trofea dx;
    Status sx;
    bool akt;
    bool _end;

    void read();
};

#endif // ENOR_H_INCLUDED
