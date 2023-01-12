$ertek= 4
while ($ertek -ne 3)
{
if($ertek -eq 1 -or $ertek -eq 2)
{
Write-Host $ertek
Start-Sleep 2
Clear-Host
}
$ertek = Read-Host -Prompt "Adjon egy szamot: "
}