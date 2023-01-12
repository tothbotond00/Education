#include <iostream>
#include <cstring>

int size(const char* s)
{
    if (s == 0)
    {
        return -1;
    }
    //* CONST bevezetésével egyértleműbb
    const char* p = s;
    while(*p != '\0') //Ha kimaradna a a ! : '\0' = *p konvenciót kövessük!!!! :)
    {
        ++p;
    }
    return p - s;
}

int size_hiba(char* s)
{
    char* p = s;
    while(*p = '\0') // <------
    {
        ++p;
    }
    return p - s;
}

//!GDB NAGYON HASZNOS!S

int main()
{
    std::cout << size("blblbbla") << std::endl;
    std::cout << size("a") << std::endl;

    //!HIBÁS MO
    char str[] = "Boti";
    std::cout << size_hiba(str) << std::endl;
    std::cout << str + 1 << std::endl; // while(*p = '\0') -> _"oti\0"

    //!NULL PTR
    size( 0 );
    //int s = std::strlen(0);
    //std::cout << s + 4 << std::endl;
}