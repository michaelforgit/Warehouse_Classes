import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.io.*;

public class LoginState extends WareState implements ActionListener{
  //private WareContext context;
  private static LoginState instance;
  private JFrame frame;
  private AbstractButton clientButton, clerkButton, managerButton, logoutButton;
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

  public void actionPerformed(ActionEvent event) {
    if (event.getSource().equals(this.clientButton)) 
       {//System.out.println("user \n"); 
         this.client();}
    else if (event.getSource().equals(this.logoutButton)) 
       (WareContext.instance()).changeState(3); //Logout user
    else if (event.getSource().equals(this.clerkButton)) 
       this.clerk();
    else if (event.getSource().equals(this.managerButton)) 
       this.manager();
  } 
  
  public void clear() { //clean up stuff
    frame.getContentPane().removeAll();
    frame.paint(frame.getGraphics());   
  }  
  private void client(){  //Client
    String clientID = JOptionPane.showInputDialog(frame, "Enter Client ID");
    if (Warehouse.instance().searchClient(clientID)){  //Warehouse.instance()
      (WareContext.instance()).setLogin(WareContext.IsClient);
      (WareContext.instance()).setUser(clientID);      
      clear();
      (WareContext.instance()).changeState(0);
    }
    else 
      System.out.println("Invalid user id.");
  } 

  private void clerk(){
    (WareContext.instance()).setLogin(WareContext.IsClerk);
    clear();
    (WareContext.instance()).changeState(1);
  }

  private void manager(){
    (WareContext.instance()).setLogin(WareContext.IsManager);
    clear();
    (WareContext.instance()).changeState(2);
  }

  public void process() {
    frame = WareContext.instance().getFrame();
    frame.getContentPane().removeAll();
    frame.getContentPane().setLayout(new FlowLayout());

    clientButton = new JButton("Client");
    clerkButton =  new JButton("Clerk");
    managerButton = new JButton("Manager");
    logoutButton = new JButton("Logout");

    AbstractButton[] buttons = {clientButton, clerkButton, managerButton, logoutButton};

    for (int i = 0; i < buttons.length; i++) {
      buttons[i].addActionListener(this);
      frame.getContentPane().add(buttons[i]);
    }
    frame.setVisible(true);
    frame.paint(frame.getGraphics());
    frame.toFront();
    frame.requestFocus();
  }

  public void run() {
    process();
  }
}
