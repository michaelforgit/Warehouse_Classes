import java.util.*;
import java.io.*;

public class Warehouse implements Serializable {
  private ProductList products;
  private ClientList clients;
  private static Warehouse warehouse;

  private Warehouse() {
    products = ProductList.instance();
    clients = ClientList.instance();
  }

  public static Warehouse instance() {
    if (warehouse == null){
      return (warehouse = new Warehouse());
    } else {
      return warehouse;
    }
  }
  
  public Product addProduct(String name, double salePrice, int inStock) {
    Product product = new Product(name, salePrice, inStock);
    if (products.addProduct(product)) {
      return (product);
    }
    return null;
  }
  
  public Client addClient(String name, String phone, String address) {
    Client client = new Client(name, phone, address);
    if (clients.insertClient(client)) {
      return (client);
    }
    return null;
  }

  
  public void addToClientWishlist(String cid, String pid, int quantity) {
    Client client = clients.findClient(cid);
    Product product = products.findProduct(pid);

    Entry entry = new Entry(quantity, product);
    WishList wishlist = client.getWishList();
    wishlist.insertEntry(entry);
  }


  public void displayClients(){
    clients.displayList();
  }

  public void displayProducts(){
    products.displayList();
  }

  public void displayClientWishlist(String cid){
    Client client = clients.findClient(cid);
    WishList wishList = client.getWishList();

    wishList.displayList();
  }

  public Iterator<?> getProducts() {
      return products.getProducts();
  }

  
  public Iterator<?> getClients() {
      return clients.getClients();
  }

  
  public String toString() {
    return products + "\n" + clients;
  }
}
