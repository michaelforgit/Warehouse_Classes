/********************************************************************
  Product List Class
        List of products in warehouse system
**********************************************************************/

import java.util.*;
import java.io.*;
import java.lang.*;

public class ProductList implements Serializable{
  private static final long serialVersionUID = 1L;
 
  private List<Product> products = new LinkedList<Product>();
  private static ProductList pList;

    
//----------Constructor-------------
  public ProductList(){    }

    
//----------instance()--------------------
  public static ProductList instance() {
  	if(pList == null){
  		return (pList = new ProductList());
  	} else{
  		return pList;
  	}//end ifelse
  }//end instance()

    
//----------accessor---------------------
   public Iterator<?> getProducts(){
     return products.iterator();  }

//----------size-------------
// size of product List
//---------------------------
    public int size(){
        return products.size();
    }//end size()
    
//-------------displayList-----------------
// Display Product List
//-----------------------------------------
    public void displayList(){
        for(Iterator<?> current = products.iterator(); current.hasNext();){
            Product P = (Product) current.next();
            System.out.println(P.getData());
        }
    }//end DisplayList
    
//-----------Add Product--------------------
// Add product to product List
//------------------------------------------
  public boolean addProduct(Product P){
    products.add(P);
    return true;
  }//end addProduct
    
 //--------removeProduct--------------------
// Remove product from Product List
//-------------------------------------------
  public void removeProduct(String PID){
    int i = 0;
    for(Iterator<?> current = products.iterator(); current.hasNext();){
      i++;
      Product P = (Product) current.next();
      if(P.getProductNumber().equals(PID)){
        products.remove(i);
      }//endif
    }//endfor
  }//end removeProduct
    
    
//----------------findProduct----------------------
// Find product in product List by product id
// returns Null if id not found in list
//-----------------------------------------------
  public Product findProduct(String id){
    for(Iterator<?> current = products.iterator(); current.hasNext();){
      Product P = (Product) current.next();
      if(P.getProductNumber().equals(id)){
        return P;
      }
    }
    return null;
  }
    
/*---------------writeObject---------------------
  private static void writeObject(java.io.ObjectOutputStream output) {
	try{
		output.defaultWriteObject();
		output.writeObject(pList);
	} catch(IOException ioe){
		ioe.printStackTrace();
	} //end try-catch block
  }//end writeObject

//---------------readObject------------------------
  private static void readObject(java.io.ObjectInputStream input){
	try{
		if(pList != null)
			return;
		else{
			input.defaultReadObject();
			if(pList == null){
				pList = (ProductList) input.readObject();
			} else {
				input.readObject();
			}//end if-else
		}//end if-else
	} catch (IOException ioe){
		ioe.printStackTrace();
	} catch(ClassNotFoundException cnfe) {
		cnfe.printStackTrace();
	}//end try-catch block
  }//end readObject
/* */
//-----------toString()------------------------
  public String toString(){
  	String returnedString = "";
  	Iterator<?> curr = products.iterator();
  	while(curr.hasNext())
  		returnedString = returnedString.concat(curr.next().toString() + '\n');
  	return returnedString;
  }//end toString

//------------Search--------------------------
// Searches by product ID
//--------------------------------------------
  public List<Product> search(String parameter){
    List<Product> returnProducts = new ArrayList<Product>();
    Iterator<?> current = products.iterator();
    while(current.hasNext()){
      Product tProduct = (Product)current.next();
      if(tProduct.getProductNumber() == parameter){
        returnProducts.add(tProduct);
      }//end if parameter equals
    }//end while

    return returnProducts;
  }//end search with id

 }
