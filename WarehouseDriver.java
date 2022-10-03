import java.util.*;

public class WarehouseDriver {
    public static void main(String args[])
    {
        int choice = 1;
        Scanner reader = new Scanner(System.in);
        ClientList clientlist = ClientList.instance();
        ProductList productlist = ProductList.instance();

        System.out.println("Welcome to the Warehouse system.");
        
        while(choice != 7)
        {
            System.out.println("Enter your choice:");
            System.out.println("1 - Add client:");
            System.out.println("2 - Add product");
            System.out.println("3 - Add product to a client's wishlist");
            System.out.println("4 - Display all clients with details");
            System.out.println("5 - Display all products with details");
            System.out.println("6 - Display a client's wishlist");
            System.out.println("7 - Exit the system");
            
            choice = Integer.parseInt(reader.nextLine());

            if(choice == 1){
                System.out.println("Enter client name:");
                String name = reader.nextLine();
                System.out.println("Enter client address:");
                String address = reader.nextLine();

                Client client = new Client(name, address);
                clientlist.addClient(client);

                System.out.println("Client created. Here is the client information:");
                System.out.println(client.toString());
            }
            else if(choice == 2){
                System.out.println("Enter product name:");
                String name = reader.nextLine();
                System.out.println("Enter product price:");
                double price = Double.parseDouble(reader.nextLine());
                
                System.out.println("Enter amount of product in stock:");
                int inStock = Integer.parseInt(reader.nextLine());

                Product product = new Product(name, price, inStock);
                productlist.addProduct(product);

                System.out.println("Product created. Here is the product information:");
                System.out.println(product.toString());
            }
            else if(choice == 3){
                System.out.println("Enter client ID:");
                String cid = reader.nextLine();
                Client client = clientlist.findClient(cid);

                System.out.println("Enter product ID:");
                String pid = reader.nextLine();
                Product product = productlist.findProduct(pid);
                System.out.println("product to be added: " + product.toString());

                System.out.println("Enter quantity of product to add to wishlist:");
                int quantity = Integer.parseInt(reader.nextLine());
                Entry entry = new Entry(quantity, product);
                
                System.out.println("The following entry will be added: " + entry.toString());

                client.getWishList().addEntry(entry);

                System.out.println("The product was added to the client's wishlist.");
            }
            else if(choice == 4){
                clientlist.displayList();
            }
            else if(choice == 5){
                productlist.displayList();
            }
            else if(choice == 6){
                System.out.println("Enter client ID:");
                String cid = reader.nextLine();

                Client client = clientlist.findClient(cid);
                client.getWishList().displayList();
            }
            else{
                choice = 7;
            }
        }

        reader.close();
    }
}
