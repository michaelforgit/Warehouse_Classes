import java.util.*;

public class QueryClientsState extends WareState {

  private static QueryClientsState queryclientsstate;
  private Scanner reader = new Scanner(System.in);
  private static Warehouse warehouse;
  private static final int EXIT = 0;
  private static final int DISPLAY_CLIENTS = 3;
  private static final int DISPLAY_OUTSTANDING_BALANCE = 6;
  private static final int DISPLAY_INACTIVE_CLIENTS = 7;
  private static final int HELP = 16;
  private QueryClientsState() {
    warehouse = Warehouse.instance();
  }

  public static QueryClientsState instance() {
    if (queryclientsstate == null) {
      return queryclientsstate = new QueryClientsState();
    } else {
      return queryclientsstate;
    }
  }

  public void help() {
    System.out.println("Shopping Cart Menu");
    System.out.println(EXIT + "  | Exit");
    System.out.println(DISPLAY_CLIENTS + "  | Display clients");
    System.out.println(DISPLAY_OUTSTANDING_BALANCE + "  | Display clients with outstanding balance");
    System.out.println(DISPLAY_INACTIVE_CLIENTS + "  | Display clients without transactions in the last 6 months");
    System.out.println(HELP + " | for help");
  }

  public void process() {
    int command;
    help();
    command = Integer.parseInt(reader.nextLine());
    while (command != EXIT) {
      switch (command) {

        case DISPLAY_CLIENTS:
            displayClients();
            break;
        case DISPLAY_OUTSTANDING_BALANCE:
            displayOutstandingBalances();
            break;
        case DISPLAY_INACTIVE_CLIENTS:
            displayInactiveClients();
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
  public void displayClients(){
    warehouse.displayClients();
  }

  public void displayOutstandingBalances(){
    warehouse.displayClientsWithBalance();
  }

  public void displayInactiveClients(){
    warehouse.inactiveUser();
  } 

  public void run() {
    process();
  }

    
  public void logout(){
    (WareContext.instance()).changeState(1); // [5][1] = 1
  }
}

