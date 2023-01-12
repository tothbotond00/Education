<?php
// phpinfo();
//var_dump();

function validate($input, &$data, &$errors){

    //name 
    if(!isset($input['name'])){
        $errors['name'] ="Nincs név";
    }else if (trim($input['name']) === ''){
        $errors['name'] = "Név kötelező";
    }else {
        $name = trim($input['name']);
        if(strstr($name," ") === false){
            $errors['name'] = "Két név kell!";
        }else {
            $data['name'] = $name;
        }
    }

    //taj
    $re = '/^[0-9]{3} [0-9]{3} [0-9]{3}$/';
    if(!isset($input['taj'])){
        $errors['taj'] ="Nincs TAJ";
    }else if (trim($input['taj']) === ''){
        $errors['taj'] = "TAJ kötelező";
    }else if(preg_match($re, $input['taj']) === 1) {
        $data['taj'] = $input['taj'];
    }else {
        $errors['taj'] = "TAJ rossz formátumú!";
    }

    //date
    if(!isset($input['date'])){
        $errors['date'] ="Nincs Dátum";
    }else if (trim($input['date']) === ''){
        $errors['date'] = "Dátum kötelező";
    }else if (strtotime($input['date']) === false){
        $errors['date'] = "Rossz dátumformátum";
    }else {
        $date = strtotime($input['date']);
        $now = strtotime('now');
        if ($now - $date < 4 * 30 * 24 * 60 * 60){
            $errors['date'] = "Nem telt el elég idő";
        }else {
            $data['date'] = $input['date'];
        }
    }

    //type
    if(!isset($input['type'])){
        $errors['type'] ="Nincs Típus";
    }else if (trim($input['type']) === ''){
        $errors['type'] = "Típus kötelező";
    }else {
        $data['type'] = $input['type'];
    }

    //agree
    if (!isset($input['agree'])){
        $errors['agree'] = "Nem járult hozzá";
    }else {
        $data['agree'] = true;
    }

    return count($errors) === 0;
}

//start
$data = [];
$errors = [];
if(count($_GET) > 0){
    if(validate($_GET, $data, $errors)){
        //valid
        $message = "Minden adat rendben van";
    }else {
        //unvalid
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
    <?php if(isset($message)) : ?>
        <div> <?= $message ?> </div>
    <?php endif ?>
    <h1>Új oltás</h1>
    <?php if(count($errors) > 0) : ?>
        <ul>
            <?php foreach ($errors as $error) : ?>
                <li> <?= $error ?> </li>
            <?php endforeach ?>
        </ul>
    <?php endif ?>
    <form action="" method="get">
        Név : <input type="text" name="name" value="<?= $_GET['name'] ?? '' ?>">
            <?php if(isset($errors['name'])) : ?>
                <span style="color: red"> <?= $errors['name'] ?> </span>
            <?php endif ?>
            <br>
        TAJ szám : <input type="text" name="taj" value ="<?= $_GET['taj'] ?? '' ?>" > <br>
        Utolsó oltás időpontja: <input type="date" name="date"
                                value ="<?= $_GET['date'] ?? '' ?>" >
        Típus: 
        <select name="type">
            <option <?= $_GET['type'] ==='Astra' ? 'selected' : '' ?> >Astra</option>
            <option <?= $_GET['type'] ==='Pfizer' ? 'selected' : '' ?> >Pfizer</option>
            <option <?= $_GET['type'] ==='Szputnyik' ? 'selected' : '' ?> >Szputnyik</option>
        </select><br>
        Hozzájárul <input type="checkbox" name="agree" <?= isset($_GET['agree']) ? 'checked' : '' ?> ><br>
        <button type="submit">Ellenőrzés</button>
    </form>
</body>
</html>