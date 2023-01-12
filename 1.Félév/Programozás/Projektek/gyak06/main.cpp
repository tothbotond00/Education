#include <iostream>
#include <cmath>

using namespace std;

/*Nehezebb feladatok:
Adatok: téli napok átlaghõmérsékletei (valós számok -30 és 20 között)

1. Mi a legkisebb érték előfordul-e többször? Adjuk meg a napokat, ahol a minimum előfordul.

2. Hány olyan van (a 2..n között), amelyik kisebb, az őt megelőző összes elemnél?

3. Hány negatív értékekből álló szakasz van?
   Határozzuk meg a negatív szakaszok számát, elejét és végét!

4. Mi a leghosszabb csupa negatív értékből álló szakasz hossza?
   Adjuk meg a hosszát, elejét és végét.
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
    int mdb=7; //Menüpontok száma
    int v; //Melyik pontot választja
    do{
        //Menu kiirása
        cout<<"\nMenu:\n";
        cout<<"1. Adatok beolvasasa\n";
        cout<<"2. Adatok kiirasa\n";
        cout<<"3. Mi a legkisebb ertek, es hol fordul elo\n";
        cout<<"4. Hany olyan elem van a 2..n elemek kozott, amelyek kisebbek valamennyi oket megelozo erteknel\n";
        cout<<"5. Negativ szakaszok szama, kezdete es vege\n";
        cout<<"6. Hatarozzuk meg a leghosszabb fagyos szakasz hosszat es kezdopontjat\n";
        cout<<"7. Kilepes\n";
        do{
            cout<<"Melyiket valasztod:";
            cin>>v;
            hiba=cin.fail() || v<1 || v>mdb;
            if(hiba) cout<<"1 es "<<mdb<<" kozotti egesz szamot kerek, add meg ujra!\n";
            cin.clear();
            getline(cin,s);
            if(n<=0 && v!=mdb && v!=1)
            {
                hiba=true;
                cout<<"\n0 darab adatunk van, valaszd az 1. vagy "<<mdb<<". pontot!\n";
            }
        }while(hiba);
        cout<<endl;
        switch (v){
            case 1:
            {//Végjelig történő olvasás (az utolsó adat után 'q' betűt ütünk
                bool vege;
                n=0;
                cout<<"Adja meg az adatokat sorban, a vegen usson egy 'q' betut.\n";
                cout<<"Legalabb egy adatot meg kell adnia!\n";
                do{
                    do{
                        cout<<n+1<<". adat:";
                        cin>>h[n];
                        vege=cin.fail();
                        hiba=false;
                        cin.clear(); //Fontos, getline nem hajtódik végre, ha cin.fail()=true
                        getline(cin,s);  //Felesleges bemenet lenyelése
                        if(vege && s!="q"){
                            cout<<"Hibas a beirt adat. Szamot kerek, vagy 'q' betut!\n";
                            hiba=true;
                        }
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
            {//Mi a legkisebb érték, és hol fordul elő (mindegyik napot fel kell sorolni).
             //Minimum kivalsztas a minimum helyének kiválogatása egy ciklusba vonva
                int min = h[0];
                int db=0;
                int y [n];
                y[0]=0;
                for (int i=1;i<n;++i)
                {
                    if (min==h[i])
                    {
                       ++db;
                       y[db]=i;
                    }
                    else if(h[i]<min)
                    {
                        min=h[i];
                        db=0;
                        y[db]=i;
                    }
                }
                cout<<"Legkisebb ertek: "<<min<<endl;
                cout<<"A kovetkezo napokon fordul elo:";
                for(int i=0; i<=db; ++i) cout<<y[i]+1<<" "; cout<<endl;
                break;
            }
            case 4:
            {//Hány olyan elem van (2..n között), amely kisebb valamennyi őt megelőzőnél
             //Megszamolas, kivalogatas (ezt nem kéri a feladat, de kiegészítjük kiválogatással is)
                int db=-1;
                int min = h[0];
                int ind [n];
                for (int i=0;i<n;++i)
                {
                  if (min>h[i])
                    {
                       ++db;
                       ind[db]=i;
                       min=h[i];
                    }
                }
                cout<<db+1<<" darab van\n";
                cout<<"sorszamaik: ";
                for(int i=0; i<=db; ++i) cout<<ind[i]+1<<" "; cout<<endl;
                break;
            }
            case 5:
            {//Fagyos szakaszok kezdő és végpontjának kiválogatása
             //1. megoldás
             int db=-1;
             int i=0;
             int j=0;
             int kezd[n];
             int veg[n];
        /*     while(i<n)
             {
                 while(i<n &&h[i]>=0)
                 {
                     ++i;
                 }
                 if(i<n)
                 {
                     j=i+1;
                     while(j<n &&h[j]<0)
                     {
                         ++j;
                     }
                     ++db;
                     kezd[db]=i;
                     veg[db]=j-1;
                     i=j+1;
                 }
             }
             */
             //2. megoldás (sajat megoldas)
             for(int i=0;i<n;++i)
             {
                 if(h[i]<0)
                 {
                     db++;
                     kezd[db]=i;
                     while(h[i]<0)
                     {
                         ++i;
                     }
                     veg[db]=i-1;
                 }
             }
             //Szakaszok kiíratása
             if(db==-1){
                cout<<"Nem volt fagyos szakasz.\n";
             }else{
                cout<<"Fagyos szakaszok darabszama: "<<db+1<<endl;
                cout<<"Kezd\tVeg\tHossz\n-------------------------------\n";
                for(int i=0; i<=db; ++i){
                    cout<<kezd[i]+1<<"\t"<<veg[i]+1<<"\t"<<veg[i]-kezd[i]<<endl;
                }
             }
                break;
            }
            case 6:
            {//Leghosszabb fagyos szakasz hossza, és kezdőpontja
             //Egy trükkös megoldás
             //A legegyszerubb megoldas (sziget hosszat egy rekurziv fuggveny szamolja, ennek keressuk a maximumat)
                int maxhossz=-1;
                int kezdet;
                int veg;
                for(int i=0;i<n;++i)
                {
                 if(h[i]<0)
                 {
                     int j=i;
                     while(h[i]<0)
                     {
                         ++i;
                     }
                     if(maxhossz<i-j)
                     {
                         maxhossz=i-j;
                         kezdet=j;
                         veg=i;
                     }
                 }
                }
                if(maxhossz==-1)
                {
                    cout<<"Nem volt negativ szakasz\n";
                }else{
                    cout<<"Leghosszabb negativ szakasz hossza: "<<maxhossz<<endl;
                    cout<<"Kezdete: "<<kezdet+1<<". nap\n";
                    cout<<"Vege: "<<veg<<".nap\n";
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


