import java.util.*;
import java.text.*;
import java.io.*;
public class ClientMenuState extends WareState {
  private static ClientMenuState clientState;
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static Warehouse warehouse;
  private static final int EXIT = 0;
  private static final int ADD_PRODUCT_TO_WISHLIST = 3;
  private static final int DISPLAY_PRODUCTS = 5;
  private static final int DISPLAY_WISHLIST = 6;
  private static final int PLACE_ORDER = 7;
  private static final int DISPLAY_INFO = 11;

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
      try {
        System.out.println(prompt);
        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
        if (tokenizer.hasMoreTokens()) {
          return tokenizer.nextToken();
        }
      } catch (IOException ioe) {
        System.exit(0);
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
    System.out.println(ISSUE_BOOKS + " to  issue books to a  member");
    System.out.println(RENEW_BOOKS + " to  renew books ");
    System.out.println(PLACE_HOLD + " to  place a hold on a book");
    System.out.println(REMOVE_HOLD + " to  remove a hold on a book");
    System.out.println(GET_TRANSACTIONS + " to  print transactions");
    System.out.println(HELP + " for help");
  }

  public void process() {
    int command;
    help();
    while ((command = getCommand()) != EXIT) {
      switch (command) {

        case ISSUE_BOOKS:       issueBooks();
                                break;
        case RENEW_BOOKS:       renewBooks();
                                break;
        case PLACE_HOLD:        placeHold();
                                break;
        case REMOVE_HOLD:       removeHold();
                                break;
        case GET_TRANSACTIONS:  getTransactions();
                                break;
        case HELP:              help();
                                break;
      }
    }
    logout();
  }

  public void run() {
    process();
  }

  public void logout()
  {
    if ((LibContext.instance()).getLogin() == LibContext.IsClerk)
       { //stem.out.println(" going to clerk \n ");
         (LibContext.instance()).changeState(1); // exit with a code 1
        }
    else if (LibContext.instance().getLogin() == LibContext.IsUser)
       {  //stem.out.println(" going to login \n");
        (LibContext.instance()).changeState(0); // exit with a code 2
       }
    else 
       (LibContext.instance()).changeState(2); // exit code 2, indicates error
  }
 
}
