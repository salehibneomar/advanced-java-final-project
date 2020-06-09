<?php 
    require 'config/init.php';

    if(isset($_SESSION['delete_message'])){
        $msg = $_SESSION['delete_message'];

        session_unset();
        session_destroy();
    }
    else{
        header("Location: logout.php");
    }

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bye</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Inter:200,400,600,700,800&display=swap" rel="stylesheet"> 
    <link rel="stylesheet" href="design-files/style.css">
</head>
<body>
    
    <?php if($msg){ ?>
    <div class="container mt-5">
        <div class="row mt-5">
            <div class="col-md-6 offset-md-3">
                <div class="card bg-dark">
                    <div class="card-body text-white">
                        <h5>
                            <?=$msg;?>, <br> We are sorry to see you leave!
                        </h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <?php } ?>
    <?php include 'includes/footer.php'; ?>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" ></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" ></script>
</body>
</html>