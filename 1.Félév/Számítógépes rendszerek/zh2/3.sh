#!/bin/sh
echo Hány felhasználót listázzak?
read bekert
while [ $bekert -ne 0 ]
do
getent passwd |cut -f5 -d':'| sort -bu | head -n$bekert
echo Hány felhasználót listázzak?
read bekert
done
