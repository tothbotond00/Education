#ifndef BAG_H_INCLUDED
#define BAG_H_INCLUDED

#include <iostream>
#include <vector>

struct Item
{
    int data;
    unsigned int count;
    Item(int a, unsigned int b) : data(a), count(b) {}
    bool operator==(const Item& other) const
    {
        return data==other.data && count == other.count;
    }
};

class Bag
{
public:
    enum Exception { EMPTY_BAG };

    void clear();
    void putIn(int e);
    void takeOut(int e);
    int mostFrequent() const;
    friend std::ostream& operator<<(std::ostream& os, const Bag& bag);
    friend std::istream& operator>>(std::istream& is, Bag& bag);
protected:
    std::vector<Item> vec;
    unsigned int maxind;
    bool logsearch(int e, int& ind) const;
    int maxsearch() const;
};

#endif
