<?php
include_once('storage.php');
include_once('userstorage.php');
include_once('teamstorage.php');
include_once('matchstorage.php');
include_once('./functions/auth.php');
include_once('./functions/redirect.php');
include_once('./functions/topfive.php');

session_start();
$auth = new Auth(new UserStorage());
$teams = new TeamStorage();
$matches = new MatchStorage();
$matches1 = $matches->findAll();
$teams1 = $teams->findAll();
$logged = false;


if ($auth->is_authenticated()) {
    $logged = true;
}

$matches5 = topFive($matches1);

?>

<!DOCTYPE html>
<html lang="hu">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title> ELS - Főoldal </title>
</head>
<body>
    <div>
        <?php if($logged) : ?>
            <a href="logout.php"> Kijelentkezés </a>
        <?php endif ?>
        <?php if(!$logged) : ?>
            <a href="register.php"> Regisztráció </a>
            <br>
            <a href="login.php"> Belépés </a></div>
        <?php endif ?>
    <h1>Eötvös Loránd Stadion</h1>
    <div><p>
        Az Eötvös Loránd Stadion (ELS) évtizedes múltra tekinthet vissza. Az itt megrendezett
        világszínvolnalú meccsek emberek millióit vonzza a fővárosba. Oldalunkon az itt 
        megrendezett meccseket tekintheti meg. Felhasználóként lehetősége van elmenteni a kedvenc
        csapatait és megjegyzéseket tenni azok oldalán. Ha nem tette még eddig, akkor kérjük
        regisztráljon! 
    </p></div>
    <div>
        <h2>Csapataink</h2>
        <ul>
            <?php foreach ($teams1 as $team) : ?>
                <li><?= $team['name'] ?>(<a href="team.php?id=<?= $team['id'] ?>">csapat oldala</a>)</li>
            <?php endforeach ?> 
        </ul>
    </div>
    <div>
        <h2>Legutóbbi meccsek</h2>
        <table>
            <thead>
                <tr>
                  <th>Dátum</th>
                  <th>Hazai</th>
                  <th>Eredmény</th>
                  <th>Vendég</th>
                </tr>
            </thead>
            <?php foreach($matches5 as $match) : ?>
            <tr>
                <td><?= $match['date'] ?></td>
                <td><?= $teams->findById($match['home']['id'])['name'] ?></td>
                <td><?= $match['home']['score'] ?> - <?= $match['away']['score'] ?></td>
                <td><?= $teams->findById($match['away']['id'])['name'] ?></td>
            </tr>
            <?php endforeach ?>
        </table>
    </div>
</body>
</html>