#include <iostream>
#include <string.h>
using namespace std;

int main()
{
    cout<<"Jo napot!\n\nKerem irja be a szoveget amiben keresni szeretne:\n";
    string keresendo;
    string szoveg;
    getline(cin,szoveg);
    cout<<"Most irja be amit keresni szeretne a szovegben:\n";
    getline(cin,keresendo);
    int db=-1;
    int indexek[szoveg.length()];
    for(int i=0;i<szoveg.length();++i)
    {
            int j=0;
            while(szoveg[i+j]==keresendo[j])
            {
                ++j;
            }
            if(j>=keresendo.length())
            {
                ++db;
                indexek[db]=i;
            }
    }
    if(db==-1)
    {
        cout<<"Nincs ilyen minta a szovegben.";
    }
    else
    {
    cout<<"Van ilyen minta a szovegben\n";
    cout<<db+1<<" db minta talalahato a szovegben az alabbi helyeken:\n";
    for (int i=0;i<=db;++i)
    {
        cout<<indexek[i]+1<<"\t";
    }
    }
    return 0;
}
