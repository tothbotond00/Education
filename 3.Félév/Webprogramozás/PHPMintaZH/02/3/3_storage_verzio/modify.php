<?php
    include('storage.php');
    $ts = new Storage(new JsonIO('teachers.json'));
    $id = $_GET['id'];
    $teacher = $ts -> findById($id);
    
    if ($_POST) {
        $teacher['maxcount'] = $_POST['maxcount'];
        $teacher['practices'] = $_POST['practices'];
        $ts -> update($id, $teacher);
        header('Location: index.php');
        exit();
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
    <h1>Teachers - modify data</h1>
    <form action="" method="post">
        Name: <?= $teacher['name'] ?> <br>
        Maxcount: <input type="number" name="maxcount" value="<?= $teacher['maxcount'] ?>"> <br>
        Practices: <br>
        <input type="checkbox" name="practices[]" value="Practice1" <?= in_array('Practice1', $teacher['practices']) ? 'checked' : '' ?>> Practice 1 <br>
        <input type="checkbox" name="practices[]" value="Practice2" <?= in_array('Practice2', $teacher['practices']) ? 'checked' : '' ?>> Practice 2 <br>
        <input type="checkbox" name="practices[]" value="Practice3" <?= in_array('Practice3', $teacher['practices']) ? 'checked' : '' ?>> Practice 3 <br>
        <input type="checkbox" name="practices[]" value="Practice4" <?= in_array('Practice4', $teacher['practices']) ? 'checked' : '' ?>> Practice 4 <br>
        <input type="checkbox" name="practices[]" value="Practice5" <?= in_array('Practice5', $teacher['practices']) ? 'checked' : '' ?>> Practice 5 <br>
        <input type="checkbox" name="practices[]" value="Practice6" <?= in_array('Practice6', $teacher['practices']) ? 'checked' : '' ?>> Practice 6 <br>
        <input type="checkbox" name="practices[]" value="Practice7" <?= in_array('Practice7', $teacher['practices']) ? 'checked' : '' ?>> Practice 7 <br>
        <input type="checkbox" name="practices[]" value="Practice8" <?= in_array('Practice8', $teacher['practices']) ? 'checked' : '' ?>> Practice 8 <br>
        <input type="checkbox" name="practices[]" value="Practice9" <?= in_array('Practice9', $teacher['practices']) ? 'checked' : '' ?>> Practice 9 <br>
        <input type="checkbox" name="practices[]" value="Practice10" <?= in_array('Practice10', $teacher['practices']) ? 'checked' : '' ?>> Practice 10 <br>
        <button type="submit">Save</button>
    </form>
</body>

</html>