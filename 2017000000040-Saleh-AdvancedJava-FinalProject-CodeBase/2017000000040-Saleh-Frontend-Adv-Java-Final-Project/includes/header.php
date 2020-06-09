<?php 
  $url = "https://adv-java-final-project-restapi.herokuapp.com/api/user-unseen-messages-count-by-recipient-id/".$_SESSION['suid'];
  $getUserMessageCount = $obj->getResponse($url); 
  if(is_null($getUserMessageCount)){$getUserMessageCount=0;}
?>
<header class="fixed-top">
        <nav class="navbar navbar-expand-lg navbar-light bg-white">
            <a class="navbar-brand text-danger" href="dashboard.php"><b><i class="fas fa-grin-hearts"></i>&ensp;Dating App</b></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
          
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                  <a class="nav-link" href="#">Messages&ensp;<span class="badge badge-primary badge-pill"><?=$getUserMessageCount;?></span></a>
                </li>
                <form class="form-inline" method="get">
                    <input class="form-control mr-sm-2" type="search" placeholder="Not implemented!" aria-label="Search">
                    <button class="btn btn-success my-2 my-sm-0" type="submit" disabled><i class="fas fa-search"></i></button>
                </form>
              </ul>

              <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <div class="dropdown-toggle bg-info text-white" id="navbarDropdown" role="button" data-toggle="dropdown"><i class="fas fa-user-circle"></i>&ensp;<?=$_SESSION['suserName'];?></div>
                    <div class="dropdown-menu dropdown-menu-right mt-2" aria-labelledby="navbarDropdown">
                      <a class="dropdown-item" href="profile.php?uid=<?=$_SESSION['suid'];?>">Profile</a>
                      <a class="dropdown-item" href="favourite-list.php">Favourite List</a>
                      <a class="dropdown-item" href="block-list.php">Block List</a>
                      <div class="dropdown-divider"></div>
                      <a class="dropdown-item" href="logout.php">Logout</a>
                    </div>
                  </li>
              </ul>
            </div>
          </nav>
    </header>