#include <stdio.h>

int main(void)
{
  int db;
  scanf("%d",&db);
  if(!db<1)
  {
    for(int i=0;i<db;++i)
    {
      for(int h=db-1;h>=0;--h)
      {
        if(i>=h)
        {
          printf("X");
        }
        else
        {
          printf(" ");
        }
      }
      printf("  ");
      for(int j=0;j<=i;++j)
      {
      printf("X");
      }
    printf("\n");
    }
  }
  return 0;
}