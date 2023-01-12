int fac(int n)
{
    if(n <= 1)
    {
        return 1;
    }
    return n * fac(n - 1);
}

//g++ fac.cpp recamin.cpp
//vagy gcc fac.o recmain.o