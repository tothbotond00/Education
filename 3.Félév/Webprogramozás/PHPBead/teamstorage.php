<?php
include_once('storage.php');

class TeamStorage extends Storage {
  public function __construct() {
    parent::__construct(new JsonIO('teams.json'));
  }
}