$szam1= Read-Host -Prompt "Kérem az első számot"
$szam2= Read-Host -Prompt "Kérem a második legnagyobb számot"
if ( $szam1 -lt 0) {  $szam1 *= -1 } 
Write-Host $szam1
if ( $szam2 -lt 0) {  $szam2 = $szam2*(-1) } 
Write-Host $szam2
if ( $szam1 -ge $szam2) { $kezdo =$szam2 }
else { $kezdo =$szam1 }
for ($i=$kezdo; $i -ge 1; $i=$i-1)
{
if ( ($szam1%$i -eq 0) -and ($szam2%$i -eq 0) )
  {
  Write-Host Legnagyobb közös osztójuk: $i
  break
  }
} 