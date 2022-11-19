import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.io.*;

public class ManagerMenuState extends WareState implements ActionListener{

  private static ManagerMenuState managermenustate;
  private Scanner reader = new Scanner(System.in);
  private static Warehouse warehouse;
  private JFrame frame;
  private AbstractButton addProductButton, receiveShipmentButton, becomeClerkButton, exitButton, helpButton;
  private ManagerMenuState() {
    warehouse = Warehouse.instance();
  }

  public static ManagerMenuState instance() {
    if (managermenustate == null) {
      return managermenustate = new ManagerMenuState();
    } else {
      return managermenustate;
    }
  }

  public void help() {

  }

   public void process() {
      frame = WareContext.instance().getFrame();
      frame.getContentPane().removeAll();
      frame.getContentPane().setLayout(new FlowLayout());
      

      exitButton = new JButton("EXIT");
      addProductButton =  new JButton("ADD A PRODUCT");
      receiveShipmentButton = new JButton("RECEIVE A SHIPMENT");
      becomeClerkButton = new JButton("BECOME A CLERK");

      AbstractButton[] buttons = {addProductButton, receiveShipmentButton, becomeClerkButton, exitButton};

      for (int i = 0; i < buttons.length; i++) {
        buttons[i].addActionListener(this);
        frame.getContentPane().add(buttons[i]);
      }

      frame.setVisible(true);
      frame.paint(frame.getGraphics());
      frame.toFront();
      frame.requestFocus();
    }

  public void addProduct(){
    System.out.println("Enter product name:");
    String name = reader.nextLine();
    System.out.println("Enter product price:");
    double price = Double.parseDouble(reader.nextLine());

    System.out.println("Enter amount of product in stock:");
    int inStock = Integer.parseInt(reader.nextLine());

    Product product = warehouse.addProduct(name, price, inStock);

    if(product == null){
        System.out.println("Product information invalid.");
    }
    else{
        System.out.println("Product added.");
    }
  }

  public void receiveShipment(){
    System.out.println("Enter ID of product in shipment:");
    String pid = reader.nextLine();

    System.out.println("Enter quantity of product in shipment:");
    int qty = Integer.parseInt(reader.nextLine());

    warehouse.processShipment(pid, qty, reader);
  }

  public void becomeClerk(){
    WareContext.instance().changeState(1); //[2][1] = 1
  }

  public void run() {
    process();
  }

    
  public void logout(){
      if (WareContext.instance().getLogin() == WareContext.IsManager) {
          //Logout of manager -> login
          (WareContext.instance()).changeState(2); // [2][2] = 3
      } else {  //Should not happen this an error
          (WareContext.instance()).changeState(3); // [2][3] = -2
      }
  }

  public void actionPerformed(ActionEvent event) {
    if (event.getSource().equals(this.exitButton)){
      logout();
    } else if (event.getSource().equals(this.addProductButton)){
      addProduct();
    } else if (event.getSource().equals(this.receiveShipmentButton)){
      receiveShipment();
    } else if (event.getSource().equals(this.becomeClerkButton)){
      becomeClerk();
    } else if (event.getSource().equals(this.helpButton)){
      help();
    }    
  }
}

