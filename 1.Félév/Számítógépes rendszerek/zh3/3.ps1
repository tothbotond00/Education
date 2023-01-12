param 
( 
 [parameter(mandatory=$true)] [int ] $N
)
Get-ChildItem C:\Windows\System32\ -Filter '*.exe' |
Sort-Object -Property LastWriteTime -Descending|
Format-Table Name , LastWriteTime |
Select -First ($N+2)