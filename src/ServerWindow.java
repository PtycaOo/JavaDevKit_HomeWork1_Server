import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ServerWindow extends JFrame {
    private static final int HEIGHT = 500;
    private static final int WEIGHT = 500;
    private static final int POS_X = 200;
    private static final int POS_Y = 200;

    private final JButton btnStart,btnExit;
    private final JTextArea log;
    private boolean isServerStatus;
    private final List<ClientGUI> connectedClient = new ArrayList<>();

    protected ServerWindow() {
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
        getLog();
        log.append("\n");
        log.append("Новые сообщения:\n");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(isServerStatus){
                    saveMassage("Сервер уже включен\n");
                } else {
                    isServerStatus = true;

                    saveMassage("Сервер включен\n");
                }

            }
        });

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

    public void addClient(ClientGUI client){
        connectedClient.add(client);
    }

    public void sendMassage(String massage, ClientGUI sender){
        log.append(massage + "\n");
        for (ClientGUI client: connectedClient){
            if(client != sender){
                client.getMassage(massage + "\n");
            }
        }
        saveLog(massage + "\n");


    }

    public void getLog(){
        try(BufferedReader reader = new BufferedReader(new FileReader("src/log.txt"))){
            String line = reader.readLine();
            while (line != null){
                log.append(line + "\n");
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            log.append("Старых сообщений нет\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ;
    }


}
