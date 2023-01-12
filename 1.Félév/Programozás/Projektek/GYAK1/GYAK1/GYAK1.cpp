// GYAK1.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>

int main()
{
    std::cout << "Téglalap területe, kerülete" << std::endl;
    float a = 0, b = 0, t = 0, k = 0;
    ///Beolvasás
    cout << "Kérem adja meg a téglalap oldalainak hosszát\n";
    cout << "a= "; cin >> a;
    cout << "b= "; cin >> b;
    ///Algoritmus
    k = 2 * a + 2 * b;
    t = a * b;
    cout << "Kerülete: " << k << endl;
    cout << "Területe: " << t << endl;
    return 0;
}

