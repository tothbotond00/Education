Write-Output "Az 1.Feladat megoldása:" 
[string] $sor = (Select-String "^$args" -Path .\adatok.txt)
 if ($sor -eq $null )
 {
   Write-Host Hibás paramétert adott meg!
   Write-Host Lehetséges nevek:
   Write-Host
   foreach ($i in Get-Content .\adatok.txt)
   {
     if ($i.Split()[0] -eq "Nev/Tantargy") {}
     else
      {
        Write-Host ($i.Split()[0]+" "+$i.Split()[1])
      }
   }
 }
 else
 {
   $pos = $sor.IndexOf(" ")
   $seged = $sor.Substring($pos+1)
   $pos2 = $seged.IndexOf(" ")
   $megoldas = $seged.Substring($pos2+1)
   $osszeg = 0
   $darab=0
   for($i = 0;$i -lt ($megoldas.Length/2); $i++ )
    {
      [int]$osszeg+=$megoldas.Split()[$i]
      $darab++
    }
   $atlag = $osszeg/$darab
   Write-Host
   Write-Host $args átlaga : $atlag
 }