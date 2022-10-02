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
  
  public Client addClient(int cid) {
    Client client = new Client(cid);
    if (cList.insertClient(client)) {
      return (client);
    }
    return null;
  }

  /*
  public boolean addToClientWishlist(int cid, int pid, int quantity) {
    Client client = cList.getClient(cid);
    Product product = pList.getProduct(pid);
    client.addToWishlist(pid, quantity);
  } */


  public void displayClients(){
    cList.displayList();
  }

  public void displayProducts(){
    products.displayList();
  }

  public void displayClientWishlist(int cid){
    //Client client = cList.getClient(cid);
  }

  public Iterator<?> getProducts() {
      return products.getProducts();
  }

  /*
  public Iterator getClients() {
      //return cList.getClients();
  } */

  /*
  public String toString() {
    //return pList + "\n" + cList;
  } */
}
