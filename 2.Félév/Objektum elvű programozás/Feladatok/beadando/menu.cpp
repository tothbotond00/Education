#include "menu.h"
#include "read.hpp"
#define MENUDB 5;

///inline check-ek

inline bool check (int a) {return 0<= a && a <=MENUDB;}
inline bool valid (int a) {return true;}

///Menupontok

void Menu::add()
{
    int x = read<int>("Kerem adja meg azt a szamot amit hozza szeretne adni a halmazhoz: ","Nem egesz szamot adott meg!",valid);
    n.add(x);
}

void Menu::remove()
{
    int x = read<int>("Kerem adja meg azt a szamot amit el szeretne venni a halmazbol: ","Nem egesz szamot adott meg!",valid);
    try {
        n.remove(x);
    }
    catch (Nums::NumsErrors err){
        if (err == Nums::NOT_EXISTENT){
            std::cout<<std::endl;
            std::cerr<<"Az elem nem letezik!\n";
        }
        else {
            std::cout<<std::endl;
            std::cerr<<"Programhiba!\n";
        }
    }
}

void Menu::setEmpty()
{
    n.setEmpty();
}

void Menu::getMax()
{
    int e;
    bool error = false;
    try{
        e = n.getMax();
    }
    catch (Nums::NumsErrors err){
        error = true;
        if (err == Nums::EMPTY_VEC){
            std::cout<<std::endl;
            std::cerr<<"A maximum kivalasztasa nem ertelmezett ures halmazon!\n";
        }
        else{
            std::cout<<std::endl;
            std::cerr<<"Programhiba!\n";
        }
    }
    if (!error){
        std::cout<<"\nA maximum elem: "<<e<<std::endl;
    }
}

void Menu::write()
{
    std::cout<<n;
}

///Menu futtatasa

int Menu::getMenuPoint()
{
    int m;
    std::cout <<"\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n";
    std::cout<<"0.Kilepes\n";
    std::cout<<"1.Halmaz elem hozzadasa\n";
    std::cout<<"2.Halmaz elem elvetele\n";
    std::cout<<"3.Halmaz uresse tetele\n";
    std::cout<<"4.Maximum lekerese\n";
    std::cout<<"5.Halmaz kiirasa\n";
    std::cout <<"\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n";

    m = read <int> ("Valsztas: ","Nem megfelelo menupont!",check);
    return m;
}

void Menu::start()
{
    n.setEmpty();
    int m;
    do{
        m = getMenuPoint();
        switch (m)
        {
        case 1:
            add();
            break;
        case 2:
            remove();
            break;
        case 3:
            setEmpty();
            break;
        case 4:
            getMax();
            break;
        case 5:
            write();
            break;
        default:
            std::cout<<"\nViszlat!\n";
            break;
        }
    }while (m != 0);
}
