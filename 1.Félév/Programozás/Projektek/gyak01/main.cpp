#include <iostream>

using namespace std;
//1.Feladat
/*
int main()
{
    std::cout << "Teglalap kerulete" << std::endl;
    float a=0,b=0,t=0,k=0;
    ///Beolvasás
    cout <<"Kerem adja meg a hosszat\n";
    cout <<"a= "; cin >>a;
    if(a<=0)
    {
        cout<<"CSak pozitiv lehet!\n";
        exit(1);
    }
    cout <<"b= "; cin >> b;
        if(b<=0)
    {
        cout<<"Csak pozitiv lehet!\n";
        exit(1);
    }
    ///Algoritmus
    k=2*a+2*b;
    t=a*b;
    cout <<"Kerulete: "<<k<<endl;
    cout <<"Terulete: "<<t<<endl;
    return 0;
}
*/
/*
///2.Feladat
int main()
{
    cout << "Akcios ar szamolasa" << endl;
    //Arleszallitas

    int ar,ujar,szazalek;

    cout<<"A termek ara:"; cin>>ar;
    if (ar<=0){
        cout<<"Csak pozitiv lehet!"<<endl;
        exit(1);
    }
    cout<<"Hany szazalekkal lett olcsobb:"; cin>>szazalek;
    if (szazalek<=0 || szazalek>=100){
        cout<<"Nullanal nagyobb, 100-nal kisebb egeszet adjon meg!"<<endl;
        exit(1);
    }
    //cout<<(100-szazalek)/100<<endl; //VIGYAZAT! Egész osztás, az eredmény nulla lesz!
    //ujar=ar*(100-szazalek)/100; //Ez jó, mert a szorzás, osztás balról jobbra hajtódik végre.
    //ujar=(100-szazalek)/100*ar; //Nullát ad az egész osztás miatt!
    //ujar=float(100-szazalek)/float(100)*ar; //átkonvertáljuk valósba, így már helyes
    ujar=(100.-szazalek)/100.*ar; //Ha a kifejezésben vegyesen való és egész van, valósban számol.
    cout<<"Az engedmenyes ar:"<<ujar<<endl;

    return 0;
}
*/
///3.Feladat
/*
int main()
{
    cout << "Oszthatosag vizsgalat" << endl;
    int oszto, osztando;
    string sz;
    cout<<"Osztando:"; cin>>osztando;
    cout<<"Oszto:"; cin>>oszto;
    if(oszto==0){
        cout<<"Nullaval nem lehet osztani!"<<endl;
        exit(1);
    }

    if(osztando%oszto==0)
        sz="osztoja";
    else
        sz="nem osztoja";

    cout<<oszto<<" "<<sz<<" "<<osztando<<"-nak/nek."<<endl;

    return 0;
}
*/
///4.Feladat
int main()
{

    //Testmagasságok összehasonlítása
    string nev_1,nev_2;
    int m_1,m_2;
    //string sz; //nem lesz rá szükség

    cout<<"Elso ember neve:"; cin>>nev_1;
    cout<<"Testmagassaga (cm):"; cin>>m_1;
    if(m_1<50 || m_1>250){
        cout<<"Nem realis!";
        exit(1);
    }
    cout<<"Masodik ember neve:"; cin>>nev_2;
    cout<<"Testmagassaga (cm):"; cin>>m_2;
    if(m_2<50 || m_2>250){
        cout<<"Nem realis!";
        exit(1);
    }

    //Szebb kiírás miatt sz változót csak a specifikációban használjuk.
    //Az elágazásokban egy-egy mondattal kiírjuk az eredményt.
    if(m_1==m_2){
        cout<<"Egyforma magasak."<<endl;
        //sz="egyformak";
    }else if(m_1>m_2){
            cout<<nev_1<<" magasabb, mint "<<nev_2<<"."<<endl;
            //sz=nev_1;
    }else{
            cout<<nev_2<<" magasabb, mint "<<nev_1<<"."<<endl;
            //sz=nev_2;
    }

    return 0;
}
