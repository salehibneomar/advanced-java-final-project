<?php

    class UserFavourite{
        public $id;
        public $dateAdded;
        public $favouriteMaker;
        public $favouriteUser;

        public function __construct($id, $dateAdded, $favouriteMaker, $favouriteUser){
            $this->id=$id;
            $this->dateAdded=$dateAdded;
            $this->favouriteMaker=$favouriteMaker;
            $this->favouriteUser=$favouriteUser;
        }
    }

?>