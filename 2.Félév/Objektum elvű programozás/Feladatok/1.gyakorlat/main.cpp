#include <iostream>
#include <vector>
#include <cmath>
#include <fstream>

using namespace std;

int hanyszor (int index,const vector <int>& v)
{
    int c = 0;
    for (unsigned int j=0; j<v.size();++j)
    {
        if (v[j]==v[index])
        {
            ++c;
        }
    }
    return c;
}

int main()
{
    cout<<"1.Feladat:"<<endl;
    ///1.Feladat
    //Adottak az x és y nem negatív egész számok.
    //Számítsuk ki a szorzatukat, úgy, hogy csak összedást használhatunk.
    unsigned int x, y;
    cout << "x: ";
    cin >> x;
    cout << "y: ";
    cin >> y;
    ///HEADERBE ÍGY KELL MAJD !!! Névegyezés veszélye!
    unsigned int z = 0;
    for (unsigned int i=1; i<=x;i++)
    {
        z = z + y;
    }
    cout<<"z = "<<z<<endl;

    cout<<"2.Feladat:"<<endl;
    ///2.Feladat
    //Adott n darab valós szám, adjuk meg a legnagyobb abszolút értékû számot.
    ifstream file("input2.txt");

    if (file.fail())
    {
        cerr <<"File error"<<endl;
        return 1;
    }

    vector <double> a ;
    double number;
    while(file>>number)
    {
        a.push_back(number);
    }
    //a.size();
    double ertek = abs ( a[0] );
    unsigned int index = 0;
    for (unsigned int i=1;i<a.size();++i)
    {
        if (abs(a[i])> ertek )
        {
            ertek = abs(a[i]);
            index = i;
        }
    }
    cout<<"maxindex = "<<index-1<<endl;
    cout<<"maxertek = "<<ertek<<endl;

    cout<<"3.Feladat:"<<endl;
    ///3.Feladat
    bool van = false;
    int ind = 0;
    ifstream file3 ("input3.txt");

    if(file3.fail())
    {
        cerr<<"File error"<<endl;
        return 1;
    }
    vector <int> h;
    int numero;
    while (file3>>numero)
    {
        h.push_back(numero);
    }

    int min = h[0];
    while (!(van) && ind < h.size() )
    {
        ++ind;
        van = (h[ind] < min );
    }
    if (!( van ) )
    {
        cout<<"Nincs ilyen elem"<<endl;
    }

    else
    {
        cout<<"Az elso ilyen elem indexe: "<<(ind+1)<<endl;
    }

    cout<<"4.Feladat:"<<endl;
    ///4.Feladat
    //Adott egy egészeket tartalmazó n méretû tömb.
    //Melyik érték fordul elõ benne legtöbbször?
    ifstream file2 ("input4.txt");

    if (file2.fail())
    {
        cerr<<"File error"<<endl;
        return 1;
    }
    vector <int> b;
    int nums;
    while (file2>>nums)
    {
        b.push_back(nums);
    }

    int max = hanyszor(0,b);
    unsigned int index2 = 0;
    for (unsigned int i=1; i< b.size();++i)
    {
        int db = hanyszor(i,b);
        if(db > max)
        {
            max = db;
            index2 = i;
        }
    }

    cout<<"maxindex = "<<index2+1<<endl;
    cout<<"maxertek = "<<b[index2]<<endl;
    cout<<"max frequency = "<<max<<endl;

    return 0;
}
