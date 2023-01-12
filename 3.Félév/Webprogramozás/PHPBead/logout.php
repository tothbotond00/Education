<?php
include_once('storage.php');
include_once('userstorage.php');
include_once('./functions/auth.php');
include_once('./functions/redirect.php');

session_start();
$auth = new Auth(new UserStorage());

$auth->logout();
redirect('index.php');