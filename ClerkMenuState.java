import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.io.*;

public class ClerkMenuState extends WareState implements ActionListener{
  private static ClerkMenuState clerkmenustate;
  private Scanner reader = new Scanner(System.in);
  private static Warehouse warehouse;
  private JFrame frame;
  private AbstractButton addClientButton, listProductsButton, queryClientsButton, acceptPaymentButton, becomeClientButton, displayProductWaitlistButton, exitButton;
  private ClerkMenuState() {
    warehouse = Warehouse.instance();
  }

  public static ClerkMenuState instance() {
    if (clerkmenustate == null) {
      return clerkmenustate = new ClerkMenuState();
    } else {
      return clerkmenustate;
    }
  }

  public void process() {
    frame = WareContext.instance().getFrame();
    frame.getContentPane().removeAll();
    frame.getContentPane().setLayout(new FlowLayout());

    exitButton = new JButton("EXIT");
    addClientButton = new JButton("ADD CLIENT");
    listProductsButton = new JButton("LIST PRODUCTS");
    queryClientsButton = new JButton("QUERY CLIENTS");
    acceptPaymentButton = new JButton("ACCEPT PAYMENT");
    becomeClientButton = new JButton("BECOME CLIENT");
    displayProductWaitlistButton = new JButton("DISPLAY PRODUCT WAITLIST");

    AbstractButton[] buttons = {addClientButton, listProductsButton, queryClientsButton, acceptPaymentButton, becomeClientButton, displayProductWaitlistButton, exitButton};

    for (int i = 0; i < buttons.length; i++) {
      buttons[i].addActionListener(this);
      frame.getContentPane().add(buttons[i]);
    }

    frame.setVisible(true);
    frame.paint(frame.getGraphics());
    frame.toFront();
    frame.requestFocus();
  }

  public void addClient(){
    System.out.print("Enter client name: ");
    String name = reader.nextLine();
    System.out.print("Enter client address: ");
    String address = reader.nextLine();

    Client client = warehouse.addClient(name, address);

    if(client == null){
      System.out.println("Client information invalid.");
    }
    else{
      System.out.println("Client added.");
    }
  }

  public void displayProducts(){
    warehouse.displayProducts();
  }

  public void acceptPayment(){
    System.out.print("Enter client ID: ");
    String cid = reader.nextLine();

    System.out.print("Enter amount to charge client: ");
    float amount = Float.parseFloat(reader.nextLine());

    warehouse.acceptClientPayment(cid, amount);

  }

  public void clear() { //clean up stuff
    frame.getContentPane().removeAll();
    frame.paint(frame.getGraphics());   
  }  

  public void becomeClient(){
    String clientID = JOptionPane.showInputDialog(frame, "Enter Client ID");

    if(warehouse.searchClient(clientID)){
      WareContext.instance().setUser(clientID);
      clear();
      WareContext.instance().changeState(0);
    }
    else{
      System.out.println("Invalid clientID.");
    }
  }


  public void displayProductWaitlist(){
    System.out.print("Enter product ID: ");
    String pid = reader.nextLine();

    warehouse.displayProductWaitlist(pid);
  }

  public void queryClients() {
    WareContext.instance().changeState(5);
  }

  public void run() {
    process();
  }

  public void logout()
  {
    //.getLogin is what the user actually IS.
    if (WareContext.instance().getLogin() == WareContext.IsClerk)
       {  //system.out.println(" going to clerk \n ");
        (WareContext.instance()).changeState(1); // [1][1] = 3
       }
    else if (WareContext.instance().getLogin() == WareContext.IsManager) {
      (WareContext.instance()).changeState(2); // [1][2] = 2
    } else {
      (WareContext.instance()).changeState(3); // [1][3] = -2
    }
  }

  public void actionPerformed(ActionEvent event) {
    if (event.getSource().equals(this.exitButton)) {
      logout();
    } else if (event.getSource().equals(this.addClientButton)) {
      addClient();
    } else if (event.getSource().equals(this.listProductsButton)) {
      displayProducts();
    } else if (event.getSource().equals(this.queryClientsButton)) {
      queryClients();
    } else if (event.getSource().equals(this.acceptPaymentButton)) {
      acceptPayment();
    } else if (event.getSource().equals(this.becomeClientButton)) {
      becomeClient();
    } else if (event.getSource().equals(this.displayProductWaitlistButton)) {
      displayProductWaitlist();
    } 
  }
 
}
