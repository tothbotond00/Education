#include "department.h"
#include <fstream>

using namespace std;

Department::Department(const string& filename){
    ifstream file(filename.c_str());
    string s; int i;
    while(file >> s >> i){
        stock.push_back(new Product(s,i));
    }
}

Department::~Department()
{
    for(Product* p : stock){
        delete p;
    }
}

void Department::takeOutFromStock(Product* product)
{
    bool l = false;
    unsigned int ind;
    for (unsigned int i = 0;!l && i < stock.size(); i++){
        l = (stock[i] == product);
        ind = i;
    }
    if(l){
        delete stock[ind];
        stock.erase(stock.begin()+ind);
    }
}
