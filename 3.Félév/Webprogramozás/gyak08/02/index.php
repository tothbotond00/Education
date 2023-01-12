<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1><?php
    date_default_timezone_set('CET');
    $time = $_SERVER['REQUEST_TIME'];
    echo date("F j, Y, g:i a",$time); 
    ?></h1>
</body>
</html>