#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char** iterator;
int count = -1;
int size = 10;

void add();
void list();
void exit1();

int main(void)
{
  iterator=(char**)malloc(size*sizeof(char*));
  char input;
  do
  {
   /* printf("Please choose between the following commands:\n");
    printf("a:adding a new memeber to the list\n");
    printf("l:listing all the members\n");
    printf("x:exting from the program\n\n");
   */
    int ch;
    scanf("%c", &input);
    while((ch = getchar()) != '\n');
    if (input=='a')
    {
      add();
    }
    else if(input=='l')
    {
      list();
    }
    else if (input=='x')
    {
      exit1();
    }
    else
    {
      printf("Wrong input!\n");
    }
  }while(input!='x');
  //printf("Goodbye!\n");
  return 0;
}

void add()
{
 char* per = (char*)malloc(30 * sizeof(char));
 fgets(per, 30, stdin);
 if (strchr(per, '\n') == NULL)
  {
   int ch;
   while((ch = getchar()) != '\n');
   printf("You have given a too long name for this person!\n");
   free (per);
  }
 else
  {
   ++count;
   per[strlen(per)-1]='\0';
   if(count>10&&count%5==1)
   {
     size=size+5;
     iterator=(char**)realloc(iterator,size*sizeof(char*));
   }
   iterator[count]=per;
  }
}

void list()
{
  for (int i=0;i<=count;++i)
  {
    printf("%s\n",iterator[i]);
  }
}

void exit1()
{
  for (int i=0;i<=count;++i)
  {
    free(iterator[i]);
  }
  free(iterator);
}
