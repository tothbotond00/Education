<?php
    if (count($_GET) > 0){
        $name = $_GET['name'];
    }
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
    <?php if (isset($name)) : ?>
        <h1>Hello <?= $name ?> !</h1>
    <?php endif ?>
    <form action="" method="get">
        <input type="text" name="name" >
        <button type="submit">Elküld</button>
    </form>
</body>
</html>