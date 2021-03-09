<?php
    session_start();
?>

<!DOCTYPE html>
<html>
    <head>
        <!-- <link rel="stylesheet" type="text/css" href="css/normalize.css"> -->
        <link rel="stylesheet" type="text/css" href="css/grid.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link href='http://fonts.googleapis.com/css?family=Lato:100,300,400,300italic' rel="stylesheet" type='text/css'>
        <title>Spilled Coffee</title>
    </head>  
    <body>
        <header>
            <nav>
                <div class="row">
                    <a href="./"><img src="img/coffee_cup_logo_small_invert.png" alt="spilled_coffee_logo" class="logo"></a>
                    <ul class="main-nav">
                        
                        <?php
                            if (isset($_SESSION["useruid"])) {
                                echo '<li><a href="cart.php"><img src="img/cart_100.png" style="height:25px" class="cart-icon"></a></li>';
                                echo '<li><a href="includes/logout-inc.php" class="">Log out</a></li>';
                            } else {
                                echo '<li><a class="show-login-modal-link">Log in</button></li>';
                            }
                        ?>                     
                    </ul>
                </div>
            </nav>
        </header>
        <div class="wrapper">