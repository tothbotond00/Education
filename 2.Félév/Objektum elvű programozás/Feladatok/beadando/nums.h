#ifndef NUMS_H_INCLUDED
#define NUMS_H_INCLUDED

#include <vector>
#include <iostream>

class Nums
{
public:
    enum NumsErrors { EMPTY_VEC, NOT_EXISTENT };

    Nums() {}
    void add(int x);
    void remove(int x);
    inline bool isEmpty() const { return (numbers.size() == 0); }
    void setEmpty();
    int getMax() const ;

    friend std::ostream& operator<< (std::ostream& o , const Nums& n );
protected:
    std::vector<int>numbers;
    int max;
    bool isElement (const std::vector<int> vec ,const int x,unsigned int& ind );
    int findMax (const std::vector<int> vec);
};

#endif // NUMS_H_INCLUDED
