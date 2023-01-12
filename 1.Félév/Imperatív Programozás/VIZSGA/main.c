#include <stdio.h>

#include "rev.h"

int main(int argc,char* argv [])
{
  if (argc > 1)
  {
    for (int i=1;i<argc;++i)
    {
      FILE* file = fopen(argv[i],"r");
      if (file == NULL)
      {
        fprintf(stderr,"Could not open the file!\n");
      }
      else reverse(file);
    }
  }
  else
  {
    fprintf(stderr,"Too few arguments!\n");
    FILE* file = stdin;
    if (file == NULL)
    {
      fprintf(stderr,"Too few arguments!\n");
      return 1;
    }
    else reverse(file);
  }
  return 0;
}
