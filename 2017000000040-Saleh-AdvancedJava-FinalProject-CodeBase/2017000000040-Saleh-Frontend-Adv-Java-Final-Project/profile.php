<?php
    require 'config/init.php';

    if(!$obj->session_check()){
        header("Location: index.php");
    exit();
    }

    if(isset($_GET['uid'])){
        $url = "https://adv-java-final-project-restapi.herokuapp.com/api/user-by-id/".$_GET['uid'];
        $getAllById = $obj->getResponse($url);
        if(is_null($getAllById)){
            header("Location: dashboard.php");
        }
    }
    else{
        header("Location: dashboard.php");
    }

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile | <?=$getAllById["userName"];?></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Inter:200,400,600,700,800&display=swap" rel="stylesheet"> 
    <link rel="stylesheet" href="design-files/style.css">
</head>
<body>

    <?php include 'includes/header.php'; ?>

    <section>
        <div class="main-wrapper mb-5">
            <div class="row">  
                <div class="col-md-10 offset-md-1">
                <?php if(isset($_SESSION['profile-update-msg'])){ ?>
                    <div class="alert alert-success text-center alert-dismissible fade show" role="alert">
                       <b><?=$_SESSION['profile-update-msg'];?></b>
                       <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                       </button>
                    </div>
                <?php } unset($_SESSION['profile-update-msg']);?>
                    <div class="card">
                        <div class="card-header bg-light">
                            <img src=<?=$getAllById["imagePath"];?> class="mx-auto d-block" alt="" width="250" height="250">
                        </div>
                        <div class="card-body text-center">
                            <h5 class="font-weight-bold"><?=$getAllById["fullName"];?></h5>
                            <p class="profile-details">
                                User Name: <?=$getAllById["userName"];?><br>
                                Age: <?=$getAllById["age"];?><br>
                                Gender: <?=$getAllById["gender"];?><br>
                                Country: <?=$getAllById["country"];?><br>
                                City: <?=$getAllById["city"];?><br>
                                Area Code: <?=$getAllById["areaCode"];?><br>
                                Profession: <?=$getAllById["profession"];?><br>
                                <hr>
                                <b>Biography</b> <br>
                                <?=$getAllById["bio"];?>
                            </p>
                        </div>
                        <div class="card-footer bg-light pb-4">
                            <?php if($_SESSION['suid']==$_GET['uid']){?>
                            <a href="delete-account.php?uid=<?=$_SESSION['suid']?>" class="profile-page-action btn btn-sm btn-danger">Delete account&ensp;<i class="fas fa-user-minus"></i></a>
                            <a href="edit-profile.php?uid=<?=$_SESSION['suid']?>" class="btn btn-sm btn-info">Edit account&ensp;<i class="fas fa-user-edit"></i></a>
                            <?php } else{?>
                            <a href="add-to-block-list.php?uid=<?=$_GET['uid'];?>" class="profile-page-action btn btn-sm btn-secondary">Block user&ensp;<i class="fas fa-stop-circle"></i></a>
                            <?php }?>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <?php include 'includes/footer.php'; ?>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" ></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" ></script>
    <script src="design-files/sweetalert.min.js"></script>
    <script>


        $('.profile-page-action').on('click', function(e){
            e.preventDefault();
            var selectedClassName = $(this).attr('class');
            selectedClassName = selectedClassName.split(" ");
            var link=$(this).attr('href');
            var swalTitle='';
            var swalText='';

            if(selectedClassName[3]=="btn-secondary"){
                let userName = $(this).text();
                swalTitle = 'Are you sure you want to block <?=$getAllById["userName"];?> ?';
            }
            else if(selectedClassName[3]=="btn-danger"){
                swalTitle = 'Are you sure you?'
                swalText = 'You will not be able to revert this action!';
            }

            Swal.fire({
                        title: swalTitle,
                        type: 'warning',
                        text: swalText,
                        showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        confirmButtonText: 'Okay'
                }).then((result) => {
                    if (result.value){
                        $(location).attr('href',link);
                    }
                });

        });

    </script>
</body>
</html>
