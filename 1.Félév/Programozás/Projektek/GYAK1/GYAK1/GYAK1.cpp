// GYAK1.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>

int main()
{
    std::cout << "T�glalap ter�lete, ker�lete" << std::endl;
    float a = 0, b = 0, t = 0, k = 0;
    ///Beolvas�s
    cout << "K�rem adja meg a t�glalap oldalainak hossz�t\n";
    cout << "a= "; cin >> a;
    cout << "b= "; cin >> b;
    ///Algoritmus
    k = 2 * a + 2 * b;
    t = a * b;
    cout << "Ker�lete: " << k << endl;
    cout << "Ter�lete: " << t << endl;
    return 0;
}

