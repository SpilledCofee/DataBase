public class EntryItem {

    private String product_id;
    private int quantity;
    private double wholesale_cost;
    private double sale_price;
    private String supplier_id;

    private static int MIN_QUANTITY = 0;
    private static int PRODUCT_ID_LEGNTH = 12;
    private static int SUPPLIER_ID_LEGNTH = 8;

    public EntryItem( String product_id, int quantity, double wholesale_cost, double sale_price, String supplier_id) {
        this.product_id = product_id;
        this.quantity = quantity;
        this.wholesale_cost = wholesale_cost;
        this.sale_price = sale_price;
        this.supplier_id = supplier_id;
    }
}


