#include <iostream>
#include "sum.cpp"

double sum(int n, double (*a)(int));

double f(int i)
{
    return 1. / (i + 1);
}

double g(int i)
{
    return 5.5 * i;
}

int main()
{
    std::cout << sum(100, f) << std::endl;
    std::cout << sum(100, g) << std::endl;
}