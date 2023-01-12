#include <stdlib.h>
#include <stdio.h>

#include "queue.h"

/*
    A program helyes kimenete ezzel a main f�ggv�nnyel:

    10 -> 20 -> nil
    10 -> 20 -> 30 -> nil
    A sor elemeinek sz�ma 3.
    20 -> 30 -> nil
    A sor elemeinek sz�ma 2.
    A sor �res.
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
        fprintf(stdout, "A sor elemeinek sz�ma %d.\n", l);
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
        fprintf(stdout, "A sor elemeinek sz�ma %d.\n", l);
    }
    else
    {
        fprintf(stderr, "Hiba!\n");
    }

    empty(list);
    display(list);    // Kimenet: A sor �res.
    free(list);
    return 0;
}
