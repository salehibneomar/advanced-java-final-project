<?php
    date_default_timezone_set('Asia/Dhaka');
    error_reporting(0);
    ob_start();
    session_cache_limiter(false);
    session_start();

    include 'ProjectFunctions.php';

    $obj = new ProjectFunctions();
    
?>