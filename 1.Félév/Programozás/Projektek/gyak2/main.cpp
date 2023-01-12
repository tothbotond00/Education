/*#include <iostream>

using namespace std;

int main()
{
    //Faktori�lis
    //�rassuk ki 1-t�l 15-ig a sz�mok faktori�lis�t
    //Int eset�n csak a 12-ig pontos! 13-n�l m�r t�lcsordul
    //Double eset�n nagys�grendileg pontos, de nem l�tjuk az �sszes �rt�kes sz�mjegyet
    //int64_t a 64 bites eg�sz, azzal pontos 19!-ig
    //long long - hossz� eg�sz, 8 byte hossz�

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
   //Hat�rozzuk meg az 1 �s 100 k�z�tti 7-tel oszthat� sz�mok �tlag�t
   //k 1 �s 100 k�z�tti eg�sz

   cout<<endl<<"1 es 100 kozotti k-val oszthato szamok atlaga"<<endl<<endl;
   //k ellen�rz�tt beolvas�sa
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

   //atlag=sum/db nem ad j� eredm�nyt, mert eg�szoszt�s!
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
   //Euklideszi algoritmussal meghat�rozni k�t term�szetes sz�m legnagyobb
   //k�z�s oszt�j�t.
   //Haszn�ljuk az ellen�rz�tt beolvas�st a sz�mok bek�r�sekor.
   //A megold� algoritmus k�v�ns�gra �jra �s �jra futtathat�.


   int a,b, maradek;
   char t;
   bool hiba;


   cout<<"Euklideszi algoritmus"<<endl;

   do{
      //El�felt�telek ellen�rz�se
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

