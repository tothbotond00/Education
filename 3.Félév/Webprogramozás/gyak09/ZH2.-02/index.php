<?php

function validate($input, &$data, &$errors){
    //nev
    if(!isset($input['tanulo'])){
        $errors['tanulo'] = "Név mező kötelező!";
    }else if (trim($input['tanulo']) === ''){
        $errors['tanulo'] = "Nincs név megadva!";
    }else{
        $name = trim($input['tanulo']);
        if(strstr($name," ") === false){
            $errors['tanulo'] = "Két név kell!";
        }else {
            $data['tanulo'] = $name;
        }
    }

    //szazlek
    if(!isset($input['szazalek'])){
        $errors['szazalek'] = "Százalék mező kötelező!";
    }else if (trim($input['szazalek']) === ''){
        $errors['szazalek'] = "Nincs szazalek megadva!";
    }else if (!is_numeric($input['szazalek'])){
        $errors['szazalek'] = "A százalék nem szám!";
    }else {
        $num = intval($input['szazalek']);
        if ($num < 0){
            $errors['szazalek'] = "Negatív százalék nem lehet!";
        }else {
            $data['szazalek'] = $num;
            //checkbox
            if (isset($input['szobeli']) && isset($input['szazalek']) && $data['szazalek'] >= 12 && $data['szazalek'] < 25 ){
                $data['szobeli'] = true;
            }else if (isset($input['szazalek']) && $data['szazalek'] >= 12 && $data['szazalek'] < 25 ) {
                $errors['szobeli'] = "Szóbeli mező kötelező!";
            }
        }
    }

}

$data = [];
$errors = [];
if(count($_GET) > 0){
    if(validate($_GET, $data, $errors)){
        //valid
        
    }else {
        //unvalid
    }
}

?>


<!DOCTYPE html>
<html lang="hu">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>PHP ZH - 2. feladat</title>
</head>
<body>
    <h1>ÉRETTSÉGI VIZSGA MATEMATIKÁBÓL</h1> 
    <h2>2. feladat: űrlapfeldolgozás</h2>
    <form action="" method="GET" novalidate>
        <label for="tanulo">Tanuló neve:</label>
        <input type="text" name="tanulo" id="tanulo" value="<?= $_GET['tanulo'] ?? '' ?>">
        <?php if(isset($errors['tanulo'])) : ?>
            <span style="color: red"> <?= $errors['tanulo'] ?> </span>
        <?php endif ?>
        <br>
        <label for="szazalek">Eredmény (%):</label>
        <input type="text" name="szazalek" id="szazalek" value="<?= $_GET['szazalek'] ?>">
        <?php if(isset($errors['szazalek'])) : ?>
            <span style="color: red"> <?= $errors['szazalek'] ?> </span>
        <?php endif ?>
        <br>
        <input type="checkbox" name="szobeli" id="szobeli" <?= isset($_GET['szobeli']) ? 'checked' : '' ?> >
        <label for="szobeli">Szóbeli időpont szükséges</label>
        <?php if(isset($errors['szobeli'])) : ?>
            <span style="color: red"> <?= $errors['szobeli'] ?> </span>
        <?php endif ?>
        <br>
        <button type="submit">Küldés</button>
    </form>
</body>
</html>