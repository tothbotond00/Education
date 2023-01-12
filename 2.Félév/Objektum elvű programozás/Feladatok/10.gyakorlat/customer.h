#ifndef CUSTOMER_H_INCLUDED
#define CUSTOMER_H_INCLUDED

#include <string>
#include <vector>
#include "store.h"

class Customer
{
public:
    Customer(const std::string& filename);
    void purchase(const Store& store);
private:
    std::string name;
    std::vector<std::string> list;
    std::vector<Product*> cart;

    bool linsearch(std::string name, Department* d, Product*& product) const;
    bool minsearch(std::string name, Department* d, Product*& product) const;
};

#endif // CUSTOMER_H_INCLUDED
