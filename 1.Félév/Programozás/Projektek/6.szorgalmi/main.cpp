#include <iostream>
using namespace std;

int main()
{
    int ugyfelek;
    int napok;
    int rendelesek;
    cin >>ugyfelek;
    cin >>napok;
    cin >>rendelesek;
    int id [rendelesek];
    int napid [rendelesek];
    for (int i=0; i<rendelesek;++i)
    {
        cin>>id[i]>>napid[i];
    }
    //1.Feladat:
    int max1=0;
    for (int i=1;i<=napok;++i)
    {
        int db=0;
        for (int j =0;j<rendelesek;++j)
        {
            if (i==napid[j]) ++db;
        }
        if (db>max1) max1=db;
    }
    cout <<max1<< endl;
    //2.Feladat:
    int db2=0;
    int foglalt [rendelesek];
    for (int i=1;i<=napok;++i)
    {
        bool igeny=false;
        for (int j =0;j<rendelesek;++j)
        {
            if (i==napid[j])
            {
               igeny=true;
               break;
            }
        }
        if (igeny)
        {
            foglalt[db2]=i;
            ++db2;
        }
    }
    cout <<db2;
    for (int i=0; i<db2;++i)
    {
        cout <<" "<<foglalt[i];
    }
    cout<<endl;
    //3.Feladat:
    int max3=0;
    int szama =0;
    for (int i=1;i<=ugyfelek;++i)
    {
        int db=0;
        for (int j=0;j<rendelesek;++j)
        {
            if (i==id[j]) ++db;
        }
        if (db>max3)
        {
            szama=1;
            max3=db;
        }
        else if (db==max3)
        {
            ++szama;
        }
    }
    cout <<szama;
    for (int i=1;i<=ugyfelek;++i)
    {
        int db=0;
        for (int j =0;j<rendelesek;++j)
        {
            if (i==id[j]) ++db;
        }
        if (db==max3)
        {
            cout <<" "<<i;
        }
    }
    cout << endl;
    //4.Feladat:
    int leghoszabb =0;
    int legkezdet=0;
    int legveg=0;
    for (int i=1;i<=napok;++i)
    {
        int hossz=0;
        int kezdet =i;
        int j=-1;
        while(j<rendelesek)
        {
            ++j;
            if(napid[j]==i)
            {
                j=-1;
                ++hossz;
                ++i;
            }
        }
        int veg =i-1;
        if (hossz>leghoszabb)
        {
            leghoszabb=hossz;
            legkezdet=kezdet;
            legveg=veg;
        }
    }
    cout <<legkezdet<<" "<<legveg<<endl;
    //5.Feladat:
    cout << endl;
    return 0;
}
