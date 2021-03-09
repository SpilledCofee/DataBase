<div class="signup-modal hidden">
    <div class="close-signup-modal">&times;</div>
    <div class="signup-form">
        <form method="post" action="includes/signup-inc.php">
            <label for="fname"><b>First Name</b></label>
            <input type="text" placeholder="First name..." name="fname" required>
            <label for="email"><b>Email</b></label>
            <input type="text" placeholder="Email..." name="email" required>
            <label for="uname"><b>Username</b></label>
            <input type="text" placeholder="Username..." name="uname" required>
            <label for="psw"><b>Password</b></label>
            <input type="password" placeholder="Password..." name="psw" required>
            <label for="pswrep"><b>Repeat Password</b></label>
            <input type="password" placeholder="Repeat password..." name="pswrep" required>
            <button type="submit" name= "submit" class="btn modal-btn">Sign Up</button>
        </form>
    </div>
</div>
<div class="signup-message">
    <?php
        if (isset($_GET["error"])){
            if ($_GET["error"] == "emptyinput") {
                echo "Please fill in all fields!";
            } else if ($_GET["error"] == "invaliduname") {
                echo "Please enter valid username!";
            } else if ($_GET["error"] == "invalidemail") {
                echo "Please enter valid email!";
            } else if ($_GET["error"] == "nopassmatch") {
                echo "Passwords do not match! Make sure you type the same password.";
            } else if ($_GET["error"] == "unametaken") {
                echo "That username is taken! Please pick a new username.";
            } else if ($_GET["error"] == "stmtfailed") {
                echo "Something went wrong! Try again.";
            } else if ($_GET["error"] == "none") {
                echo "Successfully signed up!";
            }
        }
    ?>
</div>

<div class="overlay hidden"></div>