#include <iostream>
#include <stdlib.h>
#include <math.h>

using namespace std;

int oszto_osszeg(int);
bool tokeletes(int);
bool bovelkedo(int);
bool hianyos(int);
bool baratsagos(int, int);
int beolvas_int(const string&);
int beolvas_nat(const string&);
bool primgyors(int);                                            //prím számítása
bool baratsagos2(int , int ,int );                             //optimalizálva a baratsagos fv
int main()
{

    cout << "Onallo feladatok!" << endl;
    int n=beolvas_nat("Meddig irjam ki a szamokat:");

    //Tökéletes számok kiírása n-ig
    cout<<"\nTokeletes szamok\n";
    for(int i=1; i<=n; ++i)
        if(tokeletes(i)) cout<<i<<"\t";
    cout<<endl;


    //Bővelkedő számok kiírása n-ig
    cout<<"\nBovelkedo szamok\n";
    for(int i=1; i<=n; ++i)
        if(bovelkedo(i)) cout<<i<<"\t";
    cout<<endl;

    //Hiányos számok kiírása n-ig
    cout<<"\nHianyos szamok\n";
    for(int i=1; i<=n; ++i)
        if(hianyos(i)) cout<<i<<"\t";
    cout<<endl;

    //Barátságos számpárok kiírása n-ig
    cout<<"\nBaratsagos szamok\n";
    for (int i=2; i<=n;++i)
    {
        if(primgyors(i))                                        //ellenőrizzük hogy a pím-e az i
        {
            continue;
        }
        int a=oszto_osszeg(i);
        int b;
        if(i>1000)
        {
            b=i*1.13;
        }
        else
        {
            b=i*1.3;
        }
        for(int j=i+2;j<=n;j+=2)                                 //elég csak i+2-től indulni hisz önmaga nem lehet plusz 2-esével kell csak ellenőrinzni mert páratlant csak páratlannal lehet
        {
            if(primgyors(j))                                     //itt sokminden van: ha j prím akkor is felejtős ha pedig nempárosak vagy páratlanok közösen akkor sem lehetnek
            {
                continue;                                        //előző sorhoz: ki lehet jelenteni hogy ha egy szám 1,3-ese a másiknak akkor már nem lehetnek barátok :(
            }
            else if(j>b)
            {
                break;
            }
            else if(baratsagos2(i,j,a))                               //optimalizált barátságos fv használata
            {
                cout<<i<<","<<j<<"\t";
                break;                                          //rögtön break mivel egy számnak csak egy barátságos pára lehet
            }
        }
    }
    cout<<endl;
    return 0;
}

bool primgyors(int k)
{
    for (int i=2;i<=sqrt(k);++i)
    {
        if(k%i==0)
        {
            return false;
        }
    }
    return true;
}

int oszto_osszeg(int k)
{
    int osszeg=0;
    for (int i=1; i<=k/2;++i)
    {
        if(k%i==0)
        {
            osszeg+=i;
        }
    }
    return osszeg;
}

bool tokeletes(int k)
{
    return k==oszto_osszeg(k);
}

bool bovelkedo(int k)
{
    return k<oszto_osszeg(k);
}

bool hianyos(int k)
{
    return k>oszto_osszeg(k);
}

bool baratsagos(int a, int b)
{
    return (a==oszto_osszeg(b)&&b==oszto_osszeg(a));
}

bool baratsagos2(int a, int b,int c)
{
    return (a==oszto_osszeg(b)&&b==c);
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


