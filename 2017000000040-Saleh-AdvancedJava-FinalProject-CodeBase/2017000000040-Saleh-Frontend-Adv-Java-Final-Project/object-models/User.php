<?php 

    Class User{
        public $id;
        public $userName;
        public $password;
        public $fullName;
        public $age;
        public $gender;
        public $country;
        public $city;
        public $areaCode;
        public $bio;
        public $profession;
        public $imagePath;

        public function __construct($id, $userName, $password, $fullName, $age, $gender, 
                                    $country, $city, $areaCode, $bio, $profession, $imagePath){

                                        $this->id=$id;
                                        $this->userName=$userName;
                                        $this->password=$password;
                                        $this->fullName=$fullName;
                                        $this->age=$age;
                                        $this->gender=$gender;
                                        $this->country=$country;
                                        $this->city=$city;
                                        $this->areaCode=$areaCode;
                                        $this->bio=$bio;
                                        $this->profession=$profession;
                                        $this->imagePath=$imagePath;


        }
    }

?>