#include <iostream>
#include <sstream>

using namespace std;

const int max_meret=100;
const int vsz=5;

#define BIRO
//#define TESZT

///Beolvasó függvények
void beolvasBIRO(int &N, int &K, int h[max_meret][vsz]);
void beolvasConsol(int &N, int &K, int h[max_meret][vsz]);
///Azonosan igazat adó függvény
bool def(int a){return true;}
///2 .. 100 intervallumot ellenőrző függvény
bool ketto_szaz(int a) { return (a>=2 && a<=100);}
///Egész ellenőrzött beolvasás validáló függvénnyel
int read_int(const string &msg, const string &errmsg, bool valid(int)=def);

///Megoldó függvények
///1. feladat megoldása: összegzés
void feladat1(int N, const int h[][vsz], const int pont[], int vp[]);
///2. feladat megoldása:megszámolás
void feladat2(int N, const int h[][vsz], int isz[]);
///3. feladat megoldása: kiválogatásban eldöntés
int feladat3(int N, const int h[][vsz], int K, int mv[]);
///4. feladat  megoldása: kiválogatásban eldöntés

///5. feladat megoldása: rendezés, keresés
int feladat5(int N,int K,int vp[], int kj[]);

///Buborék rendezéshez csere függvény
void csere(int& a, int& b)
{
    int tmp=a;
    a=b;
    b=tmp;
}
///Buborek rendezes
void Buborek(int N,int p[],int ind[]);

///Megoldó program
int main()
{
    ///Változók, beolvasás
    int N, K;
    int h[max_meret][vsz];
    #ifdef BIRO
        beolvasBIRO(N, K, h);
    #else
        beolvasConsol(N, K , h);
    #endif // BIRO

    ///Pontozó tömb előállítása
    int pont[max_meret+1] = {0};
    pont[K] = 1;
    for (int i=K-1; i>0; --i)
    {
        pont[i] = pont[i+1]+2;
    }
    #ifdef TESZT
        cerr << "\n#TESZT: pontozo tomb tartalma:\n";
        for (int i=0; i<max_meret+1; ++i)
        {
            cerr << pont[i] << " ";
        }
        cerr << "\n";
    #endif // TESZT

    ///1. feladat
    ///versenyzok pontjai, összegzés tétel
    int vp[max_meret];
    feladat1(N, h, pont, vp);
    #ifndef BIRO
        cout << "\nA versenyzok pontszamai:\n";
    #endif // BIRO
        for (int i=0; i<N; ++i)
        {
            cout << vp[i] << " ";
        }
    cout<<endl;

    ///2. feladat
    ///versenyenként az indultak száma, megszámolás tétel
    int isz[vsz];
    feladat2 (N,h,isz);
    #ifndef BIRO
       cout <<"\nAz egyes versenyeken indult versenyzok szama:\n";
    #endif // BIRO
    for (int i=0; i<vsz;++i)
    {
        cout<<isz[i]<<" ";
    }
    cout<<endl;

    ///3. feladat
    ///minden versenyen pontot szerzo versenyzok, kiválogatásban eldöntés
    int db3;
    int mv[max_meret];
    db3 = feladat3 (N,h,K,mv);
    #ifndef BIRO
        cout <<"\nA minden versenyen helyzest elert versenyzok szama es sorszamaik:\n";
    #endif // BIRO
    cout<<db3;
    for (int i=0;i<db3;++i)
    {
        cout<<" "<<mv[i]+1;
    }
    cout<<endl;

    ///4. feladat
    ///legalább egy versenyen gyõztek, kiválogatásban eldöntés
    int db4=-1;
    int elsok[max_meret];
    for (int i=0;i<N;++i)
    {
        bool van=false;
        for (int j=0;j<vsz;++j)
        {
            if (h[i][j]==1)
            {
                van=true;
                break;
            }
        }
        if (van)
        {
            ++db4;
            elsok[db4]=i+1;
        }
    }
    cout<<db4+1;
    for (int i=0;i<=db4;++i)
    {
        cout<<" "<<elsok[i];
    }
    cout<<endl;

    ///5. feladat
    ///Kik jutnak ki az olimpiára?
    ///Összpontszám szerinti csökkenő sorrendbe rendezés, majd megkeressük az utolsó
    ///versenyzőt, aki még kijut: rendezés, keresés
    int db5;
    int kj[max_meret];
    db5=feladat5(N,K,vp,kj);
    #ifndef BIRO
        cout <<"\nAz olimpiara kijuto versenyzok szama es sorszamaik:\n"
    #endif // BIRO
    cout<<db5;
    for (int i=0;i<db5;++i)
    {
        cout<<" "<<kj[i];
    }
    cout<<endl;
    return 0;
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

void beolvasBIRO(int &N, int &K, int h[max_meret][vsz])
{
    cin>>N>>K;
    for(int i=0; i<N; ++i)
    {
        for(int j=0; j<vsz; ++j)
        {
            cin>>h[i][j];
        }
    }
}
void beolvasConsol(int &N, int &K, int h[max_meret][vsz])
{
    N=read_int("Hany versenyzo van: ","Legalabb 2 es legfeljebb 100 legyen!\n",ketto_szaz);
    bool hiba;
    do{
        K=read_int("Mennyi a K erteke: ","Legalabb 2 es legfeljebb 100 legyen!\n",ketto_szaz);
        hiba=K>N;
        if(hiba)
        {
            cout<<"K nem lehet nagyobb mint ahany versenyzo van! Add meg ujra!\n";
        }
    }while(hiba);
    //Mátrix beolvasása
    for(int i=0; i<N; ++i)
    {
        string sz;
        do{
            hiba=false;
            cout<<"Add meg az "<<i+1<<
            ". versenyzo eredmenyeit (szokozokkel elvalasztva)\n";
            getline(cin,sz);
            istringstream inp(sz);
            for(int j=0; !hiba && j<vsz; ++j)
            {
                inp>>h[i][j];
                hiba=inp.fail() || h[i][j]<0 || h[i][j]>N;
            }
            hiba = hiba || !inp.eof();
            if(hiba){
                cerr<<"Hibas vagy tul keves/tul sok adat, add meg ujra!\n";
            }
        }while(hiba);
    }
    ///Versenyenként a helyezések megadásának ellenőrzése
    ///Ha H a legnagyobb érték, akkor minden 1..H közötti érték pontosan egyszer fordul elő
    ///Megoldás: HF
}

void feladat1(int N, const int h[][vsz], const int pont[], int vp[])
{
    for (int i=0; i<N; ++i)
    {
        int sum = 0;
        for (int j=0; j<vsz; ++j)
        {
            sum += pont[h[i][j]];
        }
        vp[i] = sum;
    }
}

void feladat2(int N, const int h[][vsz], int isz[])
{
    for (int j=0;j<vsz;++j)
    {
        isz[j]=0;
        for (int i=0; i<N;++i)
        {
            if (h[i][j]>0) ++isz[j];
        }
    }
}

int feladat3(int N, const int h[][vsz], int K, int mv[])
{
    int db=0;
    for(int i=0;i<N;++i)
    {
        int j=0;
        while(j<vsz && h[i][j]>0 && h[i][j]<=K)
        {
            ++j;
        }
        if(j==vsz)
        {
            mv[db] =i;
            ++db;
        }
    }
    return db;
}

void Buborek (int N,int p[],int ind[])
{
    ///javított bubrék rendezés
    for (int meddig =N-1;meddig >0;)
    {
        int utolso=-1;
        for (int j=0; j<meddig;++j)
        {
            if(p[j]<p[j+1])
            {
                csere(p[j],p[j+1]);
                csere(ind[j],ind[j+1]);
                utolso=j;
            }
        }
        meddig=utolso;
    }
}

int feladat5(int N,int K,int vp[], int kj[])
{
    ///kj tömb feltöltése a versenyzők sorszámaival
    for (int i=0; i<N;++i)
    {
        kj[i]=i+1;
    }
    ///Rendezés
    Buborek(N,vp,kj);
    ///Hányan jutnak ki.
    int db=K; //Első K kijut
    ///Utolsó pontszámmal megegyező pontszámúak keresése
    int up=vp[K-1];
    while (db<N && vp[db]==up)
    {
        ++db;
    }
    return db;
}
