<?php
include_once('storage.php');
include_once('userstorage.php');
include_once('./functions/auth.php');
include_once('./functions/redirect.php');
include_once('./functions/validateR.php');

// main
$user_storage = new UserStorage();
$auth = new Auth($user_storage);
$errors = [];
$data = [];
if (count($_POST) > 0) {
  if (validateR($_POST, $data, $errors)) {
    if ($auth->user_exists($data['username'])) {
      $errors['global'] = "User already exists";
    } else {
      $auth->register($data);
      redirect('login.php');
    } 
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
    <title> ELS - Regisztrálás  </title>
</head>
<body>
<a href="index.php">Vissza a kezdőlapra</a>
<h1>Regisztráció</h1>
<form action="" method="post" novalidate>
    <div>
      <label for="username">Felhasználónév: </label><br>
      <input type="text" name="username" id="username" value="<?= $_POST['username'] ?? "" ?>">
      <span></span>
      <?php if (isset($errors['username'])) : ?>
        <span class="error"><?= $errors['username'] ?></span>
      <?php endif ?>
    </div>
    <div>
      <label for="email">E-mail: </label><br>
      <input type="email" name="email" id="email" value="<?= $_POST['email'] ?? "" ?>">
      <?php if (isset($errors['email'])) : ?>
        <span class="error"><?= $errors['email'] ?></span>
      <?php endif ?>
    </div>
    <div>
      <label for="password">Jelszó: </label><br>
      <input type="password" name="password" id="password" value="<?= $_POST['password'] ?? "" ?>">
      <?php if (isset($errors['password'])) : ?>
        <span class="error"><?= $errors['password'] ?></span>
      <?php endif ?>
    </div>
    <div>
      <label for="password2">Jelszó mégegyszer: </label><br>
      <input type="password" name="password2" id="password2">
      <?php if (isset($errors['password2'])) : ?>
        <span class="error"><?= $errors['password2'] ?></span>
      <?php endif ?>
    </div>
    <div>
      <button type="submit">Register</button>
    </div>
  </form>
</body>
</html>