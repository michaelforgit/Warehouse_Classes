import java.util.*;

public class WarehouseDriver {
    public static void main(String args[])
    {
        int choice = 1;
        Scanner reader = new Scanner(System.in);
        Warehouse warehouse = Warehouse.instance();

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

                warehouse.addClient(name, address);

                System.out.println("Client created.");
            }
            else if(choice == 2){
                System.out.println("Enter product name:");
                String name = reader.nextLine();
                System.out.println("Enter product price:");
                double price = Double.parseDouble(reader.nextLine());
                
                System.out.println("Enter amount of product in stock:");
                int inStock = Integer.parseInt(reader.nextLine());

                warehouse.addProduct(name, price, inStock);

                System.out.println("Product created.");
            }
            else if(choice == 3){
                System.out.println("Enter client ID:");
                String cid = reader.nextLine();

                System.out.println("Enter product ID:");
                String pid = reader.nextLine();

                System.out.println("Enter quantity of product to add to wishlist:");
                int quantity = Integer.parseInt(reader.nextLine());
                
                warehouse.addToClientWishlist(cid, pid, quantity);

                System.out.println("The product was added to the client's wishlist.");
            }
            else if(choice == 4){
                warehouse.displayClients();
            }
            else if(choice == 5){
                warehouse.displayProducts();
            }
            else if(choice == 6){
                System.out.println("Enter client ID:");
                String cid = reader.nextLine();

                warehouse.displayClientWishlist(cid);
            }
            else{
                choice = 7;
            }
        }

        reader.close();
    }
}
