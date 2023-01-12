/* Fahrenheit -> Celsius conv */

#include <stdio.h>

#define LOWER -120
#define UPPER 140
#define STEP 10
//Hasznos pls Banki rendszereknél
#define FAHR2CELS(x) (5.0 / 9.0 + ((x) -32))

int main()
{
    //[-120, 140], 10-esevel.

    //Felesleges absztrakció: 
    //int lower = -120;
    //int upper = 140;
    //int step = 10;
    int i = LOWER;
    for ( ; i <= UPPER; i+= STEP)
    {
        printf("%d Fahrenheit\t%.2f Celsius\n",
                i, 
                FAHR2CELS(i) 
        );
        /*
        printf( "%d Fahrenheit\t%d Celsius\n",
            i,
            5 / 9 * ( i - 32 ) ); esetén mindig 0 celsius
        */
    }
    return 0;
}

//-E esetén beégeti a a makrókat a kódba