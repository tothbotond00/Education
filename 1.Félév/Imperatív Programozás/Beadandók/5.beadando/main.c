#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "rle.h"

int main(void)
{
  const int maxertek=255;
  char szoveg [maxertek];
  scanf("%s",szoveg);
  int j=0;
  while(szoveg[j]!='\0')
  {
    if (strchr("öüóqwertzuiopőúasdfghjkléáűíyxcvbnm",szoveg[j])==NULL)
    {
      printf("Hibás bemenet!\n");
      return 1;
    }
    ++j;
  }
  Encoded* kodolt;
  kodolt=encode(szoveg);
  for (int i=0;i<(kodolt->length);++i)
  {
    printf("%d",kodolt->arr[i].n);
    printf("%c",kodolt->arr[i].c);

  }
  char* dekodolt =(char *) malloc (2*kodolt->length*sizeof(char));
  dekodolt = decode(kodolt);
  printf(" -> %s",dekodolt);
  printf("\n");
  free (dekodolt);
  free(kodolt);
  return 0;
}
