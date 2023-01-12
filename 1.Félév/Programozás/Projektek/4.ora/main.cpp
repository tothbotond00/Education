#include <iostream>
using namespace std;

int main()
{
    cout<<"Huszonegy"<<endl;
    const string kartya[]={"VII","VIII","IX","X","Also","Felso","Kiraly","Asz"};
    const int ertek[]={7,8,9,10,2,3,4,11};
    int n,sum;
    string lap,s;
    bool hiba;
    //char c;
    do{
        cout<<"\nLehetseges lapok:";
        for(int i=0;i<8;++i) cout<<kartya[i]<<" ";
        cout<<endl;
        cout<<"\nA lapok ertekei:";
        for(int i=0;i<8;++i) cout<<ertek[i]<<" ";
        cout<<endl;
        //Lapok számának ellenõrzött beolvasása
        do
        {
            cout<<"Hany lapod van:";
            cin>>n;
            hiba=cin.fail()||n<1||n>32;
            if(hiba)
            {
                cout<<"Helytelen a megadott darabszam!\n";
                cin.clear();
            }
            getline(cin,s);
            hiba = hiba || s!="";
        }while(hiba);
        //Lapok beolvasása, értékük összegzése
        sum=0;
        for(int i=1; i<=n; ++i)
        {
            int j;
            do
            {
                cout<<i<<".lap:";
                getline(cin,lap);
                j=0;
                while(j<8&&kartya[j]!=lap) ++j;
                hiba=j>7;
                if(hiba) cout<<"Ilyen lap nincs!\n";
            }while(hiba);
            cout<<"Erteke:"<<ertek[j]<<endl;
            sum+=ertek[j];
        }
        cout<<"Lapjaid osszerteke:"<<sum<<endl;
        do{
            cout<<"\nFutassuk ujra (i/n):";
            //getline(cin,s);
            getline(cin,s);
            hiba=s!="i" && s!="n";
            if(hiba)
                cout<<"Helyes valasz: i/n\n";
        }while(hiba);
    }while(s=="i");
    cout<<"\nViszontlatasra!\n";
    return 0;
}
