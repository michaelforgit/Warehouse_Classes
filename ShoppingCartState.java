import java.util.*;

public class ShoppingCartState extends WareState {

  private static ShoppingCartState shoppingcartstate;
  private Scanner reader = new Scanner(System.in);
  private static Warehouse warehouse;
  private static final int EXIT = 0;
  private static final int MODIFY_CART = 3;
  private static final int DISPLAY_WISHLIST = 6;
  private static final int HELP = 16;
  private ShoppingCartState() {
    warehouse = Warehouse.instance();
  }

  public static ShoppingCartState instance() {
    if (shoppingcartstate == null) {
      return shoppingcartstate = new ShoppingCartState();
    } else {
      return shoppingcartstate;
    }
  }

  public void help() {
    System.out.println("Shopping Cart Menu");
    System.out.println(EXIT + "  | Exit");
    System.out.println(DISPLAY_WISHLIST + "  | Display wishlist");
    System.out.println(MODIFY_CART + "  | Modify Cart");
    System.out.println(HELP + " | for help");
  }

  public void process() {
    int command;
    help();
    command = Integer.parseInt(reader.nextLine());
    while (command != EXIT) {
      switch (command) {

        case DISPLAY_WISHLIST:
            displayWishlist();
            break;
        case MODIFY_CART:
            modifyCart();
            break;
        case HELP:
            help();
            break;
        default:
          System.out.println("Invalid choice");
      }
      help();
      command = Integer.parseInt(reader.nextLine());
    }
    logout();
  }

  public void displayWishlist(){
    String clientID = WareContext.instance().getClient();

    warehouse.displayClientWishlist(clientID);
  }

  public void modifyCart(){
    String clientID = WareContext.instance().getClient();

    do{
        System.out.print("Enter product ID: ");
        String productID = reader.nextLine();
        System.out.print("Enter product quantity: ");
        int quantity = Integer.parseInt(reader.nextLine());
        if(warehouse.addToClientWishlist(clientID, productID, quantity)){
            System.out.println("Added product to wishlist");
        }
        else{
            System.out.println("Invalid information.");
        }
        System.out.print("Add another product? (Y/N): ");
        String choice = reader.nextLine();
        if(choice.equals("Y") || choice.equals("y")){
          continue;
        }
        else{
          break;
        }
    } while (true);

  }

  public void run() {
    process();
  }

    
  public void logout(){

    (WareContext.instance()).changeState(0); // [4][0] = 0
  }
}

