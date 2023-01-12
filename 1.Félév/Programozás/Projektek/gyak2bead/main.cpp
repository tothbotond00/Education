#include <iostream>
#include <time.h>

using namespace std;

int main()
{
    srand(time(NULL));
    char t;
    do
    {
      cout<<"Gondoltam egy szamra.\n";
      int szam =rand() %100+1;
      bool kitalalta=true;
      do
      {
      int tipp;
      cin >>tipp;
      if(tipp==szam)
       {
          cout<<"Kitalaltad!\n";
          kitalalta=false;
       }
       else if (tipp>szam)
       {
           cout<<"Kissebbre gondoltam\n";
       }
       else
       {
           cout<<"Nagyobbra gondoltam\n";
       }
       }while(kitalalta);
       bool hiba= true;
       do{
        cout<<"szeretnel meg jatszani? (i ha igen n ha nem)\n";
        cin>>t;
        if (t=='i'||t=='n') hiba =false;
       }while(hiba);
    }while(t=='i');
    return 0;
}
