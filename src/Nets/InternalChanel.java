package Nets;

import Logic.Logic;
import Logic.CallBack;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class InternalChanel implements Runnable{
    private Socket lidhja;
    private CallBack callBack;
    private DataInputStream hyrja;
    public InternalChanel(Socket Lidhja, CallBack CallBack) throws IOException {
        this.lidhja = Lidhja;
        this.callBack = CallBack;
        this.hyrja = new DataInputStream(lidhja.getInputStream());
    }
    @Override
    public void run() {
        while (true) {
            try {
                String Input = hyrja.readUTF();
                callBack.callBack(Input,this);
                Logic.sleep(1000);
            } catch (Exception e) {
            }
        }
    }
}
