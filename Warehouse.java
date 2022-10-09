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
  
  public Client addClient(String name, String address) {
    Client client = new Client(name, address);
    if (clients.addClient(client)) {
      return (client);
    }
    return null;
  }
  
  public void addToClientWishlist(String cid, String pid, int quantity) {
    Client client = clients.findClient(cid);
    Product product = products.findProduct(pid);

    Entry entry = new Entry(quantity, product);
    WishList wishlist = client.getWishList();
    wishlist.addEntry(entry);
  }


  public void displayClients(){
    clients.displayList();
  }

  public void displayProducts(){
    products.displayList();
  }

  public void displayClientWishlist(String cid){
    Client client = clients.findClient(cid);
    WishList wishlist = client.getWishList();

    wishlist.displayList();
  }

  public Iterator<?> getProducts() {
      return products.getProducts();
  }
  
  public Iterator<?> getClients() {
      return clients.getClients();
  }

  public void processClientWishlist(String cid, Scanner reader){
    Invoice invoice = new Invoice();
    Client client = clients.findClient(cid);

    for(Iterator<?> current = client.getWishList().getWishList(); current.hasNext();){
      Entry entry = (Entry) current.next();
      
      //Display entry to user
      System.out.println("Current entry: " + entry.toString());

      //Giver user 3 options
      System.out.println("Select an option:");
      System.out.println("1 - Leave on wishlist");
      System.out.println("2 - Order product with existing quantity");
      System.out.println("3 - Order product with new quantity");
      int choice = Integer.parseInt(reader.nextLine());

      if(choice == 2){
        invoice.addEntry(entry, client);
        client.getWishList().removeEntry(entry);
      }
      else if(choice == 3){
        System.out.println("Enter new quantity: ");
        int qty = Integer.parseInt(reader.nextLine());
        entry.setQuantity(qty);
        invoice.addEntry(entry, client);
        client.getWishList().removeEntry(entry);
      }
    }

    System.out.println("Here is the finalized invoice:");
    invoice.displayList();
    client.charge(invoice.getTotal());
  }

  public void displayClientsWithBalance(){
    
    for(Iterator<?> current = clients.getClients(); current.hasNext();){
      Client client = (Client) current.next();
      if(client.getAmountDue() > 0 ){
        System.out.println(client.toString() + ", Amount Due: " + client.getAmountDue());
      }
    }
  }

  public void displayProductWaitlist(String pid){
    Product product = products.findProduct(pid);
    Waitlist waitlist = product.getWaitlist();

    waitlist.displayList();
  }
  
  public String toString() {
    return products + "\n" + clients;
  }
}
