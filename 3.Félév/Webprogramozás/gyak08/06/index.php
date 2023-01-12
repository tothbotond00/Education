<?php
//$numbers = [2, 5, 4, 3];
//foreach($numbers as $n){
//    echo $n;
//}
//$rec = [
//    "elso" => "alma",
//    "masodik" => "körte",
//];
//foreach($rec as $key => $value){
//    echo $key . "-" . $value;
//}
//be
$categories = [
    [
        "id"   => 5,
        "name" => 'Akció',
    ],
    [
        "id"   => 4,
        "name" => 'Dráma',
    ],
    [
        "id"   => 8,
        "name" => 'Vígjáték',
    ]
]

//fel 

//ki

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
    
    <form action="">
    <select name="category">
        <?php foreach ($categories as $cat) : ?>
            <option value = "<?= $cat['id']?>">
            <?= $cat['name']?>
        </option>
        <?php endforeach ?>
    </select> 
    <button type="submit">Elküld</button>
    </form>
    
</body>
</html>