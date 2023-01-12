#include <iostream>

using namespace std;

int main()
{
    cerr <<"Idojaras"<<endl;
    //const int maxmeret =1000;
    //int napimin[maxmeret],napimax[maxmeret];
    int n;
    bool hiba;
    do{
    cerr<<"Hany adat lesz: "; cin>>n;
    hiba=n<=0;
    }while(hiba);
    int napimin[n],napimax[n];
    ///Beolvasás
    for(int i=0; i<n;++i)
    {
        cin>>napimin[i]>>napimax[i];
    }
    ///1.Feladat:
    cout<<"#\n";
    int db =0;
    for (int i=0;i <n;++i)
    {
        if(napimin[i]<=0)
        {
            ++db;
        }
    }
    cout<<db<<endl;
    ///2.Feladat:
    cout<<"#\n";
    int diff=0;
    int index=0;
    for(int i=0;i<n;++i)
    {
        if((napimax[i]-napimin[i])>diff)
        {
            diff=napimax[i]-napimin[i];
            index=i;
        }
    }
    cout<<index+1<<endl;
    ///3.Feladat:
    cout<<"#\n";
    int index2= 0;
    for(int i=1;i<n;++i)
    {
        if(napimax[i]<napimin[i-1])
        {
            index2=i;
            break;
        }
    }
    if(index2==0)
    {
        cout<<-1<<endl;
    }
    else
    {
        cout<<index2+1<<endl;
    }
    ///4.Feladat:
    cout<<"#\n";
    int darab=0;
    for(int i=0;i<n;++i)
    {
        if(napimax[i]>0&&napimin[i]<=0)
        {
            ++darab;
        }
    }
    cout<<darab<<" ";
        for(int i=0;i<n;++i)
    {
        if(napimax[i]>0&&napimin[i]<=0)
        {
            cout<<i+1<<" ";
        }
    }
    return 0;
}
