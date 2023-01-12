#include <iostream>
#include <time.h>
#include <stdlib.h>
#include <sstream>


using namespace std;

enum error{VanNulla,RosszMeret};

//Keretprogram a tömbös feladatokhoz

//Tömb maximális mérete
const int maxsize=1000;

//Tömb feltöltése konzolról
//n - tömb mérete
void FillArrayFromConsol(int x[],int &n);

//Tömb feltöltése véletlen értékekkel
//n - tömb mérete
void FillArrayRnd(int x[],int &n);

//Tömb kiírása a képernyőre
//n - sorok száma
void PrintArray(const int x[],int n);

//Mátrix méretét beolvasó függvény
//n- >0 és <maxsize
void ReadSize(int &n);

//Azonosan igazat adó függvény
bool def(int a){return true;}

//Egész ellenőrzött beolvasása
//msg- beolvasás előtti üzenet
//errmsg- hibaüzenet
//valid(int)- bool függvény, az egész értékre vonatkozó megszorítás,
//            ez a paraméter elhegyható, ha nem adjuk meg, a "def" függvény
//            lesz ez a paraméter, ilyenkor tetszőleges egész értéket elfogad
//Szerző: Gregorics Tibor
int read_int(const string &msg, const string &errmsg, bool valid(int)=def);

//Segéd függvények a beolvasáshoz
bool egyketto(int a) {return a==1 || a==2;} //menü ellenőrzése
bool pozitiv(int a) {return a>0;} //tömb méretek beolvasásához

///6.Feladat:
int feladat6(const int [], int, int&);
int hanyoszto (const int [],int);
///7.Feladat:
int feladat7(const int [],int);
bool vantobb(const int [],int,int);
///8.Feladat:
void feladat8(const int [],int);
int main()
{
    int n,t[maxsize];
    //Beolvasás
    cout<<"(1) Tomb kezi feltoltese\n(2) Tomb veletlenszeru feltoltese\n";
    int v=read_int("Mit valasztasz:","1 vagy 2 a helyes valasz!",egyketto);
    switch (v)
    {
        case 1:
            FillArrayFromConsol(t,n);
            break;
        case 2:
            FillArrayRnd(t,n);
    }
    cout<<"A bemenet tomb:\n";
    PrintArray(t,n);
    ///6.Feladat:
    try
    {
    int max, maxert;
    maxert=feladat6(t,n,max);
    cout<<"Legtobb osztoja van: "<<t[max]<<" sorszama: "<<max+1<<endl;
    cout<<"osztok szama: "<<maxert<<endl;
    }catch(error hiba)
    {
        if(hiba==VanNulla)
        {
            cerr<<"AZ ADATOK KOZOTT VAN NULLA\n";
        }
        else if(hiba==RosszMeret)
        {
            cerr<<"NINCS ELEG ADAT A FELADATHOZ\n";
        }
    }
    ///7.Feladat:
    cout<<"Kulonbozo ertekek szama: "<<feladat7(t,n);
    ///8.Feladat:
    cout<<endl;
    cout<<"Kulonbozo adatok: ";
    feladat8(t,n);
    return 0;
}

void FillArrayFromConsol(int x[],int &n)
{
    //Méret beolvasása
    ReadSize(n);
    //tömb beolvasása
    bool hiba;
    string sz;
    do{
        hiba=false;
        cout<<"Add meg a tomb elemeit (szokozzel elvalasztva):\n";
        getline(cin,sz);
        istringstream inp(sz);
        for(int j=0;!hiba && j<n; ++j)
        {
            inp>>x[j];
            hiba=inp.fail();
        }
        hiba=hiba || !inp.eof();
        if(hiba)
        {
            cout<<"Hibas, vagy tul sok adat / tul keves adat, add meg ujra!\n";
        }
    }while(hiba);
}



void FillArrayRnd(int x[],int &n)
{
    //Méret beolvasása
    ReadSize(n);
    //Véletlen értékek határainak beolvasása
    int ah,fh;
    do{
        cout<<"Milyen intervallumbol legyenek a veletlen szamok?\n";
        ah=read_int("Also hatar:","Egesz szamot kerek");
        fh=read_int("Felso hatar:","Egesz szamot kerek");
        if(ah>=fh) cout<<"Helytelen intervallum\n";
    }while(ah>=fh);
    //Vélatlenszám generátor inicializálása
    srand(time(NULL));
    //Tartomány kiszámítása
    int d=fh-ah+1;
    //Mátrix feltöltése
    for(int i=0; i<n; ++i)
    {
        x[i]=rand()%d+ah; //V├ęletlen sz├ímok el┼Ĺ├íll├şt├ísa az ah..fh intervallumb├│l
    }
}

void PrintArray(const int x[],int n)
{
    for(int i=0; i<n; ++i)
    {
        cout<<x[i]<<"\t";
    }
    cout<<endl;

}

void ReadSize(int &n)
{
    bool hiba;
    do{
        n=read_int("Hany eleme legyen a tombnek:","Csak pozitiv lehet!",pozitiv);
        hiba=n>maxsize;
        if(hiba) cout<<"Legfeljebb "<<maxsize<<" meretu lehet!\n";
    }while(hiba);
}

int read_int(const string &msg, const string &errmsg, bool valid(int))
{
    int n;
    bool hiba;
    do{
        cout << msg;
        cin >> n;
        hiba=cin.fail();
        if(hiba) cin.clear();
        string tmp=""; getline(cin, tmp);
        hiba = hiba || tmp.size()!=0 || !valid(n);
        if(hiba) cout << errmsg << endl;
    }while(hiba);

    return n;
}
///6.Feladat
int feladat6(const int x[], int n , int& ind)
{
    ///Előfeltételek elleneőrzése
    if(n<2) throw RosszMeret;
    for (int i=0; i<n;++i) if(x[i]==0) throw VanNulla;
    ind=1; int maxert=hanyoszto(x,1);
    for(int i=2; i<n; ++i)
    {
        int c=hanyoszto(x,i);
        if(c>maxert)
        {
            ind=i;
            maxert=c;
        }
    }
    return maxert;
}

int hanyoszto (const int x[],int i)
{
    int c=0;
    for (int j=0;j<i;++j)
    {
        if (x[i]%x[j]==0) ++c;
    }
    return c;
}
///7.feladat
int feladat7(const int x[],int n)
{
    int c=0;
    for (int i=0;i<n;++i)
    {
        if (!vantobb(x,n,i))
        {
            ++c;
        }
    }
    return c;
}
bool vantobb (const int x[],int n,int k)
{
    for(int i=k+1;i<n;++i)
    {
        if(x[k]==x[i])
        {
            return true;
        }
    }
    return false;
}
///8.feladat
void feladat8(const int x [],int n)
{
    int kulon[n];
    int c=0;
    for (int i=0;i<n;++i)
    {
        if (!vantobb(x,n,i))
        {
            kulon[c]=x[i];
            ++c;
        }
    }
    for (int i=0;i<c;++i)
    {
        cout<<kulon[i]<<" ";
    }
}
