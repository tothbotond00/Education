#include <iostream>
#include <iomanip>

int main()
{
    char ch;
    std::cin >> std::noskipws;
    while (std::cin >> ch)
    {
        std::cout << static_cast<char>(ch >= 'a' && ch <= 'z' ? ch - 'a' + 'A' : ch);
    }
}