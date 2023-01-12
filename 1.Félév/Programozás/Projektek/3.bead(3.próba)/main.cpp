#include <iostream>

using namespace std;

int main()
{
    int percek [(24*60)]={0};
    //adatok beolvasása
    int db;
    cin>>db;
    bool be [db];
    string azon [db];
    int ora [db];
    int perc[db];
    int percben [db];
    bool ervenyes [db];
    for(int i=0;i<db;++i)
    {
        string beki;
        cin>>beki;
        if(beki=="BE") be[i]=true;
        else be[i]=false;
        cin>>azon[i]>>ora[i]>>perc[i];
        percben[i]=ora[i]*60+perc[i];
        ervenyes[i]=true;
    }
    //érvényesség
    for (int i=0;i<db;++i)
    {
        bool alap =be[i];
        if (!ervenyes) continue;
        for(int j=i+1;j<db;++j)
        {
            if(azon[j]==azon[i]&&be[j]==alap)
            {
                ervenyes[j]=false;
            }
            else if (azon[j]==azon[i]&&be[j]!=alap)
            {
                alap=(!alap);
            }
        }
    }
    //fõ algoritmus
    for (int i=0;i<db;++i)
    {
        if (ervenyes[i]==false)
        {
            continue;
        }
        else if (be[i])
        {
            int j=i+1;
            while (j<db)
            {
                if(azon[i]==azon[j]&&ervenyes[j]==true&&be[j]==false)
                {
                    break;
                }
                ++j;
            }
            if (j<db)
            {
                ervenyes[j]=false;
                for (int k=percben[i];k<=percben[j];++k)
                {
                    ++percek[k];
                }
            }
            else if (j==db)
            {
                for (int k=percben[i];k<=(24*60);++k)
                {
                    ++percek[k];
                }
            }
        }
        else
        {
            for (int k=0;k<=percben[i];++k)
            {
                ++percek[k];
            }
        }
    }
    int max=0;
    for (int i=0;i<24*60;++i)
    {
        if (max<percek[i])
        {
            max=percek[i];
        }
    }
    int kezdeto[db];
    int kezdetp[db];
    int vego [db];
    int vegp[db];
    int index=-1;
    for (int i=0;i<24*60;++i)
    {
        if(max==percek[i])
        {
            ++index;
            kezdeto[index]=i/60;
            kezdetp[index]=i%60;
            while (percek[i]==max)
            {
                ++i;
            }
            vego[index]=i/60;
            vegp[index]=i%60-1;
        }
    }
    //megoldás kiírása
    cout<<index+1;
    for(int i=0;i<=index;++i)
    {
        cout<<" "<<kezdeto[i]<<" "<<kezdetp[i]<<" "<<vego[i]<<" "<<vegp[i];
    }
    cout<<" "<<endl;
    return 0;
}

