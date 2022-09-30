import java.util.*;
import java.io.*;
public class Client {
  private String name;
  private String address;
  private String id;
  
  
  public  Client (String name, String address, String id) {
    this.name = name;
    this.address = address;
    this.id = id;
  }

  public String getName() {
    return name;
  }
  public String getAddress() {
    return address;
  }
  public String getId() {
    return id;
  }
  
  public void setName(String newName) {
    name = newName;
  }
  public void setAddress(String newAddress) {
    address = newAddress;
  }
  public void setId(String newId) {
    phone = newId;
  }

  public String toString() {
    return "Client name: " + name + " address: " + address + " id: " + id;
  }
}
