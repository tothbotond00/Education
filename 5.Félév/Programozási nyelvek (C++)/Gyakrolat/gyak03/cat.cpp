#include <iostream>
#include <iomanip>

int main()
{
    char ch;
    std::cin >> std::noskipws;
    while (std::cin >> ch)
    {
        std::cout << ch;
    }
}