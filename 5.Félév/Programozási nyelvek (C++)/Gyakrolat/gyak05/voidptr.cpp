#include <iostream>
#include <string>

int main()
{
    int i = 5;
    double d = 4.5;
    std::string s = "Propba";
    void* p = &i;
    std::cout<< p << std::endl;
    p = &d; 
    std::cout<< p << std::endl;
    p = &s; 
    std::cout<< p << std::endl; 
}