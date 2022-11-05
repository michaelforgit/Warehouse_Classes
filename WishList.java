import java.util.*;
import java.io.*;

public class WishList implements Serializable {
  private LinkedList<Entry> entries = new LinkedList<Entry>();
  private static WishList wishList;
  public WishList() {  
  }
  public static WishList instance() {
    if (wishList == null) {
        return (wishList = new WishList());
    } else {
        return wishList;
    }
  }

  public boolean addEntry(Entry entry) {
    Entry result = findEntry(entry.getProduct());
    if (result == null) {
      entries.add(entry);
      return true;
    } else {
      result.setQuantity(entry.getQuantity());
      return true;
    }
  }

  public String toString() {
    return entries.toString();
  }

  public Iterator<?> getWishList(){
    return entries.iterator();
  }

  public void displayList(){
    System.out.println(toString());
  }
  
  public Entry findEntry(Product product){
    for(Iterator<?> current = entries.iterator(); current.hasNext();){
      Entry entry = (Entry) current.next();
      if(entry.getProduct().equals(product)){
        return entry;
      }
    }
    return null;
  }

}

