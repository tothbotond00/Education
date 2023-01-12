<?php
include('storage.php');

$family = new Storage(new JsonIO('members.json'));

$members = $family->findAll();

?>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Gift list</title>
  <link rel="stylesheet" href="index.css">
</head>
<body>
  <h1>Gift list</h1>
  <h2>My family members</h2>
  <ul>
  <?php foreach($members as $member) : ?>
    <li>
      <a href="member.php?id=<?= $member['id'] ?>"> <?= $member['name'] ?> </a>
    </li>
  <?php endforeach ?>
  </ul>
</body>
</html>