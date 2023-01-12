#include <iostream>
#include <time.h>
#include <stdlib.h>
#include <sstream>


using namespace std;

//Keretprogram a tömbös feladathoz

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

//feladat kellékei
//típus a multi halmaz ábrázolásához
struct elem
{
    int ertek,db;
};
//függvények a feladathoz
int Kigyujt(const int x[],int n, elem y[]);
int Eldont(const elem y[], int n, int z);
void PrintSet(const elem y[],int n, const string& s);
void Rendez(elem y[], int n);
bool operator>(elem e1,elem e2);
void csere(elem&,elem&);


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

    //megoldás
    elem h[maxsize];
    int db=Kigyujt(t,n,h);
    string s="A kigyujtott elemek\nErtek\tDarab\n";
    PrintSet(h,db,s);
    Rendez(h,db);
    s="A rendezett elemek\nErtek\tDarab\n";
    PrintSet(h,db,s);

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

int Kigyujt(const int x[],int n, elem y[])
{
    int db=0;
    for(int i=0; i<n; ++i)
    {
        int j=Eldont(y,db,x[i]);
        if(j==-1)
        {
            y[db].ertek=x[i];
            y[db].db=1;
            ++db;
        }else{
            ++y[j].db;
        }
    }
    return db;
}
int Eldont(const elem y[], int n, int z)
{
    int i=0;
    while(i<n && y[i].ertek!=z)
    {
        ++i;
    }
    if(i<n) return i; else return -1;
}
void PrintSet(const elem y[],int n, const string& s)
{
    cout<<s;
    for(int i=0; i<n; ++i)
    {
        cout<<y[i].ertek<<"\t"<<y[i].db<<endl;
    }
}

void Rendez(elem y[], int n)
{
    //Maximum kiválasztó rendezés
    int maxi;
    for(int i=0;i<n-1;++i)
    {
        maxi=i;
        for(int j=i+1;j<n;++j)
        {
            if(y[j]>y[maxi])
            {
            maxi=j;
            }
        }
        csere(y[i],y[maxi]);
    }
}

bool operator>(elem e1,elem e2)
{
    if(e1.db> e2.db)
    {
        return true;
    }
    else if(e1.db == e2.db)
    {
        return e1.ertek < e2.ertek;
    }
    else
    {
        return false;
    }
}

void csere(elem& e1, elem& e2)
{
    elem tmp=e1;
    e1=e2;
    e2=tmp;
}
