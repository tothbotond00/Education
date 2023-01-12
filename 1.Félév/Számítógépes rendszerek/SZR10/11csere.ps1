$szam1=Read-Host -Prompt "Kérek egy számot "
$szam2=Read-Host -Prompt "Kérek egy másik számot "
if ($szam1 -gt $szam2)
{
   $tmp =$szam1
   $szam1 =$szam2
   $szam2=$tmp
}
foreach ($i in Get-Content $args[0])
{
 Write-Host $i.Substring($szam1-1,($szam2-$szam1+1))
}