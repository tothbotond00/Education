$szum=0
if (@($input).Count -ne 0)
{
$input.Reset()
foreach ($i in $input)
{
$szum+=$i
}
}
else
{
for ($i=0 ; $i -lt $args.Length; $i++)
{
$szum+=$args[$i]
}
}
Write-Host $szum