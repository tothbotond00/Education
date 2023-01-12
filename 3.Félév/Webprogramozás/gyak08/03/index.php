<?php
declare(strict_types=1);

function fakt (int $n) : int{
    $f = 1;
    for ($i = 2; $i <= $n; $i++) { 
      $f *= $i;   
    }
    return $f;
}

echo fakt(0) . "<br>\n" ;
echo fakt(1) . "<br>\n" ;
echo fakt(5) . "<br>\n" ;