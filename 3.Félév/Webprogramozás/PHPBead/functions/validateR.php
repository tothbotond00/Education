<?php
function validateR($post, &$data, &$errors) {

///USERNAME
if (!isset($post['username'])) {
  $errors['username'] = 'A felhasználónév nincs beállítva!';
}
else if (trim($post['username']) === '') {
  $errors['username'] = 'A felhasználónév kötelező!';
}
else {
  $data['username'] = $post['username'];
}

///EMAIL
if (!isset($post['email'])) {
  $errors['name'] = 'Az email nincs beállítva!';
}
else if (trim($post['email']) === '') {
  $errors['email'] = 'Az email kötelező!';
}
else if (!filter_var($post['email'], FILTER_VALIDATE_EMAIL)){
  $errors['email'] = 'Helytelen email!';
}
else {
      $data['email'] = $post['email'];
}

///PASSWORD
if (!isset($post['password'])) {
  $errors['password'] = 'A jelszó nincs beállítva';
}
else if (trim($post['password']) === '') {
  $errors['password'] = 'A jelszó kötelező!';
}else if (!isset($post['password2']) || trim($post['password2']) === '' ){
  $errors['password2'] = 'A jelszó megerősítése kötelező!';
}else if($post['password2'] != $post['password']){
  $errors['password2'] = 'A két jelszó nem egyezik meg!';
}else {
  $data['password'] = $post['password'];
}


return count($errors) === 0;
}