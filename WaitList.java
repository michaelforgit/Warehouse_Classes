import java.util.*;
import java.io.*;
public class WaitList {
  private LinkedList<OrderRequest> orders = new LinkedList<OrderRequest>();
  public WaitList() {
    
  }
  public boolean insertOrder(OrderRequest order) {
    orders.add(order);
    return true;
  }
}

