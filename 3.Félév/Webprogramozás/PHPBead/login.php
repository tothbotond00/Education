<?php
include_once('storage.php');
include_once('userstorage.php');
include_once('./functions/auth.php');
include_once('./functions/redirect.php');
include_once('./functions/validateL.php');

// main
session_start();
$user_storage = new UserStorage();
$auth = new Auth($user_storage);
$data = [];
$errors = [];
if (count($_POST) > 0) {
    if (validateL($_POST, $data, $errors)) {
      $auth_user = $auth->authenticate($data['username'], $data['password']);
      if (!$auth_user) {
        $errors['global'] = "Hibás felhasználónév vagy jelszó!";
      } else {
        $auth->login($auth_user);
        redirect('index.php');
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
    <title> ELS - Belépés </title>
</head>
<body>
<a href="index.php">Vissza a kezdőlapra</a>
<h1>Bejelentkezés</h1>
  <?php if (isset($errors['global'])) : ?>
    <p><span class="error"><?= $errors['global'] ?></span></p>
  <?php endif ?>
  <form action="" method="post" novalidate>
    <div>
      <label for="username">Felhasználónév: </label><br>
      <input type="text" name="username" id="username" value="<?= $_POST['username'] ?? "" ?>">
      <?php if (isset($errors['username'])) : ?>
        <span class="error"><?= $errors['username'] ?></span>
      <?php endif ?>
    </div>
    <div>
      <label for="password">Jelszó: </label><br>
      <input type="password" name="password" id="password">
      <?php if (isset($errors['password'])) : ?>
        <span class="error"><?= $errors['password'] ?></span>
      <?php endif ?>
    </div>
    <div>
      <button type="submit">Bejelentkezés</button>
    </div>
  </form>
</body>
</html>