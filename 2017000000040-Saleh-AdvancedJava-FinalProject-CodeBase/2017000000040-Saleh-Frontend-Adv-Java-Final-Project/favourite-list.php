<?php 
  require 'config/init.php';

  if(!$obj->session_check()){
    header("Location: index.php");
    exit();
  }

  $url = "https://adv-java-final-project-restapi.herokuapp.com/api/user-favourites-by-favourite-maker-id/".$_SESSION['suid'];
  $getAll = $obj->getResponse($url);

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Favourite list</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Inter:200,400,600,700,800&display=swap" rel="stylesheet"> 
    <link rel="stylesheet" href="design-files/style.css">
</head>
<body>

    <?php include 'includes/header.php'; ?>

    <section>
        <div class="main-wrapper mb-5">
            <div class="card">
                <div class="card-body table-con">
                    <?php if(is_null($getAll)){ ?>
                        <h5><span class="badge badge-secondary">0 Records Found</span></h5>
                    <?php } else{ ?>
                    <table class="table table-bordered bg-light">
                        <thead class="">
                            <tr>
                                <th width="5%">#</th>
                                <th width="10%">Image</th>
                                <th width="20%">User name</th>
                                <th width="35%">Full name</th>
                                <th width="15%">Date added</th>
                                <th width="5%">Remove</th>
                                <th width="5%">View</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php $c=1; foreach($getAll as $value){ ?>
                            <tr>
                                <td><?=$c;?></td>
                                <td><img src="<?=$value["favouriteUser"]["imagePath"];?>" alt="" width="50" height="50"></td>
                                <td><?=$value["favouriteUser"]["userName"];?></td>
                                <td><?=$value["favouriteUser"]["fullName"];?></td>
                                <td><?=$value["dateAdded"];?></td>
                                <td><a href="remove-from-favourite-list.php?fvid=<?=$value["id"];?>" class="btn btn-sm btn-danger"><i class="fas fa-heart-broken"></i><span class="d-none"><?=$value["favouriteUser"]["userName"];?></span></a></td>
                                <td><a href="profile.php?uid=<?=$value["favouriteUser"]["id"];?>" class="btn btn-sm btn-info"><i class="fas fa-eye"></i></a></td>
                            </tr>
                            <?php ++$c;} ?>
                        </tbody>
                    </table>
                    <?php } ?>
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
        $('.btn-danger').on('click', function(e){
        e.preventDefault();
        let name = $(this).text();
            let link=$(this).attr('href');
            Swal.fire({
                    title: "Are you sure you want to remove "+name+" from your favourite list?",
                    type: 'warning',
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