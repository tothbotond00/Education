#!/bin/sh
if [ $# -gt 2 ]
then
echo Több mint 2 paramétert adott meg!
elif [ $# -eq 1 ]
then
echo -n "Kérem az osztó értékét: "
read oszto
echo $1/$oszto=`expr $1 / $oszto`
else
echo -n  "Kérem az osztandó értékét: "
read osztando
echo -n  "Kérem az osztó értékét: "
read oszto
echo $osztando/$oszto=`expr $osztando / $oszto`
fi
