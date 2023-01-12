<?php

include('informationstorage.php');

if (count($_POST) > 0) {
        $data['fullname'] = $_POST['fullname'];
        $data['phone'] = $_POST['country_code'] . $_POST['phone_number'];
        // Feldolgozás
        $information = new InformationStorage();
        $information->add($data);
        header("Location: index.php?");
        exit();
}

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="" method="post">
        Full name: <input type="text" name="fullname"> <br>
        Phone:
        <select name="country_code">
            <option value="+43">Austria</option>
            <option value="+36">Hungary</option>
        </select>
        <input type="text" name="phone_number">
        <br>
        <button type="submit">Save information</button>
    </form>
</body>
</html>