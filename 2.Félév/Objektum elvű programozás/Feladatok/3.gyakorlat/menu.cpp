#include "menu.h"
#include <sstream>
#include "read.hpp"
#define MENUDB 5;

bool check(int a) {return 0<= a && a <=MENUDB;}

void Menu::putIn()
{
    Item e;
    std::cout<<"Mit tegyunk a sorba?"<<std::endl;
    std::cin >>e;
    pq.add(e);
}

void Menu::removeMax()
{
    Item e;
    bool hiba = false;
    try {
        e = pq.remMax();
    }
    catch (PrQueue::PrQueueError err){
        hiba = true;
        if (err == PrQueue::EMPTY_PRQUEUE){
            std::cerr<<"Az elem kivitele nem sikerult mert a sor ures"<<std::endl;
        }
        else {
        std::cerr<<"programhiba"<<std::endl;
        }
     }
     if (!hiba){
        std::cout<<"A kivett elem: "<<e<<std::endl;
     }
}

void Menu::getMax()
{
    Item e;
    bool hiba = false;
    try {
        e = pq.getMax();
    }
    catch (PrQueue::PrQueueError err){
        hiba = true;
        if (err == PrQueue::EMPTY_PRQUEUE){
            std::cerr<<"Az elem kivalasztasa nem sikerult mert a sor ures"<<std::endl;
        }
        else {
        std::cerr<<"programhiba"<<std::endl;
        }
     }
     if (!hiba){
        std::cout<<"A max elem: "<<e<<std::endl;
     }
}

void Menu::checkEmpty()
{
    if(pq.isEmpty() ){
        std::cout <<"A sor ures"<<std::endl;
    }
    else {
        std::cout <<"A sor nem ures"<<std::endl;
    }
}

void Menu::write()
{
    std::cout<<pq;
}

int Menu::getMenuPoint()
{
    int v;
    std::cout <<"\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n";
    std::cout<<"0.Kilepes\n";
    std::cout<<"1.Elem hozzadasa a sorhoz\n";
    std::cout<<"2.Legnagyobb kivesz\n";
    std::cout<<"3.Legnagyobbat lekerdez\n";
    std::cout<<"4.Ures-e?\n";
    std::cout<<"5.Sor kiirasa\n";
    std::cout <<"\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n";

    std::ostringstream s;
    s<<"0 es "<<MENUDB<<" kozott egesz szamot kell megadni!";

    v = read <int> ("Valsztas: ",s.str(),check);
    return v;
}

void Menu::run()
{
    int v;
    do{
        v= getMenuPoint();
        switch (v)
        {
        case 1:
            putIn();
            write();
            break;
        case 2:
            removeMax();
            write();
            break;
        case 3:
            getMax();
            write();
            break;
        case 4:
            checkEmpty();
            write();
            break;
        case 5:
            write();
            break;
        default:
            std::cout <<"\n Viszlat\n";
            break;
        }
    }while(v!=0);
}
