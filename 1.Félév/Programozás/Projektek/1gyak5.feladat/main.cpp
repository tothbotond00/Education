#include <iostream>

using namespace std;

int main()
{
    float a,b,c,d,x,y;
    string sz;
    cout<<"Elso egyenes egyenleteben az 'a': "; cin>>a;
    cout <<"Elso egyenes egyenleteben a 'b': "; cin>>b;
    cout<<"Masodik egyenes egyenleteben a 'c': "; cin>>c;
    cout <<"Masodik egyenes egyenleteben a 'b': "; cin>>d;
    if(a==c)
    {
        if(b==d)
        {
            sz = "azonosak";
            cout<<"Ezek az egyensek "<<sz;
        }
        else
        {
            sz="parhuzamosak";
            cout<<"Ezek az egyensek "<<sz;
        }

    }
    else
    {
      x=(d-b)/(a-c);
      y=a*x+b;
      cout<<"Az egyensek metszéspontja ("<<x<<";"<<y<<")";
    }
    return 0;
}
