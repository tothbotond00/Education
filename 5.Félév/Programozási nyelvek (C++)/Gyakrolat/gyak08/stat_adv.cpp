#include <map>
#include <iterator>
#include <iostream>
#include <algorithm>

struct Print
{
    int a;

public:

    Print(int i ){ a = i; }

    void operator()(const std::pair<char,int>& p)
    {   
    std::cout << p.first 
              << ':' 
              <<  p.second
              << "( "
              << 100.0 * p.second / a
              << " % )" 
              << std::endl;
    }
};

int main()
{
    std::map<char, int> stat;
    char ch;
    int a = 0;
    while(std::cin >> ch) {
        ++stat[ch];
        ++a;
    };
    std::for_each(stat.begin(), stat.end(),Print(a));
}