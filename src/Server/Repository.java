package Server;

import Client.ClientController;

import java.util.ArrayList;

public interface Repository {

    void addUser(ClientController client);
    ArrayList<ClientController> getClientList();
}
