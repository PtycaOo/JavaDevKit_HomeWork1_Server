package Client;

import Server.ServerController;

public class ClientController {
    private boolean connected;
    String name;
    private ServerController serverController;

    public void setClientView(ClientView clientView) {
        this.clientView = clientView;
    }

    private ClientView clientView;


    public boolean connected() {
        if(serverController.connect()){
            clientView.connectToServer();
            serverController.addClient(clientView);
            return true;
        } else {
            return false;
        }

    }

    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }

    private void showMassage(String massage){
        clientView.showMassage(massage);
    }

}
