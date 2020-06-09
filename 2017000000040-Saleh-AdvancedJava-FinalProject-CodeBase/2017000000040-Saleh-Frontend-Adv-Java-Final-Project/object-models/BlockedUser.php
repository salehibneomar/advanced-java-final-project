<?php

    class BlockedUser{
        public $id;
        public $dateBlocked;
        public $blockedUser;
        public $blockerUser;

        public function __construct($id, $dateBlocked, $blockedUser, $blockerUser){
            $this->id=$id;
            $this->dateBlocked=$dateBlocked;
            $this->blockedUser=$blockedUser;
            $this->blockerUser=$blockerUser;
        }
    }

?>