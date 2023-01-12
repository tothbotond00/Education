#ifndef RLE_H
#define RLE_H

struct Pair
{
  char c;
  int n;
};
typedef struct Pair Pair;

struct Encoded
{
  size_t length;
  Pair* arr;
};

typedef struct Encoded Encoded;

Encoded * encode (char*);
char* decode(Encoded*);

#endif
