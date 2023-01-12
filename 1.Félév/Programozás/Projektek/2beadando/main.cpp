#include <iostream>

using namespace std;

int main()
{
    int n;
    string keresendo;
    cin>>n;
    cin>>keresendo;
    string nevek [n];
    for (int i=0; i<n;++i)
    {
        cin>>nevek[i];
    }
    int db=0;
    int elofordul[5];
    for(int i=0;i<n;++i)
    {
        if(nevek[i].find(keresendo+",") != string::npos||nevek[i].find(","+keresendo) != string::npos||keresendo==nevek[i])
        {
            elofordul[db]=i;
            ++db;
        }
    }
    cout<<db;
    for(int i=0;i<db;++i)
    {
        cout<<" "<<elofordul[i]+1;
    }
    return 0;
}
