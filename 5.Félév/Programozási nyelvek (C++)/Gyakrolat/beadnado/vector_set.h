#ifndef VECTOR_SET__H
#define VECTOR_SET__H

#include <vector>
#include <algorithm>
#include <functional>

template <class T, class Comp = std::less<T>>
class vector_set
{
private:
    std::vector<T> vec;
public:
    typedef typename std::vector<T>::const_iterator iterator;

    vector_set(const std::vector<T>& v)
    {
        vec.insert(vec.begin(),v.begin(),v.end());
    }

    vector_set(const vector_set& vs)
    {
        vec.insert(vec.begin(),vs.vec.begin(),vs.vec.end());
    }

    void insert(const T& t)
    {
        vec.insert(std::lower_bound(vec.begin(), vec.end(), t, Comp()),t);
    }

    int size() const
    {
        return vec.size();
    }

    iterator find(const T& t)
    {
        iterator it = std::lower_bound(vec.begin(), vec.end(), t, Comp());
        if (it != vec.end() && *it == t) return it;
        else return vec.end();
    }

    iterator end()
    {
        return vec.end();
    }

    iterator begin()
    {
        return vec.begin();
    }
};

#endif
