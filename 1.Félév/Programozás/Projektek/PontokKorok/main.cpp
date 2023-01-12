#include <iostream>
#include <fstream>
#include <cmath>
#include <stdlib.h>

#define PI 3.14159265359;

using namespace std;

const int maxmeret=100;

///négyzetre emelő függvény
float negyzet (float x) { return x*x;}

///pont típus typedef-fel
typedef struct
{
    float x,y,d;
} pont;

///kör típus struct-tal
struct kor
{
    pont kpont;
    float r;
    float terulet () const { return r*r*PI};
};

///Két pont távolságának négyzete
float tav_negyzet (pont p, pont q)
{
    return negyzet(p.x-q.x)+negyzet (p.y-q.y);
    //return pow((p.x-q.x),2)+pow((p.y-q.y),2);
}

///Adott pont rajta ven-e a körlemezen
bool rajtavan (kor k, pont p)
{
    float d=tav_negyzet(k.kpont,p);
    return d<=negyzet(k.r);
}

///Beolvasó függvények
void beolvasPontok(pont [],int&);
void beolvasKorok(kor [], int&);

///Feltételes maximum keresés függvénye
///Paraméterek:
///pontok tömbje, pontok száma,
///körök tömbje, körök száma,
///a feladat szerinti t paraméter
///felt.maxker eredménye: index és darabszám
bool feltMaxKer(const pont [], int, const kor [], int, float, int&, int&);

///Megszámlálás: hány pontot tartalmaz a körlemez
///Paraméterek
///A vizsgált kör
///pontok tömbje, pontok száma
int hanyPont(kor, const pont[],int);

///Pontok origótól vett távolságát kiszámító függvény
void pontTavolsagok(pont [], int);
///Javított buborék rendezés
///Origótól vett távolság szerint növekvő sorrendbe rendezi a pontokat
void rendez(pont[],int);
///Kiírja a pontok tömbjét
void kiir(const pont[], int);
//Csere függvény a rendezéshez
void csere(pont& p1, pont& p2);

///Hazi feladat:
///Legtavolabbi pontok
float legtavolabb (pont [],int ,int&, int&);

int main()
{
    pont p[maxmeret];
    int n;
    beolvasPontok(p,n);

    kor k[maxmeret];
    int m;
    beolvasKorok(k,m);

    ///Melyik legfeljebb t területû kör tartalmazza a legtöbb pontot
    float t;
    cout<<"Kerem a t paramter erteket: "; cin>>t;
    int ind,db;
    bool van =feltMaxKer(p,n,k,m,t,ind,db);
    if(van)
    {
        cout<<"Az "<<ind+1<<". kor tartalmazza a legtobb pontot: "<<db<<endl;
    }
    else
    {
        cout<<"Mindegyik kor terulete nagyobb, mint "<<t<<endl;
    }
    ///Legnagyobb tavolsagu ket pont (HAZI FELADAT)
    int pont1;
    int pont2;
    float max = legtavolabb(p,n,pont1,pont2);
    cout<<legtavolabb;
    cout<<"A legnagyobb tavolsag: "<<max<<" a ("<<p[pont1].x<<","<<p[pont1].y<<") es a ("<<p[pont2].x<<","<<p[pont2].y<<") kozott.";
    ///Pontok listázása az origótól vett távolságuk szerint növekvõ sorrendben
    pontTavolsagok(p,n);
    rendez(p,n);
    kiir(p,n);

    return 0;
}

void beolvasPontok(pont q[],int& meret)
{
    bool hiba;
    //Fajl megnyitasa
    string fnev;
    ifstream inp;
    do{
        cout<<"Add meg a pontokat tartalmazo fajl nevet:";
        cin>>fnev;
        inp.open(fnev.c_str());
        hiba=inp.fail();
        if(hiba){
            cerr<<"Nem sikerult "<<fnev<<" fajl megnyitasa! Add meg ujra!\n";
            inp.clear();
        }
    }while(hiba);
    //Adatok beolvasasa;
    cout<<"\nBeolvasott pontok:\n";
    inp>>meret;
    for(int i=0;i<meret;++i)
    {
        inp>>q[i].x>>q[i].y;
        cout<<i+1<<".pont:\t"<<q[i].x<<"\t"<<q[i].y<<endl;
    }
    cout<<endl;
    inp.close();
}

void beolvasKorok(kor q[], int& meret)
{
    bool hiba;
    //Fajl megnyitasa
    string fnev;
    ifstream inp;
    do{
        cout<<"Add meg a koroket tartalmazo fajl nevet:";
        cin>>fnev;
        inp.open(fnev.c_str());
        hiba=inp.fail();
        if(hiba){
            cerr<<"Nem sikerult "<<fnev<<" fajl megnyitasa! Add meg ujra!\n";
            inp.clear();
        }
    }while(hiba);
    //Adatok beolvasasa;
    cout<<"\nBeolvasott korok:\n";
    inp>>meret;
    for(int i=0;i<meret;++i)
    {
        inp>>q[i].kpont.x>>q[i].kpont.y>>q[i].r;
        cout<<i+1<<".kor:\tkpont=("<<q[i].kpont.x<<","<<q[i].kpont.y<<")\tr="<<q[i].r<<
        "\tterulete: "<<q[i].terulet()<<endl;
    }
    cout<<endl;
    inp.close();
}



bool feltMaxKer(const pont p[], int n, const kor k[], int m, float t, int& ind, int& maxe)
{
    bool van= false;
    for (int i=0;i<m;++i)
    {
        bool jo=k[i].terulet() <=t;
        if(!jo)   continue;
        else if (jo && van)
        {
            int c= hanyPont(k[i],p,n);
            if (c>=maxe)
            {
                ind=i;
                maxe=c;
            }
        }
        else //jo && !van
        {
            van=true;
            ind=i;
            maxe=hanyPont(k[i],p,n);
        }
    }
    return van;
}
int hanyPont(kor k, const pont p[],int n)
{
    int db=0;
    for (int i=0;i<n;++i)
    {
        if(rajtavan(k,p[i])) ++db;
    }
    return db;
}


void pontTavolsagok(pont p[], int n)
{
    cout<<"\nPontok tavolsaga az origotol\n";
    for(int i=0; i<n; ++i)
    {
        p[i].d=negyzet(p[i].x)+negyzet(p[i].y);
        cout<<i+1<<" ("<<p[i].x<<","<<p[i].y<<")  "<<sqrt(p[i].d)<<endl;
    }
}
void rendez(pont p[],int n)
{
    //javított buborék
    int i=n-1;
    while(i>0)
    {
        int ind=-1;
        for(int j=0; j<=i-1; ++j)
        {
            if(p[j].d > p[j+1].d)
            {
                csere(p[j],p[j+1]);
                ind=j;
            }
        }
        i=ind;
    }
}

void kiir(const pont p[], int n)
{
    cout<<"\nPontok tavolsag szerint novekvoen:\n";
    for(int i=0; i<n; ++i)
    {
        cout<<i+1<<". pont:"<<p[i].x<<","<<p[i].y<<"\ttavolsaga: "<<sqrt(p[i].d)<<endl;
    }
}

void csere(pont& p1, pont& p2)
{
    pont tmp=p1;
    p1=p2;
    p2=tmp;
}

float legtavolabb (pont p [],int n,int& k1, int& k2)
{
    float maxtav=-1.0;
    for (int i=0;i<n;++i)
    {
        for (int j=i+1;i<n;++j)
        {
            float d = negyzet(p[i].x-p[j].x)+negyzet (p[i].y-p[j].y);
            cout<<d;
            if (d>maxtav)
            {
                maxtav=d;
                k1=i;
                k2=j;
            }
        }
    }
    return maxtav;
}
