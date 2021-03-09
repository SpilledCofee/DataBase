<div class="login-modal hidden">
    <div class="close-login-modal">&times;</div>
    <div class="login-form">
        <form method="post" action="includes/login-inc.php">
            <label for="uname"><b>Username</b></label>
            <input type="text" placeholder="Username/Email..." name="uid" required>
            <label for="psw"><b>Password</b></label>
            <input type="password" placeholder="Password..." name="psw" required>
            <button type="submit" name="submit" class="btn modal-btn">Log in</button>
        </form>
    </div>
</div>
<?php
    if (isset($_GET["error"])){
        if ($_GET["error"] == "emptyinput") {
            echo "Please fill in all fields!";
        } else if ($_GET["error"] == "wronglogin") {
            echo "Incorrect login information!";
        }
    }
?>
<div class="overlay hidden"></div>