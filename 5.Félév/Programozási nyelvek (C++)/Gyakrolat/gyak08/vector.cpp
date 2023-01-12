#include <vector>
#include <iostream>

int main()
{
    int max = 150;
    std::vector<int> v;
    for (int i = 0; i < max; i++)
    {
        v.push_back(i);
        std::cout << &v[0] << std::endl;
    }
    //./a.out | uniq -c
}