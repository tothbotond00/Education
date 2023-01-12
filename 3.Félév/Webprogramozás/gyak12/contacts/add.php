<?php
include_once('storage.php');
include_once('contactstorage.php');
include_once('userstorage.php');
include_once('auth.php');

session_start();
$auth = new Auth(new UserStorage());

if (!$auth->is_authenticated()) {
  redirect('login.php');
}

// debug
print_r($_GET);
print_r($_POST);

// functions
function validate($post, &$data, &$errors) {
  $data = $post;
  // Ellenőrzés
  if (!isset($post['name'])) {
    $errors['name'] = 'Name is not set';
  }
  else if (trim($post['name']) === '') {
    $errors['name'] = 'Name is required';
  }
  else {
    $data['name'] = $post['name'];
  }

  if (!isset($post['email'])) {
    $errors['name'] = 'Email is not set';
  }
  else if (!is_array($post['email'])) {
    $errors['name'] = 'Email has a wrong format';
  }
  else {
    $emails = array_filter($post['email'], function($email) {
      return trim($email) !== '';
    });
    if (count($emails) === 0) {
      $errors['email'] = 'Email is required';
    } else {
      $valid_emails = array_filter($emails, function ($email) {
        return filter_var($email, FILTER_VALIDATE_EMAIL) !== false;
      });
      if (count($emails) !== count($valid_emails)) {
        $errors['email'] = 'Invalid email';
      } else {
        $data['email'] = $valid_emails;
      }
    }
  }

  if (!isset($post['sex'])) {
    $errors['sex'] = 'Sex is required';
  } else {
    $data['sex'] = $post['sex'];
  }

  // $errors['test'] = true;

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
      $data['user_id'] = $auth->authenticated_user()['id'];
      $contactStorage = new ContactStorage();
      $contactStorage->add($data);
      header('Location: index.php'); // POST -> GET
      exit();
  } else {
    print_r($errors);
  }
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
      color: red;
      font-size: smaller;
    }
  </style>
</head>
<body>
  <h1>Add new contact</h1>
  <form action="" method="post" novalidate>
    Name: <input type="text" name="name" required
      value="<?= $_POST['name'] ?? '' ?>"
    > 
    <?php if(isset($errors['name'])) : ?>
      <span><?= $errors['name'] ?></span>
    <?php endif ?>
    <br>
    Email: <br>
    <input type="email" name="email[]" value="<?= $_POST['email'][0] ?? '' ?>"> <br>
    <input type="email" name="email[]" value="<?= $_POST['email'][1] ?? '' ?>"> <br>
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