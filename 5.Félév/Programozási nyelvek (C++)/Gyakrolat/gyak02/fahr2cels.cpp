#include <iostream>

inline double fahr2cels(double x)
{
    return 5. / 9 * (x - 32);
}

int main()
{
    const int lower =  -120;
    const int upper =   140;
    const int step  =    10;
    //complier hiba -> int* p = &step;
    //complier hiba -> lower = 60;

    for (int i = lower; i <= upper ; i += step)
    {
        std::cout << i << " Fahrenheit\t"
                  << fahr2cels(i)
                  << " Celsius\n"
                  << std::endl;
    }
     
}