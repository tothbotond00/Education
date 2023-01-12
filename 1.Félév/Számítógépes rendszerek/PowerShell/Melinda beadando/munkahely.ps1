#Készítsünk shell szkriptet munkahely.sh néven, amely egy argumentumban megadott szöveges adatfájlt dolgoz fel és választ ad a megadott kérdésekre!
#A szöveges fájlban az munkahelyi erőszakkal kapcsolatban tárolunk adatokat. Soronként az munkahely neve, címe, az egy évben előforduló erőszakos cselekmények száma, biztonsági őrök száma - vesszővel elválasztva.
#pl. Fűzfa fütyülő kft, 1111 Budapest Ökör utca 6, 5, 3

#a) Adjuk meg azokat az munkahelyeket, ahol egyetlen erőszakos cselekedetet se jelentettek, ha nincs ilyen, írjuk ki, hogy "NINCS"
Write-Output "----------------------------------------"
Write-Output "A) feladat"

$a=0
foreach ($i in get-Content $args[0])
{
    if([int]$i.split(',')[2] -eq 0)
    {
        Write-Output $i.split(',')[0]
        $a=$a+1
    }
}
if($a -eq 0)
{
    Write-Output "NINCS"
}

Write-Output "----------------------------------------"
Write-Output ""

#b) Számoljuk össze, hány biztonsági őr van összesen a megadott adatfájl szerint!
Write-Output "----------------------------------------"
Write-Output "B) feladat"

$b=0
foreach ($i in get-Content $args[0])
{
    $b=$b+[int]$i.split(',')[3]
}
Write-Output $b

Write-Output "----------------------------------------"
Write-Output ""

#c) Hol található a legtöbb erőszakot jelentő munkahely, adjuk meg a nevét/nevüket és címét/címüket!
Write-Output "----------------------------------------"
Write-Output "C) feladat"

$c=-1
foreach ($i in get-Content $args[0])
{
    if([int]$i.split(',')[2] -gt $c)
    {
        $c=[int]$i.split(',')[2]
    }
}
foreach ($i in get-Content $args[0])
{
    if([int]$i.split(',')[2] -eq $c)
    {
        Write-Output "$($i.split(',')[0]),$($i.split(',')[1])"
    }
}

Write-Output "----------------------------------------"
Write-Output ""