#include <cstdlib>
#include <iostream>

void f(int i)
{
    int  j = 6;
    int* q = &j;
    int  k = 9;
    int* p = static_cast<int*>(std::malloc( i * sizeof (int)));
    int* p2 = new int[i];
    int* p3 = new int(i);
    std::cout << p  << std::endl;
    std::cout << p2 << std::endl;
    std::cout << p3 << std::endl;
    std::cout << q  << std::endl;
    std::cout << &k << std::endl;
    
    std::free(p);
    delete [] p2;
    delete    p3;
    //Ha free nem lenne akkor MEMORY LEAK
    //valgrind --leak-check=full
    
    //delete [] p2;
    //free(): double free detected in tcache 2

    //Ezek allokációk keverése elég fádalmas -> new int [i] => delete [] p 
}

int main()
{
    f( 5 );
}