if (@($input).Count -ne 0)
{
    $max = -999999
    $input.Reset()
    foreach ($i in $input)
    {
        if($i -gt $max)
        {
           $max=$i
        }
    }
    Write-Host A legnagyobb érték: $max
}
elseif( $args.Length -ne 0)
{
  [int]$max = $args[0]
  foreach($i in $args)
  {
     if ($i -gt $max)
     {
        $max=$i
     }
  }
  Write-Host A legnagyobb érték: $max
}