<?php
include('contactstorage.php');

//debug
print_r($_GET);
print_r($_POST);

// functions
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

    //Email
    if(!isset($post['email'])){
        $errors['email'] ="Email is not set!";
    }else if (!is_array($post['email'])){
        $errors['email'] ="Email is wrong format";
    }else{
        $emails = array_filter($post['email'] , function($email){
            return trim($email) !== '';
        });
        if (count($emails) === 0){
            $errors['email'] ="Email is required";
        }else{
            $valid_emails = array_filter($emails, function ($email){
                return filter_var($email, FILTER_VALIDATE_EMAIL) !== false;
            });
            if(count($emails) !== count($valid_emails)){
                $errors['email'] = "An email is in wrong format!";
            }else{
                $data['email'] = $valid_emails;
            }
        }
    }

    //Telephone
    $re = '/^\+36[0-9]{9}$/m';
    if(isset($post['phone'])){
        if (trim($post['phone']) === ''){
            $data['phone'] = "unknown";
        }else if(preg_match($re, $post['phone']) === 1) {
            $data['phone'] = $post['phone'];
        }else {
            $errors['phone'] = "Phone number is wrong format!";
        }
    }else {
        $data['phone'] = "unknown";
    }

    //Address
    if(isset($post['address'])){
        if (trim($post['address']) === ''){
            $data['address'] = "unknown";
        }else {
            $data['address'] = $post['address'];
        }
    }else {
        $data['phone'] = "unknown";
    }

    //Sex
    if (!isset($post['sex'])){
        $errors['sex'] = "Your sex is mandatory!";
    }else {
        $data['sex'] = $post['sex'];
    }


    return count($errors) === 0;
}

// main
$data = [];
$errors = [];
if (count($_POST) > 0) {
    if (validate($_POST, $data, $errors)) {
        // Beolvasás: $data
        print_r($data);
        // Feldolgozás
        $contacts = new ContactStorage();
        $contacts->add($data);
        header("Location: index.php");
        exit();
    }else{
        print_r($errors);
    }
}
if(isset($_POST["sex"]) ){
    $isMale = $_POST["sex"] == "male" ? "checked" : '';
    $isFemale = $_POST["sex"] == "female" ? "checked" : '';
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact manager</title>
    <style>
        form span {
            color : red;
            font-size : smaller;
        }
    </style>
</head>
<body>
    <h1>Add new contact</h1>
    <form action="" method="post" novalidate>
        Name : <input type="text" name="name" required
                value ="<?= $_POST['name'] ?? '' ?>"><br>
                <?php if (isset($errors['name'])) : ?>
                    <span> <?= $errors['name'] ?> </span>
                <?php endif ?><br>
        E-mail : <br>
        <input type="email" name="email[]" value="<?= $_POST['email'][0] ?? '' ?>" ><br>
        <input type="email" name="email[]" value="<?= $_POST['email'][0] ?? '' ?>" ><br>
        <?php if (isset($errors['email'])) : ?>
            <span> <?= $errors['email'] ?> </span>
        <?php endif ?><br>
        Telephone: <input type="text" name="phone" placeholder="+36..."
                    value ="<?= $_POST['phone'] ?? '' ?>"><br>
                    <?php if (isset($errors['phone'])) : ?>
                        <span> <?= $errors['phone'] ?> </span>
                    <?php endif ?><br>
        Address : <input type="text" name="address"
                    value ="<?= $_POST['address'] ?? '' ?>"><br>
                    <?php if (isset($errors['address'])) : ?>
                        <span> <?= $errors['address'] ?> </span>
                    <?php endif ?><br>
        Notes: <textarea name="notes" cols="30" rows="10" ><?= $_POST['notes'] ?? '' ?></textarea>
                    <?php if (isset($errors['notes'])) : ?>
                        <span> <?= $errors['notes'] ?> </span>
                    <?php endif ?><br>
        Sex :
            <input type="radio" name="sex" value="male"
            <?= $isMale ?? '' ?> >Male 
            <input type="radio" name="sex" value="female"
            <?= $isFemale ?? '' ?> >Female <br>
            <?php if (isset($errors['sex'])) : ?>
                    <span> <?= $errors['sex'] ?> </span>
            <?php endif ?><br> 
        <button>Save content</button>
    </form>
</body>
</html>