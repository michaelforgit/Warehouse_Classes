public class ProductDriver {
    public static void main(String[] args) {

    /* Product class testing */
        Product product1 = new Product("Watermelon", 2.00, 50);
        Product product2 = new Product("Apple", 3.00, 25);
        Product product3 = new Product("Peanut", 1.99, 300);
        Product product4 = new Product("Almond", 5.99, 500);
        System.out.println(product1.toString());
        System.out.println(product2.toString());
        /* Individual Product class method testing */
        System.out.println("---");
        System.out.println(product1.getName());
        System.out.println(product1.getProductNumber());
        System.out.println(product1.getSalePrice());
        System.out.println(product1.getStock());
        System.out.println("---");
    /* Product list testing */
        ProductList products = ProductList.instance();
        /* Shows addProduct working */
        products.addProduct(product1);
        products.addProduct(product2);
        products.displayList();
        System.out.println("---");
        /* Shows addProducts working */
        products.addProducts(product3, product4);
        products.displayList();
        System.out.println("---");
        /*
        products.removeProduct("P1");  //Remove product currently not working.
        products.displayList();
        System.out.println("---");
        */
        /* shows findProduct working */
        System.out.println(products.findProduct("P5"));
    }
}
