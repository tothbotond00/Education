# pragma once

#include "department.h"
#include <string>

class Store
{
public:
    Store(const std::string& f1, const std::string& f2);
    ~Store() { delete foods; delete techincal; }

    Department* foods;
    Department* techincal;
};
