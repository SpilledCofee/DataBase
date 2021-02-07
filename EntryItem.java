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

        if (quantity <= 0){
            this.quantity = 0;
        }
        else {
            this.quantity = quantity;
        }

        if (wholesale_cost <= 0){
            this.quantity = 0;
        }
        else {
            this.wholesale_cost = wholesale_cost;
        }
        if (sale_price <= 0){
            this.sale_price = 0;
        }
        else {
            this.sale_price= sale_price;
        }
        this.supplier_id = supplier_id;
    }
    public String getProduct_id(){return product_id;}
    public int getQuantity(){return quantity;}
    public double getSale_price() {return sale_price;}

    public double getWholesale_cost() {
        return wholesale_cost;
    }
    public String getSupplier_id() {return supplier_id;}
}//FIN EntryItem


