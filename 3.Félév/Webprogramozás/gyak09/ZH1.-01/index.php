<?php
    $students = [
        ['name' => 'Student1', 'age' => 20],
        ['name' => 'Student2', 'age' => 10],
        ['name' => 'Student3', 'age' => 30],
        ['name' => 'Student4', 'age' => 20],
        ['name' => 'Student5', 'age' => 10],
        ];

    $sort = null;
    if (!isset($_GET['age'] )){
    }else if (!is_numeric($_GET['age'])){
    }else{
        $sort = intval($_GET['age']);
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
    <?php if ($sort === null) : ?>
    <ul>
        <?php foreach ($students as $student) : ?>
            <li> <?= $student['name'] ?>(<?= $student['age'] ?>)  </li>
        <?php endforeach ?>
    </ul>
    <?php else : ?>
        <?php foreach ($students as $student) : ?>
            <?php if ($student['age'] === $sort) : ?>
            <li> <?= $student['name'] ?>(<?= $student['age'] ?>)  </li>
            <?php endif ?>
        <?php endforeach ?>
    <?php endif ?>

</body>
</html>