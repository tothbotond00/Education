#pragma once

#include <vector>
#include <string>
#include "product.h"

class Department
{
public:
    Department(const std::string& filename);
    ~Department();
    void takeOutFromStock(Product* product);
    std::vector<Product*> stock;
};
