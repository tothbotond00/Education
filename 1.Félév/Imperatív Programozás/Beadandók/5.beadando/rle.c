#include <stdlib.h>

#include "rle.h"

Encoded* encode (char* szoveg)
{
  Encoded* megoldas=(Encoded*)malloc(sizeof(Encoded));
  megoldas->length=0;
  int i=0;
  while (szoveg[i] !='\0')
  {
    int j=1;
    while(szoveg[i]==szoveg[i+j])
    {
      ++j;
    }
    Pair elem;
    elem.c =szoveg[i];
    elem.n =j;
    ++megoldas->length;
    megoldas->arr =(Pair *) realloc (megoldas->arr,megoldas->length*sizeof(Pair));
    megoldas->arr[megoldas->length-1]=elem;
    i=i+j;
  }
  return megoldas;
}

char* decode(Encoded* encoded)
{
  int k=0;
  char* megoldas =(char*)malloc(2*encoded->length*sizeof(char));
  for (int i=0;i<(encoded->length);++i)
  {
    for (int j=0;j<(encoded->arr[i].n);++j)
    {
      megoldas[k] = encoded->arr[i].c;
      ++k;
    }
  }
  return megoldas;
}
