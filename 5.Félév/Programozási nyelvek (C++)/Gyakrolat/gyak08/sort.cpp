#include <iostream>
#include <vector>
#include <iterator>
#include <algorithm>

int main()
{
    std::vector<int> v(
        std::istream_iterator<int>(std::cin),
        std::istream_iterator<int>()
    );

    std::sort(v.begin(), v.end());

    std::copy(v.begin(),
              v.end(),
              std::ostream_iterator<int>(std::cout, " "));
    std::endl(std::cout);
}