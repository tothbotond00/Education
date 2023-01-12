#pragma once

#include <string>

class Product
{
public:
    Product(std::string nev, int ar): name(nev), price(ar) {}
    std::string getName() const { return name; }
    int getPrice() const { return price; }
private:
    std::string name;
    int price;
};
