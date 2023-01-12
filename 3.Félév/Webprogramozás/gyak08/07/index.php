<?php

$goods =
[
    [
        "id"   => 23,
        "name" => 'pendrive',
    ],
    [
        "id"   => 10,
        "name" => 'laptop',
    ],
    [
        "id"   => 44,
        "name" => 'camera',
    ]
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
    <?php foreach ($goods as $good) : ?>
        <input type="checkbox" id="<?= $good['id']?>" name="<?= $good['name']?>" value="<?= $good['name']?>">
        <label for="<?= $good['name']?>"><?= $good['name']?></label><br>
    <?php endforeach ?>
</body>
</html>