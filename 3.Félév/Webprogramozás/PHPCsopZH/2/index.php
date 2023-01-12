<?php
// phpinfo();
//var_dump();

function validate($input, &$data, &$errors){

    //text
    if(!isset($input['text'])){
        $errors['text'] ="Nincs text!";
    }else if (trim($input['text']) === ''){
        $errors['text'] = "Text kötelező";
    }else {
        $data['text'] = $input['text'];
    }

    //length
    if(!isset($input['length'])){
        $errors['length'] ="Nincs length!";
    }else if (trim($input['length']) === ''){
        $errors['length'] = "Length kötelező";
    }else if(is_int($input['length'])) {
        $errors['length'] = "A length-nek számnak kell lennie!";
    }else if (intval($input['length']) < 0) {
        $errors['length'] = "A length nem lehet negatív érték!";
    }else {
        $data['length'] = $input['length'];
    }

    return count($errors) === 0;
}

//start
$data = [];
$errors = [];
if(count($_GET) > 0){
    if(validate($_GET, $data, $errors)){
        //valid
        $subs = substr($data['text'], 0, $data['length']);
        if (strlen($subs) < strlen($data['text'])){
            $message = $subs . "...";
        }else {
            $message = $subs;
        }
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
    <?php if(count($errors) > 0) : ?>
        <ul>
            <?php foreach ($errors as $error) : ?>
                <li> <?= $error ?> </li>
            <?php endforeach ?>
        </ul>
    <?php endif ?>
    <h1>Szöveg leváginátor</h1>
    <?php if(isset($message)) : ?>
        <div> <?= $message ?> </div>
    <?php endif ?>
</body>
</html>