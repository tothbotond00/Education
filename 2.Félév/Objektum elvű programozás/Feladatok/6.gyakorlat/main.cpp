#include <iostream>
#include "enor.h"

using namespace std;

int main()
{
    Enor t("input.txt");

    int c = 0;
    for (t.first(); !t.end(); t.next()){
        cout<<t.current() << endl;
        if(t.current() > 12) c+=2;
        else c++;
    }

    cout<<"Result: "<<c<<endl;

    return 0;
}
