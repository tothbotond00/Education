#include <iostream>

using namespace std;

int main()
{
    cerr <<"Legszennyezes"<<endl;
    //const int maxmeret =1000;
    //int napimin[maxmeret],napimax[maxmeret];
    int n;
    bool hiba;
    do{
    cin>>n;
    hiba=n<=0;
    }while(hiba);
    int reggeli[n],esti[n];
    for(int i=0;i<n;++i)
    {
        cin>>reggeli[i]>>esti[i];
    }
    ///1.Feladat
    int db=0;
    for (int i=1;i<n;++i)
    {
        if (reggeli[i]>reggeli[i-1]&&esti[i]>esti[i-1])
        {
            ++db;
        }
    }
    cout<<db;
    return 0;
}
