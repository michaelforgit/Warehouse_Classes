import java.util.*;
import java.io.*;
public class Client implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String phone;
  private String address;
  private int id;
  //private static final String CLIENT_STRING = "C";


  public  Client (String name, String phone, String address) {
    this.name = name;
    this.phone = phone;
    this.address = address;
    id = ClientIdServer.instance().getId();
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
  public int getId() {
    return id;
  }
  public void setName(String newName) {
    name = newName;
  }
  public void setAddress(String newAddress) {
    address = newAddress;
  }
  public boolean equals(int id) {
    return this.id == id;
  }
  public String toString() {
    return "Client name: " + name + "phone: " + phone + " address: " + address + " id: " + id;
  }
}