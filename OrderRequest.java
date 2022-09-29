import java.util.*;
import java.io.*;
public class OrderRequest {
  private int quantity;
  private Client client;
  public OrderRequest(int quantity, Client client) {
    this.quantity = quantity;
    this.client = client;
  }
}
