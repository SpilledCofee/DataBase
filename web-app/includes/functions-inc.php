<?php

function emptyInputSignup($name, $email, $username, $psw, $pswrep) {
    //$result;
    if (empty($name) || empty($email) || empty($username) || empty($psw) || empty($pswrep)) {
        $result = true;
    } else {
        $result = false;
    }
    return $result;
}

function invalidUname($username) {
    //$result;
    if (!preg_match("/^[a-z\d_]{2,20}$/i", $username)) { //check this
        $result = true;
    } else {
        $result = false;
    }
    return $result;
}

function invalidEmail($email) {
    //$result;
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) { 
        $result = true;
    } else {
        $result = false;
    }
    return $result;
}

function pwdMatch($psw, $pswrep) {
    //$result;
    if ($psw !== $pswrep) { 
        $result = true;
    } else {
        $result = false;
    }
    return $result;
}

function unameExists($conn, $username, $email) {
    $sql = "SELECT * FROM users WHERE usersUid = ?
        OR usersEmail = ?;";
    $stmt = mysqli_stmt_init($conn);
    if (!mysqli_stmt_prepare($stmt, $sql)) {
        header("location: ../?error=stmtfailed");
        exit();
    }

    mysqli_stmt_bind_param($stmt, "ss", $username, $email);
    mysqli_stmt_execute($stmt);

    $resultdata = mysqli_stmt_get_result($stmt);

    if ($row = mysqli_fetch_assoc($resultdata)) {
        return $row;
    } else {
        $result = false;
        return $result;
    }

    mysqli_stmt_close($stmt);
}

function createUser($conn, $name, $email, $username, $psw) {
    $sql = "INSERT INTO users (usersName, usersEmail, usersUid, usersPwd)
        VALUES (?, ?, ?, ?);";
    $stmt = mysqli_stmt_init($conn);
    if (!mysqli_stmt_prepare($stmt, $sql)) {
        header("location: ../?error=stmtfailed");
        exit();
    }

    $hashedPsw = password_hash($psw, PASSWORD_DEFAULT);

    mysqli_stmt_bind_param($stmt, "ssss", $username, $email, $username, $hashedPsw);
    mysqli_stmt_execute($stmt);
    mysqli_stmt_close($stmt);
    header("location: ../?error=none");
    exit();

}

function emptyInputLogin($username, $psw) {
    //$result;
    if (empty($username) || empty($psw)) {
        $result = true;
    } else {
        $result = false;
    }
    return $result;
}

function loginUser($conn, $username, $psw) {
    $uidExists = unameExists($conn, $username, $username);

    if ($uidExists === false) {
        header("location: ../?error=wronglogin");
        exit();
    }

    $pswHashed = $uidExists["usersPwd"];
    $checkPwd = password_verify($psw, $pswHashed);

    if ($checkPwd === false) {
        header("location: ../?error=wronglogin");
        exit();
    } else if ($checkPwd === true) {
        session_start();
        $_SESSION["userid"] = $uidExists["usersId"];
        $_SESSION["useruid"] = $uidExists["usersUid"];
        $_SESSION["cart"] = array();
        header("location: ../products");
        exit();
    }
}

function ordersExist($conn, $usersId) {
    $sql = "SELECT * FROM orders WHERE usersId = ?";

    $stmt = mysqli_stmt_init($conn);
    if (!mysqli_stmt_prepare($stmt, $sql)) {
        header("location: ./orders?error=stmtfailed");
        exit();
    }

    mysqli_stmt_bind_param($stmt, "s", $usersId);
    mysqli_stmt_execute($stmt);

    $resultdata = mysqli_stmt_get_result($stmt);

    if ($row = mysqli_fetch_assoc($resultdata)) {
        $result = true;
        return $result;
    } else {
        $result = false;
        return $result;
    }

    mysqli_stmt_close($stmt);
}

function getUserOrders ($conn, $id) {

    $sql = "SELECT * FROM orders WHERE usersId = ?;";

    $stmt = mysqli_stmt_init($conn);

    if (!mysqli_stmt_prepare($stmt, $sql)) {
        header("location: ../orders.php?error=stmtfailed");
        exit();
    }

    mysqli_stmt_bind_param($stmt, "s", $id);
    mysqli_stmt_execute($stmt);

    $resultdata = mysqli_stmt_get_result($stmt);

    while ($row = mysqli_fetch_assoc($resultdata)) {
        $order_id = $row['order_id'];
        $order_date = $row['order_date'];
        $order_quantity = $row['order_quantity'];
        $order_total = $row['order_total'];
        $order_status = $row['order_status'];
        include 'order-table-inc.php';
    }
    mysqli_stmt_close($stmt);
}