<?php
    include_once 'header.php'
?>
<div class="row">
    <div class="cart-container">
        <?php
            if (!isset($_SESSION['useruid'])) {
                header('Location: ./?error=illegalaccess');
            }
            if (isset($_GET['error'])) {
                if ($_GET['error'] === 'stmtfailed') {
                echo '<div class="user-message">';
                echo '<div class="close-user-message">&times;</div>';
                    echo '<p>There was a problem getting the information. Try again later.</p>';
                echo '</div>';
                }
            }
            if (isset($_GET['removedfromcart'])) {
                echo '<div class="user-message">';
                echo '<div class="close-user-message">&times;</div>';
                    echo '<p>Item has been removed from your cart!</p>';
                echo '</div>';
            }
            if (empty($_SESSION['cart'])) {
                echo '<div class="user-message">';
                    echo '<p>Your cart is currently empty!<br>Check out our <a href="./products">products</a>.</p>';
                echo '</div>';
            } else {
                echo '<h2>YOUR SHOPPING CART</h2>';
                echo '<table class="cart-table" cellspacing="5px">';
                echo '<tr style="padding-top: 50px;">';
                echo '<td>ITEM #</td>';
                echo '<td style="width:50%;">PRODUCT</td>';
                echo '<td style="width:10%;">QTY</td>';
                echo '<td style="width:10%;">PRICE</td>';
                echo '</tr>';
                echo '<tr>';
                echo '<td class="cart-table-top-row" colspan="5"></td>';
                echo '</tr>';
                $cart = $_SESSION['cart'];
                $totalprice = 0;
                $totalquantity = 0;
                for ($i = 0; $i < count($cart); $i++) {
                        
                    require_once 'includes/dbh-inc.php';

                    $sql = "SELECT * FROM products WHERE product_id = ?;";

                    $stmt = mysqli_stmt_init($conn);
                    if (!mysqli_stmt_prepare($stmt, $sql)) {
                        header("location: ./cart?error=stmtfailed");
                        exit();
                    }

                    mysqli_stmt_bind_param($stmt, "i", $cart[$i][0]);
                    mysqli_stmt_execute($stmt);

                    $resultdata = mysqli_stmt_get_result($stmt);

                    $row = mysqli_fetch_assoc($resultdata);

                    $product_title = $row["product_title"];
                    $product_desc = $row["product_description"];
                    $price = $row["price"];

                    echo '<h2 style="color: white">';
                    echo '<tr>';
                    echo '<td>';
                    echo $i+1;
                    echo '</td><td>';
                    echo $product_title;
                    echo '</td><td>';
                    echo $cart[$i][1];
                    echo '</td><td>';
                    echo number_format($price*$cart[$i][1], 2);
                    echo '</td><td valign="middle">';
                    // echo '<button class="btn btn-full-bw btn-table">';
                    // echo '&nbsp&nbspUPDATE&nbsp&nbsp</button>';
                    echo '<button class="btn btn-full-bw btn-table" type="submit"';
                        echo 'onclick="delRedirect('.$i.')">';
                    echo '<script src="js/delRedirect.js"></script>';
                    echo '&nbsp&nbspDELETE&nbsp&nbsp</button>';
                    echo '</td></tr></h2>';

                    mysqli_stmt_close($stmt);
                    $totalprice += $price*$cart[$i][1];
                    $totalquantity += $cart[$i][1];
                }
                echo '<td class="cart-table-top-row" colspan="5"></td>';
                echo '<h2 style="color: white">';
                echo '<tr>';
                echo '<td>';
                echo '</td><td>';
                echo '</td><td>';
                echo $totalquantity;
                echo '</td><td>';
                echo number_format($totalprice, 2);
                echo '</td><td style="width: 15%"></td><tr><td align="center" colspan="5">';
                echo '<a href="checkout"><button class="btn btn-full-bw btn-table"><h2>&nbsp&nbspCHECKOUT&nbsp&nbsp</h3></button>';
                echo '</td></tr></h2>';
                echo '</table>';
            }
        ?>
    </div>
</div>

<?php
    include_once 'footer.php'
?>