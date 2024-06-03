package Client;

import Server.ServerController;

public class ClientController {
    private boolean isConnected;
    String name;
    private ClientView clientView;
    private ServerController serverController;

    public String getName() {
        return clientView.getName();
    }

    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }

    public void setClientView(ClientView clientView) {
        this.clientView = clientView;
    }

    public void connected() {
        if(!isConnected && serverController.isConnected()){
            isConnected = true;
            serverController.addClient(this);
        } else if(isConnected && !serverController.isConnected()) {
            clientView.showMassage(clientView.getName() + " уже подключен");
            serverController.showOnWindow(clientView.getName() + " уже подключен");
        } else {
            clientView.showMassage("Cервер отключен");
        }
    }

    public void disconnected(){
        if(isConnected){
            isConnected = false;
            clientView.showMassage("Сервер отключен");
            serverController.stopServer();
        }
    }

    public void showMassage(String massage){
        clientView.showMassage(massage);
    }

    public void sendMassage(String massage) {
        if(isConnected && serverController.isConnected()){
            String toSend = clientView.getName() + " : " + massage;
            clientView.showMassage(toSend);
            serverController.sendMassageToUser(toSend,this);
        } else if(!isConnected){
            clientView.showMassage("Вы не подключены к серверу");
        } else{
            clientView.showMassage("Сервер выключен");
        }

    }
}
