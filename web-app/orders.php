<?php
    include_once 'header.php'
?>

<div class="hero-text-box">
    <?php
        // print_r($_SESSION['cart']);
        
        if (empty($_SESSION['orders'])) {
            echo '<div class="user-message-cart">';
                echo '<p>You currently have no past orders!<br>Check out our <a href="./products">products</a>.</p>';
            echo '</div>';
        } 
        
        
    ?>
</div>
  
<?php
    include_once 'footer.php'
?>