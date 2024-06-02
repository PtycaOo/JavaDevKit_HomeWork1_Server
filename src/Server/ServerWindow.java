package Server;

import Client.ClientGUI;
import Client.ClientView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServerWindow extends JFrame implements ServerView{
    private static final int HEIGHT = 500;
    private static final int WEIGHT = 500;
    private static final int POS_X = 200;
    private static final int POS_Y = 200;

    private JButton btnStart,btnExit;
    private JTextArea log;
    private boolean isServerStatus;
    private ServerController serverController;
    ArrayList<ClientView> clientList;



    public ServerWindow() {
        isServerStatus = false;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(POS_X,POS_Y,WEIGHT,HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Server");
        setResizable(false);

        btnStart = new JButton("Start");
        btnExit = new JButton("Exit");
        log = new JTextArea();
        JPanel serverPanel = new JPanel(new GridLayout(1,2));
        add(log);
        add(serverPanel, BorderLayout.SOUTH);
        serverPanel.add(btnStart);
        serverPanel.add(btnExit);


        setVisible(true);

    }

    public void saveLog(String massage){
        String path = "src/log.txt";
        try(PrintWriter fileWriter = new PrintWriter(new FileWriter(path,true))){
            fileWriter.write(massage);
        } catch (IOException e) {
            System.out.println("ошибка при записи файла");
        }
    }

    public void saveMassage(String string){
        log.append(string + "\n");
        saveLog(string);
    }

    public boolean isServerStatus() {
        return isServerStatus;
    }

    @Override
    public void showMassageToUser(ClientView sender, String massage) {
        log.append(massage + "\n");
        for (ClientView clientView: clientList){
            if(clientView != sender){
                clientView.showMassage(massage + "\n");
            }
        }
        saveLog(massage + "\n");

    }

    @Override
    public void saveMassageToFile() {

    }

    @Override
    public void addClient(ClientView client) {
        clientList.add(client);
    }



    @Override
    public void connect() {
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ghbdtn");
//                if(isServerStatus){
//                    saveMassage("Сервер уже включен\n");
//                } else {
//                    isServerStatus = true;
//                    saveMassage("Сервер включен\n");
//                }

            }
        });
    }


    @Override
    public void disconnect() {
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isServerStatus){
                    saveMassage("Сервер уже остановлен\n");
                } else {
                    isServerStatus = false;
                    saveMassage("Сервер выключен\n");
                }
            }
        });

    }

    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }

    public void showOnWindow(String s){
        log.append(s);
    }

    @Override
    public void getHistory() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("src/log.txt"))) {
            String line = bufferedReader.readLine();
            while(line != null){
                log.append(line + "\n");
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            log.append("История сообщений пуста");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ServerController serverController() {
        return serverController;
    }
}