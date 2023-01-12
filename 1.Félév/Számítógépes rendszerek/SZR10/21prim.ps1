[int]$szam = Read-Host -Prompt "Írj be egy természetes számot: "
if ($szam -lt 0)
{
Write-Host Nem természetes számot írtál be!
}
elseif ($szam -eq 0)
{
Write-Host A szám nem prím
}
elseif($szam -eq 1)
{
Write-Host A szám sem nem prím sem nem összetett.
}
else
{
$osztok=0
for ($i=2; $i -lt $szam; $i++)
{
if($szam%$i -eq 0 )
{
$osztok++
}
}
if ($osztok -eq 0)
{
Write-Host A szám prím!
}
else
{
Write-Host A szám nem prím!
}
}