<?php

$errors = 
[
    "Not found",
    "404 Error",
    "File does not exist"
]

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <ul>
    <?php foreach ($errors as $er) : ?>
        <li> <?= $er ?> </li>
    <?php endforeach ?>
    </ul>
</body>
</html>