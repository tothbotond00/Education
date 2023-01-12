<?php
$forecast = [
    [
        "date"  => "2021-11-23",
        "icon"  => "10d",
        "daily_min" => "2",
        "daily_max" => "10",
    ],
    [
        "date"  => "2021-11-24",
        "icon"  => "50d",
        "daily_min" => "2",
        "daily_max" => "10",
    ],
    [
        "date"  => "2021-11-25",
        "icon"  => "02d",
        "daily_min" => "2",
        "daily_max" => "10",
    ],
    [
        "date"  => "2021-11-26",
        "icon"  => "04d",
        "daily_min" => "-20",
        "daily_max" => "10",
    ],
];
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Weather forecast</title>
    <style>
        table { border-collapse: collapse; }
        tr:nth-child(even) { background-color: #eee; }
        .freezing { border-left: 5px solid lightblue; }
    </style>
</head>
<body>
    <table>
        <tr>
            <th>Day</th>
            <th>Weather</th>
            <th>Temperature</th>
        </tr>
        <?php foreach ($forecast as $day) : ?>
            <tr <?= $day['daily_min'] < 0 ? "class=\"freezing\"" : ''  ?> >
                <td><?=date("D, M j",strtotime($day['date']))?></td>
                <td><img src="https://openweathermap.org/img/wn/<?=$day['icon'] ?>.png"></td>
                <td> <?=$day['daily_max']?> / <?=$day['daily_min']?> C</td>
            </tr>
        <?php endforeach ?>
    </table>
</body>
</html>