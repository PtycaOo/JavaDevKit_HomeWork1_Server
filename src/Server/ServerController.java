package Server;

import Client.ClientView;

import java.util.ArrayList;

public class ServerController {
    private boolean connected;
    private ServerView serverView;

    public void setServerView(ServerView serverView) {
        this.serverView = serverView;
    }

    public boolean connect(){
        if(connected) {
            serverView.c;
            serverView.getHistory();
            return true;
        } else {
            return false;
        }
    }

    public void disconnect(){
        serverView.disconnect();
    }


    public void addClient(ClientView clientView){
        if(connected){
            serverView.addClient(clientView);
            showOnWindow(" подключился");
        }
    }

    public void sendMassageToClient(ClientView sender,String s){
        serverView.showMassageToUser(sender,s);
    }

    private void showOnWindow(String string){
        serverView.showOnWindow(string);
    }




}
