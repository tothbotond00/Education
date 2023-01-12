<?php
include_once('storage.php');

class CommentStorage extends Storage {
  public function __construct() {
    parent::__construct(new JsonIO('comments.json'));
  }
}