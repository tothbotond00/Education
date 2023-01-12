#include <iostream>
#include "enor.h"

using namespace std;

int main()
{
    try {
        Enor t("inp.txt");

        bool l = true;

        for(t.first(); l && !t.end(); t.next()){
            l = t.current();
        }

        if(l){
            cout<<"Minden vadasz lott medvet!"<<endl;
        }
        else{
            cout<<"Nem minden vadasz lott medvet!"<<endl;
        }
    }
    catch(Enor::Errors err){
        if(err == Enor::FILE_ERROR){
            cerr<<"File error"<<endl;
        }
        else{
            cerr<<"unexcepted error"<<endl;
        }
        exit(1);
    }
    return 0;
}
