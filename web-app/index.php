<?php
    include_once 'header.php'
?>

<?php
    include_once 'login.php'
?>

<?php
    include_once 'signup.php'
?>

<div class="hero-text-box">
    <h1>Spilled Coffee</h1>
    <h3>Web App Test</h3>
    <?php
        if (isset($_SESSION["useruid"])) {
            echo '<a class="btn btn-full" href="products">VIEW PRODUCTS</a>';
            echo '<a class="btn btn-full" href="#">VIEW YOUR ORDERS</a>';
        } else {
            echo '<a class="btn btn-full show-login-modal" href="#">LOG IN</a>';
            echo '<a class="btn btn-full show-signup-modal" href="#">SIGN UP</a>';
        }
    ?>
</div>
  
<?php
    include_once 'footer.php'
?>