<?php 

function topFive($matches){
    foreach ($matches as $match){
       $match['time'] =  strtotime($match['date']);
    }
    $date = array();
    foreach ($matches as $key => $row)
    {
        $date[$key] = $row['date'];
    }
    array_multisort($date, SORT_DESC, $matches);
    $matches5 = array_slice($matches, 0, 5);
    return $matches5;
}