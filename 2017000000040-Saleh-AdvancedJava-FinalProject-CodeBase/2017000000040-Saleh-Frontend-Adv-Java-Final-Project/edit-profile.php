<?php
    require 'config/init.php';
    include 'object-models/User.php';

    if(!$obj->session_check()){
        header("Location: index.php");
    exit();
    }

    if(isset($_GET['uid'])){
        if($_GET['uid']==$_SESSION['suid']){
            $url = "https://adv-java-final-project-restapi.herokuapp.com/api/user-by-id/".$_SESSION['suid'];
            $getAllById = $obj->getResponse($url);
        
            $err="";
            $image_path = $getAllById['imagePath'];
            $gender = $getAllById['gender'];
            $pasword = $getAllById['password'];
            $id = $_SESSION['suid'];
            
            if(isset($_POST['profile-update-btn'])){

                $file_size = $_FILES['user-image']['size'];

                if($file_size>0){
                    $image_name = $_FILES['user-image']['name'];
                    $image_tmp =$_FILES['user-image']['tmp_name'];
                    $image_path = "img/".$image_name;
                }

                if(!(empty(trim($_POST['user-password'])))){
                    $pasword = $_POST['user-password'];
                }

                if(empty(trim($_POST['user-fullname'])) ||
                    empty(trim($_POST['user-age'])) || empty(trim($_POST['user-country'])) || empty(trim($_POST['user-city'])) ||
                     empty(trim($_POST['user-areacode'])) || empty(trim($_POST['user-profession'])) || empty(trim($_POST['user-bio']))){
                    $err ="Empty files found!";
                }
                else{

                    $userObj = new User($id, $_POST['user-name'], $pasword, $_POST['user-fullname'],
                    $_POST['user-age'], $gender, $_POST['user-country'], $_POST['user-city'],
                    $_POST['user-areacode'], $_POST['user-bio'], $_POST['user-profession'], $image_path);

                    $userObj = json_encode($userObj);

                    $url="https://adv-java-final-project-restapi.herokuapp.com/api/user-account-update-by-id/".$id;

                    $result = $obj->putRequest($url, $userObj);

                    if(is_null($result)){
                        $err="Error occured!";
                    }
                    else{
                        move_uploaded_file($image_tmp, $image_path);
                        $_SESSION['profile-update-msg']=$result;
                        header("Location: profile.php?uid=".$id);
                    }
                }


            }
        }
        else{
            header("Location: profile.php?uid=".$_SESSION['suid']);
        }
    }
    else{
        header("Location: profile.php?uid=".$_SESSION['suid']);
    }

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit profile | <?=$getAllById["userName"];?></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Inter:200,400,600,700,800&display=swap" rel="stylesheet"> 
    <link rel="stylesheet" href="design-files/style.css">
</head>
<body>
    <?php include 'includes/header.php'; ?>

    <section class="container mb-5">
        <div class="row">
            <div class="col-md-10 offset-md-1">
            <?php if($err){?>
                <div class="alert alert-warning text-center" role="alert">
                   <b> <?=$err;?> </b>
                </div>
            <?php } ?>
                <div class="card">
                    <div class="card-header bg-secondary">
                        <h5 class="text-center text-white">Edit profile</h5>
                    </div>
                    <div class="card-body">
                        <form action="" class="row" method="post" enctype="multipart/form-data">
                            <div class="form-group mt-2 col-md-6">
                            <label class="text-muted small">User Name</label>
                                <input type="text" class="form-control" placeholder="User Name" name="user-name" value="<?=$getAllById["userName"];?>" readonly>
                            </div>
                            <div class="form-group mt-2 col-md-6">
                            <label class="text-muted small">Password</label>
                                <input type="password" class="form-control " name="user-password" >
                            </div>
                            <div class="form-group mt-2 col-md-8">
                            <label class="text-muted small">Full Name</label>
                                <input type="text" class="form-control " placeholder="Full Name" name="user-fullname" value="<?=$getAllById["fullName"];?>">
                            </div>
                            <div class="form-group mt-2 col-md-4">
                            <label class="text-muted small">Age</label>
                                <input type="number" class="form-control " placeholder="Age" min="18" name="user-age" value="<?=$getAllById["age"];?>">
                            </div>
                            <div class="form-group mt-2 col-md-6">
                            <label class="text-muted small">Country</label>
                                <input type="text" class="form-control " placeholder="Country" name="user-country" value="<?=$getAllById["country"];?>">
                            </div>
                            <div class="form-group mt-2 col-md-6">
                            <label class="text-muted small">City</label>
                                <input type="text" class="form-control " placeholder="City" name="user-city" value="<?=$getAllById["city"];?>">
                            </div>
                            <div class="form-group mt-2 col-md-6">
                            <label class="text-muted small">Area Code</label>
                                <input type="text" class="form-control " placeholder="Area Code" name="user-areacode" value="<?=$getAllById["areaCode"];?>">
                            </div>
                            <div class="form-group mt-2 col-md-6">
                            <label class="text-muted small">Profession</label>
                                <input type="text" class="form-control " placeholder="Profession" name="user-profession" value="<?=$getAllById["profession"];?>">
                            </div>
                            <div class="form-group mt-2 col-md-12">
                                <label class="text-muted small">Update profile picture</label>
                                <div class="custom-file">
                                    <input type="file" class="custom-file-input" accept='.jpg,.jpeg,.png' name="user-image">
                                    <label class="custom-file-label">Image...</label>
                                </div>
                            </div>
                            <div class="form-group mt-2 col-md-12">
                            <label class="text-muted small">Biography</label>
                                <textarea name="user-bio" id="" rows="4" class="form-control" placeholder="Bio" ><?=$getAllById["bio"];?></textarea>
                            </div>
                            <div class="form-group col-md-12">
                                <button type="submit" class="btn btn-primary mt-3" name="profile-update-btn">Update&ensp;<i class="fas fa-edit"></i></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>

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
