//Tóth Botond, MQH41V
#include <iostream>

using namespace std;

int BeolvasasN (int);
int BeolvasasSuly(int);

int main()
{
    cout<<"Jo napot!\n";
    bool menjen=true;
    do{

    //Adatok beolvasasa:
    int n ;
    //n ellenorzese

    n=BeolvasasN(n);

    //adatok beolvasasa:
    string vad [n];
    double suly [n];
    string fajta;
    cout<<"Most kerem az "<<n<<" db vadat es sulyt.\n";
    for (int i=0;i<n;++i)
    {
        cin>>vad[i];
        //suly ellenorzese
        suly[i]=BeolvasasSuly(suly[i]);
    }
    cout<<"Most kerem a vad nevet amely az (A)feladahoz kell\n";
    cin>>fajta;

    //a Feladat:
    cout<<"\nA.feladat megoldasa:\n";
    //a feladat megoldo algoritmusa:
    double maxs =0;
    for (int i=0;i<n;++i)
    {
        if(vad[i]==fajta)
        {
            if(suly[i]>maxs)
            {
                maxs=suly[i];
            }
        }
    }
    bool zsakmany = maxs>0;
    //a Feladat kiirasa:
    if(zsakmany)
    {
        cout<<"Lott "<<fajta<<"-t,\n";
        cout<<"A legsulyosabb "<<fajta<<" sulya: "<<maxs<<" kg.\n";
    }
    else
    {
        cout<<"Nem lott "<<fajta<<"-t,\n";
    }

    //b Feladat:
    cout<<"\nB.feladat megoldasa:\n";
    //b feladat megoldo algoritmusa
    int db=-1;
    string v[n];
    int num[n];
    for (int i=0;i<n;++i)
    {
        int j=0;
        while(j<=db && vad[i]!=v[j]) ++j;
        if(j<=db)
        {
            ++num[j];
        }
        else
        {
            ++db;
            v[db]=vad[i];
            num[db]=1;
        }
    }
    //b feladat kiirasa
    cout<<db+1<<" fele vadat lott:\n";
    for (int i=0;i<=db;++i)
    {
        cout<<v[i]<<"\t"<<num[i]<<"\n";
    }

    //Rakerdezes ismertlesre:
    cout<<"Szeretne-e meg egy esettel megnezni (i/n)?\n";
    string sz;
    cin>>sz;
    if(sz=="n") menjen=false;
    cout<<"\n";
    } while(menjen);

    return 0;
}

//Fuggvenyek:
int BeolvasasN (int n)
{
    bool rossz=false;
    do{
    string s;
    bool rossz;
    cout<<"Hany adatot fog megadni?(2-100-ig)\n";
    cin>>n;
    rossz=cin.fail();
    cin.clear();
    getline(cin,s);
    if(rossz){
    cout<<"Nem szamot adott meg!\n";
    }
    else if (n<2||n>100)
    {
        cout<<"Hibas bemenet!\n";
    }
    } while (n<2||n>100||rossz);
    return n;
}

int BeolvasasSuly(int n)
{
    bool rossz=false;
    do{
    string s;
    bool rossz;
    cin>>n;
    rossz=cin.fail();
    cin.clear();
    getline(cin,s);
    if(rossz){
    cout<<"Nem szamot adott meg!\n";
    }
    else if (n<=(0.5)||n>500)
    {
        cout<<"Hibas bemenet!\n";
    }
    } while (n<=(0.5)||n>500||rossz);
    return n;
}
