<?php
    include_once 'header.php'
?>

<?php
    if (!isset($_SESSION["useruid"]) && !isset($_SESSION["userid"])) {
        header('Location: index.php?error=illegalaccess');
        exit();
    }
?>

<?php
    include_once 'products-grid.php'
?>

<?php
    include_once 'footer.php'
?>