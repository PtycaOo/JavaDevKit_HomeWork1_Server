package Client;

import Server.ServerController;
import Server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;

public class ClientGUI extends JFrame implements ClientView{
    public static final int HEIGHT = 400;
    public static final int WEIGHT = 500;

    private boolean clientConnect;
    private final JTextField ip;
    private final JTextField port;



    private final JTextField name;
    private final JPasswordField password;
    private final JButton login, send;
    private final JTextArea userArea;
    private final JTextArea log = new JTextArea();
    private ServerController serverController;
    private ClientController clientController;


    public ClientGUI() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(WEIGHT, HEIGHT);
        setTitle("Client");
        ip = new JTextField("127.0.0.1");
        ip.setSize(20, 20);
        port = new JTextField("8080");
        name = new JTextField("Name");
        password = new JPasswordField("password");
        login = new JButton("Login");
        userArea = new JTextArea();
        send = new JButton("Отправить");
        JPanel clientPanel = new JPanel(new GridLayout(2, 6));
        clientPanel.add(ip);
        clientPanel.add(port);
        clientPanel.add(name);
        clientPanel.add(password);
        clientPanel.add(login);
        add(clientPanel, BorderLayout.NORTH);

        JPanel clientChat = new JPanel(new GridLayout(1, 2));
        clientChat.add(userArea, BorderLayout.CENTER);
        clientChat.add(send, BorderLayout.EAST);
        add(clientChat, BorderLayout.SOUTH);
        JScrollPane chat = new JScrollPane(log);
        add(chat, BorderLayout.CENTER);

        setVisible(true);

        userArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == '\n'){
                    clientController.sendMassage(userArea.getText());
                    userArea.setText("");
                }
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientController.connected();
            }
        });


        send.addActionListener(e -> {
            clientController.sendMassage(userArea.getText());
            userArea.setText("");
        });

    }

    @Override
    public String getName() {
        return name.getText();
    }


    public void clientDisconnect() {
        clientConnect = false;
    }

    public void getMassage(String string){
        log.append(string);
    }

    @Override
    public void showMassage(String massage) {
        log.append(massage + "\n");
    }

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }
}
