#ifndef BAG_TEST_H_INCLUDED
#define BAG_TEST_H_INCLUDED

#include <vector>
#include "bag.h"

class Bag_Test : public Bag
{
public:
    Bag_Test() : Bag() {}
    std::vector<Item> getSequence() const
    {
        std::vector<Item> x;
        for(Item e : vec) {
            x.push_back(Item(e.data, e.count));
        }
        return x;
    }
};

#endif