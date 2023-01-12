#include <iostream>
#include "menu.h"

//#define NORMAL_MODE
#ifdef NORMAL_MODE

int main()
{
    std::cout<<"5.feladat megoldasa: "<<std::endl;
    std::cout<<std::endl;

    Menu menu;
    menu.start();
    return 0;
}

#else

#define CATCH_CONFIG_MAIN
#include "catch.hpp"
#include "nums_test.h"

///Fekete doboz teszteles

TEST_CASE ("halmaz hossza: 0","[size]")
{
    Nums_Test n;
    CHECK (0 == n.getVector().size() );
}

TEST_CASE ("halmaz hossza: 1","[size]")
{
    Nums_Test n;
    n.add(10);
    CHECK (1 == n.getVector().size());
}

TEST_CASE ("halmaz hossza: 5","[size]")
{
    Nums_Test n;
    n.add(3);n.add(10);n.add(2);n.add(4);n.add(5);
    CHECK(5 == n.getVector().size());
}

TEST_CASE ("maximum keresese 0 méretu halmazon","[getMax]")
{
    Nums_Test n;
    CHECK_THROWS(n.getMax());
}

TEST_CASE ("maximum keresese 1 meretu halmazon","[getMax]")
{
    Nums_Test n;
    n.add(10);
    CHECK (10 == n.getMax());
}

TEST_CASE ("maximum keresese 5 meretu halmazon","[getMax]")
{
    Nums_Test n;
    n.add(3);n.add(-3);n.add(2);n.add(4);n.add(5);
    CHECK(5 == n.getMax());
}

TEST_CASE ("elem ketszer berakasa","[add]")
{
    Nums_Test n;
    n.add(3);
    CHECK (1 == n.getVector().size());
    n.add(3);
    CHECK (1 == n.getVector().size());
}

TEST_CASE ("maximum valtozasa nagyobb elemnel","[add]")
{
    Nums_Test n;
    n.add(2);
    CHECK (2==n.getMax());
    n.add(3);
    CHECK (3==n.getMax());
}

TEST_CASE ("elso elem elvetele","[remove]")
{
    Nums_Test n;
    n.add(3);n.add(-3);n.add(2);n.add(4);n.add(5);
    n.remove(3);
    CHECK (3 != n.getVector()[0] );
}

TEST_CASE ("utolso elem elvetele","[remove]")
{
    Nums_Test n;
    n.add(3);n.add(-3);n.add(2);n.add(4);n.add(5);
    n.remove(5);
    CHECK (5 != n.getVector()[n.getVector().size()-1]);
}

TEST_CASE ("nem letezo elem elvetele","[remove]")
{
    Nums_Test n;
    n.add(3);n.add(-3);n.add(2);n.add(4);n.add(5);
    CHECK_THROWS(n.remove(0));
}

TEST_CASE ("maximum elem elvetele","[remove]")
{
    Nums_Test n;
    n.add(3);n.add(-3);n.add(2);n.add(4);n.add(5);
    CHECK(5 == n.getVector().size());
    CHECK(5 == n.getMax());
    n.remove(5);
    CHECK(5 != n.getMax());
}

TEST_CASE ("ures-e egy ures halmaz","[isEmpty]")
{
    Nums_Test n;
    n.setEmpty();
    CHECK (n.isEmpty() == true);
}

TEST_CASE ("ures-e egy nem ures halmaz","[isEmpty]")
{
    Nums_Test n;
    n.add(3);
    CHECK (n.isEmpty() == false);
}

TEST_CASE ("nem ures halmaz ures lesz-e es lesz-e maximuma","[setEmpty]")
{
    Nums_Test n;
    n.add(3);n.add(-3);n.add(2);n.add(4);n.add(5);
    n.setEmpty();
    CHECK (0 == n.getVector().size());
    CHECK_THROWS (n.getMax());
}

///Feher doboz teszteles (terheleses teszteles)

TEST_CASE ("ures vektor hossza maximuma","[0:size]")
{
    Nums_Test n;
    CHECK (0 == n.getVector().size() );
    CHECK_THROWS (n.getMax());
}

TEST_CASE ("46 elemu vektor hossza maximuma","[46:add]")
{
    Nums_Test n;
    n.add(0);n.add(1);n.add(2);n.add(3);n.add(4);n.add(5);n.add(6);n.add(7);n.add(8);n.add(9);n.add(10);n.add(11);n.add(12);n.add(13);n.add(14);
    n.add(15);n.add(-1);n.add(-2);n.add(-3);n.add(-4);n.add(-5);n.add(-6);n.add(-7);n.add(-8);n.add(-9);n.add(-10);n.add(-11);n.add(-12);n.add(-13);n.add(-14);
    n.add(-15);n.add(16);n.add(17);n.add(18);n.add(19);n.add(20);n.add(21);n.add(22);n.add(23);n.add(24);n.add(25);n.add(26);n.add(27);n.add(28);n.add(29);
    n.add(30);
    CHECK (46 == n.getVector().size() );
    CHECK (30==n.getMax());
}

TEST_CASE ("46 elemu vektor elemeinek egyesevel elvetele","[46:remove]")
{
    Nums_Test n;
    n.add(0);n.add(1);n.add(2);n.add(3);n.add(4);n.add(5);n.add(6);n.add(7);n.add(8);n.add(9);n.add(10);n.add(11);n.add(12);n.add(13);n.add(14);
    n.add(15);n.add(-1);n.add(-2);n.add(-3);n.add(-4);n.add(-5);n.add(-6);n.add(-7);n.add(-8);n.add(-9);n.add(-10);n.add(-11);n.add(-12);n.add(-13);n.add(-14);
    n.add(-15);n.add(16);n.add(17);n.add(18);n.add(19);n.add(20);n.add(21);n.add(22);n.add(23);n.add(24);n.add(25);n.add(26);n.add(27);n.add(28);n.add(29);
    n.add(30);
    n.remove(0);n.remove(1);n.remove(2);n.remove(3);n.remove(4);n.remove(5);n.remove(6);n.remove(7);n.remove(8);n.remove(9);n.remove(10);n.remove(11);n.remove(12);
    n.remove(13);n.remove(14);n.remove(15);n.remove(-1);n.remove(-2);n.remove(-3);n.remove(-4);n.remove(-5);n.remove(-6);n.remove(-7);n.remove(-8);n.remove(-9);
    n.remove(-10);n.remove(-11);n.remove(-12);n.remove(-13);n.remove(-14);n.remove(-15);n.remove(16);n.remove(17);n.remove(18);n.remove(19);n.remove(20);n.remove(21);
    n.remove(22);n.remove(23);n.remove(24);n.remove(25);n.remove(26);n.remove(27);n.remove(28);n.remove(29);n.remove(30);
    CHECK (n.isEmpty());
    CHECK_THROWS (n.getMax());
}

#endif
