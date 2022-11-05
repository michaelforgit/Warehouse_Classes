import java.util.*;

public class LoginState extends WareState{
  private static final int CLIENT_LOGIN = 0;
  private static final int CLERK_LOGIN = 1;
  private static final int MANAGER_LOGIN = 2;
  private static final int EXIT = 3;
  private Scanner reader = new Scanner(System.in);
  //private WareContext context;
  private static LoginState instance;
  private LoginState() {
      super();
     // context = LibContext.instance();
  }

  public static LoginState instance() {
    if (instance == null) {
      instance = new LoginState();
    }
    return instance;
  }

  private void client(){  //Client
    System.out.println("Enter client ID: ");
    String clientID = reader.nextLine();
    if (Warehouse.instance().searchClient(clientID)){  //Warehouse.instance()
      (WareContext.instance()).setLogin(WareContext.IsClient);
      (WareContext.instance()).setUser(clientID);      
      (WareContext.instance()).changeState(0);
    }
    else 
      System.out.println("Invalid user id.");
  } 

  private void clerk(){
    (WareContext.instance()).setLogin(WareContext.IsClerk);
    (WareContext.instance()).changeState(1);
  }

  private void manager(){
    (WareContext.instance()).setLogin(WareContext.IsManager);
    (WareContext.instance()).changeState(2);
  }

  public void process() {
    int command;
    System.out.println("Please input 0 to login as Client\n"+ 
                        "input 1 to login as Clerk\n" +
                        "input 2 to login as Manager\n" +
                        "input 3 to exit the system\n");
    command = Integer.parseInt(reader.nextLine());
    while (command != EXIT) {
      switch (command) {
        case CLIENT_LOGIN:
          client();
          break;
        case CLERK_LOGIN:
          clerk();
          break;
        case MANAGER_LOGIN:
          manager();
          break;

        default:                System.out.println("Invalid choice");
                                
      }
      System.out.println("Please input 0 to login as client\n"+ 
                        "input 1 to login as clerk\n" +
                        "input 2 to login as manager\n" +
                        "input 3 to exit the system\n");
      command = Integer.parseInt(reader.nextLine());
    }
    (WareContext.instance()).changeState(3);
  }

  public void run() {
    process();
  }
}
