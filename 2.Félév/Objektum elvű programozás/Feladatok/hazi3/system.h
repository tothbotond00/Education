#ifndef SYSTEM_H_INCLUDED
#define SYSTEM_H_INCLUDED

#include "directory.hpp"

class System
{
public:
    System(Directory* dir) : root(dir) {}
    ~System() { delete root; }
    Directory* root;
};

#endif // SYSTEM_H_INCLUDED
