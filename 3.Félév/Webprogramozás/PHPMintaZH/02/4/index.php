<?php
    include_once("table.php");
    $data = table_fields();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Exam grader</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h1>Exam grader</h1>

    <h2>New entry</h2>
    Neptun: <input type="text" maxlength="6" id="neptun"> <br>
    Points: <input type="number" value="0" min="0" max="8" step="0.1" id="points"> <br>
    <button id="save">Save</button>

    <h2>Results</h2>
    <table>
        <tr>
            <th>Neptun</th>
            <th>1</th>
            <th>2</th>
            <th>3</th>
            <th>4</th>
            <th>SUM</th>
        </tr>
        <?php foreach($data as $row): ?>
        <tr>
            <?php foreach($row as $field): ?>
                <td><?= $field ?></td>
            <?php endforeach; ?>
        </tr>
        <?php endforeach; ?>
    </table><br>
    <button id="clear">Clear table</button>

    <script src="script.js"></script>
</body>
</html>