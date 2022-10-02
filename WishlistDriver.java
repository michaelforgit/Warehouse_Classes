public class WishlistDriver {
    public static void main(String[] args) {

        Client client1 = new Client("Bob", "231 Elmo Drive");

        Product product1 = new Product("Apple", 2.00, 500);
        Product product2 = new Product("Grape", 2.00, 50);
        Product product3 = new Product("Cashew", 1.99, 20);
        Entry entry1 = new Entry(20, product1);
        Entry entry2 = new Entry(30, product2);
        Entry entry3 = new Entry(50, product2);
        client1.getWishList().addEntry(entry1);
        client1.getWishList().addEntry(entry2);
        client1.getWishList().displayList();
        System.out.println(client1.getWishList().findEntry(product3));  //Shows findEntry returning null if the product is not in the client's wishlist.
        client1.getWishList().addEntry(entry3);  //Adds an entry of the same product already in the wishlist and overrides the quantity.
        client1.getWishList().displayList();
    }
}
