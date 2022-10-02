import java.util.*;
import java.io.*;

public class Warehouse implements Serializable {
  private productList pList;
  private clientList cList;
  private static Warehouse warehouse;

  private Warehouse() {
    pList = productList.instance();
    cList = clientList.instance();
  }

  public static Warehouse instance() {
    if (warehouse == null){
      return (warehouse = new Warehouse());
    } else {
      return warehouse;
    }
  }
  
  public Product addProduct(int pid) {
    Product product = new Product(pid);
    if (pList.insertProduct(product)) {
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

  public boolean addToClientWishlist(int cid, int pid, int quantity) {
    Client client = cList.getClient(cid);
    Product product = pList.getProduct(pid);
    client.addToWishlist(pid, quantity);
  }


  public void displayClients(){

  }

  public void displayProducts(){

  }

  public void displayClientWishlist(int cid){
    Client client = cList.getClient(cid);
  }

  public Iterator getProducts() {
      return pList.getProduct();
  }

  public Iterator getClients() {
      return cList.getClients();
  }

  public String toString() {
    return pList + "\n" + cList;
  }
}
