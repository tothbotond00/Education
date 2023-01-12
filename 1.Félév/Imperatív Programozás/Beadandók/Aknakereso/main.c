#include <stdio.h>
#include <time.h>
#include <stdlib.h>

int main(int argc,char* argv[])
{
  //1-2Feladat
  if (argc==1) return 1;
  int darab=0;
  sscanf(argv[1],"%d",&darab);
  if (darab<3||darab>33)
  {
    printf("Rossz param�ter �tad�s!\n");
    return 1;
  }
  char akna[11][11]={' '};
  char megoldas [11] [11]={' '};
    for (int i=0;i<=10;++i)
  {
    for (int j=0;j<=10;++j)
    {
      megoldas[i][j]='0';
    }
  }
  for (int i=48;i<=57;++i)
  {
    akna[0][i-47]=i;
    megoldas[0][i-47]=i;
  }
  for (int i=65;i<=74;++i)
  {
    akna[i-64][0]=i;
    megoldas[i-64][0]=i;
  }
  //3Feladat-- itt nemes egyszer�s�ggel nem haszn�ltam f�ggv�nyt
  //sajnos nem siker�lt a k�tdimenzi�s t�mbbel dolgoznom hi�ba magyar�ztad el :/
  int i=0;
  srand(time(NULL));
  while(i<darab)
  {
    int x = rand()%10+1;
    int y = rand()%10+1;
    if(megoldas[x][y]=='*')
    {
      continue;
    }
     else
     {
       megoldas[x][y]='*';
       ++i;
     }
  }
  //4.Feladatszint�gy nem megy a f�ggv�ny
  for (int i=1;i<=10;++i)
  {
    for (int j=0;j<=10;++j)
    {
      if (megoldas[i][j]=='*')
      {
        //ez lett a legrond�bb r�sze
        if (megoldas[i+1][j-1]>=48&&megoldas[i+1][j-1]<=56)
        ++megoldas[i+1][j-1];
        if (megoldas[i+1][j]>=48&&megoldas[i+1][j]<=56)
        ++megoldas[i+1][j];
        if (megoldas[i+1][j+1]>=48&&megoldas[i+1][j+1]<=56)
        ++megoldas[i+1][j+1];
        if (megoldas[i-1][j]>=48&&megoldas[i-1][j]<=56)
        ++megoldas[i-1][j];
        if(megoldas[i-1][j+1]>=48&&megoldas[i-1][j+1]<=56)
        ++megoldas[i-1][j+1];
        if (megoldas[i-1][j-1]>=48&&megoldas[i-1][j-1]<=56)
        ++megoldas[i-1][j-1];
        if (megoldas[i][j-1]>=48&&megoldas[i][j-1]<=56)
        ++megoldas[i][j-1];
        if (megoldas[i][j+1]>=48&&megoldas[i][j+1]<=56)
        ++megoldas[i][j+1];
      }
    }
  }
  printf("\t0\t1\t2\t3\t4\t5\t6\t7\t8\t9\t\n");
  for (int i=1;i<=10;++i)
  {
    for (int j=0;j<=10;++j)
    {
      printf("%c\t",megoldas[i][j]);
    }
    printf("\n");
  }
  //5.Feladat
  int robbant = 0;
  printf("Opci�k:\n1.exit->kil�p�s a programb�l\n2.koordin�ta megad�sa->[A-J][0-9] form�tumban\n");
  while (robbant < 100-darab)
  {
    char cor [5];
    scanf("%s",cor);
    int x=0;
    int y=0;
    if (cor=='1')
    {
      printf("Good Bye!\n");
      return 0;
    }
    x=cor[0]-64;
    y=cor[1]-47;
    if (megoldas[x][y]=='*')
    {
      printf("Kabboom!!!!\n");
      return 0;
    }
    else
    {
      akna[x][y]=megoldas[x][y];
      for (int i=0;i<=10;++i)
      {
        for (int j=0;j<=10;++j)
        {
          printf("%c\t",akna[i][j]);
        }
        printf("\n");
      }
      ++robbant;
    }
  }
  printf("Gratul�lunk! Igazi akna keres� vagy!!!!");
  return 0;
}
