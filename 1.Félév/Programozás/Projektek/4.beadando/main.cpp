#include <iostream>

using namespace std;

int main()
{
    //Beolvas�s
    int tdb;   //i
    int idb;  //j
    int korlat;
    cin>>tdb;
    cin>>idb;
    cin>>korlat;
    int adatok [tdb][idb];
    int telepules[tdb]={0};
    for (int i=0;i<tdb;++i)
    {
        for (int j=0;j<idb;++j)
        {
            cin>>adatok[i][j];
        }
    }
    //Naponk�nt a H�m�rs�klet maximuma
    for (int j=0;j<idb;++j)
    {
        int max=0;
        int index=-1;
        for (int i=0;i<tdb;++i)
        {
            if(adatok[i][j]>max)
            {
                max=adatok[i][j];
                index=i;
            }
            else if (adatok[i][j]==max)
            {
                index=-1;
            }
        }
        if (index==-1)
        {
            continue;
        }
        else
        {
            ++telepules[index];
        }
    }

    //Maximum keres�s a telep�l�sekn�l
    int max=0;
    int index=-2;
    for (int i=0;i<tdb;++i)
    {
        if(telepules[i]>max)
        {
            max=telepules[i];
            index=i;
        }
    }
    cout<<index+1<<endl;
    return 0;
}
