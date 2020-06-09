<?php

require 'config/init.php';

    if(!$obj->session_check()){
    header("Location: index.php");
    exit();
    }

    if(isset($_GET['uid'])){
        if($_GET['uid']==$_SESSION['suid']){
            $url = "https://adv-java-final-project-restapi.herokuapp.com/api/blocked-user-remove-all-by-blocker-id/".$_SESSION['suid'];
            $blockListResult = $obj->deleteRequest($url, "");
        
            $url = "https://adv-java-final-project-restapi.herokuapp.com/api/user-favourite-remove-all-by-favourite-maker-id/".$_SESSION['suid'];
            $favouriteListResult = $obj->deleteRequest($url, "");
        
            $url = "https://adv-java-final-project-restapi.herokuapp.com/api/user-delete-all-by-user/".$_SESSION['suid'];
            $messagesResult = $obj->deleteRequest($url, "");
        
            $url ="https://adv-java-final-project-restapi.herokuapp.com/api/user-account-delete-by-id/".$_SESSION['suid'];
            $user = $obj->deleteRequest($url, "");
            
            $_SESSION['delete_message']=$user;
            header("Location: account-deletion-message.php");
        }
        else{
            header("Location: profile.php?uid=".$_SESSION['suid']);
        }
    }
    else{
        header("Location: profile.php?uid=".$_SESSION['suid']);
    }




?>