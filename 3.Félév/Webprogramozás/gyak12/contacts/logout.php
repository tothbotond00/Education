<?php
include_once('storage.php');
include_once('userstorage.php');
include_once('auth.php');

function redirect($page) {
  header("Location: ${page}");
  exit();
}

session_start();
$auth = new Auth(new UserStorage());

$auth->logout();
redirect('login.php');
