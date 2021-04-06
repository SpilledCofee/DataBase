<?php

    $servname = "localhost";
    $dbuser = "fbacon_admin";
    $dbpsw = "SplldCffTstDtbs";
    $dbname = "fbacon_spilledcoffee_test";

    // $servname = "localhost";
    // $dbuser = "root";
    // $dbpsw = "";
    // $dbname = "spilledcoffee_test";

    $conn = mysqli_connect($servname, $dbuser, $dbpsw, $dbname);

    if (!$conn) {
        die("Connection failed: " . mysqli_connect_error());
    }