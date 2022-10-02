public class Entry {
    private int quantity;
    private Product product;

    Entry(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public String toString() {  //Assuming product has a method called getName.
        String string = "product: " + product.getName() + " quantity: " + quantity;
        return string;
    }

}