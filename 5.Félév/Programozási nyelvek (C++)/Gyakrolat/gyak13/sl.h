#ifndef SL__H
#define SL__H

#include<functional>
#include<list>
#include<algorithm>

template <class T, class Comp = std::less<T> >
class SortedList
{
  std::list<T> c;
public:
  typedef typename std::list<T>::const_iterator iterator;

  template <class InputIterator>
  SortedList( InputIterator first,
              InputIterator last ): c( first, last )
  {
    c.sort( Comp() );
  }

  SortedList() { }

  void insert( const T& t )
  {
    c.insert(
      std::lower_bound( c.begin(), c.end(), t, Comp() ),
      t );
  }

  int size() const
  {
    return c.size();
  }

  const T& front() const
  {
    return c.front();
  }

  const T& back() const
  {
    return c.back();
  }

  void remove( const T& t )
  {
    std::pair<typename std::list<T>::iterator,
              typename std::list<T>::iterator> p =
      std::equal_range( c.begin(), c.end(), t, Comp() );
    c.erase( p.first, p.second );
  }

  iterator begin() const
  {
    return c.begin();
  }

  iterator end() const
  {
    return c.end();
  }
};
#endif