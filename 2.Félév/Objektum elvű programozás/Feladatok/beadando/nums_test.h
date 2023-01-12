#ifndef NUMS_TEST_H_INCLUDED
#define NUMS_TEST_H_INCLUDED

#include <vector>
#include "nums.h"

class Nums_Test : public Nums
{
public:
    Nums_Test() : Nums() {}
    std::vector<int> getVector() const
    {
        std::vector<int> x;
        for (int e : numbers){
            x.push_back(e);
        }
        return x;
    }
};

#endif // NUMS_TEST_H_INCLUDED
