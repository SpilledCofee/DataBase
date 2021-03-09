<?php
    include_once 'header.php'
?>
<div class="row">
    <div class="cart-container">
        <?php
            //print_r($_SESSION['cart']);
            
            if (empty($_SESSION['cart'])) {
                echo '<div class="user-message-cart">';
                    echo '<p>Your cart is currently empty!<br>Check out our <a href="./products">products</a>.</p>';
                echo '</div>';
            } else {
                echo '<h2>YOUR SHOPPING CART</h2>';
                echo '<table class="cart-table" cellspacing="5px">';
                echo '<tr style="padding-top: 50px;">';
                echo '<td>Item #</td>';
                echo '<td>PRODUCT</td>';
                echo '<td>PRICE</td>';
                echo '<td>QTY</td>';
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
                        echo $price;
                        echo '</td><td>';
                        echo $cart[$i][1];
                        echo '</td><td valign="middle">';
                        echo '<button class="btn btn-full-bw btn-table">&nbsp&nbspUPDATE&nbsp&nbsp</button>';
                        echo '<button class="btn btn-full-bw btn-table">&nbsp&nbspDELETE&nbsp&nbsp</button>';
                        echo '</td></tr></h2>';
                    

                    mysqli_stmt_close($stmt);
                    $totalprice += $price;
                    $totalquantity += $cart[$i][1];
                }
                echo '<td class="cart-table-top-row" colspan="5"></td>';
                echo '<h2 style="color: white">';
                echo '<tr>';
                echo '<td>';
                echo '</td><td>';
                echo '</td><td>';
                echo $totalprice;
                echo '</td><td>';
                echo $totalquantity;
                echo '</td><td>';
                echo '<button class="btn btn-full-bw btn-table">&nbsp&nbspPLACE ORDER&nbsp&nbsp</button>';
                echo '</td></tr></h2>';
                echo '</table>';
            }
        ?>
    </div>
</div>
<?php
    include_once 'footer.php'
?>