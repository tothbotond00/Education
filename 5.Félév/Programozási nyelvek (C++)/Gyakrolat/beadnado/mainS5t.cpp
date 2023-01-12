// A vector_set egy template osztály, amely egy halmaz adatszerkezetet ábrázol
// egy rendezett vector-ban. A vector objektumot, melyben az elemeket kell
// tárolni konstruktor paraméterként kapja meg. A vector_set template osztály
// template paramétere határozza meg az elemek típusát, melyeken a < operátor
// egy teljes rendezést definiál. A vector_set elemei nem módosíthatóak.
// Beszúrás és keresés során a vector_set a bináris keresés (binary search)
// algoritmusát alkalmazza, ezzel korlátozva az elemek összehasonlításának
// számát.

#include "vector_set.h"

#include <vector>
#include <iostream>
#include <string>
#include <algorithm>

bool is_const( int& ) { return false; }
bool is_const( const int& ) { return true; }

struct counted
{
  static int comparison;

  int value;

  counted( int val ) : value( val ) {}
};

int counted::comparison = 0;

bool operator<( counted a, counted b )
{
  ++counted::comparison;
  return a.value < b.value;
}

bool operator==( counted a, counted b )
{
  ++counted::comparison;
  return a.value == b.value;
}

bool operator!=( counted a, counted b )
{
  return !( a == b );
}

const int max = 1023;

bool check()
{
  std::vector<int> numbers;

  vector_set<int> m( numbers );

  m.insert( 100 );
  m.insert( 1 );
  m.insert( 10 );

  if ( m.size() != 3 || *m.find( 100 ) != 100 || m.find( 2 ) != m.end() )
  {
    return false;
  }

  const vector_set<int> m2( m );

  if ( m2.size() != 3 || !is_const( *m.begin() ) )
  {
    return false;
  }

  std::vector<int> numbers_copy = numbers;
  std::sort( numbers_copy.begin(), numbers_copy.end() );
  if ( numbers_copy != numbers )
  {
    return false;
  }

  std::vector<counted> cs;
  vector_set<counted> vc( cs );

  for (int i = 0; i != max; ++i)
  {
    vc.insert( i );
  }

  counted::comparison = 0;
  vc.insert( max );
  if ( counted::comparison > 20 )
  {
    return false;
  }

  counted::comparison = 0;
  vector_set<counted>::iterator i = vc.find( 892 );
  if ( *i != counted( 892 )|| counted::comparison > 20)
  {
    return false;
  }

  return true;
}

int main()
{
  std::cout
    << "Your solution is " 
    << (check() ? "" : "not ")
    << "ready for submission."
    << std::endl;
}
