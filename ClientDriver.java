public class ClientDriver {
    public static void main(String[] args) {
        Client client1 = new Client("Bob", "231 Elmo Drive");
        System.out.println(client1.getName());

        ClientList clientlist1 = ClientList.instance();

        clientlist1.addClient(client1);
        clientlist1.displayList();
    }
}