#include <iostream>
#include <time.h>
#include <stdlib.h>
#include <sstream>


using namespace std;

//Keretprogram a mátrixos feladatokhoz

//Mátrix maximális méretei
const int maxsor=100;
const int maxoszlop=100;

//Mátrix feltöltése konzolról
//n - sorok száma
//m - oszlopok száma
void FillMatrixFromConsol(int x[][maxoszlop],int &n, int &m);

//Mátrix feltöltése véletlen értékekkel
//n - sorok száma
//m - oszlopok száma
void FillMatrixRnd(int x[][maxoszlop],int &n, int &m);

//Mátrix kiírása a képernyőre
//n - sorok száma
//m - oszlopok száma
void PrintMatrix(const int x[][maxoszlop],int n, int m);

//Mátrix méreteit beolvasó függvény
//n- sorok száma (>0 és <maxsor
//m- oszlopok száma (>0 és <maxoszlop
void ReadSizes(int &n, int &m);

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
bool pozitiv(int a) {return a>0;} //mátrix méretek beolvasásához

///hány negatív összegű sor van
int feladat1(const int [][maxoszlop],int, int);
int sorosszeg(const int [],int);

///csupa páros számból álló sor
bool feladat2(const int [][maxoszlop],int, int,int&);
bool mindparos(const int [], int );
int main()
{
    int m,n,t[maxsor][maxoszlop];
    //Beolvasás
    cout<<"(1) Matrix kezi feltoltese\n(2) Matrix veletlenszeru feltoltese\n";
    int v=read_int("Mit valasztasz:","1 vagy 2 a helyes valasz!",egyketto);
    switch (v)
    {
        case 1:
            FillMatrixFromConsol(t,n,m);
            break;
        case 2:
            FillMatrixRnd(t,n,m);
    }
    cout<<"A bemenet matrix:\n";
    PrintMatrix(t,n,m);
    ///hány negatív összegű sor van
    cout<<feladat1(t,n,m)<<" negativ osszegu sort talaltam\n";
    ///csupa paros szamu sor
    bool van; int ind;
    van=feladat2(t,n,m,ind);
    if(van)
    {
        cout<<ind+1<<". sor csupa paros\n";
    }
    else
    {
        cout<<"Nem talaltam csupa paros sort\n";
    }
    return 0;
}

void FillMatrixFromConsol(int x[][maxoszlop],int &n, int &m)
{
    //Méretek beolvasása
    ReadSizes(n,m);
    //Mátrix beolvasása
    for (int i=0; i<n; ++i)
    {
        bool hiba;
        do{
            hiba=false;
            cout<<"Add meg az "<<i+1<<". sor elemeit szokozokkel elvalasztva\n";
            string sz;
            getline(cin,sz);
            istringstream inp(sz);
            for (int j=0;!hiba && j<m; ++j)
            {
                inp>>x[i][j];
                hiba=inp.fail();
            }
            hiba=hiba || !inp.eof();
            if(hiba)
            {
                cerr<<"Hibas vagy tul keves/sok az adat, add meg ujra!\n";
            }
            }while(hiba);
    }
}

void FillMatrixRnd(int x[][maxoszlop],int &n, int &m)
{
    //Méretek beolvasása
    ReadSizes(n,m);
    //Véletlen értékek határainak beolvasása
    int ah,fh;
    do{
        cout<<"Milyen intervallumbol legyenek a veletlen szamok?\n";
        ah=read_int("Also hatar:","Egesz szamot kerek");
        fh=read_int("Felso hatar:","Egesz szamot kerek");
        if(ah>=fh) cout<<"Helytelen intervallum\n";
    }while(ah>=fh);
    //Véletlenszám generátor inicializálása
    srand(time(NULL));
    //Tartomány kiszámítása
    int d=fh-ah+1;
    //Mátrix feltöltése
    for(int i=0; i<n; ++i)
    {
        for(int j=0; j<m; ++j)
        {
            x[i][j]=rand()%d+ah; //Véletlenszámok előállítása az ah..fh intervalumból
        }
    }

}

void PrintMatrix(const int x[][maxoszlop],int n, int m)
{
    for(int i=0; i<n; ++i)
    {
        for(int j=0; j<m;++j)
        {
            cout<<x[i][j]<<"\t";
        }
        cout<<endl;
    }

}

void ReadSizes(int &n, int &m)
{
    bool hiba;

    do{
        n=read_int("Sorok szama:","Pozitiv legyen!",pozitiv);
        hiba=n>maxsor;
        if(hiba)
        {
            cerr<<"Legfeljebb "<<maxsor<<" sora lehet!\n";
        }
    }while(hiba);
    do{
        m=read_int("Oszlopok szama:","Pozitiv legyen!",pozitiv);
        hiba=m>maxoszlop;
        if(hiba)
        {
            cerr<<"Legfeljebb "<<maxoszlop<<" oszlopa lehet!\n";
        }
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
///hány negatív összegű sor van
int feladat1(const int x[][maxoszlop],int n, int m)
{
    int c=0;
    for(int i=0;i<n;++i)
    {
        if((sorosszeg(x[i],m))<0) ++c;
    }
    return c;
}
int sorosszeg(const int y[],int m)
{
    int s=0;
    for (int j=0; j<m;++j) s+=y[j];
    return s;
}
///csupa páros számból álló sor
bool feladat2(const int x[][maxoszlop],int n,int m,int& ind)
{
    int i=0;
    while(i<n && !mindparos(x[i],m))
    {
        ++i;
    }
    ind=i;
    return i<n;
}
bool mindparos(const int y [], int m)
{
    int j;
    for (j=0; j<m;++j)
    {
        if (y[j]%2!=0)
        {
            break;
        }
    }
    return j==m;
}

