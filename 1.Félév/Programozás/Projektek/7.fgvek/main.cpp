#include <iostream>
#include <cmath>
#include <sstream> //!!!!

using namespace std;

int beolvas_int(const string&);
float beolvas_float(const string&);
int beolvas_nat(const string& sz);
int maximum(int,int);
float maximum(float,float);
void csere(int&,int&);
void csere(float&,float&);
bool prim(int);
void beolvas(int mibe[], int& meret, int maxmeret);
void szetvalogat(const int honnan[], int n, int hova[] , int& db);
void kiir(const int adatok[], int n, const int indexek[], int db);

int main()
{
    cout << "Fuggvenyek keszitese\n" << endl;
/*
    int i,j;
    cout<<"maximum fuggveny tesztelese\n";
    i=beolvas_int("kerem az elso egesz szamot:");
    j=beolvas_int("kerem a masodik egesz szamot:");
    cout<<"A nagyobbik: "<<maximum(i,j)<<endl;
    float x,y;
    x=beolvas_float("kerem az elso valos szamot:");
    y=beolvas_float("kerem a masodik valos szamot:");
    cout<<"A nagyobbik: "<<maximum(x,y)<<endl;

    cout<<"Csere fuggveny tesztelese\n";
    cout<<"csere elott:"<<i<<" "<<j<<endl;
    csere(i,j);
    cout<<"csere utan:"<<i<<" "<<j<<endl;
    cout<<"csere elott:"<<x<<" "<<y<<endl;
    csere(x,y);
    cout<<"csere utan:"<<x<<" "<<y<<endl;

    int meddig=beolvas_nat("Meddig irjam ki a primszamokat:");
    for(int i=2; i<=meddig; ++i){
        if(prim(i)) cout<<i<<"\t";
    }
    cout<<endl;

    */
    const int maxmeret=100;
    int a[maxmeret],n;
    beolvas(a,n,maxmeret);
    int z[n],db;
    szetvalogat(a,n,z,db);
    kiir(a,n,z,db);
    return 0;
}

int maximum(int a, int b)
{
    if (a>b) return a;
    else return b;
}

float maximum(float a, float b)
{
    if (a>b) return a;
    else return b;
}

void csere(int &a, int &b)
{
    int temp = a;
    a = b;
    b = temp;
}

void csere(float &a, float &b)
{
    float temp = a;
    a = b;
    b = temp;
}

bool prim(int k)
{
    if(k<2)
    {
        return false;
    }
    for (int i=2;i<=sqrt(k);++i)
    {
        if (k%i==0)
        {
            return false;
        }
    }
    return true;
}

int beolvas_nat(const string &sz){
    bool hiba;
    int e;
    do{
        e=beolvas_int(sz);
        hiba=e<=0;
        if(hiba)
        {
            cout<<"Pozitiv legyen! Add meg ujra!\n";
        }
    }while(hiba);
    return e;
}

int beolvas_int(const string &sz){
    string s;
    bool hiba;
    int e;
    do{
        cout<<sz;
        cin>>e;
        hiba=cin.fail();
        if (hiba){
            cout<<"Ez nem egesz szam, add meg ujra!\n";
            cin.clear();  //hiba flag-ek tölése
        }
        getline(cin,s); //Enter-ig a puffer kitisztítása
        if(!hiba && s!="")
        {
            hiba=true;
            cout<<"A beirt szam utan felesleges karakterek vannak! Add meg ujra!\n";
        }
    }while (hiba);
    return e;
}

float beolvas_float(const string &sz){
    string s;
    bool hiba;
    float e;
    do{
        cout<<sz;
        cin>>e;
        hiba=cin.fail();
        if (hiba){
            cout<<"Ez nem valos szam, add meg ujra!\n";
            cin.clear();  //hiba flag-ek tölése
        }
        getline(cin,s); //Enter-ig a puffer kitisztítása
        if(!hiba && s!="")
        {
            hiba=true;
            cout<<"A beirt szam utan felesleges karakterek vannak! Add meg ujra!\n";
        }
    }while (hiba);
    return e;
}

void beolvas(int mibe[], int& meret, int maxmeret)
{
    string s;
    bool hiba;
    do
    {
        meret=beolvas_nat("Hany adat lesz?: ");
        hiba=meret>maxmeret;
        if(hiba) cout<<"1 es "<<maxmeret<<" kozott legyen!\n";
    }while(hiba);
    for (int i=0;i<meret;++i)
    {
        stringstream out;
        out<<i+1<<". adat: ";
        s=out.str();
        mibe[i]=beolvas_int(s);
    }
    cout<<"Beolvasas vege"<<endl;
}
void szetvalogat(const int honnan[], int n, int hova[] , int& db)
{
    db=0;
    int ind2=n;
    for (int i=0; i<n;++i)
    {
        if(prim(honnan[i]))
        {
            hova[db]=i;
            db++;

        }
        else
        {
            --ind2;
            hova[ind2]=i;
        }
    }
}
void kiir(const int adatok[], int n, const int indexek[], int db)
{
    cout<<"primek:\n";
    for(int i=0; i<db; ++i)
    {
        cout<<indexek[i]+1<<". elem: "<<adatok[indexek[i]]<<endl;
    }
    cout<<"\nnem primek:\n";
    for(int i=n-1; i>=db; --i)
    {
        cout<<indexek[i]+1<<". elem: "<<adatok[indexek[i]]<<endl;
    }
}
