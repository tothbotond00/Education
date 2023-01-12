#include <iostream>
#include "enor.h"
using namespace std;

int main()
{
    cout<<"2. kis beadando"<<endl;
    Enor t("input.txt");
    t.first();
    if ( t.end() ){
        cerr<<"A fileban nem voltak szamok!";
        return 1;
    }
    else {
        NumAmount max;
        max = t.current();
        for ( ; !t.end(); t.next()){
            if (t.current().amount > max.amount){
                max = t.current();
            }
        }
        cout<<endl;
        cout<<"A legtobb a(z) "<<max.number<<" szambol van : "<<max.amount<<" db van"<<endl;
        return 0;
    }
}
