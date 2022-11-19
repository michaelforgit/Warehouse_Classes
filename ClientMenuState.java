import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.io.*;

public class ClientMenuState extends WareState implements ActionListener{
  private static ClientMenuState clientState;
  private Scanner reader = new Scanner(System.in);
  private static Warehouse warehouse;
  private JFrame frame;
  private AbstractButton displayProductsButton, placeOrderButton, showDetailsButton, displayTransactionsButton, becomeShoppingCartButton, exitButton, helpButton;

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

  public void process() {
    frame = WareContext.instance().getFrame();
    frame.getContentPane().removeAll();
    frame.getContentPane().setLayout(new FlowLayout());

    exitButton = new JButton("EXIT");
    displayProductsButton = new JButton("DISPLAY PRODUCTS");
    placeOrderButton = new JButton("PLACE ORDER");
    showDetailsButton = new JButton("SHOW DETAILS");
    displayTransactionsButton = new JButton("DISPLAY TRANSACTIONS");
    becomeShoppingCartButton = new JButton("EDIT SHOPPING CART");

    AbstractButton[] buttons = {displayProductsButton, placeOrderButton, showDetailsButton, displayTransactionsButton, becomeShoppingCartButton, exitButton};

    for (int i = 0; i < buttons.length; i++) {
      buttons[i].addActionListener(this);
      frame.getContentPane().add(buttons[i]);
    }

    frame.setVisible(true);
    frame.paint(frame.getGraphics());
    frame.toFront();
    frame.requestFocus();
  }


  public void displayProducts(){
    warehouse.displayProducts();
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
    String clientID = WareContext.instance().getClient();

    warehouse.displayClientTransactions(clientID);
  }

  public void shoppingCart(){
    WareContext.instance().changeState(4);
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
    else {
      (WareContext.instance()).changeState(3); // [0][3] = -2
    }
  }

  public void actionPerformed(ActionEvent event) {
    if (event.getSource().equals(this.exitButton)) {
      logout();
    } else if (event.getSource().equals(this.displayProductsButton)) {
      displayProducts();
    } else if (event.getSource().equals(this.placeOrderButton)) {
      placeOrder();
    } else if (event.getSource().equals(this.showDetailsButton)) {
      showDetails();
    } else if (event.getSource().equals(this.displayTransactionsButton)) {
      displayTransactions();
    } else if (event.getSource().equals(this.becomeShoppingCartButton)) {
      shoppingCart();
    }
  }
}
