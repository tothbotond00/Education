#ifndef PRIORSOR_H_INCLUDED
#define PRIORSOR_H_INCLUDED

#include <vector>
#include <string>
#include <iostream>
#include "read.hpp"

inline bool valid(int a) { return true; }

struct Item
{
    int pr;
    std::string data;

    Item() {}
    Item(int p, const std::string& s) : pr(p),data(s) {}

    friend std::istream& operator>>(std::istream& s,Item& e)
    {
        e.pr = read<int>("Elem prioritasa: ","Egesz szamot kell megadni!",valid);
        std::cout <<"Elembe kerulo adat: ";
        s>> e.data;
        return s;
    }
    friend std::ostream& operator<<(std::ostream& o, const Item& e)
    {
        o << "(" << e.pr <<"," << e.data << ")" <<std::endl;
        return o;
    }
    bool operator==(const Item& other)
    {
        return (pr==other.pr && data == other.data) ;
    }
};

class PrQueue
{
public:
    enum PrQueueError { EMPTY_PRQUEUE };

    void setEmpty();
    bool isEmpty() const { return vec.size() == 0; };
    void add(const Item& e );
    Item remMax();
    Item getMax() const;

    friend std::ostream& operator<<(std::ostream& s, const PrQueue& q);
private:
    std::vector<Item> vec;
    int maxindex() const;
};

#endif // PRIORSOR_H_INCLUDED
