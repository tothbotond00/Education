#include <iostream>
///#include <string>

using namespace std;

///Következő függvényeket használjuk:

void Kisbetus(const string&, string&);
///Az első paraméterben kapott szöveget kisbetűsre alakítja,
///A második paraméterben kapjuk vissza az átlakított szöveget

int Eldontes(const char[],int,char);
///Megnézi, hogy a harmadik paraméterben kapott karakter előfordul-e a megadott tömbben.
///Ha igen az index-szel tér vissza, ha nem, -1-gyel.

void Kigyujt(const string&, char [], int&);
///Kigyűjti, hogy az első paraméterben megadott sztring milyen karaktereket
///tartalmaz a második paraméterben megadott tömbbe. A kigyűjtött jelek száma
///a harmadik paraméterben kerül visszaadásra

void Kiir(const char[], int, const string&);
///Kiirja az első paraméterben kapott tömböt a képernyőre
///Második paraméter a tömb elemeinek száma
///Harmadik paraméter az a szöveg, amit a tömb listázása előtt kiír

void Metszet(const char[], int, const char[], int, char[],int&);
///Az első és második, valamint a harmadik és negyedik paraméter
///egy-egy karakterekből álló halmaz elemeit adja meg.
///A két halmaz metszetét határozza meg a függvény,
///az ötödik és hatodik paraméterbe.

bool Keres(const string[], int, const string&, int&, int&);
///Keres egy kettős mássalhangzót a megadott szövegben
///Visszatérési érték igaz, ha talált, hamis ha nem
///Első paraméter: a kettős mássalhangzók tömbje
///Második paraméter: a tömb hossza
///Harmadik paraméter: a vizsgált szöveg
///Negyedik paraméter: melyik pozíción talált kettős mássalhangzót
///Ötödik paraméter: melyik kettős mássalhangzót találta meg

bool Eldontes(const string[],int,char,char,int&);
///Megnézi, hogy a két karakter szerepel-e a megadott tömbben (az első és második betűként).
///Ha igen igazzal tér vissza, és az utolsó paraméterben megadja a találat indexét.
///Ha nem, akkor hamissal tér vissza.


int main()
{
    const char mh[5]={'a','e','i','o','u'};
    const string kmh[8]={"cs","gy","dz","ly","ny","sz","ty","zs"};
    string s,s2,sz;
    char ch;
    do{
        ///magánhangzók megszámolása
        ///számlálók nullára állítása
        int mhdb[5]={0};
        int egyeb=0;

        ///Szöveg bekérése (lehet benne szóköz, ezért getline!
        cout<<"Add meg a mondatot:\n";
        getline(cin,s);

        ///Átalakítás kis-betűsre
        Kisbetus(s,sz);
        cout<<"A kis betus szoveg:\n"<<sz<<endl;

        ///Számlálás
        for (int i=0; i<(int)sz.size();++i)
        {
            int k=Eldontes(mh,5,sz[i]);
            if(k!=-1)
            {
                ++mhdb[k];
            }
            else
            {
                ++egyeb;
            }
        }
        cout<<"\nA szovegben elofordulo maganhangzok:\n";
        for(int i=0;i<5;++i)
        {
            cout<<mh[i]<<" betuk szama: "<<mhdb[i]<<endl;
        }
        cout<<"Egyeb jelek szama: "<<egyeb<<endl;

        ///Milyen jelek fordulnak elo a szovegben?
        ///Itt most az eredeti szövegen dolgozunk!
        char jel[256];
        int db;
        Kigyujt(s,jel,db);
        Kiir(jel,db,"A szoveg jelkeszlete:\n");

        ///Mely jelek fordulnak elő mindkét szövegben
        cout<<"Add meg a masodik szoveget:\n";
        getline(cin,s2);
        char jel2[256];
        int db2=0;
        Kigyujt(s2,jel2,db2);
        Kiir(jel2,db2,"A masodik szoveg jelkeszlete:\n");
        char jel3[256];
        int db3;
        Metszet(jel,db,jel2,db2,jel3,db3);
        Kiir(jel3,db3,"A metszete a ket jelkeszletnek:\n");

        ///Van-e benne kétjegyû mássalhangzó?
        int hol,mit;
        bool van=Keres(kmh,8,sz,hol,mit);
        if(van)
        {
          cout<<"Talaltam "<<kmh[mit]<<" massalhangzot a/az "<<hol+1<<". pozicion.\n";
        }
        else
        {
            cout<<"Nem talaltam kettos massalhangzot.\n";
        }

        do{
            cout<<"\nKivanja ujra futtatni a programot (i/I/n/N)? :";
            cin>>ch;
            ch=tolower(ch);
        }while (ch!='i' && ch!='n');

    getline(cin,sz);

    }while(ch=='i');

    return 0;
}

void Kisbetus(const string& s, string& sz)
{
    sz="";
    for(unsigned int i=0;i<s.size();++i)
    {
        sz.push_back(tolower(s[i]));
    }
}

int Eldontes(const char t[], int n, char c)
{
    int j=0;
    while(j<n && c!=t[j]) ++j;
    if(j<n) return j; else return -1;
}

void Kigyujt(const string& s, char jel[], int& db)
{
    db=0;
    for (int i=0; i<(int)s.size();++i)
    {
        int k=Eldontes(jel,db,s[i]);
        if(k==-1)
        {
            jel[db]=s[i];
            ++db;
        }
    }
}

void Kiir(const char jel[],int db, const string& szoveg)
{
    cout<<szoveg;
    for(int i=0;i<db;++i)
    {
        if(jel[i]==' ')
            cout<<"szokoz ";
        else
            cout<<jel[i]<<" ";
    }
    cout<<endl;
    return;

}

void Metszet(const char t1[],int n1,const char t2[], int n2, char t3[],int& n3)
{
    n3=0;
    for(int i=0; i<n1;++i)
    {
        int k=Eldontes(t2,n2,t1[i]);
        if (k!=-1)
        {
            t3[n3]=t1[i];
            ++n3;
        }
    }
}

bool Keres(const string kmh[], int n, const string& sz, int& ind, int& mit)
{
    int i=0;
    bool van=false;
    while(i<int(sz.size())-1 && !van)
    {
        van=Eldontes(kmh,8,sz[i],sz[i+1],mit);
        ind=i;
        ++i;
    }
    return van;
}

bool Eldontes(const string t[], int n, char a, char b, int &ind)
{
    int j=0;
    bool van=false;
    while(j<n && !van )
    {
        van=t[j][0]==a && t[j][1]==b;
        ind=j;
        ++j;
    }
    return van;
}
