#include "osztaly.h"

using namespace std;

///Next() fuggveny kidolgozasa
void OsztalyEnor::next()
{
    _end = tt.end();
    if (!_end){
        _cur.szorgos = true;
        _cur.osztaly = tt.current().osztaly;
        for ( ; !tt.end() && tt.current().osztaly == _cur.osztaly; tt.next()){
            _cur.szorgos = _cur.szorgos && (tt.current().kilo >= 10 );
        }
    }
}
