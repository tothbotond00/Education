<?php
include('storage.php');

function validate($post, &$data, &$errors) {
    $data = $post;

    //Name
    if(!isset($post['name'])){
        $errors['name'] ="Name is required!";
    }else if (trim($post['name']) === ''){
        $errors['name'] = "Name is mandatory!";
    }else {
        $name = trim($post['name']);
        if(strstr($name," ") === false){
            $errors['name'] = "You must submit two names!";
        }else {
            $data['name'] = $name;
        }
    }
    //Age
    if(!isset($post['age'])){
        $errors['age'] ="Age is required!";
    }else if (trim($post['age']) === ''){
        $errors['age'] = "Age is mandatory!";
    }else {
        if(!is_numeric($post['age'])){
            $errors['age'] = "You must use numbers for the age!";
        }else {
            $data['age'] = $post['age'];
        }
    }
    return count($errors) === 0;
}

// main
$data = [];
$errors = [];
if (count($_POST) > 0) {
    if (validate($_POST, $data, $errors)) {
        // Feldolgozás
        $contacts = new Storage( new JsonIO('students.json'));
        $contacts->add($data);
        header("Location: index.php");
        exit();
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

<h1>Diák mentése</h1>
    <form action="" method="post">
    Név: <input type="text" name="name" /> <br>
    Kor: <input type="text" name="age" /> <br>
    <button type="submit">Diák mentése</button>
    </form>
</body>
</html>