#include <iostream>
#include <vector>

using namespace std;

struct tagname{
    int x;
    int y;
};

int main()
{
    vector<tagname> tgs;
    while(tgs.size() < 5){
        tagname temp;
        cin >> temp.x >> temp.y;
        tgs.push_back(temp);
    }
    return 1;
}