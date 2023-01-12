<?php

function validateL($post, &$data, &$errors) {
    ///USERNAME
    if (!isset($post['username'])) {
      $errors['username'] = 'A felhasználónév nincs kitöltve!';
    }
    else if (trim($post['username']) === '') {
      $errors['username'] = 'A felhasználónév kötelező!';
    }
    else {
      $data['username'] = $post['username'];
    }
  
    ///PASSWORD
    if (!isset($post['password'])) {
      $errors['password'] = 'A jelszó nincs kitöltve!';
    }
    else if (trim($post['password']) === '') {
      $errors['password'] = 'A jelszó kötelező!';
    }
    else {
      $data['password'] = $post['password'];
    }
    
      return count($errors) === 0;
}