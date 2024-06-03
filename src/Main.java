import Client.ClientController;
import Client.ClientGUI;
import DateBase.StorageSystem;
import DateBase.WorkWithFile;
import Server.ServerController;
import Server.ServerRepository;
import Server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        ServerRepository serverRepository = new ServerRepository();
        WorkWithFile workWithFile = new WorkWithFile();
        ServerController serverController = new ServerController(serverWindow,serverRepository,workWithFile);
        serverWindow.setServerController(serverController);

        ClientGUI clientGUI_1 = new ClientGUI();
        ClientController clientController_1 = new ClientController();
        clientController_1.setClientView(clientGUI_1);
        clientGUI_1.setClientController(clientController_1);
        clientController_1.setServerController(serverController);

        ClientGUI clientGUI_2 = new ClientGUI();
        ClientController clientController_2 = new ClientController();
        clientController_2.setClientView(clientGUI_2);
        clientGUI_2.setClientController(clientController_2);
        clientController_2.setServerController(serverController);
    }
}