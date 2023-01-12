#include <iostream>

using namespace std;

int main()
{
    int n;
    cin>>n;
    int kor [n],penz[n];
    for(int i=0;i<n;++i)
    {
        cin>>kor[i]>>penz[i];
    }
    ///1.feladat:
    int idos=0;
    int index=0;
    for(int i=0;i<n;++i)
    {
        if(idos<kor[i])
        {
            index=i;
            idos=kor[i];
        }
    }
    cout<<index+1<<endl;
    ///2.feladat:
    int db=0;
    for(int i=0;i <n;++i)
    {
        if(kor[i]>40&&penz[i]<200000)
        {
            ++db;
        }
    }
    cout<<db<<endl;
    ///3.feladat:
    cout<<1<<endl;
    /*int eletkor[n];
    int darabszam=0;
    for(int i=0;i<n;++i)
    {
        for(int j=0;j<n;++j)
        {
            if(kor[i]==eletkor[j])
            {
                break;
            }
            else if (kor[])
            {
            }
        }
    }
    cout<<darabszam*/
    ///4.feladat:
    int szam=0;
    for(int i=0;i<n;++i)
    {
        if(kor[i]<30)
        {
            ++szam;
        }
    }
    cout<<szam<<" ";
    for(int i=0;i<n;++i)
    {
        if(kor[i]<30)
        {
            cout<<i+1<<" ";
        }
    }
    return 0;
}
