<?php
include('storage.php');

$studentsStorage = new Storage(new JsonIO('vizsgazok.json'));

if (isset($_GET['neptun'])){
    if( $studentsStorage->findById($_GET['neptun']) == 0 ){
        $studentsStorage->update($_GET['neptun'], 1);
    }
}

$students = $studentsStorage->findAll();

$all = count($students);
$counter = 0;
foreach ($students as $value){
    if ($value == 1){
        $counter += 1;
    }
}

file_put_contents('statisztika.txt', $counter / $all );

?>


<!DOCTYPE html>
<html lang="hu">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>PHP ZH - 3. feladat</title>
</head>
<body>
    <h1>ÉRETTSÉGI VIZSGA MATEMATIKÁBÓL</h1>
    <h2>3. feladat: adattárolás</h2>
    <h3>Könyvelt törzslapok</h3>
    <ul>
    <?php foreach ($students as $student => $value ) : ?>
        <?php if ($value == 1) : ?>
            <li><?= $student ?></li>
        <?php endif ?>
    <?php endforeach ?>  
    </ul>

    <h3>Hiányos törzslapok</h3>
    <?php foreach ($students as $student => $value ) : ?>
        <?php if ($value == 0) : ?>
            <li><a href="?neptun=<?= $student ?>"> <?= $student ?> </a></li>
        <?php endif ?>
    <?php endforeach ?>
    

    
</body>
</html>