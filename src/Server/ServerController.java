package Server;

import Client.ClientController;
import DateBase.StorageSystem;
import DateBase.WorkWithFile;

import java.io.*;

public class ServerController {
    private boolean connected;
    private ServerView serverView;
    private final Repository repository;
    private final StorageSystem system;

    public ServerController(ServerView serverView,Repository repository,StorageSystem system) {
        this.serverView = serverView;
        this.repository = repository;
        this.system = system;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setServerView(ServerView serverView) {
        this.serverView = serverView;
    }

    public void addClient(ClientController client){
        showOnWindow(client.getName() + " подключился");
        client.showMassage(client.getName() + " подключился");
        repository.addUser(client);
    }

    public void showOnWindow(String string){
        serverView.showOnWindow(string);
    }

    public void StartServer() {
        if(!connected) {
            connected = true;
            loadingFromFile();
            serverView.showOnWindow("Сервер запущен");
            saveLogToFile("Сервер запущен");
        } else {
            serverView.showOnWindow("Сервер уже запущен");
            saveLogToFile("Сервер уже запущен");
        }
    }

    public void stopServer() {
        if (connected){
            serverView.showOnWindow("Сервер выключен");
            saveLogToFile("Сервер выключен");
            connected = false;
            for (ClientController client: repository.getClientList()){
                    client.disconnected();
            }
        }

    }

    public void saveLogToFile(String s){
        system.saveLogToFile(s);
    }

    private void loadingFromFile(){
        showOnWindow(system.loadLogFromFile());
    }

    public void sendMassageToUser(String massage, ClientController sender) {
        serverView.showOnWindow(massage);
        for (ClientController client: repository.getClientList()){
            if(client != sender){
                client.showMassage(massage);
            }
        }
        saveLogToFile(massage);

    }
}
