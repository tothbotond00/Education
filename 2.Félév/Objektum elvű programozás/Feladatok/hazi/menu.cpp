#include "menu.h"
#include "read.hpp"
#include <sstream>
#define MENUPONT 4

bool verify (int a)
{
    return (a>=0 && a<=MENUPONT);
}

void Menu::plus()
{
    std::cout<<"\nKomplex szamok osszeadasa: "<<std::endl;
    Complex c;
    Complex d;
    std::cout<<"\nElso elem: "<<std::endl;
    std::cin>>c;
    std::cout<<"\nMasodik elem: "<<std::endl;
    std::cin>>d;
    std::cout<<"Megoldas:"<<std::endl;
    std::cout<<(c+d);
}

void Menu::minus()
{
    std::cout<<"\nKomplex szamok kivonasa: "<<std::endl;
    Complex c;
    Complex d;
    std::cout<<"\nElso elem: "<<std::endl;
    std::cin>>c;
    std::cout<<"\nMasodik elem: "<<std::endl;
    std::cin>>d;
    std::cout<<"Megoldas:"<<std::endl;
    std::cout<<(c-d);
}

void Menu::multiplies()
{
    std::cout<<"\nKomplex szamok szorzasa: "<<std::endl;
    Complex c;
    Complex d;
    std::cout<<"\nElso elem: "<<std::endl;
    std::cin>>c;
    std::cout<<"\nMasodik elem: "<<std::endl;
    std::cin>>d;
    std::cout<<"Megoldas:"<<std::endl;
    std::cout<<(c*d);
}

void Menu::divides()
{
    std::cout<<"\nKomplex szamok osztasa: "<<std::endl;
    Complex c;
    Complex d;
    std::cout<<"\nElso elem: "<<std::endl;
    std::cin>>c;
    std::cout<<"\nMasodik elem: "<<std::endl;
    std::cin>>d;
    bool hiba = false;
    try {
        (c/d);
    }
    catch (Complex::ComplexError err){
        hiba = true;
        if (err==Complex::DIV_BY_ZERO){
            std::cout<<"Nullaval valo osztas sikertelen!"<<std::endl;
        }
        else {
            std::cout<<"Programhiba!"<<std::endl;
        }
    }
    if(!hiba){
            std::cout<<"Megoldas:"<<std::endl;
            std::cout<<(c/d);
}
}

int Menu::menuPoint()
{
    int a;
    std::cout <<"\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n";
    std::cout<<"0.Kilepes\n";
    std::cout<<"1.Osszeadas komplex szamokkal\n";
    std::cout<<"2.Kivonas komplex szamokkal\n";
    std::cout<<"3.Szorzas komplex szamokkal\n";
    std::cout<<"4.Osztas komplex szamokkal\n";
    std::cout <<"\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n";

    std::cout<<std::endl;
    std::cout<<"0 es "<<MENUPONT<<" kozotti szamot adj meg!"<<std::endl;

    a = read<int>("Menupont: ","Nem megfelelo menupont",verify);
    return a;
}

void Menu::run()
{
    int m;
    do{
        m = menuPoint();
        switch (m)
        {
        case 1:
            plus();
            break;
        case 2:
            minus();
            break;
        case 3:
            multiplies();
            break;
        case 4:
            divides();
            break;
        default:
            std::cout<<"\nViszontlatasra!"<<std::endl;
            break;
        }
    } while (m!=0);
}
