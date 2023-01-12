#include <iostream>
#include <fstream>
#include <vector>
#include "point.h"
#include "circle.h"

using namespace std;

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

    double min;
    unsigned int ind;
    bool l = false;

    for (unsigned int i = 0; i<points.size(); i++) {
            bool felt = !circle.contains(points[i]);
            if (!felt){
                //SKIP
            }
            else if (l && felt){
                double d = points[i].distance(circle.getc());
                if ( d < min ){
                    min = d;
                    ind = i;
                }
            }
            else if (!l && felt ){
                l = true;
                min = points[i].distance(circle.getc());
                ind = i;
            }
    }

    if (!l){
        cout <<"There wasn't a suitable point for this task"<<endl;
    }
    else {
        cout <<"("<<points[ind].getX()<<","<<points[ind].getY() <<")"<<endl;
    }

    return 0;
}
