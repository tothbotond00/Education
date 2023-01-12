<?php
    session_start();
    $silver = $_SESSION['silver'];
    $success = true;
    if ($silver + intval($_GET['change']) >= 0)
        $silver += intval($_GET['change']);
    else $success = false;
    $_SESSION['silver'] = $silver;
    $resp = [
        "time" => date("Y.m.d. H:i:s"),
        "balance" => floor($silver / 12) ."g ". $silver % 12 ."s",
        "success" => $success
    ];
    echo json_encode($resp);
?>