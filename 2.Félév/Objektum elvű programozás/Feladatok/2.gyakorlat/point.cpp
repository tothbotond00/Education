#include <iostream>
#include <fstream>
#include <vector>
#include "point.h"
#include "circle.h"

using namespace std;

bool search(const Circle& circle, const vector <Point>& points, unsigned int& ind)
{
    unsigned int i = 0;
    bool l = false;
    while ( !l && i < points.size() ){
        l = circle.contains( points[i] );
        ind = i;
        i++;
    }
    return l;
}

int main()
{
    ifstream file ("input.txt");
    if (file.fail()){
        cerr << "File error!" << endl;
    }

    vector <Point> points;
    Point k;
    double c;
    file >> k >> c;
    Circle circle( k, c );

    while (file >> k){
        points.push_back(k);
    }
    unsigned int index;
    if ( search (circle, points, index ) ) {
        cout<<"("<<points[index].getX()<<","<<points[index].getY() <<")"<<endl;
    }
    else {
        cout<<"There wasn't a suitable point for this task"<<endl;
    }

    return 0;
}
