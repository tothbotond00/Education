#include <iostream>
#include <algorithm>
#include <vector>
#include <set>
#include <list>

bool is_even(int x)
{
    return 0 == x % 2;
}

struct Is_even
{
    Is_even(int i) : n(i) {}
    bool operator()(int x)
    {
        return 0 == x % n;
    }
private:
    int n;
};

template <class T, class Pred>
void predIf(const T& t, Pred p)
{
    if(p(t))
    {
        std::cout << "True" << std::endl;
    }
    else
    {
        std::cout << "False" << std::endl;
    }
}

int main()
{
    std::vector<int> v;
    v.push_back(7);
    v.push_back(11);
    v.push_back(8);
    v.push_back(3);
    std::vector<int>::iterator vi = 
        std::find_if(v.begin(),v.end(), is_even);
    if( vi != v.end())
    {
        std::cout << *vi << std::endl;
    }

    std::set<int> s;
    s.insert(15);
    s.insert(8);
    s.insert(2);
    s.insert(7);
    std::set<int>::iterator si = 
        std::find_if(s.begin(),s.end(), Is_even(2));
    if( si != s.end())
    {
        std::cout << *si << std::endl;
    }

    std::list<int> l;
    l.push_back(7);
    l.push_back(11);
    l.push_back(8);
    l.push_back(3);
    std::list<int>::iterator li = 
        std::find_if(l.begin(),l.end(), Is_even(3));
    if( li != l.end())
    {
        std::cout << *li << std::endl;
    }

    predIf(7,Is_even(7));
    predIf(7, is_even);  //de itt nem lehet paraméteresen hívni az is_even
}