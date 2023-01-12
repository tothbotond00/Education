foreach ($i in get-Content $args[0])
{
   Write-Host ($i[1]+$i[2]+$i[3])
}