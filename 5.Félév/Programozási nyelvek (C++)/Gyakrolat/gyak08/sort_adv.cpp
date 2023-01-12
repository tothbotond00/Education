#include <iostream>
#include <set>
#include <algorithm>
#include <iterator>

int main()
{
    std::multiset<int> m(
        std::istream_iterator<int>(std::cin),
        std::istream_iterator<int>()
    );

    std::copy(m.begin(),
              m.end(),
              std::ostream_iterator<int>(std::cout, " "));
    std::endl(std::cout);
}