package Server;

import Client.ClientController;

import java.util.ArrayList;

public class ServerRepository implements Repository{
    ArrayList<ClientController> clientToServer = new ArrayList<>();


    @Override
    public void addUser(ClientController client) {
        this.clientToServer.add(client);
    }

    public ArrayList<ClientController> getClientList(){
        return clientToServer;
    }

}
