<?php
$activities = [
    1 => [
        "name" => "alvás",
        "difficulty" => 0.05
    ],
    2 => [
        "name" => "bányászás",
        "difficulty" => 0.6
    ],
    3 => [
        "name" => "család",
        "difficulty" => 0.4
    ],
    4 => [
        "name" => "programozás",
        "difficulty" => 0.95
    ],
    5 => [
        "name" => "zsákmányolás",
        "difficulty" => 0.7
    ],
    6 => [
        "name" => "vadászat",
        "difficulty" => 0.6
    ],
    7 => [
        "name" => "játék",
        "difficulty" => 0.0
    ],
    8 => [
        "name" => "főzés",
        "difficulty" => 0.6
    ]
];
$goblins = [
    "WEB'LIN" => [
        [
            "startHour" => 0,
            "activityKey" => 3
        ],
        [
            "startHour" => 1,
            "activityKey" => 3
        ],
        [
            "startHour" => 3,
            "activityKey" => 5
        ],
        [
            "startHour" => 4,
            "activityKey" => 4
        ],
        [
            "startHour" => 5,
            "activityKey" => 4
        ],
        [
            "startHour" => 7,
            "activityKey" => 1
        ]
    ],
    "HUN'TER" => [
        [
            "startHour" => 0,
            "activityKey" => 1
        ],
        [
            "startHour" => 1,
            "activityKey" => 6
        ],
        [
            "startHour" => 3,
            "activityKey" => 3
        ],
        [
            "startHour" => 4,
            "activityKey" => 3
        ],
        [
            "startHour" => 5,
            "activityKey" => 6
        ],
        [
            "startHour" => 7,
            "activityKey" => 6
        ]
    ],
    "MOT'HER" => [
        [
            "startHour" => 0,
            "activityKey" => 3
        ],
        [
            "startHour" => 1,
            "activityKey" => 3
        ],
        [
            "startHour" => 3,
            "activityKey" => 6
        ],
        [
            "startHour" => 4,
            "activityKey" => 8
        ],
        [
            "startHour" => 5,
            "activityKey" => 8
        ],
        [
            "startHour" => 7,
            "activityKey" => 3
        ]
    ],
    "GOB'KID" => [
        [
            "startHour" => 0,
            "activityKey" => 7
        ],
        [
            "startHour" => 1,
            "activityKey" => 7
        ],
        [
            "startHour" => 3,
            "activityKey" => 7
        ],
        [
            "startHour" => 4,
            "activityKey" => 7
        ],
        [
            "startHour" => 5,
            "activityKey" => 7
        ],
        [
            "startHour" => 7,
            "activityKey" => 7
        ]
    ]
];


function findActivityName($gdata, $hour){
    global $activities;
    $filtered = array_filter($gdata, fn($i) => $i["startHour"] == $hour);
    if (count($filtered) == 0) return "";
    return $activities[$filtered[array_keys($filtered)[0]]["activityKey"]]["name"];
}

/*
function findActivityName($gdata, $hour){
    global $activities;
    foreach($gdata as $i){
        if ($i["startHour"] == $hour) 
            return $activities[$i["activityKey"]]["name"];
    }
    return "";
}*/

function findActivityColor($gdata, $hour){
    global $activities;
    foreach($gdata as $i){
        if ($i["startHour"] == $hour){
            $d = $activities[$i["activityKey"]]["difficulty"];
            if ($d <= 0.5) return "lightgreen";
            if ($d <= 0.8) return "orange";
            return "red";
        }
    }
    return "white";
}

?>

<!DOCTYPE html>
<html lang="hu">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>1. feladat</title>
    <style>
        table, td, th {
            border: 1px black solid;
            border-collapse: collapse;
        }
        td { text-align: center; }
    </style>
</head>

<body>
    <h1>1. feladat</h1>
    <h2>Időbeosztás</h2>
    <table>
        <tr>
            <th>ÓRA</th>
            <?php foreach(array_keys($goblins) as $gname): ?>
                <th> <?= $gname ?> </th>
            <?php endforeach ?>
        </tr>
        <?php for ($hour = 0; $hour < 8; $hour++): ?>
            <tr>
                <td><?= $hour ?></td>
                <?php foreach ($goblins as $gdata): ?>
                    <td style="background-color: <?= findActivityColor($gdata, $hour) ?>;"> <?= findActivityName($gdata, $hour) ?> </td>
                <?php endforeach ?>
            </tr>
        <?php endfor ?>
    </table>
</body>

</html>