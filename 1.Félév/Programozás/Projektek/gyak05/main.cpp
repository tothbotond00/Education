#include <iostream>
#include <math.h>

using namespace std;

/*Feladatok:
Adatok: téli napok átlaghõmérsékletei (valós számok -30 és 20 között)

1. (Összegzés és Megszámolás)
    Adjuk meg a fagyos napok hõmérsékleteinek átlagát.
    Vigyázzunk, nem biztos, hogy volt fagyos nap!

2. (Keresés)
    Van-e olyan, három egymás utáni nap, hogy a középső nap kisebb,
    mint a két szomszédjának az átlaga?
    Ha igen, adjuk meg a három nap sorszámát.

3. (Maximum kiválasztás)
    Melyik két nap között változott legnagyobbat a hõmérséklet?
    Mennyi volt a változás?

4. (Keresés)
    Keressük meg melyik volt az utolsó fagyos nap?

5. (Kiválogatás)
   Hány lehűléses nap volt, és mely napokon volt lehűlés, adjuk meg a napok sorszámát is!

6. (Maximum kiválasztás a kiválogatott adatok segítségével.)
   Lehűlések előtti napok közül melyiken volt maximális az átlaghőmérséklet?
*/

int main()
{
    //Tömb lefoglalása
    const int max_meret=100; //tömb elemeinek maximális száma
    float h[max_meret]; //hõmérsékleteket tartalmazó tömb
    int n=-1; //hány eleme van a tömbnek (n>=0) -1 jelzi, hogy még nem volt beolvasás
    bool hiba; //ellenőrzött beolvasáshoz
    string s; //getline-hoz
    //Menü rendszer
    int mdb=9; //Menüpontok száma
    int v; //Melyik pontot választja
    do{
        //Menu kiirása
        cout<<"\nMenu:\n";
        cout<<"1. Adatok beolvasasa\n";
        cout<<"2. Adatok kiirasa\n";
        cout<<"3. Fagyos napok atlaga\n";
        cout<<"4. Van-e olyan ertek, amelyik kisebb a szomszedai atlaganal\n";
        cout<<"5. Melyik ket nap kozott a legnagyobb az elteres\n";
        cout<<"6. Melyik volt az utolso fagyos nap\n";
        cout<<"7. Lehuleses napok darabszama es sorszamai\n";
        cout<<"8. Lehuleses napok elotti napok kozul a legmelegebb\n";
        cout<<"9. Kilepes\n";
        do{
            cout<<"Melyiket valasztod:";
            cin>>v;
            hiba=cin.fail() || v<1 || v>mdb;
            if(hiba) cout<<"1 es "<<mdb<<" kozotti egesz szamot kerek, add meg ujra!\n";
            cin.clear();
            getline(cin,s);
            if(n==-1 && v!=mdb && v!=1)
            {
                hiba=true;
                cout<<"\nMeg nem olvastal be adatot, valaszd az 1. vagy "<<mdb<<". pontot!\n";
            }
        }while(hiba);
        cout<<endl;
        switch (v){
            case 1:
            {//Végjelig történő olvasás (az utolsó adat után nem számot ütünk
                bool vege;
                n=0;
                cout<<"Adja meg az adatokat sorban, a vegen usson egy betut.\n";
                do{
                    do{
                        cout<<n+1<<". adat:";
                        cin>>h[n];
                        vege=cin.fail();
                        hiba=false;
                        cin.clear(); //Fontos, getline nem hajtódik végre, ha cin.fail()=true
                        getline(cin,s);  //Felesleges bemenet lenyelése
                        if(!vege){ //számot írt be
                            hiba=h[n]<-30 || h[n]>20 || s!=""; //ellenõrizzük az elõfeltételt
                            if(hiba){
                                cout<<"Helytelen az adat, vagy extra karakterek, add meg ujra (-30 es 20 kozott legyen)!\n";
                            }
                        }
                    }while(hiba);
                    if(!vege){
                        n++;
                        if(n==max_meret){
                            cout<<"Tobb adatot nem tudok feldolgozni, a beolvasas leall!\n";
                            vege=true;
                        }
                    }
                }while(!vege);

                break;
            }
            case 2:
            {//Az adatok visszaírása
                cout<<"Adatok szama: "<<n<<endl;
                cout<<"A beolvasott adatok:\n";
                for(int i=0;i<n;++i)
                    cout<<h[i]<<"\t";
                cout<<endl;
                break;
            }
            case 3:
            {//Megszámolás és összegzés
                int db=0;
                int sum=0;
                for (int i=0;i<n;++i)
                {
                    if (h[i]<0)
                    {
                        sum+=h[i];
                        ++db;
                    }
                }
                if(db==0)
                {
                    string s="Nem volt fagyos nap";
                    cout<<s;
                }
                else
                {
                    float atlag=sum/db;
                    cout<<atlag;
                }
                break;
            }
            case 4:
            {//Kereses
                int i=1;
                while(i<=n-2&&!(h[i]<(h[i-1]+h[i+1])/2))
                {
                    ++i;
                }
                bool van =i<=n-2;
                if(van)
                {
                    int ind=i;
                    cout<<i+1;
                }
                else
                {
                    cout<<"Nem volt ilyen nap.";
                }
                break;
            }
            case 5:
            {//Maximum kivalasztas
                if(n<2)
                {
                    cout<<"Hiba: Túl kevés adat!";
                }
                else
                {
                    int max1=0;
                    float maxertek=sqrt((h[0]-h[1])*(h[0]-h[1]));
                    for(int i=1;i<=n-2;++i)
                    {
                        float d =sqrt((h[i]-h[i+1])*(h[i]-h[i+1]));
                        if(maxertek<d)
                        {
                            max1=i;
                            maxertek=d;
                        }
                    }
                    cout<<maxertek<<" a valtozas erteke es "<<max1+1<<" a helye";
                }
                break;
            }
            case 6:
            {//Kereses
                int i =n-1;
                while(i>=0&&h[i]>=0)
                {
                    --i;
                }
                bool van= i>=1;
                if(van)
                {
                    int ind=i;
                    cout<<ind+1<<". napon fagyott utoljára";
                }
                else
                {
                    cout<<"Nem fagyott";
                }
                break;
            }
            case 7: case 8:
            {//Kivalogatas
                int db=-1;
                int elotti [n-1];
                int lehult [n-1];
                for (int i=1;i<n;++i)
                {
                    if(h[i]+5<=h[i-1])
                    {
                        db++;
                        elotti[db]=h[i-1];
                        lehult[db]=i;
                    }
                }

                if(v==7)
                {
                    if(db==-1)
                    {
                        cout<<"Nem volt lehult nap";
                    }
                    else
                    {
                        cout<<db+1<<" db lehult nap volt"<<endl;
                        for(int i=0;i<=db;++i)
                        {
                            cout<<lehult[i]<<" napon volt az "<<i+1<<". lehules"<<endl;
                        }
                    }
                   break;
                }
            //Maximum kiválasztas a kiválogatás eredményén
                    if(db==-1)
                    {
                        cout<<"Nem volt lehult nap";
                    }
                    else
                    {
                        int max1=elotti[0];
                        int ind =0;
                        for (int i=1; i<db;++i)
                        {
                            if(max1<elotti[i])
                            {
                                max1=elotti[i];
                                ind=i;
                            }
                        }
                        cout<<ind+1<< ".napon volt a legmagasabb atlaghm. egy lehult nap elott.";
                    }
                break;
            }

            default:
            {
                cout<<"Viszontlatasra!\n";
            }
        }

    }while(v!=mdb);

    return 0;
}


