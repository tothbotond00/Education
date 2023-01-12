#include <iostream>
#include "priorsor.h"

using namespace std;

void PrQueue::setEmpty()
{
    vec.clear();
}
void PrQueue::add(const Item& e )
{
    vec.push_back(e);
}
Item PrQueue::remMax()
{
    if( isEmpty() ){
        throw EMPTY_PRQUEUE;
    }
    int ind = maxindex();
    Item e = vec[ind];
    vec[ind] = vec [vec.size() - 1];
    vec.pop_back();
    return e;
}
Item PrQueue::getMax() const
{
    if ( isEmpty() ){
        throw EMPTY_PRQUEUE;
    }
    int ind = maxindex();
    return vec[ind];
}
int PrQueue::maxindex() const
{
    unsigned int = 0;
    int maxkey = vec[0].pr;
    for (unsigned i = 1; i <vec.size();i++){
        if (maxkey < vec[ind].pr){
            maxkey = vec[ind.pr];
            ind = i;
        }
    }
    return ind;
}
std::ostream& operator<<(std::ostream& s, const PrQueue& q)
{
    s << "\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n";
    s << "Sor jellemzoi: ";
    s << "Elemszam: "<<q.vec.size()<<"\nTartalom: \n";
    for (unsigned int i =0; i< q.vec.size();i++){
        s << " "<<q.vec[i];
    }

    s << "\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n";
    return s;
}
