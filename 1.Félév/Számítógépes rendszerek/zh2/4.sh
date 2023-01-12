#!/bin/sh
if [ $# -ne 1 ]
then
echo Nem hexadecimális számot adott meg!
elif [ $1 = A ]
then 
echo 10
elif [ $1 = B ]
then 
echo 11
elif [ $1 = C ]
then 
echo 12
elif [ $1 = D ]
then 
echo 13
elif [ $1 = E ]
then 
echo 14
elif [ $1 = F ]
then 
echo 15
elif [ $1 -ge 0 -a $1 -lt 10 ]
then
echo $1
else 
echo Nem hexadecimális számot adott meg!
fi