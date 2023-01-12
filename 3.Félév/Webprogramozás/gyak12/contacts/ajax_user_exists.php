<?php
include_once('storage.php');
include_once('userstorage.php');
include_once('auth.php');

$auth = new Auth(new UserStorage());
$username = $_GET['username'];

echo $auth->user_exists($username) ? 'false' : 'true';