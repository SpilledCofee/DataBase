<?php
    session_start();
    if (!isset($_SESSION["useruid"]) || !isset($_SESSION["userid"])) {
        header('Location: ./?error=illegalaccess');
        exit();
    }
?>

<div class="row">
    <?php
        if (isset($_GET['addedtosessioncart'])) {
            echo '<div class="user-message">';
                echo '<div class="close-user-message">&times;</div>';
                echo '<p>Item added to you cart!<br><a class="btn btn-full" style="font-size: 80%;" href="cart">VIEW CART</a></p>';
            echo '</div>';
        } else if (isset($_GET['error'])) { 
            if ($_GET['error'] == 'selectquantity') {
                echo '<div class="user-message">';
                    echo '<div class="close-user-message">&times;</div>';
                    echo '<p>You must choose a quantity to add an item to your cart!</p>';
                echo '</div>';
            }
            if ($_GET['error'] == 'stmtfailed') {
                echo '<div class="user-message">';
                    echo '<div class="close-user-message">&times;</div>';
                    echo '<p>There was a problem retrieving our products!<br>Try again later or contact us.</p>';
                echo '</div>';
            }
        }
    ?>
    <div class="grid-container">
        <?php
            require_once 'includes/dbh-inc.php';

            $sql = "SELECT * FROM products;";

            $resultdata = mysqli_query($conn, $sql);

            while ($row = mysqli_fetch_assoc($resultdata)) {
                $product_title = $row["product_title"];
                $product_desc = $row["product_description"];
                $quantity = $row["quantity"];
                $product_id = $row["product_id"];

                echo '<div class="grid-item">';
                    echo '<img src="img/coffee-171653_640.jpg">';
                    echo '<h4 id="product-title">'.$product_title.'</h4>';
                    echo '<p id="product-description">'.$product_desc.'</p>';
                    echo '<div class="sub-grid-container">';
                        echo '<div>';
                        if ($quantity > 10) {
                            echo '<p class="stock-status">IN STOCK</p>';
                        } else if ($quantity < 10 && $quantity > 0) {
                            echo '<p class="stock-status">IN STOCK</p>';
                            echo '<p class="stock-message">Only '. $quantity . ' left!</p>';
                        } else {
                            echo '<p class="stock-status">OUT OF<br>STOCK</p>';
                        }
                        echo '</div>';
                        echo '<div class="align-right">';
                                if ($quantity > 10) {
                                    echo '<p class="quant-label">QTY:</p>';
                                    echo '<select id="'.$product_id.'quantity" name="quantity">';
                                    echo '<option value="0"></option>';
                                    for ($i = 1; $i <= 10; $i++) {
                                        echo '<option value="'.$i.'">'.$i.'</option>';
                                    }
                                } else if ($quantity < 10 && $quantity > 0) {
                                    echo '<p class="quant-label">QTY:</p>';
                                    echo '<select id="'.$product_id.'quantity" name="quantity">';
                                    echo '<option value="0"></option>';
                                    for ($i = 1; $i <= $quantity; $i++) {
                                        echo '<option value="'.$i.'">'.$i.'</option>';
                                    }
                                } else {
                                    echo '';
                                }
                            echo '</select>';
                        echo '</div>';
                        echo '<button class="btn btn-full-bw grid-item-button" type="submit" onclick="redirect('.$product_id.');">ADD TO CART</button>';
                        echo '<script src="js/redirect.js"></script>';
                    echo '</div>';
                echo '</div>';
            }
        ?>
        
    </div>
</div>