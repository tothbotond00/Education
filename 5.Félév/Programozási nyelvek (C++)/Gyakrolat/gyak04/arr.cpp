#include <iostream>

using namespace std;

void printArr(int a[], int n)
{
    //sizeof(a) / sizeof(int) Mi van ha változik a típus???
    for (int i = 0; i < n; i++)
    {
        cout << a[i] << ' ';
    }
    cout << endl;
}

void inc (int a[], int n)
{
    //sizeof(a) / sizeof(a[0]) függvényhívásnál a tömnb pointerré castolódik
    for (int i = 0; i < n; i++)
    {
        a[i] += 1;
    }
}

int main()
{
    //! ARRAY
    int a[] ={1,2,3,4,5,6,7,8,9};

    //! ARRAY IN FUNCTIONS
    printArr(a, sizeof(a) / sizeof(a[0]));
    inc(a,sizeof(a) / sizeof(a[0]));
    printArr(a,sizeof(a) / sizeof(a[0]));
    //Stack smashing esetleg túl hosszú tömb méret megadása esetén (ha a tömbméret ténylegesen kevesebb)

    //! ARRAY AND POINTER
    int *p = a + 5;
    cout <<  a << endl;
    cout <<  p << endl;
    cout << *p << endl;

    ++p;
    cout << *p << endl;

    p -= 2;
    cout << *p << endl;

    int *q = a;
    if(q < p)
    {
        cout << "Diff: " << p - q <<endl;
    }
}