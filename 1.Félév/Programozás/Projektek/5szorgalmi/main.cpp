#include <iostream>

using namespace std;

int main()
{
        int hossz;
    int javitasok;
    cin>>hossz;
    cin>>javitasok;
    int kezdet[javitasok];
    int veg [javitasok];
    for (int i=0;i<javitasok;++i)
    {
        cin>>kezdet[i];
        cin>>veg[i];
    }
    //1.Fealadat:
    int min1=veg[0]-kezdet[0];
    for (int i=1;i<javitasok;++i)
    {
        if(min1>veg[i]-kezdet[i])
        {
            min1=veg[i]-kezdet[i];
        }
    }
    cout<<min1<<endl;
    //2.Fealadat:
    int index=-1;
    for(int i=0;i<=hossz;++i)
    {
        int counter = 0;
        for (int j=0;j<javitasok;++j)
        {
            if(kezdet[j]<=i&&i<=veg[j])
            {
                ++counter;
                if(counter>=3) break;
            }
        }
        if(counter>=3)
        {
            index=i;
            break;
        }
    }
    cout<<index<<endl;
    //3.Fealadat:
    int km=0;
    for(int i=0;i<=hossz;++i)
    {
        bool van=true;
        for (int j=0;j<javitasok;++j)
        {
            if(kezdet[j]<=i&&i<veg[j])
            {
                van=false;
                break;
            }
        }
        if(van)
        {
            ++km;
        }
    }
    cout<<km-1<<endl;
    //4.Fealadat:
    cout<<endl;
    return 0;
}
