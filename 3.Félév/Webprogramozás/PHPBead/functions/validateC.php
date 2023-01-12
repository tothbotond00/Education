<?php

function validateC($post, &$data, &$errors){
    //TEXT
    if (!isset($post['comment'])) {
        $errors['comment'] = 'A hozzászólás mező nincs kitöltve!';
      }
      else if (trim($post['comment']) === '') {
        $errors['comment'] = 'A hozzászólás mező kötelező!';
      }
      else {
        $data['comment'] = $post['comment'];
      }
      return count($errors) === 0;
}