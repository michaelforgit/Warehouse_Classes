import java.util.*;
import java.io.*;
public class Client implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String phone;
  private String address;
  private String id;
  private static final String CLIENT_STRING = "C";
  private WishList wishlist;


  public  Client (String name, String phone, String address) {
    this.name = name;
    this.phone = phone;
    this.address = address;
    id = CLIENT_STRING + (ClientIdServer.instance().getId());
    wishlist = WishList.instance();
  }

  public String getName() {
    return name;
  }
  public String getPhone() {
    return phone;
  }
  public String getAddress() {
    return address;
  }
  public String getId() {
    return id;
  }
  public WishList getWishList(){
    return wishlist;
  }

  public void setName(String newName) {
    name = newName;
  }
  public void setAddress(String newAddress) {
    address = newAddress;
  }
  public boolean equals(String id) {
    return this.id.equals(id);
  }
  public String toString() {
    return "Client name: " + name + "phone: " + phone + " address: " + address + " id: " + id;
  }
}