do
{
  $ertek = Read-Host -Prompt "�rj be egy hexadecim�lis sz�mjegyet"
  if ($ertek -eq "v�ge")
       {
        break;
       }
  else
  {
  if ($ertek -match '^[0-9]+$' -or $ertek -match '^-[0-9]+$')
  {
    if ([int]$ertek -ge 0 -and [int]$ertek -le 9)
     {
       Write-Host $ertek 
     }
     else
     {
       Write-Host Nem hexadecim�lis sz�mot �rt�l be!
     }
  }
  else
  {
     if ($ertek -eq 'A') { Write-Host 10}
     elseif ($ertek -eq 'B') { Write-Host 11}
     elseif ($ertek -eq 'C') { Write-Host 12}
     elseif ($ertek -eq 'D') { Write-Host 13}
     elseif ($ertek -eq 'E') { Write-Host 14}
     elseif ($ertek -eq 'F') { Write-Host 15}
     else { Write-Host Nem hexadecim�lis sz�mot �rt�l be! }
  }
  }
} while ($ertek -ne "v�ge")
