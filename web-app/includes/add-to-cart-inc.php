<?php

    session_start();

    if (!isset($_GET["product_id"]) || !isset($_GET["quantity"])) {
        header("location: ../products?error=badproductinfo");
        exit();
    }

    if ($_GET["quantity"] == 0 || $_GET["quantity"] == '') {
        header("location: ../products?error=selectquantity");
        exit();
    }

    $product = $_GET['product_id'];
    $quant = $_GET['quantity'];
    $newItem = array();
    array_push($newItem, $product, $quant);

    if (!isset($_SESSION['cart'])) {
        $_SESSION['cart'] = array();
    }

    array_push($_SESSION['cart'], $newItem);

    header("location: ../products?addedtosessioncart");
    exit();
    