<?php
  require 'config/init.php';
  include 'object-models/UserFavourite.php';
  include 'object-models/User.php';

  if(!$obj->session_check()){
    header("Location: index.php");
    exit();
  }

  if(isset($_GET['uid'])){
    $url = "https://adv-java-final-project-restapi.herokuapp.com/api/user-by-id/".$_GET['uid'];
    $userTobeFavourite= $obj->getResponse($url);
    $url = "https://adv-java-final-project-restapi.herokuapp.com/api/user-by-id/".$_SESSION['suid'];
    $userWantsToMakeFavourite= $obj->getResponse($url);
    $url = "https://adv-java-final-project-restapi.herokuapp.com/api/user-favourites-max-id";
    $id= $obj->getResponse($url);

    $favouriteMaker = new User($userWantsToMakeFavourite["id"], $userWantsToMakeFavourite["userName"], $userWantsToMakeFavourite["password"], $userWantsToMakeFavourite["fullName"],
                            $userWantsToMakeFavourite["age"], $userWantsToMakeFavourite["gender"], $userWantsToMakeFavourite["country"], $userWantsToMakeFavourite["city"],
                            $userWantsToMakeFavourite["areaCode"], $userWantsToMakeFavourite["bio"], $userWantsToMakeFavourite["profession"], $userWantsToMakeFavourite["imagePath"]);

    $favouriteUser = new User($userTobeFavourite["id"], $userTobeFavourite["userName"], $userTobeFavourite["password"], $userTobeFavourite["fullName"],
                            $userTobeFavourite["age"], $userTobeFavourite["gender"], $userTobeFavourite["country"], $userTobeFavourite["city"],
                            $userTobeFavourite["areaCode"], $userTobeFavourite["bio"], $userTobeFavourite["profession"], $userTobeFavourite["imagePath"]);  
                            

    $userFavouriteObj = new UserFavourite($id+1, date("Y-m-d"), $favouriteMaker, $favouriteUser);
    
    $userFavouriteObj = json_encode($userFavouriteObj);
    
    $url = "https://adv-java-final-project-restapi.herokuapp.com/api/user-favourite-create";

    $result = $obj->postRequest($url, $userFavouriteObj);

    header("Location: favourite-list.php");

  }
  else{
      header("Location: dashboard.php");
  }


?>