<?php 
  require 'config/init.php';

  if(!$obj->session_check()){
    header("Location: index.php");
    exit();
  }

  $url = "https://adv-java-final-project-restapi.herokuapp.com/api/users-by-filtering-blocked-users-and-id/".$_SESSION['suid'];
  $getAllUsers = $obj->getResponse($url);
  
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Inter:200,400,600,700,800&display=swap" rel="stylesheet"> 
    <link rel="stylesheet" href="design-files/style.css">
</head>
<body>
    <?php include 'includes/header.php'; ?>
    <section>
        <div class="main-wrapper">
            <div class="row">
              
              <?php foreach($getAllUsers as $value){ ?>
                  <?php if($value['id']==$_SESSION['suid']){ continue;?>
                  <?php } else{ ?>
                        <div class="col-md-4 mb-5 col-sm-6">
                    <div class="card">
                      <div class="card-body p-0">
                        <img src="<?=$value["imagePath"];?>" class="card-img-top" alt="" height="320">
                        <div class="p-4">
                          <h5 class="card-title"><?=$value["userName"];?></h5>
                          <small>Age:&ensp;<?=$value["age"];?></small> <br>
                          <small>Country:&ensp;<?=$value["country"];?></small> <br>
                          <small>Gender:&ensp;<?=$value["gender"];?></small>
                        </div>
                      </div>
                      <div class="card-footer m-0 p-0">
                          <div class="row">
                              <a class="profile-card-action bg-danger p-2 col-sm-4 d-block text-center text-white" href="add-to-favourite-list.php?uid=<?=$value["id"];?>"><i class="fas fa-heart "></i><span class="d-none"><?=$value["userName"];?></span></a>
                              <a class="p-2 col-sm-4 d-block bg-info text-center text-white" href="profile.php?uid=<?=$value["id"];?>"><i class="fas fa-info-circle "></i></a>
                              <a class="profile-card-action bg-success p-2 col-sm-4 d-block text-center text-white" href=""><i class="fas fa-comment-dots"></i></a>
                          </div>
                      </div>
                    </div>
                  </div>
                <?php }?>
              <?php }?>
        
            </div>
        </div>
    </section>

    <?php include 'includes/footer.php'; ?>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" ></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" ></script>
    <script src="design-files/sweetalert.min.js"></script>
    <script>
        $('.profile-card-action').on('click', function(e){
        e.preventDefault();
        let selectedClassName = $(this).attr('class');
        selectedClassName = selectedClassName.split(" ");

        if(selectedClassName[1]=="bg-success"){
          Swal.fire({
              type: 'error',
              title: 'Sorry',
              text: 'Chat service is not implemented!'
            });
        }
        else if(selectedClassName[1]=="bg-danger"){
          let link=$(this).attr('href');
          let userName = $(this).text();

          Swal.fire({
                    title: "Are you sure you want to add '"+userName+"' to your favourite list?",
                    type: 'info',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Okay'
            }).then((result) => {
                if (result.value){
                    $(location).attr('href',link);
                }
            });
        }

        });
    </script>
</body>
</html>