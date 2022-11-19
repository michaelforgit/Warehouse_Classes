import java.util.*;
import java.io.*;
import java.time.*;
public class InvoiceList implements Serializable{
    private static final long serialVersionUID = 1L;
    private LinkedList<Invoice> invoices = new LinkedList<Invoice>();
    private static InvoiceList invoiceList;

    public InvoiceList(){
    }

    public static InvoiceList instance(){
        if(invoiceList == null){
            return (invoiceList = new InvoiceList());
        } else{
            return invoiceList;
        }
    }

    public boolean addInvoice(Invoice invoice){
        invoices.add(invoice);

        return true;
    }

    public String toString(){
        return invoices.toString();
    }

    private static void writeObject(java.io.ObjectOutputStream output) {
        try{
          output.defaultWriteObject();
          output.writeObject(invoiceList);
        } catch(IOException ioe){
          ioe.printStackTrace();
        } //end try-catch block
      }//end writeObject
    
    //---------------readObject------------------------
      private static void readObject(java.io.ObjectInputStream input){
        try{
          if(invoiceList != null)
            return;
          else{
            input.defaultReadObject();
            if(invoiceList == null){
              invoiceList = (InvoiceList) input.readObject();
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

      public boolean inactiveUser() {
        LocalDate date = LocalDate.now();
        LocalDate date6MonthsAgo = date.minusMonths(6);
        for (Iterator<?> current = invoices.iterator(); current.hasNext();) {
          Invoice invoice = (Invoice) current.next();
          if (invoice.getDate().isAfter(date6MonthsAgo)) {
            return false;
          }
        }
        return true;
      }

}