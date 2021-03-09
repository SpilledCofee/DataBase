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
        header("location: ../products.php");
        exit();
    }
}