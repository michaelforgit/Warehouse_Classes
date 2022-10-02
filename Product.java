
/**************************************************************************
Product Class 
 Create product object. Each product has a description, id,
 Sale Price and amount in stock.
***************************************************************************/

//import java.util.*;
//import java.lang.*;
import java.io.*;

public class Product implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private String name;
  private int id;
  private int inStock;
  private double salePrice;

//----------Contructor-------------------
  public Product(String n, double sp, int st){
    id = ProductIdServer.instance().getId();
    name = n;
    salePrice = sp;
    inStock = st;
  }

//---------accessors------------------
  public int getProductNumber(){
    return id;   }

  public String getName(){
    return name;  }

  public double getSalePrice(){
    return salePrice;     }

  public int getStock(){
	return inStock;  }

  
  public String getData(){
    String data = "";
    data += name;
    data += "\n\tId: " + id;
    data += "\n\tSale Price:     ";
    data += salePrice;
    data += "\n\tStock:          ";
    data += inStock;
    data += "\n\tNumber of waitlisted orders: ";
    return data;
    }
    
    
//-----------mutators----------------
  public void setName(String input){
    name = input;  }
    
  public void setSalePrice(double input){
    salePrice = input;     }

    
  public void setData(){
    System.out.println("To use data Setter input data members in this order\n\tString name\n\tdouble salePrice");
    }
    
  public void setData(String n, double sp, int st){
    name            =    n;
    salePrice       =   sp;
    inStock 		=   st;

    }
    
    
//-------------removeStock------------
// Remove specified amount of stock
//------------------------------------
  public void removeStock(int r){
	inStock -= r;
  }//end removeStock
  
//-----------AddItem-----------------
// Add Item to Stock from Shipment
//-----------------------------------
  public void addStock(int quantity){
	  inStock += quantity;
  }//end addShippedItem
  

    
//-------------toString()----------------
  public String toString(){
     return getData();  }
    
	  
}//end Product Class





