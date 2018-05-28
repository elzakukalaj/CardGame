package Main;

import Nets.Server;
import View.GameFrame;
import View.Images;

import javax.swing.*;

public class StartClient {
    public static void main (String[] Args) {
        if(Args.length != 0 && Args[0].equals("-Server")) {
            try {
                Server s = new Server(12345);
                Thread serverThread = new Thread(s);
                serverThread.start();
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            String PlayerName = JOptionPane.showInputDialog("Emri juaj:");
            String IPAddress = JOptionPane.showInputDialog("IP e serverit");
            try {
                Images.BufferImages();
                GameFrame mainWindow = new GameFrame(IPAddress, 12345,PlayerName);
                mainWindow.setVisible(true);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
