<?php

include('storage.php');

class InformationStorage extends Storage {
    public function __construct() {
        parent::__construct(new JsonIO('information.json'));
    }
}