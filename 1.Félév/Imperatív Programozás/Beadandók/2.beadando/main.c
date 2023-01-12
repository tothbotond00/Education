#include <stdio.h>

int main(void)
{
  int elem;
  int hossz=0;
  scanf("%d",&elem);
  printf("%d",elem);

  do
  {
    if(elem%2==1)
    {
      elem=elem*3+1;
    }
    else
    {
      elem=elem/2;
    }
    ++hossz;
    printf(",%d",elem);
  }while(elem!=1);
  printf("\n");
  printf("%d",hossz+1);
  return 0;
}
