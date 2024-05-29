import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    public static final int HEIGHT = 400;
    public static final int WEIGHT = 500;

    private boolean clientConnect;
    private final JTextField ip, port, name;
    private final JPasswordField password;
    private final JButton login, send;
    private final JTextArea userArea;
    private final JTextArea log = new JTextArea();


    ClientGUI(ServerWindow serverWindow) {
        clientConnect = false;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(WEIGHT, HEIGHT);
        setTitle("Client");
        ip = new JTextField("127.0.0.1");
        ip.setSize(20, 20);
        port = new JTextField("8080");
        name = new JTextField();
        password = new JPasswordField("password");
        login = new JButton("Login");
        userArea = new JTextArea();
        send = new JButton("Send");
        JPanel clientPanel = new JPanel(new GridLayout(2, 6));
        clientPanel.add(ip);
        clientPanel.add(port);
        clientPanel.add(name);
        clientPanel.add(password);
        clientPanel.add(login);
        add(clientPanel, BorderLayout.NORTH);

        JPanel clientChat = new JPanel(new GridLayout(1, 2));
        clientChat.add(userArea, BorderLayout.WEST);
        clientChat.add(send, BorderLayout.EAST);
        add(clientChat, BorderLayout.SOUTH);
        JScrollPane chat = new JScrollPane(log);
        add(chat, BorderLayout.CENTER);

        setVisible(true);

        login.addActionListener(e -> {
            if (serverWindow.isServerStatus() && !clientConnect) {
                if (name.getText().isEmpty()) {
                    log.append("Необходимо ввести имя пользователя\n");
                } else {
                    clientConnect = true;
                    log.append("Соединение установленно\n");
                    serverWindow.addClient(this);
                }
            } else if(serverWindow.isServerStatus() && clientConnect){
                log.append("Вы уже вошли в учетную запись\n");
            } else {
                log.append("Сервер отключен\n");
            }
        });

        send.addActionListener(e -> {
                    if(serverWindow.isServerStatus() && clientConnect) {
                        String massage = name.getText() + " : " + userArea.getText();
                        log.append(massage + "\n");
                        serverWindow.sendMassage(massage,this);
                        userArea.setText("");
                    }
                }
        );


    }

    public void clientDisconnect() {
        clientConnect = false;
    }

    public void getMassage(String string){
        log.append(string);
    }

}
