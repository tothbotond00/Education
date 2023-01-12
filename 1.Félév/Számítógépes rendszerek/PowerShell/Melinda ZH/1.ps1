if ($null -ne ($args[0] -as [int]))
{
    if ([int]$args[0] -gt 0)
    {
        if([int]$args[0] -lt 31)
        {
            [int]$szam = $args[0]
            for ($i = 1; $i -le $szam; $i++)
            {
                Write-Output $("*" * $i)
            }
            for ($i = $szam-1; $i -ge 1; $i--)
            {
                Write-Output $("*" * $i)
            }
        }
        else 
        {
            Write-Output "Tul nagy a szam!"  
        }
    }
    else 
    {
        Write-Output "Nem eleg nagy a szam!"  
    }
}