package Server;

import Client.ClientView;

public interface ServerView {
    void showOnWindow(String s);
    void setServerController(ServerController serverController);
    void showMassageToUser(String s);
}
