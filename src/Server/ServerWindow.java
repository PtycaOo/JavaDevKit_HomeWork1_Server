package Server;

import Client.ClientView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class ServerWindow extends JFrame implements ServerView{
    private static final int HEIGHT = 500;
    private static final int WEIGHT = 500;
    private static final int POS_X = 200;
    private static final int POS_Y = 200;

    private JButton btnStart,btnExit;
    private JTextArea log = new JTextArea();;
    private boolean isServerStatus;
    private ServerController serverController;




    public ServerWindow() {
        isServerStatus = false;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(POS_X,POS_Y,WEIGHT,HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Server");
        setResizable(false);
        btnStart = new JButton("Start");
        btnExit = new JButton("Exit");


        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog, BorderLayout.CENTER);

        JPanel serverPanel = new JPanel(new GridLayout(1,2));
        add(serverPanel, BorderLayout.SOUTH);
        serverPanel.add(btnStart);
        serverPanel.add(btnExit);


        setVisible(true);


        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverController.StartServer();
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverController.stopServer();
            }
        });

    }

    public void saveLog(String massage){
        String path = "src/log.txt";
        try(PrintWriter fileWriter = new PrintWriter(new FileWriter(path,true))){
            fileWriter.write(massage);
        } catch (IOException e) {
            System.out.println("ошибка при записи файла");
        }
    }

    public boolean isServerStatus() {
        return isServerStatus;
    }

    @Override
    public void showMassageToUser(String massage) {
        log.append(massage + "\n");


    }

    @Override
    public void setServerController(ServerController serverController) {
        this.serverController = serverController;
    }

    public void showOnWindow(String s){
        log.append(s + "\n");
    }

    public ServerController serverController() {
        return serverController;
    }
}