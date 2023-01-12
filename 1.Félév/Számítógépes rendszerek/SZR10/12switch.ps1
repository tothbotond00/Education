$szam =Read-Host -Prompt "Kérek egy számot: "
switch ($szam)
{
1
  {
    Get-ChildItem
  }
2
  {
    [int]$ertek =Read-Host -Prompt "Még egy számot szeretnék: "
    $ertek*=[int]10
    Write-Host ($ertek)
  }
3
  {
    Write-Host "Köszönöm!"
  }
default
  {
    Write-Host "Nagyobb mint 3"
  }
}