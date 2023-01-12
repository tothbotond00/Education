#include <iostream>
// s->A
// H->T
// f->g

struct Pair
{
    char from;
};

// karbantartható megoldás 
char conv( char ch)
{
    static const char from[] = {'s', 'H', 'f'};
    static const char to[]   = {'A', 'T', 'g'};

    //1.karbantartható megoldás
    /*if(sizeof(from) != sizeof(to))
    {
        //throw is lehet
        std::cerr << "Sizeof from is greater than to" << std::endl;
    }*/

    //2.karbantartható megoldás
    /*const int n = sizeof(from) / sizeof(from[0]);
    static const char to2[n]   = {'A', 'T', 'g'};*/

    for (unsigned int i = 0u;
         i < sizeof(from)/ sizeof(from[0]);
         i++)
    {
        if(from[i] == ch) return to[i];
    }
    return ch;
}