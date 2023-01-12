<?php
include_once('storage.php');
include_once('userstorage.php');
include_once('teamstorage.php');
include_once('matchstorage.php');
include_once('commentstorage.php');
include_once('./functions/auth.php');
include_once('./functions/redirect.php');
include_once('./functions/colorcalc.php');
include_once('./functions/validateC.php');

session_start();
$auth = new Auth(new UserStorage());
$teams = new TeamStorage();
$matches = new MatchStorage();
$comments = new CommentStorage();
$comments1 = $comments->findAll();
$matches1 = $matches->findAll();
$curteam = $teams->findbyId($_GET['id']);
$logged = false;


if ($auth->is_authenticated()) {
  $logged = true;
}

///Hamis id-vel oldalra érkezés
if (empty($curteam) ){
    redirect("index.php");
}

$data = [];
$errors = [];
if (count($_POST) > 0) {
    if (validateC($_POST, $data, $errors)) {
        $data['user'] = $auth->authenticated_user()['username'];
        $data['teamid'] = $curteam['id'];
        $data['date'] = date('Y-m-d');
        $comments->add($data);
        $id = $curteam['id'];
        redirect("team.php?id=$id");
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
    <title> ELS - <?= $curteam['name'] ?> </title>
</head>
<body>
    <a href="index.php">Vissza a kezdőlapra</a>
    <h1><?= $curteam['name'] ?></h1>
    <h2>Meccsek</h2>
    <table>
        <thead>
                    <tr>
                    <th>Dátum</th>
                    <th>Hazai</th>
                    <th>Eredmény</th>
                    <th>Vendég</th>
                    <?php if ($logged && in_array("admin",$auth->authenticated_user()['roles']) ) :?>
                    <th>Admin funkció</th>
                    <?php endif ?> </li>
                    </tr>
        </thead>
        <?php foreach($matches1 as $key => $match) : ?>
            <?php if($teams->findById($match['home']['id'])['id'] == $curteam['id'] ||
                    $teams->findById($match['away']['id'])['id'] == $curteam['id']) : ?>
                <tr>
                    <td><?= $match['date'] ?></td>
                    <td <?php if($curteam['id'] == $match['home']['id']) : ?>
                            class ="<?= calcColor($match['home']['score'],$match['away']['score']) ?>"
                        <?php endif ?> ><?= $teams->findById($match['home']['id'])['name'] ?></td>
                    <td><?= $match['home']['score'] ?> - <?= $match['away']['score'] ?></td>
                    <td <?php if($curteam['id'] == $match['away']['id']) : ?>
                            class ="<?= calcColor($match['away']['score'],$match['home']['score']) ?>"
                        <?php endif ?> ><?= $teams->findById($match['away']['id'])['name'] ?></td>
                    <td><?php if ($logged && in_array("admin",$auth->authenticated_user()['roles']) ) :?>
                        <a href="modify.php?curteam=<?= $curteam['id'] ?>&id=<?= $key ?>&homeid=<?= $match['home']['id'] ?>&homescore=<?= $match['home']['score'] ?>&awayid=<?= $match['away']['id'] ?>&awayscore=<?= $match['away']['score'] ?>&date=<?= $match['date'] ?>">Szerkeszt</a>
                        <?php endif ?> </li></td>
                </tr>
            <?php endif ?>
        <?php endforeach ?>
    </table>
    <h2>Hozzászólások</h2>
    <ul>
        <?php foreach($comments1 as $key => $comment) : ?>
            <?php if($comment['teamid'] == $curteam['id']) : ?>
                <li> <?= $comment['user'] ?> (<?= $comment['date'] ?>): <?= $comment['comment'] ?>
                <?php if ($logged && in_array("admin",$auth->authenticated_user()['roles']) ) :?>
                    <a href="delete.php?id=<?=$key?>&team=<?= $curteam['id'] ?>" class="x">❌</a>
                <?php endif ?> </li>
            <?php endif ?>
        <?php endforeach ?>
    </ul>
    <h2>Hozzászólás írása</h2>
    <?php if (!$logged) : ?>
        <h3 class="error">A hozzászólás írásához bejelntkezés szükséges!</h3><br>
        <a href="register.php"> Regisztráció </a><br>
        <a href="login.php"> Belépés </a></div>
    <?php endif ?>
    <?php if ($logged) : ?>
        <form method="post" novalidate>
            <textarea name="comment" cols="30" rows="10"></textarea>
            <?php if (isset($errors['comment'])) : ?>
            <span class="error"><?= $errors['comment'] ?></span>
            <?php endif ?><br>
            <button>Elküld</button>
        </form>
    <?php endif ?>
</body>
</html>