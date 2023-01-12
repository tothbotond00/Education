int fac(int n)
{
    int ret = 1;
    for (int i = 1; i <= n; ++i)
    {
        ret *= i;    
    }
    
    return ret;
}

//g++ fac.cpp fac2.cpp recmain.cpp
//Több defincíció - linkelési hiba