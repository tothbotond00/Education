#include <iostream>

int main()
{
    int i = -3;
    unsigned int j = 2;
    if ( i < j )
    {
        std::cout << "OK" << std::endl;
    } 
    else
    {
        std::cout << "WTF?" << std::endl;
    }
    return 0;
}

//g++ - W -Wall a.cpp -> warning
//Nincs megírva int és unsinged int között <