<?php
$products = [
  '1' => 'Warm tea cup',
  '2' => 'Hot chocolate mug',
  '3' => 'Dreamy music headset',
  '4' => 'Calm tenderness',
  '5' => 'Peace inside',
];

// solution starts here
session_start();

?>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Cart</title>
  <link rel="stylesheet" href="index.css">
</head>
<body>
  <h1>XMas webshop</h1>
  <h2>Products</h2>
  <ul>
  <?php foreach($products as $id => $product ) : ?>
    <li>
      <?= $product ?>
      <a href="add.php?id=<?= $id ?>">Add to cart</a>
    </li>
  <?php endforeach ?>
  </ul>

  <h2>Cart</h2>
  <ul>
  <?php foreach($_SESSION['cart'] as $id => $num) : ?>
    <li>
      <?= $products[$id] ?>
      <a href="add.php?id=<?= $id ?>&count=-1">⊖</a>
      <?= $num ?>x
      <a href="add.php?id=<?= $id ?>">⊕</a>
      <a href="remove.php?id=<?= $id ?>">Delete</a>
    </li>
  <?php endforeach ?>
  </ul>
</body>
</html>