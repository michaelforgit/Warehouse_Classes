public class ClientDriver {
    public static void main(String[] args) {
        Client client1 = new Client("Bob", "231 Elmo Drive");
        Client client2 = new Client("Jeff", "441 Apartment 5");
        /* Testing invidiual accessor methods for Client */
        System.out.println(client1.getName());
        System.out.println(client1.getAddress());
        System.out.println(client1.getId());
        ClientList clientlist1 = ClientList.instance();  //Create ClientList
        /* Test that addClient works and displayList works */
        System.out.println("Test that addClient works and displayList works");
        clientlist1.addClient(client1);
        clientlist1.addClient(client2);
        clientlist1.displayList();
    }
}