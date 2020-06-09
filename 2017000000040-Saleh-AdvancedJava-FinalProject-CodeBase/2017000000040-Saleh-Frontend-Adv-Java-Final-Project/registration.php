<?php
    require 'config/init.php';
    include 'object-models/User.php';

    if($obj->session_check()){
        header("Location: dashboard.php");
        exit();
    }
    
    $err=$msg="";

    if(isset($_POST['reg-btn'])){

        $file_size =$_FILES['user-image']['size'];
        if($file_size==0 || empty(trim($_POST['user-name'])) || empty(trim($_POST['user-password'])) || empty(trim($_POST['user-fullname'])) ||
            empty(trim($_POST['user-age'])) || empty(trim($_POST['user-gender'])) || empty(trim($_POST['user-country'])) || empty(trim($_POST['user-city'])) ||
            empty(trim($_POST['user-areacode'])) || empty(trim($_POST['user-profession'])) || empty(trim($_POST['user-bio']))){
                $err="Empty fields found!";
        }
        else if(mb_strlen(trim($_POST['user-bio']))>=240){
            $err="Character limit exceded for biograpghy!";
        }
        else{
            $image_name = $_FILES['user-image']['name'];
            $image_tmp =$_FILES['user-image']['tmp_name'];
            $image_path = "img/".$image_name;

            $url="https://adv-java-final-project-restapi.herokuapp.com/api/user-max-id";
            $id = $obj->getResponse($url)+1;

            

            $userObj = new User($id, $_POST['user-name'], $_POST['user-password'], $_POST['user-fullname'],
                                $_POST['user-age'], $_POST['user-gender'], $_POST['user-country'], $_POST['user-city'],
                                $_POST['user-areacode'], $_POST['user-bio'], $_POST['user-profession'], $image_path);

            $userObj = json_encode($userObj);
           
            $url="https://adv-java-final-project-restapi.herokuapp.com/api/user-create";
            
            $result = $obj->postRequest($url, $userObj);

            if(is_null($result)){
                $err="Error occured! Type a different user name.";
            }
            else{
                $msg=$result.", login with your user name and password!";
                move_uploaded_file($image_tmp, $image_path);
            }

        }
    }
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Panel | Dating App</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Inter:200,400,600,700,800&display=swap" rel="stylesheet"> 
    <link rel="stylesheet" href="design-files/style.css">
</head>
<body>
    
    <div class="container login-reg-panel mb-5">
        <div class="row">
            <div class="col-md-8 offset-md-2">
            <?php if($err){?>
                <div class="alert alert-warning text-center" role="alert">
                   <b> <?=$err;?> </b>
                </div>
            <?php } else if($msg){ ?>
                <div class="alert alert-success text-center" role="alert">
                   <b> <?=$msg;?> </b>
                </div>
            <?php } ?>
                <div class="card">
                    <div class="card-header bg-light">
                        <h5 class="text-center">Account Registration</h5>
                    </div>
                    <div class="card-body">
                        <form action="" method="post" enctype="multipart/form-data">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="User Name" name="user-name">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control mt-3" placeholder="Password" name="user-password" >
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control mt-3" placeholder="Full Name" name="user-fullname">
                            </div>
                            <div class="form-group">
                                <input type="number" class="form-control mt-3" placeholder="Age" min="18" name="user-age">
                            </div>
                            <div class="form-group">
                                <select name="user-gender" class="form-control">
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control mt-3" placeholder="Country" name="user-country">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control mt-3" placeholder="City" name="user-city">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control mt-3" placeholder="Area Code" name="user-areacode">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control mt-3" placeholder="Profession" name="user-profession">
                            </div>
                            <div class="form-group">
                                <label class="text-muted small">Upload profile picture</label>
                                <div class="custom-file">
                                    <input type="file" class="custom-file-input" accept='.jpg,.jpeg,.png' name="user-image">
                                    <label class="custom-file-label">Image...</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <textarea name="user-bio" id="" rows="4" class="form-control" placeholder="Bio"></textarea>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-success mt-3" name="reg-btn">Submit&ensp;<i class="fas fa-check-circle"></i></button>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <small class="text-muted">Already have an account?</small>
                        <a href="index.php"><small>Login</small></a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <?php include 'includes/footer.php'; ?>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" ></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" ></script>
    <script>
    
        $('.custom-file-input').on('change',function(){
            var fileName = $(this).val().substring($(this).val().lastIndexOf("\\")+1);
            $('.custom-file-label').html(fileName);
        });
    
    </script>
</body>
</html>