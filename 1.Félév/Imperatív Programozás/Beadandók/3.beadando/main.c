#include <stdio.h>


int isValidTime(char c [])
{
   if(c[0]=='0'&&c[1]>=48&&c[1]<=57
   ||c[0]=='1'&&c[1]>=48&&c[1]<=57
   ||c[0]=='2'&&c[1]>=48&&c[1]<=51)
   {
     //ez akkor jo
   }
   else
   {
      printf("Hibás bemenet!\n");
      return 1;
   }
   if(c[2]!=':')
   {
      printf("Hibás bemenet!\n");
      return 1;
   }
   if(c[3]>=48&&c[3]<=53&&c[4]>=48&&c[4]<=57&&c[5]=='\0')
   {
     return 0;
   }
   else
   {
     printf("Hibás bemenet!\n");
     return 1;
   }
}

int isValidTodo(char c [])
{
  int i=0;
  while(c[i]!='\0')
  {
    if(c[i]>=65 && c[i]<=90 || c[i]>=97&& c[i]<=122)
    {
      ++i;
    }
    else
    {
      printf("Hibás bemenet!\n");
      return 1;
    }
  }
  return 0;
}

int main(int argc, char* argv[])
{
  if(argc<3)
  {
    printf("Hibás bemenet!\n");
    return 1;
  }
  FILE * output =fopen("todolist.txt", "w");
  if (output==NULL)
  {
    return 1;
  }
  //innentõl messy
  for (int i=1;i<argc;++i)
  {
    if(i%2==1)
    {
      if(isValidTime(argv[i])!=0)
      {
        ++i;
      }
    }
    else
    {
      if(isValidTodo(argv[i])==0)
      {
        fprintf(output, "%s - %s\n",argv[i-1],argv[i]);
      }
    }
  }
  //kb. ideáig xd
  fclose(output);
  return 0;
}
