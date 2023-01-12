#include <iostream>

using namespace std;

int main()
{
    //1.Feladat:
    int n;
    cin >>n;
    int orok [n];
    int orok2 [n];
    int falszakasz [n-1]={0};
    for (int i=0;i<n;++i)
    {
        cin>>orok[i];
        orok2[i]=orok[i];
    }
    int db=0;
    for (int i=0;i<n-1;++i)
    {
        if(orok[i]!=0||orok[i+1]!=0)
        {
            ++db;
        }
    }
    cout<<db<<endl;
    //2.Feladat:
    int db2=0;
    for(int i=0;i<n-1;++i)
    {
        if(orok2[i]!=0||orok2[i+1]!=0)
        {
            falszakasz[i]=1;
        }
        if(falszakasz[i]==0)
        {
            orok2[i+1]=1;
            ++db2;
        }
    }
    cout<<db2<<endl;
    //3.Feladat:
    int index=0;
    for(int i=0;i<n-1;++i)
    {
        if(orok[i]!=0 || orok[i+1]!=0)
        {
        }
        else
        {
            index=i+1;
            break;
        }
    }
    cout<<index<<endl;
    //4.Feladat:
    cout<<index<<endl;
    return 0;
}
