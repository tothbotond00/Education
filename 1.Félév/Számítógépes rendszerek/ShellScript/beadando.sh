#!/bin/sh
echo
echo Az 1.Feladat megoldása:
echo 
szoveg=`grep -w "^$1 $2*" ./adatok.txt | tr -d "$1$2"`
if [ -z "$szoveg" ]
then
echo Hibás paramétert adott meg!
echo Lehtséges nevek:
tail -n+2 ./adatok.txt | cut -f1-2 -d ' '
else
osszeg=0
for i in `echo $szoveg | sed 's/.$//'`
do
osszeg=`expr $osszeg + $i`
done
darab=`grep -w "^$1 $2*" ./adatok.txt | tr -d "$1$2" | wc -w`
echo $1 $2-nak/-nek az átlaga: `expr $osszeg / $darab`
fi