<?php
    $image = $_POST['image'];
    $filedir = $_POST['filedir'];
    $name = time();

    $image = str_replace('data:image/png;base64,', '', $image);
    $decoded = base64_decode($image);

    file_put_contents($filedir . "/" . $name . ".png", $decoded, LOCK_EX);

   echo $image;
?>