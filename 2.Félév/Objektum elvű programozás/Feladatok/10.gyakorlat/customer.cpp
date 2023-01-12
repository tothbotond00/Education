#include "customer.h"
#include "product.h"
#include "store.h"
#include <fstream>
#include <iostream>
#include <string>

using namespace std;

Customer::Customer(const std::string& filename){
    ifstream file;
    file.open(filename.c_str());

    file >> name;
    string s;
    while(file >> s){
        list.push_back(s);
    }
}

void Customer::purchase(const Store& store){
    cout<< name<< " vasarlo megvette az alabbi termeket:"<<endl;
    for(string productName : list){
        Product* product;
        if(linsearch(productName,store.foods,product)){
            cart.push_back(product);
            cout << product ->getName() <<" - "<<product->getPrice() << endl;
            store.foods->takeOutFromStock(product);
        }
        else if(minsearch(productName,store.techincal,product)){
            cart.push_back(product);
            cout << product ->getName() <<" - "<<product->getPrice() << endl;
            store.techincal->takeOutFromStock(product);
        }
    }
}

bool Customer::linsearch(std::string name, Department* d, Product*& product) const{
    bool l = false;
    for (Product* p : d->stock){
        if((l = (name == p->getName()))){
            product = p;
            break;
        }
    }
    return l;
}

bool Customer::minsearch(std::string name, Department* d, Product*& product) const{
    bool l = false;
    int min;
    for(Product* p : d->stock){
        if(p->getName() != name) continue;
        else if (l){
            if (min > p->getPrice()){
                min = p->getPrice();
                product = p;
            }
        }
        else {
            l = true;
            min =  p->getPrice();
            product = p;
        }
    }
    return l;
}
