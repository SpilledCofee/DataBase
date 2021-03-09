<div class="row">
    <?php
        if (isset($_GET['addedtosessioncart'])) {
            echo '<div class="user-message-products">';
                echo '<div class="close-user-message-products">&times;</div>';
                echo '<p>Item added to you cart!</p>';
            echo '</div>';
        } else if (isset($_GET['error'])) { 
            if ($_GET['error'] == 'selectquantity') {
                echo '<div class="user-message-products">';
                    echo '<div class="close-user-message-products">&times;</div>';
                    echo '<p>You must choose a quantity to add item to your cart!</p>';
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
                                echo '<input class="quant-input" type="number" id="'.$product_id.'quantity" name="quantity" min="0" max="10">';
                            } else if ($quantity < 10 && $quantity > 0) {
                                echo '<p class="quant-label">QTY:</p>';
                                echo '<input class="quant-input" type="number" id="'.$product_id.'quantity" name="quantity" min="0" max="'.$quantity.'">';
                            } else {
                                echo '';
                            }
                        echo '</div>';
                        echo '<button class="btn btn-full-bw grid-item-button" type="submit" onclick="redirect('.$product_id.');">ADD TO CART</button>';
                        echo '<script src="js/redirect.js"></script>';
                    echo '</div>';
                echo '</div>';
            }
        ?>
        
    </div>
</div>