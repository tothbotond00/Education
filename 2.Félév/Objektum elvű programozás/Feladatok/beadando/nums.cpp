#include <iostream>
#include "nums.h"

///Fo fuggvenyek

void Nums::add(int x)
{
    unsigned int ind;
    if (isEmpty()) { numbers.push_back(x); max = x; }
    else if ( !isElement(numbers,x,ind)){
        numbers.push_back(x);
        if (max < x ) { max = x;}
    }
}


void Nums::remove(int x)
{
    unsigned int ind;
    if ( isElement(numbers,x,ind) ){
        numbers.erase(numbers.begin()+ind);
        if ( x== max && !isEmpty() ){
            max = findMax(numbers);
        }
    }
    else {
        throw NOT_EXISTENT;
    }
}

void Nums::setEmpty()
{
    numbers.clear();
    max = 0; //Mindegy hisz uresre ugyse lehet lekerdezni
}

int Nums::getMax() const
{
    if (isEmpty()){
        throw EMPTY_VEC;
    }
    else {
        return max;
    }
}

std::ostream& operator<< (std::ostream& o , const Nums& n )
{
    if (n.isEmpty()){
        o<<std::endl;
        o<<"A halmaz ures"<<std::endl;
        return o;
    }
    else {
    o<<std::endl;
    o<< "<<<<<<<<<<<<<<<<<<<<<<<<<<<"<<std::endl;
    o<< "A halmaz elemei: "<<std::endl;
    for (unsigned int i = 0; i < n.numbers.size(); ++i){
        o<<n.numbers[i]<<" ";
    }
    o<<std::endl;
    o<< "A halmaz legnagyobb eleme: "<< n.getMax()<<std::endl;
    o<< "<<<<<<<<<<<<<<<<<<<<<<<<<<<"<<std::endl;
    return o;
    }
}

///Seged fuggvenyek

bool Nums::isElement (const std::vector<int> vec ,const int x,unsigned int& ind )
{
    //Kereses
    bool l = false;
    unsigned int i = 0;
    while (!l && i< vec.size()){
        l = vec[i]==x;
        ind = i;
        ++i;
    }
    return l;
}

int Nums::findMax (const std::vector<int> vec)
{
    //Maximum kereses
    max = vec[0];
    for (unsigned int i = 1; i < vec.size(); ++i ){
        if (max < vec[i]) max = vec[i];
    }
    return max;
}
