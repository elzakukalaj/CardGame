package Nets;

import Logic.CallBack;
import Logic.Processor;
import Logic.Sender;

import java.io.IOException;
import java.net.Socket;

public class Connection implements CallBack, Sender{
    protected CallBack callBack;
    protected Socket lidhja;
    protected InternalChanel internalchanel;
    protected ExternalChanel externalchanel;
    protected Logic.Processor Processor;
    public Connection(){}
    public Connection(String IP,int Port,CallBack CallBack,Processor Processor) throws IOException {
        this.callBack = CallBack;
        this.lidhja = new Socket(IP, Port);
        this.externalchanel = new ExternalChanel(this.lidhja);
        this.internalchanel = new InternalChanel(this.lidhja, this);
        Thread outputThread = new Thread(externalchanel);
        Thread inputThread   = new Thread(internalchanel);
        this.Processor = Processor;
        outputThread.start();
        inputThread.start();
    }
    public void send(String Data) {
        this.externalchanel.send(Data);
    }
    public void callBack(String Data,Object Caller) {
        Processor.Process(Data, this.callBack,this);
    }
}
