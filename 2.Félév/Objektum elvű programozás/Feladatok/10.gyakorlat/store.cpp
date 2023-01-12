#include "store.h"

using namespace std;

Store::Store(const string& f1, const string& f2){
    foods = new Department(f1);
    techincal = new Department(f2);
}
