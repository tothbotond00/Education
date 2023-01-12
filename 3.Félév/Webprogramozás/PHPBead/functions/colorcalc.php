<?php

function calcColor($current, $other){
    if ($current > $other) return "winner";
    else if ($other > $current) return "loser";
    else return "draw";
}