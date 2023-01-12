<?php 
    $output = [];

    $goblins = $_GET['goblins'] ?? '';
    $chief = $_GET['chief'] ?? '';
    $shovels = $_GET['shovels'] ?? '';

    $goblin_err = false;
    if ($goblins === '' || !is_numeric($goblins) || filter_var($goblins, FILTER_VALIDATE_INT) === false || intval($goblins) < 1 ){
        $output[] = "Érvénytelen goblin mennyiség!";
        $goblin_err = true;
    }

    $words = explode(' ', $chief);
    if ($chief === '' || count($words) < 2)
        $output[] = "Érvénytelen vezető!";
    else {
        $ranks = ['goblinka', 'kisfőnök', 'nagyfőnök', 'főfőnök', 'törzsfő'];
        if (!in_array($words[count($words) - 1] , $ranks))
            $output[] = "Érvénytelen rang!";
        else if (array_search($words[count($words) - 1], $ranks) < 2)
            $output[] = "Túl alacsony rang!";
    }

    if ($shovels === '' || !is_numeric($shovels) || filter_var($shovels, FILTER_VALIDATE_INT) === false || intval($shovels) < 0 )
        $output[] = "Érvénytelen ásó mennyiség!";
    else if (!$goblin_err && intval($shovels) < intval($goblins))
        $output[] = "Túl kevés asó!";

    if (count($output) == 0){
        if (intval($shovels) >= 2* intval($goblins))
            $output[] = "Gyorsan megszerezzük a kincset!";
        else
            $output[] = "Indulhat az akció!";
    }
?>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>2. feladat</title>
</head>
<body>
  <h1>2. feladat</h1>

  <h2>Üzenetek</h2>
  <ul>
  <?php foreach($output as $o): ?>
    <li> <?= $o ?> </li>
  <?php endforeach ?>
  </ul>

  <h2>Próbalinkek</h2>
  <a href="index.php?goblins=5&chief=Snuch Nawdow nagyfőnök&shovels=7"><pre>index.php?goblins=5&chief=Snuch Nawdow nagyfőnök&shovels=7</pre></a>
  <a href="index.php?goblins=5&chief=Snuch Nawdow nagyfőnök&shovels=10"><pre>index.php?goblins=5&chief=Snuch Nawdow nagyfőnök&shovels=10</pre></a>
  <a href="index.php"><pre>index.php</pre></a>
  <a href="index.php?goblins=nemszám&chief=nincsszóköz&shovels=nemszám"><pre>index.php?goblins=nemszám&chief=nincsszóköz&shovels=nemszám</pre></a>
  <a href="index.php?goblins=-5&chief=Snuch Nawdow nagyfőnök&shovels=10"><pre>index.php?goblins=-5&chief=Snuch Nawdow nagyfőnök&shovels=10</pre></a>
  <a href="index.php?goblins=16.2&chief=Snuch Nawdow nagyfőnök&shovels=10"><pre>index.php?goblins=16.2&chief=Snuch Nawdow nagyfőnök&shovels=10</pre></a>
  <a href="index.php?goblins=16&chief=Snuch Nawdow nagyfőnök&shovels=10"><pre>index.php?goblins=16&chief=Snuch Nawdow nagyfőnök&shovels=10</pre></a>
  <a href="index.php?goblins=5&chief=Snuch Nawdow párttitkár&shovels=10"><pre>index.php?goblins=5&chief=Snuch Nawdow párttitkár&shovels=10</pre></a>
  <a href="index.php?goblins=5&chief=Snuch Nawdow kisfőnök&shovels=10"><pre>index.php?goblins=5&chief=Snuch Nawdow kisfőnök&shovels=10</pre></a>
</body>
</html>