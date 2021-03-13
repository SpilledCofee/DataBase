<?php
    include_once 'header-ns.php'
?>

<div class="row">
<?php
    if (isset($_GET['error'])) {
        if ($_GET['error'] === 'wronglogin') {
            echo '<div class="user-message">';
                echo '<div class="close-user-message">&times;</div>';
                echo '<p>Your login information was incorrect or does not exist! Please try again.</p>';
            echo '</div>';
        } else if ($_GET['error'] === 'emptyinput') {
            echo '<div class="user-message">';
                echo '<div class="close-user-message">&times;</div>';
                echo '<p>Please fill in all fields!</p>';
            echo '</div>';
        } else if ($_GET['error'] === 'invaliduname') {
            echo '<div class="user-message">';
                echo '<div class="close-user-message">&times;</div>';
                echo '<p>Please enter valid username!</p>';
            echo '</div>';
        } else if ($_GET['error'] === 'nopassmatch') {
            echo '<div class="user-message">';
                echo '<div class="close-user-message">&times;</div>';
                echo '<p>Passwords do not match! Make sure you type the same password.</p>';
            echo '</div>';
        } else if ($_GET['error'] === 'unametaken') {
            echo '<div class="user-message">';
                echo '<div class="close-user-message">&times;</div>';
                echo '<p>That username is taken! Please pick a new username.</p>';
            echo '</div>';
        } else if ($_GET['error'] === 'stmtfailed') {
            echo '<div class="user-message">';
                echo '<div class="close-user-message">&times;</div>';
                echo '<p>Something went wrong! Try again or contact us.</p>';
            echo '</div>';
        } else if ($_GET['error'] === 'none') {
            echo '<div class="user-message">';
                echo '<div class="close-user-message">&times;</div>';
                echo '<p>Successfully signed up!</p>';
            echo '</div>';
        } else if ($_GET['error'] === 'illegalaccess') {
            echo '<div class="user-message">';
                echo '<div class="close-user-message">&times;</div>';
                echo '<p>You must be logged in to access that page!</p>';
            echo '</div>';
        }
    }
?>
</div>

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
            echo '<a class="btn btn-full" href="cart">VIEW CART</a>';
            echo '<a class="btn btn-full" href="orders">VIEW PAST ORDERS</a>';
        } else {
            echo '<button class="btn btn-full show-login-modal">LOG IN</button>';
            echo '<button class="btn btn-full show-signup-modal">SIGN UP</button>';
        }
    ?>
</div>
  
<?php
    include_once 'footer.php'
?>