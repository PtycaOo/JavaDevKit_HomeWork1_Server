package Server;

import Client.ClientView;

public interface ServerView {
    void showOnWindow(String s);
    void getHistory();
    void showMassageToUser(ClientView sender,String s);
    void saveMassageToFile();
    void addClient(ClientView clientView);
    void connect();
    void disconnect();
}
