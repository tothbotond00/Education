#include <iostream>
#include <cmath>

using namespace std;

int main()
{
    cout << "Masodfoku egyenlet megoldo program!\n";
    char valasz;
    do
    {
    cout << "ax+bx+c=0 az alak\n";
    float a;
    cout << "a= "; cin>>a;
    float b;
    cout << "b= "; cin>>b;
    float c;
    cout << "c= "; cin>>c;
    string sz;
    float x1;
    float x2;
    cout<<a<<"x^2+"<<b<<"x+"<<c<<"=0"<<endl;
    if(a==0)
    {
        if (b==0)
        {
           if(c==0)
           {
               sz="azonossag";
               cout<<sz<<"\n";
           }
           else
           {
               sz="ellentmondas";
               cout<<sz<<"\n";
           }
        }
        else
        {
            sz="lineralis";
            cout<<sz<<"\n";
            x1=-c/b;
            cout<<"x= "<<x1;
        }
    }
    else
    {
        float d=(b*b)-4*a*c;
        if(d<0)
        {
            sz="nincs valos megoldas";
            cout<<sz<<"\n";
        }
        else if(d==0)
        {
            sz="ketszeres multipilitasu gyok";
            cout<<sz<<"\n";
            x1=-b/(2*a);
            cout<<"x= "<<x1;
        }
        else
        {
            x1=-b+sqrt(d)/2*a;
            x2=-b-sqrt(d)/2*a;
            cout<<"x1= "<<x1<<endl;
            cout<<"x2= "<<x2<<endl;
        }
    }
    do
    {
       cout<<"Szeretnel meg szamolni?? (i ha igen n ha nem)";cin>>valasz;
    }while(valasz!='i'&& valasz!='n');
    cout<<endl;
    }while(valasz=='i');
    return 0;
}
