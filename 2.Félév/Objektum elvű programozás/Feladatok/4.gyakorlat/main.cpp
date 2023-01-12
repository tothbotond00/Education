#include <iostream>
#include <fstream>
#include "bag.h"

using namespace std;

#define NORMAL_MODE
#ifdef NORMAL_MODE

int main()
{
    try
    {
        ifstream f("input.txt");
        if(f.fail()) {
            cerr << "File error!" << endl;
            return 1;
        }
        Bag b;
        f >> b;
        cout << b << endl;
        cout << "A leggyakoribb elem: " << b.mostFrequent() << endl;
    }
    catch (Bag::Exception exc)
    {
        if (exc == Bag::EMPTY_BAG)
        {
            cerr << "A zsak ures, tehat nincs leggyakoribb eleme" << endl;
        }
    }

    return 0;
}

#else

#define CATCH_CONFIG_MAIN
#include "catch.hpp"
#include "bag_test.h"



#endif
