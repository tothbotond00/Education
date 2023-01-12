if ($args[0] -lt 0)
{
Write-Host "Egy 0-nál nagyobb számot Adjon meg paraméternek!"
}
else
{
$szorzat=1
$ertek= $args[0]
for ($i=1; $i -lt [int]$ertek ;$i++)
{
$szorzat =$i*$szorzat
}
 Write-Host "A "$args[0]" faktoriálisa : $szorzat"
}