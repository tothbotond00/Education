Write-Output "x `t` sin(x) `t` cos(x)"
Write-Output "------------------------------------"
[double]$value = 0.1
for ([double]$i = 0.0; $i -lt 1.0; $i+=$value) 
{
    $sin = [math]::Round([Math]::Sin($i),5)
    $cos = [math]::Round([Math]::Cos($i),5)
    if ($cos -eq 1.0 )
    {
        Write-Output "$i `t` $sin `t`t $cos"
    }
    else
    {
        Write-Output "$i `t` $sin `t` $cos"
    }
}