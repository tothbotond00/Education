#include <iostream>

using namespace std;

int main()
{
    ///Beolvasás
    int a;
    int v;
    cin>>a;
    cin>>v;
    int indulasok [a] [v];
    int erkezesek [a] [v];
    int in=0;
    int paros=0;
    int paratlan=0;
    while(in < a)
    {
        bool paritas =true;
        while (paratlan<v)
        {
            if (paritas)
            {
                cin>>indulasok[in][paros];
                ++paros;
                paritas = false;
            }
            else
            {
                cin>>erkezesek[in] [paratlan];
                ++paratlan;
                paritas = true;
            }
        }
        paros=0;
        paratlan=0;
        ++in;
    }

    ///1.Feladat
    cout<<"#"<<endl;
    int db=0;
    for (int i=0;i<v;++i)
    {
        if(indulasok[0][i]==-1 &&erkezesek[0][i]!=-1 &&erkezesek[a-1][i] ==-1&&indulasok[a-1][i] != -1) ++db;
    }
    cout<<db<<endl;

    ///2.Feladat
    cout<<"#"<<endl;
    int min = 1439;
    int minindex=0;
    for (int i=0;i<a;++i)
    {
        for (int j=0;j<v;++j)
        {
            if (indulasok[i][j]!=-1 && indulasok[i][j]!=erkezesek[i][j] &&erkezesek[i][j] !=-1)
            {
                if (erkezesek[i][j]-indulasok[i][j]<min)
                {
                    min = erkezesek[i][j]-indulasok[i][j];
                    minindex = i+1;
                }
            }
        }
    }
    cout<<minindex<<endl;

    ///3.Feladat
    cout<<"#"<<endl;
    int db3 =-1;
    int vonatok [v];
    int idok [v];
    for (int j=0;j<v;++j)
    {
        if (indulasok[0][j]==-1 && indulasok[a-1][j]==-1  && erkezesek[0][j]==-1 &&erkezesek[a-1][j]==-1)
        {
            ++db3;
            vonatok[db3]=j+1;
            for (int i=0;i<a;++i)
            {
                if (indulasok[i][j]==-1 && erkezesek[i] [j] !=-1)
                {
                    int kezd = erkezesek[i] [j];
                    while(erkezesek[i][j] != -1)
                    {
                        ++i;
                    }
                    int veg =indulasok[i][j];
                    idok[db3] = veg-kezd;
                    break;
                }
                else if (indulasok[i][j]!=-1 && erkezesek[i] [j]==-1)
                {
                    int kezd = indulasok[i] [j];
                    while(indulasok[i][j] != -1)
                    {
                        ++i;
                    }
                    int veg =erkezesek[i][j];
                    idok[db3] = kezd-veg;
                    break;
                }
            }
        }
    }
    cout<<(db3+1);
    for (int i=0; i<=db3;++i)
    {
        cout<<" "<<vonatok[i]<<" "<<idok[i];
    }
    cout<<endl;

    ///4.Feladat
    cout<<"#"<<endl;
    int index = 0;
    int max = 0;
    for (int j=0;j<v;++j)
    {
        int darab = 0;
        for (int i=0;i<a;++i)
        {
            if (indulasok[i][j] !=-1 && erkezesek[i][j] !=-1 && indulasok[i][j]!=erkezesek[i][j])
            {
                ++darab;
            }
        }
        if (darab>max)
        {
            max = darab;
            index = j+1;
        }
    }
    cout<<index;
    return 0;
}
