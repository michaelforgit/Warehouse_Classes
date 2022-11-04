import java.util.*;

public class ClientMenuState extends WareState {
  private static ClientMenuState clientState;
  private Scanner reader = new Scanner(System.in);
  private static Warehouse warehouse;
  private static final int EXIT = 0;
  private static final int MODIFY_CART = 3;
  private static final int DISPLAY_PRODUCTS = 5;
  private static final int DISPLAY_WISHLIST = 6;
  private static final int PLACE_ORDER = 7;
  private static final int SHOW_DETAILS = 11;
  private static final int DISPLAY_TRANSACTIONS = 12;
  private static final int HELP = 13;
  private ClientMenuState() {
    warehouse = Warehouse.instance();
  }

  public static ClientMenuState instance() {
    if (clientState == null) {
      return clientState = new ClientMenuState();
    } else {
      return clientState;
    }
  }
  public String getToken(String prompt) {
    do {
        System.out.println(prompt);
        String line = reader.nextLine();
        StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
        if (tokenizer.hasMoreTokens()) {
          return tokenizer.nextToken();
        }
    } while (true);
  }

  private boolean yesOrNo(String prompt) {
    String more = getToken(prompt + " (Y|y)[es] or anything else for no");
    if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
      return false;
    }
    return true;
  }
  public int getNumber(String prompt) {
    do {
      try {
        String item = getToken(prompt);
        Integer num = Integer.valueOf(item);
        return num.intValue();
      } catch (NumberFormatException nfe) {
        System.out.println("Please input a number ");
      }
    } while (true);
  }

  public int getCommand() {
    do {
      try {
        int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
        if (value >= EXIT && value <= HELP) {
          return value;
        }
      } catch (NumberFormatException nfe) {
        System.out.println("Enter a number");
      }
    } while (true);
  }

  public void help() {
    System.out.println("Enter a number between 0 and 12 as explained below:");
    System.out.println(EXIT + " to Exit\n");
    System.out.println(MODIFY_CART + " to modify the shopping cart");
    System.out.println(DISPLAY_PRODUCTS + " to display products with prices");
    System.out.println(DISPLAY_WISHLIST + " to to display wishlist");
    System.out.println(PLACE_ORDER + " to place an order");
    System.out.println(SHOW_DETAILS + " to display client details");
    System.out.println(DISPLAY_TRANSACTIONS + " to display transactions");
    System.out.println(HELP + " for help");
  }

  public void process() {
    int command;
    help();
    while ((command = getCommand()) != EXIT) {
      switch (command) {

        case MODIFY_CART:
            modifyCart();
            break;
        case DISPLAY_PRODUCTS:
            displayProducts();
            break;
        case DISPLAY_WISHLIST:
            displayWishlist();
            break;
        case PLACE_ORDER:
            placeOrder();
            break;
        case SHOW_DETAILS:
            showDetails();
            break;
        case DISPLAY_TRANSACTIONS:
            displayTransactions();
            break;
        case HELP:
            help();
            break;
      }
    }
    logout();
  }

  public void modifyCart(){
    String clientID = WareContext.instance().getClient();

    do{
        String productID = getToken("Enter product ID: ");
        int quantity = Integer.parseInt(getToken("Enter product quantity: "));
        if(warehouse.addToClientWishlist(clientID, productID, quantity)){
            System.out.println("Added product to wishlist");
        }
        else{
            System.out.println("Invalid information.");
        }
        if(!(yesOrNo("Add another product?"))){
            break;
        }
    } while (true);

  }

  public void displayProducts(){
    warehouse.displayProducts();
  }

  public void displayWishlist(){
    String clientID = WareContext.instance().getClient();

    warehouse.displayClientWishlist(clientID);
  }

  public void placeOrder(){
    String clientID = WareContext.instance().getClient();

    warehouse.processClientWishlist(clientID, reader);
  }

  public void showDetails(){
    String clientID = WareContext.instance().getClient();

    warehouse.displayClientDetails(clientID);
  }

  public void displayTransactions(){
    
  }

  public void run() {
    process();
  }

  public void logout()
  {
    if ((WareContext.instance()).getLogin() == WareContext.IsClient)
       { //client->client logout go to logout
         (WareContext.instance()).changeState(0); // [0][0] = 3
        }
    else if (WareContext.instance().getLogin() == WareContext.IsClerk)
       {  //clerk->client logout go to clerk
        (WareContext.instance()).changeState(1); // [0][1] = 1
       }
    else if (WareContext.instance().getLogin() == WareContext.IsManager) {
      //manager->client is not possible
      (WareContext.instance()).changeState(2); // [0][2] = -2
    }
  }
 
}
