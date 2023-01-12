<?php

    $students =
    [
        [
            "name" => 'Tóth Botond',
            "neptun" => 'MQH41V',
            "year" => '2000.11.14',
            "gender" => false
        ],
        [
            "name" => 'Gipsz Jakab',
            "neptun" => 'ABCD123',
            "year" => '2000.12.31',
            "gender" => false
        ],
        [
            "name" => 'Gipsz Jakab',
            "neptun" => 'ABCD123',
            "year" => '2000.12.31',
            "gender" => false
        ],
        [
            "name" => 'Gipsz Jakab',
            "neptun" => 'ABCD123',
            "year" => '2000.12.31',
            "gender" => false
        ],
        [
            "name" => 'Gipsz Jakab',
            "neptun" => 'ABCD123',
            "year" => '2000.12.31',
            "gender" => false
        ],
        [
            "name" => 'Nagy Anna',
            "neptun" => 'FFESD12',
            "year" => '2002.12.10.',
            "gender" => true
        ],
        [
            "name" => 'Nagy Anna',
            "neptun" => 'FFESD12',
            "year" => '2002.12.10.',
            "gender" => true
        ],
        [
            "name" => 'Nagy Anna',
            "neptun" => 'FFESD12',
            "year" => '2002.12.10.',
            "gender" => true
        ],
        [
            "name" => 'Nagy Anna',
            "neptun" => 'FFESD12',
            "year" => '2002.12.10.',
            "gender" => true
        ]
    ];

    $max = $students[0]['year'];
    $oldest = $students[0]['name'];
    foreach($students as $student) {
        if ($max > $student['year']){
            $max = $student['year'];
            $oldest = $student['name'];
        }
    }

    $isgirl = false;
    foreach($students as $student) {
        if ($student['gender']){
            $isgirl = true;
        }
    }

    $girls = 0;
    $boys = 0;
    foreach($students as $student) {
        if ($student['gender']){
            $girls += 1;
        }else {
            $boys += 1;
        }
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

    <!-- a feladat -->
    <table>
    <tr>
        <th>name </th>
        <th>neptun</th>
        <th>year</th>
        <th>gender</th>
    </tr>
        <?php foreach ($students as $student) : ?>
            <tr>
                <td><?= $student["name"] ?></td>
                <td><?= $student["neptun"] ?></td>
                <td><?= $student["year"] ?></td>
                <td><?= $student["gender"] ? "female" : "male" ?></td>
            </tr>
        <?php endforeach ?>
    </table>

    <!-- b feladat -->
    <h1><?php echo $oldest ?></h1><br>

    <!-- d feladat -->
    <h1><?php echo $isgirl ? 'Van lány' : 'Nincs lány' ?></h1>

    <!-- e feladat -->
    <h2>Fiúk : <?php for ($i=0; $i < $boys; $i++) { 
        echo '-';
    } ?></h2>
    <h2>Lányok : <?php for ($i=0; $i < $girls; $i++) { 
        echo '-';
    } ?> </h2>

</body>
</html>