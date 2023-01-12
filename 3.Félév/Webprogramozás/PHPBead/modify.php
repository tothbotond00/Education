<?php
include_once('storage.php');
include_once('userstorage.php');
include_once('teamstorage.php');
include_once('matchstorage.php');
include_once('./functions/auth.php');
include_once('./functions/redirect.php');

session_start();
$auth = new Auth(new UserStorage());
$teams = new TeamStorage();
$matches = new MatchStorage();
$curmatch = $matches->findbyId($_GET['id']);
$logged = false;
$curteam = 1;
$curteam = $_GET['curteam'];


if ($auth->is_authenticated()) {
  $logged = true;
}

///Hamis id-vel oldalra érkezés vagy nem adminként 
if (empty($curmatch)|| ! $logged || !in_array("admin",$auth->authenticated_user()['roles'])){
    redirect("team.php?id=$curteam");
}

function validateM($post, &$data, &$errors) {
    //Home ID

    //Home Score
    if(!isset($post['homescore'])){
        $errors['homescore'] ="Nincsenek hazai pontok!";
    }else if (trim($post['homescore']) === ''){
        $errors['homescore'] = "A hazai pontok kitöltése kötelező!";
    }else if(preg_match('/^\d+$/',$post['homescore']) == false) {
        $errors['homescore'] = "A hazai pontoknak egész számnak kell lennie!";
    }else {
        if(intval($post['homescore']) < 0){
            $errors['homescore'] = 'A hazai pontoknak legalább 0-nak kell lennie!';
        }else {
            $data['homescore'] = $post['homescore'];
        }
    }

    //Away ID

    //Away Score
    if(!isset($post['awayscore'])){
        $errors['awayscore'] ="Nincsenek idegen pontok!";
    }else if (trim($post['awayscore']) === ''){
        $errors['awayscore'] = "Az idegen pontok kitöltése kötelező!";
    }else if(preg_match('/^\d+$/',$post['awayscore']) == false) {
        $errors['awayscore'] = "Az idegen pontoknak egész számnak kell lennie!";
    }else {
        if(intval($post['awayscore']) < 0){
            $errors['awayscore'] = 'Az idegen pontoknak legalább 0-nak kell lennie!';
        }else {
            $data['awayscore'] = $post['awayscore'];
        }
    }

    //Date
    $re = '/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/m';
    if(!isset($post['date'])){
        $errors['date'] ="Nincsen dátum!";
    }else if (trim($post['date']) === ''){
        $errors['date'] = "Az dátum kitöltése kötelező!";
    }else if(preg_match_all($re, $_POST['date']) == false){
        $errors['date'] = "Az dátum formátuma : yyyy-mm-dd";
    }

    return count($errors) == 0;
}

$data = [];
$errors = [];
if (count($_POST) > 0) {
    if (validateM($_POST, $data, $errors)) {
        redirect("team.php?id=$curteam");
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
    <title> ELS - Szerkesztés </title>
</head>
<body>
    <a href="index.php">Vissza a kezdőlapra</a>
    <h1>Meccs szerkesztése</h1>
    <form action="" method="post" novalidate>
    <div>
      <label for="homeid">Hazai Id: </label><br>
      <input type="text" name="homeid" id="homeid" value="<?= $_POST['homeid'] ?? $_GET['homeid'] ?? "" ?>">
      <?php if (isset($errors['homeid'])) : ?>
        <span class="error"><?= $errors['homeid'] ?></span>
      <?php endif ?>
    </div>
    <div>
      <label for="homescore">Hazai Pont: </label><br>
      <input type="text" name="homescore" id="homescore" value="<?= $_POST['homescore'] ?? $_GET['homescore'] ?? "" ?>">
      <?php if (isset($errors['homescore'])) : ?>
        <span class="error"><?= $errors['homescore'] ?></span>
      <?php endif ?>
    </div>
    <div>
      <label for="awayid">Idegen Id: </label><br>
      <input type="text" name="awayid" id="awayid" value="<?= $_POST['awayid'] ?? $_GET['awayid'] ?? "" ?>">
      <?php if (isset($errors['awayid'])) : ?>
        <span class="error"><?= $errors['awayid'] ?></span>
      <?php endif ?>
    </div>
    <div>
      <label for="awayscore">Idegen Pont: </label><br>
      <input type="text" name="awayscore" id="awayscore" value="<?= $_POST['awayscore'] ?? $_GET['awayscore'] ?? "" ?>">
      <?php if (isset($errors['awayscore'])) : ?>
        <span class="error"><?= $errors['awayscore'] ?></span>
      <?php endif ?>
    </div>
    <div>
      <label for="date">Dátum: </label><br>
      <input type="text" name="date" id="date" value="<?= $_POST['date'] ?? $_GET['date'] ?? "" ?>">
      <?php if (isset($errors['date'])) : ?>
        <span class="error"><?= $errors['date'] ?></span>
      <?php endif ?>
    </div>
      <button type="submit">Módosít</button>
    </div>
  </form>
</body>
</html>