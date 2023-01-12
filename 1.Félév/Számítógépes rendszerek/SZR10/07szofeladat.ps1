foreach ($i in get-Content $args[0])
{
   Write-Host ($i.split()[1])
}