package Nets;

import Logic.Sender;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ExternalChanel implements Runnable, Sender{
    private boolean SendingFlag = false;
    private String Data = "";
    private Socket lidhja;
    private DataOutputStream dalja;
    public ExternalChanel(Socket Connection) throws IOException {
        this.lidhja = Connection;
        dalja = new DataOutputStream(this.lidhja.getOutputStream());
    }
    public void send(String data) {
        this.Data = data;
        this.SendingFlag = true;
    }
    @Override
    public void run() {
        while (true) {
            try {
                if(SendingFlag) {
                    //System.out.println("Dergim : " + Data);
                    dalja.writeUTF(Data);
                    dalja.flush();
                    SendingFlag = false;
                }
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }
}
