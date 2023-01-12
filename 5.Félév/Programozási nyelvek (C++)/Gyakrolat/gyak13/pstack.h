#ifndef PSTACK__H
#define PSTACK__H
#include <functional>
#include <map>
#include <stack>
template <class T, class Pr = int, class Comp = std::less<Pr> >
class priority_stack
{
  typedef std::map<Pr, std::stack<T>, Comp> prs;
  prs ps;
public:
  void push( const Pr& p, const T& t )
  {
    ps[ p ].push( t );
  }

  int size( const Pr& p ) const
  {
    return ps.find( p )->second.size();
  }

  int size() const
  {
    int ret = 0;
    for( typename prs::const_iterator i = ps.begin();
         i != ps.end();
         ++i )
    {
      ret += i->second.size();
    }
    return ret;
  }

  const T& top() const
  {
    return ps.rbegin()->second.top();
  }

  T& top()
  {
    return ps.rbegin()->second.top();
  }

  void pop()
  {
    const Pr& p = ps.rbegin()->first;
    ps.rbegin()->second.pop();
    if ( ps.rbegin()->second.empty() )
    {
      ps.erase( p );
    }
  }
};
#endif