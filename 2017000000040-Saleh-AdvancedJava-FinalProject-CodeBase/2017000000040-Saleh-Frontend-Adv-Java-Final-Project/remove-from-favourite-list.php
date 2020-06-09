<?php
  require 'config/init.php';

  if(!$obj->session_check()){
    header("Location: index.php");
    exit();
  }

  if(isset($_GET['fvid'])){
    $url = "https://adv-java-final-project-restapi.herokuapp.com/api/user-favourite-removal?";
    $param = "favouriteId=".$_GET['fvid']."&"."makerId=".$_SESSION['suid'];
    $result = $obj->deleteRequest($url, $param);
  }

  header("Location: favourite-list.php");

?>