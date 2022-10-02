import java.util.*;
import java.io.*;
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
}

