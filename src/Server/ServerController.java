package Server;

import Client.ClientController;
import Client.ClientView;

import java.io.*;
import java.util.ArrayList;

public class ServerController {
    private boolean connected;
    private ServerView serverView;
    ArrayList<ClientController> clientList = new ArrayList<>();

    public ArrayList<ClientController> getClientList() {
        return clientList;
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
        clientList.add(client);
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
            for (ClientController client: clientList){
                    client.disconnected();
            }
        }

    }

    public void saveLogToFile(String s){
        String filePath = "Server/log.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath,true))){
            writer.println(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadingFromFile(){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("Server/log.txt"));) {
            String line;
            while((line = bufferedReader.readLine()) !=null){
                showOnWindow(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void sendMassageToUser(String massage, ClientController sender) {
        serverView.showOnWindow(massage);
        for (ClientController client: clientList){
            if(client != sender){
                client.showMassage(massage);
            }
        }
        saveLogToFile(massage);

    }
}
