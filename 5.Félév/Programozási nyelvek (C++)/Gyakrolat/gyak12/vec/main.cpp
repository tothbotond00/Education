#include "vector.h"
#include <iostream>

int main()
{
    const int max = 44;
    Vector<int> v(8);
    for (int i = 0; i < max; i++)
    {
        v.push_back(i);
    }
    for (int i = 0; i < v.size(); i++)
    {
        std::cout << v[i] << std::endl;   
    }

    Vector<double> s(2);
    s.push_back(31.2);
    s.push_back(31.3);
    s.push_back(31.4);
    s.push_back(31.6);
    s.push_back(31.7);
    std::cout << s.size() << std::endl; 
    for (int i = 0; i < s.size(); i++)
    {
        s[i] = 2.121*i;
    }
    Vector<double> t(s);
    Vector<double> r = s;
}