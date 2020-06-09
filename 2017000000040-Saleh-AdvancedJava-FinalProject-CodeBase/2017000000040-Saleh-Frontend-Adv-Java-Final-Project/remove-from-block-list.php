<?php
  require 'config/init.php';

  if(!$obj->session_check()){
    header("Location: index.php");
    exit();
  }

  if(isset($_GET['blid'])){
    $url = "https://adv-java-final-project-restapi.herokuapp.com/api/blocked-user-removal?";
    $param = "blockId=".$_GET['blid']."&"."blockerId=".$_SESSION['suid'];
    $result = $obj->deleteRequest($url, $param);
    print_r($result);
  }

  
  header("Location: block-list.php");


?>