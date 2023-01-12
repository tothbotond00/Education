Write-Host "Másodfokú egyenlet megoldó program"
$a = Read-Host -Prompt "Írd be az ""a"" együtthatót"
$b = Read-Host -Prompt "Írd be az ""b"" együtthatót"
$c = Read-Host -Prompt "Írd be az ""c"" együtthatót"
Write-Host Az egyenlet: $a*x2+$b*x+$c=0 
$d = [Math]::pow($b,2)-4*$a*$c
if ($d -lt 0)
{
Write-Host A feladatnak nincs megoldása a valós számok halmazán!
}
elseif( $d -eq 0)
{
$megoldas = (-$b)/2*$a
Write-Host "A fealadtnak egy megoldása van: "$megoldas
}
else
{
$megoldas1 = ((-$b)+[Math]::Sqrt($d))/2*$a
$megoldas2 = ((-$b)-[Math]::Sqrt($d))/2*$a
Write-Host "A két megoldás: " $megoldas1 " és" $megoldas2
}

