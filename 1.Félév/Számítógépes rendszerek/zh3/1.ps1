if ($args.Length -gt 2)
{
Write-Host Több mint 2 paramétert adott meg!
}
elseif ($args.Length -eq 0)
{
  $szam1=Read-Host -Prompt "Kérem az osztandó értékét"
  $szam2=Read-Host -Prompt "Kérem az osztó értékét"
  $megoldas = $szam1/$szam2
  Write-Host $szam1 / $szam2 = $megoldas
}
elseif ($args.Length -eq 1)
{
  $szam2=Read-Host -Prompt "Kérem az osztó értékét"
  $megoldas = $args[0]/$szam2
  Write-Host $args[0] / $szam2 = $megoldas
}
else
{
  $megoldas = $args[0]/$args[1]
  Write-Host $args[0] / $args[1] = $megoldas
}