/*#include <iostream>

using namespace std;

int main()
{
    //Faktoriális
    //Írassuk ki 1-tõl 15-ig a számok faktoriálisát
    //Int esetén csak a 12-ig pontos! 13-nál már túlcsordul
    //Double esetén nagyságrendileg pontos, de nem látjuk az összes értékes számjegyet
    //int64_t a 64 bites egész, azzal pontos 19!-ig
    //long long - hosszú egész, 8 byte hosszú

    cout<<"Faktorialist szamito program"<<endl;

    //int fakt=1;
    //cout<<"int merete:"<<sizeof(int)<<" byte\n";

    //double fakt=1;
    //cout<<"double merete:"<<sizeof(double)<<" byte\n";

    //int64_t fakt=1;
    //cout<<"int64_t merete:"<<sizeof(int64_t)<<" byte\n";

    double fakt=1;
    cout<<"long long merete:"<<sizeof(double)<<" byte\n";

    for(int i=1;i<=30;++i){
        fakt=fakt*i;
        cout<<i<<"!  =  "<<fakt <<endl;
    }

    return 0;
}

#include <iostream>

using namespace std;

int main()
{
   //Határozzuk meg az 1 és 100 közötti 7-tel osztható számok átlagát
   //k 1 és 100 közötti egész

   cout<<endl<<"1 es 100 kozotti k-val oszthato szamok atlaga"<<endl<<endl;
   //k ellenõrzött beolvasása
   int k;
   bool hiba;
   do{
       cout<<"Kerem k erteket:";
       cin>>k;
       hiba=(k<1 || k>100);
       if(hiba)
            cerr<<"0 < k <= 100 legyen!\n";
   }while(hiba);

   int db=0,sum=0;
   float atlag;

   for(int i=k;i<=100;i+=k){
      sum+=i;
      ++db;
   }

   //atlag=sum/db nem ad jó eredményt, mert egészosztás!
   atlag=float(sum)/(float)db;

   cout<<"Az osszeg:"<<sum<<endl;
   cout<<"A darabszam:"<<db<<endl;
   cout<<"Az atlag:"<<atlag<<endl;
   return 0;
}
*/
#include <iostream>

using namespace std;

int main()
{
   //Feladat:
   //Euklideszi algoritmussal meghatározni két természetes szám legnagyobb
   //közös osztóját.
   //Használjuk az ellenõrzött beolvasást a számok bekérésekor.
   //A megoldó algoritmus kívánságra újra és újra futtatható.


   int a,b, maradek;
   char t;
   bool hiba;


   cout<<"Euklideszi algoritmus"<<endl;

   do{
      //Elõfeltételek ellenõrzése
      do{
         cout<<"Elso szam="; cin>>a;
         hiba=a<=0;
         if (hiba) cerr<<"Nem nagyobb, mint nulla: "<<a<<endl;
      }while (hiba);
      do{
         cout<<"Masodik szam="; cin>>b;
         hiba=b<=0;
         if (hiba) cerr<<"Nem nagyobb, mint nulla: "<<b<<endl;
      }while (hiba);

      cout<<"LNKO("<<a<<","<<b<<")=";

      //Euklideszi algoritmus
      maradek=a%b;
      while(maradek>0){
         a=b;
         b=maradek;
         maradek=a%b;
      }
      cout<<b<<endl;

      do{
         cout<<"Ujra (i/n):"; cin>>t;
         hiba=t!='i' && t!='n';
      }while (hiba);
      cout<<endl;
   }while (t=='i');
   return 0;
}

