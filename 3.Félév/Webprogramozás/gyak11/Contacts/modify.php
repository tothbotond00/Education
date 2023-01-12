<?php
include('contactstorage.php');
$contactStorage = new ContactStorage();

$id = $_GET['id'];
$contact = $contactStorage->findById($id);

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Contact</title>
</head>
<body>
    <h1>Edit Contact</h1>
    <form action="" method="post" novalidate>
        Name : <input type="text" name="name" required
                value ="<?= $_POST['name'] ?? $contact['name'] ?>"><br>
                <?php if (isset($errors['name'])) : ?>
                    <span> <?= $errors['name'] ?> </span>
                <?php endif ?><br>
        E-mail : <br>
        <input type="email" name="email[]" value="<?= $_POST['email'][0] ?? $contact['email'][0] ?>" ><br>
        <input type="email" name="email[]" value="<?= $_POST['email'][0] ?? $contact['name'][1] ?>" ><br>
        <?php if (isset($errors['email'])) : ?>
            <span> <?= $errors['email'] ?> </span>
        <?php endif ?><br>
        Telephone: <input type="text" name="phone" placeholder="+36..."
                    value ="<?= $_POST['phone'] ?? $contact['phone'] ?>"><br>
                    <?php if (isset($errors['phone'])) : ?>
                        <span> <?= $errors['phone'] ?> </span>
                    <?php endif ?><br>
        Address : <input type="text" name="address"
                    value ="<?= $_POST['address'] ?? $contact['address'] ?>"><br>
                    <?php if (isset($errors['address'])) : ?>
                        <span> <?= $errors['address'] ?> </span>
                    <?php endif ?><br>
        Notes: <textarea name="notes" cols="30" rows="10" ><?= $_POST['notes'] ?? $contact['notes'] ?></textarea>
                    <?php if (isset($errors['notes'])) : ?>
                        <span> <?= $errors['notes'] ?> </span>
                    <?php endif ?><br>
        Sex :
            <input type="radio" name="sex" value="male"
             >Male 
            <input type="radio" name="sex" value="female"
             >Female <br>
            <?php if (isset($errors['sex'])) : ?>
                    <span> <?= $errors['sex'] ?> </span>
            <?php endif ?><br> 
        <button>Save content</button>
    </form>
</body>
</html>