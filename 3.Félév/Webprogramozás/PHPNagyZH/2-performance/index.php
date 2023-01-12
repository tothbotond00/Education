<?php

function validate($get, &$data, &$errors) {
    $check = false;

    //Num Children
    if(!isset($get['num_children'])){
        $errors['num_children'] ="Num of children is required!";
    }else if (trim($get['num_children']) === ''){
        $errors['num_children'] = "Num of children is mandatory!";
    }else if (!is_numeric($get['num_children'])){
        $errors['num_children'] = "Num of children must be a number!";
    }else if(preg_match('/^\d+$/',$get['num_children']) == false) {
        $errors['num_children'] = "Num of children must be an integer!";
    }else {
        if(intval($get['num_children']) <= 0){
            $errors['num_children'] = 'Num of children must be positive!';
        }else {
            $data['num_children'] = $get['num_children'];
            $check = true;
        }
    }

    //Children names
    if(!isset($get['children_names'])){
        $errors['children_names'] ="Children names are required!";
    }else if (trim($get['children_names']) === ''){
        $errors['children_names'] = "Children names are mandatory!";
    }else if ($check){
        if (count(strpos_all($get['children_names'], ",")) != (intval($get['num_children'])-1) ){
        $errors['children_names'] ="Children names are not matches with number of children!";
        }else{
            $data['children_names'] = $get['children_names'];
        }
    }

    //Music url
    if(!isset($get['music_url'])){
        $errors['music_url'] ="Music url is required!";
    }else if (trim($get['music_url']) === ''){
        $errors['music_url'] = "Music url is mandatory!";
    }else if (filter_var($get['music_url'], FILTER_VALIDATE_URL)) { 
        $data['music_url'] = $get['music_url'];
    }else {
        $errors['music_url'] ="Music url must be vaild!";
    }

    //Performance type
    if(!isset($get['performance_type'])){
        $errors['performance_type'] ="Performance type is required!";
    }else if (trim($get['performance_type']) === ''){
        $errors['performance_type'] = "Performance type is mandatory!";
    }else if ($get['performance_type'] == "poem" || $get['performance_type'] == "song" ){
        $data['performance_type'] = $get['performance_type'];
    }else {
        $errors['performance_type'] = "Performance type must be song or poem!";
    }

    //Ready
    if(!isset($get['ready'])){
        $errors['ready'] ="You must be ready to submit!";
    }else if (trim($get['ready']) === ''){
        $errors['ready'] = "You must tick ready to submit!";
    }else if ($get['ready'] == "on"){
        $data['ready'] = $get['ready'];
    }else {
        $errors['ready'] = "Error with the ready button!";
    }

    return count($errors) === 0;
}

// main
$good = false;
$data = [];
$errors = [];
if (count($_GET) > 0) {
    if (validate($_GET, $data, $errors)) {
        $good = true;
    }else{
    }
}

//helper
function strpos_all($haystack, $needle) {
    $offset = 0;
    $allpos = array();
    while (($pos = strpos($haystack, $needle, $offset)) !== FALSE) {
        $offset   = $pos + 1;
        $allpos[] = $pos;
    }
    return $allpos;
}

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="index.css">
    <title>Performance</title>
    <style>
        form span {
            color : red;
            font-size : smaller;
        }
    </style>
</head>
<body>
    <h1>Performance</h1>
    <form action="index.php" method="get" novalidate>
        <label for="i1">Number of children:</label> <input type="text" name="num_children" id="i1" value="<?= $_GET['num_children'] ?? '' ?>">
        <?php if (isset($errors['num_children'])) : ?>
                        <span> <?= $errors['num_children'] ?> </span>
        <?php endif ?><br>
        <label for="i2">Children's names:</label> <input type="text" name="children_names" id="i2" value="<?= $_GET['children_names'] ?? '' ?>">
        <?php if (isset($errors['children_names'])) : ?>
                        <span> <?= $errors['children_names'] ?> </span>
        <?php endif ?><br>
        <label for="i3">URL of music to be played:</label> <input type="text" name="music_url" id="i3" value="<?= $_GET['music_url'] ?? '' ?>">
        <?php if (isset($errors['music_url'])) : ?>
                        <span> <?= $errors['music_url'] ?> </span>
        <?php endif ?><br>
        <label for="i4">Performance type:</label> <input type="text" name="performance_type" id="i4" value="<?= $_GET['performance_type'] ?? '' ?>">
        <?php if (isset($errors['performance_type'])) : ?>
                        <span> <?= $errors['performance_type'] ?> </span>
        <?php endif ?><br>
        <input type="checkbox" name="ready" id="i5" <?= isset($_GET['ready']) && $_GET['ready'] == 'on' ? 'checked' : '' ?>><label for="i5" >Everyone is ready</label>
        <?php if (isset($errors['ready'])) : ?>
                        <span> <?= $errors['ready'] ?> </span>
        <?php endif ?><br>
        <button type="submit">Submit</button>
    </form>

    
    <?php if($good) : ?>
        <div class="merry">🎄 MERRY CHRISTMAS AND HAPPY NEW YEAR! 🎄</div>
    <?php endif ?>

    <h2>Test cases</h2>
        <a href="index.php?num_children=&children_names=&music_url=&performance_type=">num_children=&children_names=&music_url=&performance_type=</a><br>
        <a href="index.php?num_children=n&children_names=&music_url=&performance_type=">num_children=n&children_names=&music_url=&performance_type=</a><br>
        <a href="index.php?num_children=6.7&children_names=&music_url=&performance_type=">num_children=6.7&children_names=&music_url=&performance_type=</a><br>
        <a href="index.php?num_children=0&children_names=&music_url=&performance_type=">num_children=0&children_names=&music_url=&performance_type=</a><br>
        <a href="index.php?num_children=3&children_names=Adam%2CBarbara&music_url=&performance_type=">num_children=3&children_names=Adam%2CBarbara&music_url=&performance_type=</a><br>
        <a href="index.php?num_children=3&children_names=Adam%2CBarbara%2CChloe&music_url=&performance_type=">num_children=3&children_names=Adam%2CBarbara%2CChloe&music_url=&performance_type=</a><br>
        <a href="index.php?num_children=3&children_names=Adam%2CBarbara%2CChloe&music_url=5c3ezwen&performance_type=">num_children=3&children_names=Adam%2CBarbara%2CChloe&music_url=5c3ezwen&performance_type=</a><br>
        <a href="index.php?num_children=3&children_names=Adam%2CBarbara%2CChloe&music_url=http%3A%2F%2Ftinyurl.com%2F5c3ezwen&performance_type=">num_children=3&children_names=Adam%2CBarbara%2CChloe&music_url=http%3A%2F%2Ftinyurl.com%2F5c3ezwen&performance_type=</a><br>
        <a href="index.php?num_children=3&children_names=Adam%2CBarbara%2CChloe&music_url=http%3A%2F%2Ftinyurl.com%2F5c3ezwen&performance_type=good">num_children=3&children_names=Adam%2CBarbara%2CChloe&music_url=http%3A%2F%2Ftinyurl.com%2F5c3ezwen&performance_type=good</a><br>
        <a href="index.php?num_children=3&children_names=Adam%2CBarbara%2CChloe&music_url=http%3A%2F%2Ftinyurl.com%2F5c3ezwen&performance_type=song">num_children=3&children_names=Adam%2CBarbara%2CChloe&music_url=http%3A%2F%2Ftinyurl.com%2F5c3ezwen&performance_type=song</a><br>
        <a href="index.php?num_children=3&children_names=Adam%2CBarbara%2CChloe&music_url=http%3A%2F%2Ftinyurl.com%2F5c3ezwen&performance_type=song&ready=on">num_children=3&children_names=Adam%2CBarbara%2CChloe&music_url=http%3A%2F%2Ftinyurl.com%2F5c3ezwen&performance_type=song&ready=on</a><br>
</body>
</html>
