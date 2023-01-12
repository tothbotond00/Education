<?php
include_once('storage.php');

class MatchStorage extends Storage {
  public function __construct() {
    parent::__construct(new JsonIO('matches.json'));
  }
}