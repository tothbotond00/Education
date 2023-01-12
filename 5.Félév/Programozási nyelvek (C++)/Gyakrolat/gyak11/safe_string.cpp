#include <iostream>
#include <string>
#include <vector>

class safe_string
{

    std::string s;

public:

    safe_string(const char* p): s(p ? p : "") {}

    int size() const 
    {
        return s.size();
    }

    void push_back(char ch)
    {
        s.push_back(ch);
    }

    //.....
};

class safe_string2: public std::string
{

public:

    safe_string2(const char* p): std::string(p ? p : "") {}

};

int main()
{
    std::vector<int> v(6);
    std::cout<< v.size() << std::endl;
    std::string s("123456");
    safe_string ss(0);
    ss.push_back('a');
    safe_string2 ss2(0);
    ss2.push_back('1');
    std::cout<< s.size() << std::endl;
    std::cout<< ss.size() << std::endl;
    std::cout<< ss2.size() << std::endl;
}