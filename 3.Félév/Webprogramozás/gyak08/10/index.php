<?php

    $r = random_int(0,255);
    $g = random_int(0,255);
    $b = random_int(0,255);
    $backChange = false;
    if ($r > 128 && $g > 128 && $b > 128) $backChange = true;
    $ha = str_pad(dechex($r),2,0,STR_PAD_LEFT);
    $hg = str_pad(dechex($g),2,0,STR_PAD_LEFT);
    $hb = str_pad(dechex($b),2,0,STR_PAD_LEFT);
    $sol = "#" . $ha . $hg . $hb;
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body style="background-color:<?php if ($backChange) echo $sol; else   echo "#cccccc"; ?>">
    <?php echo $sol; ?>
    <br>
    <?php if ($backChange) echo "We used the generated as bg."; else   echo "We didn't use the generated as bg."; ?>
</body>
</html>