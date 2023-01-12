#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "rev.h"

char** read (FILE* fp, char** lines,int* lSize)
{
  int j=0;
  int seged = INITCAP;
  while(fscanf(fp, "%s", lines[j])!=EOF)
  {
    *lSize=*lSize+1;
    ++j;
    if (*lSize==seged)
    {
      seged *= 2;
      lines = (char**) realloc (lines,seged*sizeof(char*));
      for (int i = *lSize;i<seged;++i)
      {
        lines[i] = (char*) malloc(BUFSIZE*sizeof(char));
      }
    }
  }
  return lines;
}

void write (char** lines,int lSize)
{
  for (int i = lSize-1;i>=0;--i)
  {
    printf("%d ",i+1);
    int j = strlen(lines[i]);
    while(j>=0)
    {
      printf("%c",lines[i][j]);
      j--;
    }
    printf("\n");
  }
}

void reverse(FILE* fp)
{
  char** lines = (char**) malloc (INITCAP*sizeof(char*));
  for (int i = 0;i<INITCAP;++i)
   {
    lines[i] = (char*) malloc(BUFSIZE*sizeof(char));
   }
  int lSize = 0;
  lines = read(fp,lines,&lSize);
  write(lines,lSize);
  int j = sizeof(lines);
  for(int i=0;i<j;++i)
  {
    free(lines[i]);
  }
  free(lines);
}
