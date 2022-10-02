import java.util.*;
import java.io.*;
public class ClientList {
  private LinkedList<Client> clients;	

  public ClientList() {
	clients = new LinkedList<>();
  }
  
  public boolean insertClient (Client client) {
    clients.add(client);
    return true;
  }
  
  public void displayClients (PrintWriter p) {
	Iterator<?> i = clients.iterator();
	while(i.hasNext()){
		p.println(i.next().toString());
	}
  }
  
  public int findClient (String cid) {
	 int i = 0;
	 for (Iterator<?> it = clients.iterator(); it.hasNext(); i++) {
			Client client = (Client) it.next();
			 if (client.getId().equals(cid)) {
				 return i;
			 }
		 }
		 return -1;
	 }

}
