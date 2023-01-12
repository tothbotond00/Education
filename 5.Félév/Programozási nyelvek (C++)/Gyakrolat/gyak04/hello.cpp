#include <iostream>

using namespace std;

int main()
{
    cout << "Hello" << endl;

    cout << "Hel\0lo" << endl;

    cout << "Hello" + 3 << endl;
    
    cout << 1["Hello"] << endl;
    char str[] = "blalabal";
    str[2] = 'i';
    *(str + 2) = 'i';
    *(2 + str) = 'i';
    2[str] = 'i';
    cout << str << endl;
}