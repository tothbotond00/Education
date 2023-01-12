#ifndef QUEUE_H
#define QUEUE_H

#include <stdbool.h>
//Node elodeklaracio
struct Node;
typedef struct Node Node;

//Queue elodeklaracio
struct Queue
{
  Node* front;
  Node* back;
};
typedef struct Queue Queue;

//Módósító műveletek
Queue* initialize ();
void enqueue (Queue*,int);
void dequeue (Queue*);
void empty (Queue*);

//Nem módosító műveletek
bool isEmpty (Queue*);
int front (Queue*);
int back (Queue*);
int length (Queue*);
void display (Queue*);

#endif
