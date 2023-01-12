<?php
include('storage.php');

$family = new Storage(new JsonIO('members.json'));

$member = $family->findById($_GET['id']);

if (count($_POST) > 0) {
  header("Location: member.php?id=$id");
  exit();
}

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
  <h1>Ideas for <?= $member['name'] ?></h1>
  <a href="index.php">Back to main page</a>
  <form action="" method="post">
    <fieldset>
      <legend>New idea</legend>
      Idea: <input type="text" name="idea" required> <br>
      <button name="function-add" type="submit">Add new idea</button>
    </fieldset>
  </form>
  <ul>
    <?php foreach($member['ideas'] as $idea) : ?>
      <li class="new">
      <?= $idea['name'] ?>
      <form action="" method="post">
        <input type="hidden" name="idea-id" value="<?= $idea['id'] ?>">
        <button type="submit" name="function-ok">Got it!</button>
        <button type="submit" name="function-discard">Discard it!</button>
      </form>
    </li>
    <?php endforeach ?>
  </ul>
</body>
</html>