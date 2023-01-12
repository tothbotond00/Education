#include <set>
#include <iostream>
#include <iterator>

double rate(int c , int a)
{
    return c * 100.0 / a;
}

int main()
{
    std::multiset<char> m(
        std::istream_iterator<char>(std::cin),
        std::istream_iterator<char>() );
    
    for (std::multiset<char>::iterator it = m.begin();
         it != m.end();)
    {
        int cnt = m.count(*it);
        std::cout << *it << ':'
                  << cnt
                  << " ( "
                  << rate(cnt,m.size())
                  << "% )"
                  << std::endl;
        it = m.upper_bound( *it );
    }
}