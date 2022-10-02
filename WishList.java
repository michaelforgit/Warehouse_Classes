import java.util.*;
public class WishList {
  private LinkedList<Entry> entries = new LinkedList<Entry>();
  private static WishList wishList;
  private WishList() {  
  }
  public static WishList instance() {
    if (wishList == null) {
        return (wishList = new WishList());
    } else {
        return wishList;
    }
  }

  public boolean insertEntry(Entry entry) {
    entries.add(entry);
    return true;
  }

  public String toString() {
    return entries.toString();
  }

  public Iterator<?> getWishList(){
    return entries.iterator();
  }

  public void displayList(){
    for(Iterator<?> current = entries.iterator(); current.hasNext();){
      Entry E = (Entry) current.next();
      System.out.println(E.toString());
    }
  }
}

