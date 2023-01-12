<?php
    $treasures = json_decode(file_get_contents('treasure.json'), true);
    $write = false;
    if (count($_POST) > 0){
        $data = [
            "name" => $_POST['name'],
            "value"=> intval($_POST['value']),
            "color"=> $_POST['color'],
            "keep" => ($_POST['keep'] == 'igen')
        ];
        $treasures[$data['name']] = $data;
        $write = true;
    }
    if (isset($_GET['id'])){
        unset($treasures[$_GET['id']]);
        $write = true;
    }
    if ($write) file_put_contents('treasure.json', json_encode($treasures, JSON_PRETTY_PRINT));
?>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>3. feladat</title>
</head>
<body>
  <h1>3. feladat</h1>

  <h2>Űrlap</h2>
  <form action="index.php" method="post">
  Kincs neve: <input type="text" name="name"> <br>
  Kincs értéke: <input type="number" name="value"> <br>
  Kincs színe: <select name="color">
    <option value="piros">piros</option>
    <option value="narancs">narancs</option>
    <option value="sárga">sárga</option>
    <option value="zöld">zöld</option>
    <option value="kék">kék</option>
    <option value="lila">lila</option>
  </select> <br>
  Megtartjuk? <br>
  <input type="radio" name="keep" value="igen"> igen<br>
  <input type="radio" name="keep" value="nem"> nem<br>
  <button type="submit">Küldés</button>
  </form>

  <h2>Kincslista</h2>
  <table>
    <tr>
        <th>Név</th>
        <th>Érték</th>
        <th>Szín</th>
        <th>Megtartjuk?</th>
        <th></th>
    </tr>
    <?php foreach($treasures as $id => $t): ?>
        <tr>
            <td> <?= $t['name'] ?> </td>
            <td> <?= $t['value'] ?> </td>
            <td> <?= $t['color'] ?> </td>
            <td> <?= $t['keep'] ? 'Megtartjuk' : 'Eladományozzuk' ?> </td>
            <td> <a href="index.php?id=<?= $id ?>">Töröl</a> </td>
        </tr>
    <?php endforeach ?>
  </table>

</body>
</html>
