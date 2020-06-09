<?php
  require 'config/init.php';
  include 'object-models/BlockedUser.php';
  include 'object-models/User.php';

  if(!$obj->session_check()){
    header("Location: index.php");
    exit();
  }

  if(isset($_GET['uid'])){
    $url = "https://adv-java-final-project-restapi.herokuapp.com/api/user-by-id/".$_GET['uid'];
    $userTobeBlocked= $obj->getResponse($url);
    $url = "https://adv-java-final-project-restapi.herokuapp.com/api/user-by-id/".$_SESSION['suid'];
    $userWantsToBlock= $obj->getResponse($url);
    $url = "https://adv-java-final-project-restapi.herokuapp.com/api/blocked-users-max-id";
    $id= $obj->getResponse($url);

    $blockedUser = new User($userTobeBlocked["id"], $userTobeBlocked["userName"], $userTobeBlocked["password"], $userTobeBlocked["fullName"],
                            $userTobeBlocked["age"], $userTobeBlocked["gender"], $userTobeBlocked["country"], $userTobeBlocked["city"],
                            $userTobeBlocked["areaCode"], $userTobeBlocked["bio"], $userTobeBlocked["profession"], $userTobeBlocked["imagePath"]);

    $blockerUser = new User($userWantsToBlock["id"], $userWantsToBlock["userName"], $userWantsToBlock["password"], $userWantsToBlock["fullName"],
                            $userWantsToBlock["age"], $userWantsToBlock["gender"], $userWantsToBlock["country"], $userWantsToBlock["city"],
                            $userWantsToBlock["areaCode"], $userWantsToBlock["bio"], $userWantsToBlock["profession"], $userWantsToBlock["imagePath"]);  
                            

    $blockedUserObj = new BlockedUser($id+1, date("Y-m-d"), $blockedUser, $blockerUser);
    
    $blockedUserObj = json_encode($blockedUserObj);
    
    $url = "https://adv-java-final-project-restapi.herokuapp.com/api/blocked-user-create";

    $result = $obj->postRequest($url, $blockedUserObj);

    header("Location: block-list.php");

  }
  else{
      header("Location: dashboard.php");
  }




?>