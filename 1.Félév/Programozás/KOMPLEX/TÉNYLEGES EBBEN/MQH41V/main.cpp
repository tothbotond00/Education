/*
  Készítette: Tóth Botond
  Neptun: MQH41V
  E-mail: tothbotond00@gmail.com
  Feladat: „Programozás komplex beadandó” téma „Magának való madárfaj” feladat
*/

#include <iostream>
#include <sstream>
using namespace std;

const int maxsor = 50;
const int maxoszlop = 200;

//#define BIRO

///Egész ellenőrzött beolvasása
//msg - beolvasás előtti üzenet
//errmsg - hibaüzenet
//Szerző: Gregorics Tibor
int read_int(const string &msg, const string &errmsg);
///Mátrix méreteit beolvasó függvény(Forrás:Matrix feladatsor)
//n - sorok száma (>0 és <maxsor
//m - oszlopok száma (>0 és <maxoszlop
void ReadSizes(int &n, int &m);
///Mátrix feltöltése BÍRÓ-ra feltöltés miatt
//n - sorok száma
//m - oszlopok száma
//x - feltöltendõ mátrix
void FillMatrixBiro (int x [] [maxoszlop],int &n, int &m);
///Mátrix feltöltése konzolról (Forrás:Olimpiai feladatsor)
//n - sorok száma
//m - oszlopok száma
//x - feltöltendõ mátrix
void FillMatrixFromConsol(int x[][maxoszlop],int &n, int &m);
///Egy segédtömb feltöltése az alapján hogy egy madárfaj átlagosan mennyit találkozott más fajokkal
//n - sorok száma
//m - oszlopok száma
//x - adatokkal teli tömb
//madarak - feltöltendő tömb
void FillBirdsArray(int x[][maxoszlop],int &n, int &m,float madarak []);
///A minimum megkeresése az átlagokat tartalmazó tömbben
//m - tömb hossza
//madarak - a tömb amiben a minimumot keressük
int FindMimimum(float madarak[],int &m);

int main()
{
    #ifndef BIRO
    cout<<"KOMPLEX BEADANDO\n";
    cout<<"Ez a program megkeresi meresek alapjan, hogy hanyadik madarfaj az,\n";
    cout<<"amelyik legkevesebe szereti mas madarfajok tarsasagat\n";
    #endif // BIRO
    ///N és M bekérése (BÍRÓ/CONSOL)
    int n;
    int m;
    #ifdef BIRO
    //Bírós "n" és "m" bekérése
    cin>>n;
    cin>>m;
    #else
    //Ellenõrzéses bekérdezés
    cout<<"Kerem adja meg a meresek (maximum "<<maxsor<<") es a madarak szamat (maximum "<<maxoszlop<<"):\n";
    ReadSizes(n,m);
    #endif

    ///Mátrix feltöltése (BÍRÓ/CONSOL)
    int meresek[maxsor][maxoszlop];
    #ifdef BIRO
    //Bírós mátrix feltöltés
    FillMatrixBiro(meresek,n,m);
    #else
    //Konzolos feltöltés
    cout<<"Kerem toltse fel az igy keltkezett matrixot:\n";
    FillMatrixFromConsol(meresek,n,m);
    #endif // BIRO

    ///Segédtömbe átlagok kilistázása:
    float madarak [m]={0};
    FillBirdsArray(meresek,n,m,madarak);

    ///Minimum átlag megkeresése:
    int minimum;
    minimum = FindMimimum(madarak,m);

    ///Kiírás (BÍRÓ/CONSOL)
    #ifndef BIRO
    cout<<"A madar sorszama: ";
    #endif // BIRO
    cout<<minimum<<endl;
    return 0;
}

int read_int(const string &msg, const string &errmsg)
{
    int n;
    bool hiba;
    do{
        cout << msg;
        cin >> n;
        hiba=cin.fail();
        if(hiba) cin.clear();
        string tmp=""; getline(cin, tmp);
        hiba = hiba || tmp.size()!=0 ;
        if(hiba) cout << errmsg << endl;
    }while(hiba);

    return n;
}

void FillMatrixBiro (int x [] [maxoszlop],int &n, int &m)
{
    for (int i=0;i<n;++i)
    {
        for (int j=0; j<m;++j) cin>>x[i][j];
    }
}

void ReadSizes(int &n, int &m)
{
    bool hiba;

    do{
        n=read_int("Meresek szama:","Pozitiv legyen!");
        if(n>maxsor)
        {
            hiba = true;
            cerr<<"Legfeljebb "<<maxsor<<" sora lehet!\n";
        }
        else if(n<=0)
        {
            hiba = true;
            cerr<<"Nem lehet 0-nal kisebb a sorok szama\n";
        }
        else hiba =false;
    }while(hiba);
    do{
        m=read_int("Madarak szama:","Pozitiv legyen!");
        if(m>maxoszlop)
        {
            hiba = true;
            cerr<<"Legfeljebb "<<maxoszlop<<" sora lehet!\n";
        }
        else if(m<=0)
        {
            hiba = true;
            cerr<<"Nem lehet 0-nal kisebb a sorok szama\n";
        }
        else hiba =false;
    }while(hiba);
}

void FillMatrixFromConsol(int x[][maxoszlop],int &n, int &m)
{
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
                hiba=inp.fail() || (x [i][j]<0 || x[i][j] >1000);
            }
            hiba=hiba || !inp.eof();
            if(hiba)
            {
                cerr<<"Hibas vagy tul keves/sok az adat, add meg ujra!\n";
            }
            }while(hiba);
    }
}

void FillBirdsArray(int x[][maxoszlop],int &n, int &m,float madarak [])
{
    for (int j=0;j<m;++j)
    {
        int oszto=0;
        int osztando=0;
        for (int i=0;i<n;++i)
        {
            if (x[i][j]==0) continue;
            else
            {
               ++oszto;
               for (int k=0;k<m;++k)
                 {
                   if (x[i][k]!=0 &&k!=j) ++osztando;
                 }
            }
        }
        if (oszto==0)
        {
            madarak[j]=maxsor*maxoszlop;
        }
        else
        {
        madarak[j]=(float)osztando/(float)oszto;
        }
    }
}

int FindMimimum(float madarak[],int &m)
{
    int min=1;
    for (int j=1;j<m;++j)
    {
        if (madarak[j]<madarak[min-1])
        {
            min=j+1;
        }
    }
    return min;
}
