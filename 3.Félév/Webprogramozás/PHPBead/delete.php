<?php
include_once('storage.php');
include_once('userstorage.php');
include_once('commentstorage.php');
include_once('./functions/auth.php');
include_once('./functions/redirect.php');

session_start();
$auth = new Auth(new UserStorage());
$comments = new CommentStorage();


if (count($_GET) > 0 && $auth->is_authenticated() && in_array("admin",$auth->authenticated_user()['roles']) ){
    $comments->delete($_GET['id']);
    $teamid = $_GET['team'];
    redirect("team.php?id=$teamid");
}else  redirect("index.php");
