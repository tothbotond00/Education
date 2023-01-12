#include "bag.h"

using namespace std;

void Bag::clear()
{
    vec.clear();
}

void Bag::putIn(int e)
{
    int ind;

    if( logsearch(e, ind) ) {
        vec[ind].count++;
        if(vec[ind].count > vec[maxind].count) {
            maxind = ind;
        }
    }
    else {
        vec.insert(vec.begin()+ind, Item(e, 1));
        if(vec.size() == 1) {
            maxind = 0;
        }
    }
}

void Bag::takeOut(int e)
{
    int ind;
    if(logsearch(e, ind)) {
        if(vec[ind].count > 1) {
            vec[ind].count--;
        }
        else {
            vec.erase(vec.begin()+ind);
        }
        if(vec.size() > 0) {
            maxind = maxsearch();
        }
    }
}

int Bag::mostFrequent() const
{
    if(vec.size() == 0) {
        throw EMPTY_BAG;
    }
    return vec[maxind].data;
}

bool Bag::logsearch(int e, int& ind) const
{
    bool l = false;
    int ah = 0;
    int fh = vec.size()-1;

    while( !l && ah <= fh ) {
        ind = (ah+fh) / 2;
        if(vec[ind].data > e) {
            fh--;
        }
        else if(vec[ind].data == e) {
            l = true;
        }
        else if(vec[ind].data < e) {
            ah++;
        }
    }
    if( !l ) {
        ind = ah;
    }
    return l;
}

int Bag::maxsearch() const
{
    if(vec.size() == 0) {
        throw EMPTY_BAG;
    }

    unsigned int max=vec[0].count;
    int ind = 0;
    for(unsigned int i=1; i<vec.size(); i++) {
        if(vec[i].count > max) {
            max = vec[i].count;
            ind = i;
        }
    }
    return ind;
}

ostream& operator<<(ostream& os, const Bag& bag)
{
    os << "***********************\n";
    os << "A zsak tartalma: \n";
    for(Item e : bag.vec) {
        os << e.data << ": " << e.count << endl;
    }
    os << "***********************\n";
    return os;
}

istream& operator>>(istream& is, Bag& bag)
{
    bag.clear();
    int e;
    while(is >> e) {
        bag.putIn(e);
    }
    return is;
}