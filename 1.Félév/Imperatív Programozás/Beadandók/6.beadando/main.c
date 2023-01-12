#include <stdlib.h>
#include <stdio.h>

#include "queue.h"

/*
    A program helyes kimenete ezzel a main függvénnyel:

    10 -> 20 -> nil
    10 -> 20 -> 30 -> nil
    A sor elemeinek száma 3.
    20 -> 30 -> nil
    A sor elemeinek száma 2.
    A sor üres.
*/

int main()
{
    Queue* list = initialize();
    if (!isEmpty(list))
    {
        fprintf(stderr, "Hiba!\n");
    }

    enqueue(list, 10);
    enqueue(list, 20);
    display(list);       // Kimenet: 10 -> 20 -> nil

    if (front(list) != 10)
    {
        fprintf(stderr, "Hiba!\n");
    }

    if (back(list) != 20)
    {
        fprintf(stderr, "Hiba!\n");
    }

    enqueue(list, 30);
    display(list);       // Kimenet: 10 -> 20 -> 30 -> nil

    int l = length(list);
    if (l == 3)
    {
        fprintf(stdout, "A sor elemeinek száma %d.\n", l);
    }
    else
    {
        fprintf(stderr, "Hiba!\n");
    }

    dequeue(list);
    display(list);     // Kimenet: 20 -> 30 -> nil

    if (front(list) != 20)
    {
        fprintf(stderr, "Hiba!\n");
    }

    if (back(list) != 30)
    {
        fprintf(stderr, "Hiba!\n");
    }

    l = length(list);
    if (l == 2)
    {
        fprintf(stdout, "A sor elemeinek száma %d.\n", l);
    }
    else
    {
        fprintf(stderr, "Hiba!\n");
    }

    empty(list);
    display(list);    // Kimenet: A sor üres.
    free(list);
    return 0;
}
