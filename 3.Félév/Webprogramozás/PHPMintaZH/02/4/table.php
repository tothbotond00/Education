<?php
    session_start();
    if (isset($_GET['clear'])){
        session_destroy();
        echo json_encode([]);
    }
    if (!isset($_SESSION['data'])) $_SESSION['data'] = [];

    if (isset($_GET['neptun']) && isset($_GET['points']) && !isset($_GET['clear'])){
        if (!isset($_SESSION['data'][$_GET['neptun']])){
            $_SESSION['data'][$_GET['neptun']] = [floatval($_GET['points'])];
        } else {
            if (count($_SESSION['data'][$_GET['neptun']]) < 4) {
                $_SESSION['data'][$_GET['neptun']][] = floatval($_GET['points']);
            }
        }
        echo json_encode(table_fields());
    }

    function table_fields(){
        $output = [];
        foreach($_SESSION['data'] as $neptun => $pts){
            $output[] = [
                'neptun' => $neptun,
                '1'   => $pts[0] ?? '',
                '2'   => $pts[1] ?? '',
                '3'   => $pts[2] ?? '',
                '4'   => $pts[3] ?? '',
                'sum' => array_sum($pts)
            ];
        }
        return $output;
    }
?>