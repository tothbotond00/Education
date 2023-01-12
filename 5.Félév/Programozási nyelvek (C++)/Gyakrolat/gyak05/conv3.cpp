#include <iostream>
// s->A
// H->T
// f->g

struct Pair
{
    char from;
    char to;

    Pair(char f, char t)
    {
        from = f;
        to = t;
    }
};

// karbantartható megoldás struct
char conv( char ch)
{
    static const Pair cnv[] = {Pair('s','A'),
                               Pair('H','T'),
                               Pair('f','g')};

    for (unsigned int i = 0u;
         i < sizeof(cnv)/ sizeof(cnv[0]);
         i++)
    {
        if(cnv[i].from == ch) return cnv[i].to;
    }
    return ch;
}