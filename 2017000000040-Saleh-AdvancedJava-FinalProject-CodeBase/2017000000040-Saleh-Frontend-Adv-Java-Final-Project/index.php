<?php 
    require 'config/init.php';
    include 'object-models/Login.php';

    if($obj->session_check()){
        header("Location: dashboard.php");
        exit();
    }

    $err="";

    if(isset($_POST['login-btn'])){
        if(empty(trim($_POST['user-name'])) || empty(trim($_POST['user-password']))){
            $err="Empty fields found!";
        }
        else{
            $payload = new Login($_POST['user-name'], $_POST['user-password']);
            $payload = json_encode($payload);
            $url="https://adv-java-final-project-restapi.herokuapp.com/api/user-by-username-password";
            $result = $obj->getSingleResponseByPostingBody($url, $payload);

            if(is_null($result)){
                $err="Invalid login informations!";
            }
            else{
                $_SESSION['suid']=$result['id'];
                $_SESSION['suserName']=$result['userName'];
                header("Location: index.php");
            }
        }   
    }

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Landing Panel | Dating App</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Inter:200,400,600,700,800&display=swap" rel="stylesheet"> 
    <link rel="stylesheet" href="design-files/style.css">
</head>
<body>
    
    <div class="container login-reg-panel">
        <div class="row">
            <div class="col-md-6 offset-md-3">
            <?php if($err){?>
                <div class="alert alert-warning text-center" role="alert">
                   <b><?=$err;?></b> 
                </div>
            <?php } ?>
                <div class="card">
                    <div class="card-header bg-light">
                        <h5 class="text-center">Dating App Login</h5>
                    </div>
                    <div class="card-body">
                        <form action="" method="post">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="User Name" name="user-name" >
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control mt-3" placeholder="Password" name="user-password" >
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary mt-3" name="login-btn">Login&ensp;<i class="fas fa-sign-in-alt"></i></button>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <small><b>Sample User Name: Jhon, Pass: 123456 </b></small> <br>
                        <small>Before creating a new account click on the question button at the right-bottom corner of your screen!</small> <br>
                        <small class="text-muted">Don't have an account?</small>
                        <a href="registration.php"><small>create new account</small></a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="site-info">
        <a href="javascript:void(0)" class="btn btn-sm btn-info"><i class="fas fa-question-circle text-white"></i></a>
    </div>

    <?php include 'includes/footer.php'; ?>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" ></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" ></script>
    <script src="design-files/sweetalert.min.js"></script>

    <script>
        $('.site-info').on('click', function(){
            Swal.fire({
                type: 'info',
                html: 'Designed and Developed by Saleh Ibne Omar <br>'+
                       'RESTapi = Java Spring Boot <br>'+
                       'Database = MySQL <br>'+
                       'CSS framework = Bootstrap 4.5 <br>'+
                       'Plugin = Sweet Alert 2 <br>'+
                       'Frontend handled using PHP 7 (cURL)<br><br>'+
                       'This a complete demo application, so the backend, database, and frontend are uploaded to different free live servers,'+ 
                       'for this reason the site will work slowly! so be patitent!<br><br>'+
                       '<b>Warning: The password field is not encrypted since it is a demo app, so do not give your personal password while creating an account</b>'
            });
        });
    </script>
</body>
</html>