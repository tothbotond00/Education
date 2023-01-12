<?php
include_once('contactstorage.php');
$contactStorage = new ContactStorage();

// debug
print_r($_GET);
print_r($_POST);

// read
$id = $_GET['id'];
$contact = $contactStorage->findById($id);
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
  <h1>Edit contact</h1>
  <form action="" method="post" novalidate>
    Name: <input type="text" name="name" required
      value="<?= $_POST['name'] ?? $contact['name'] ?>"
    > 
    <?php if(isset($errors['name'])) : ?>
      <span><?= $errors['name'] ?></span>
    <?php endif ?>
    <br>
    Email: <br>
    <input type="email" name="email[]" value="<?= $_POST['email'][0] ?? $contact['email'][0] ?>"> <br>
    <input type="email" name="email[]" value="<?= $_POST['email'][1] ?? $contact['email'][1] ?>"> <br>
    <?php if(isset($errors['email'])) : ?>
      <span><?= $errors['email'] ?></span> <br>
    <?php endif ?>
    Phone: <input type="text" name="phone"> <br>
    Address: <input type="text" name="address"> <br>
    Notes: <textarea name="notes" cols="30" rows="10"></textarea> <br>
    Sex: 
    <input 
      type="radio" 
      name="sex" 
      value="male"
      <?= isset($_POST['sex']) && $_POST['sex'] === 'male' ? 'checked' : '' ?>
    > Male 
    <input type="radio" name="sex" value="female"
      <?= isset($_POST['sex']) && $_POST['sex'] === 'female' ? 'checked' : '' ?>
    > Female <br>
    <?php if(isset($errors['sex'])) : ?>
      <span><?= $errors['sex'] ?></span> <br>
    <?php endif ?>
    <button>Save contact</button>
  </form>
</body>
</html>