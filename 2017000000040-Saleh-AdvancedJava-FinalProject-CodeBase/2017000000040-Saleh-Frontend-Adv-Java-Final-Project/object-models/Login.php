<?php 

    Class Login{
        public $userName;
        public $password;

        public function __construct($userName, $password){
            $this->userName=$userName;
            $this->password=$password;
        }
    }

?>