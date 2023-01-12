<?php
    include('storage.php');
    $ts = new Storage(new JsonIO('teachers.json'));
    if (isset($_GET['filter'])) {
        if ($_GET['filter'] === 'free') {
            $teachers = $ts -> findMany(function ($t) {
                return $t['maxcount'] > count($t['practices']);
            });
        } else if ($_GET['filter'] === 'over') {
            $teachers = $ts -> findMany(function ($t) {
                return $t['maxcount'] < count($t['practices']);
            });
        }
    } else {
        $teachers = $ts -> findAll();
    }
?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Teachers</title>
</head>

<body>
    <h1>Teachers</h1>
    <h2>List of teachers</h2>
    <p>
        <a href="?filter=free">Free capacity</a>
        <a href="?filter=over">Capacity overflow</a>
        <a href="?">All teachers</a>
    </p>
    <ul>
        <li>
            <b>Example Teacher</b> <br>
            <small>
                Practice11, Practice22
                (Max: 2)
            </small>
        </li>
        <?php foreach ($teachers as $t) : ?>
            <li>
                <a href="modify.php?id=<?= $t['id'] ?>"><b><?= $t['name'] ?></b></a> <br>
                <small>
                    <?= implode(',', $t['practices']) ?>
                    (Max: <?= $t['maxcount'] ?>)
                </small>
            </li>
        <?php endforeach ?>
    </ul>
</body>

</html>