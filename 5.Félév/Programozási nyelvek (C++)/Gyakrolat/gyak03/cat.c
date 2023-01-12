#include <stdio.h>

int main()
{
    int ch;
    //nem egyenlő ch = getchar() != EOF -> ch vagy 0 vagy 1 lesz e miatt
    while((ch = getchar()) != EOF)
    {
        putchar(ch);
    }
    return 0;
}