package Nets;

import Logic.CallBack;
import Logic.Processor;

import java.io.IOException;
import java.net.Socket;

public class NodeConnection extends Connection{
    private String playerName;
    private int playerIndex;
    private int Score =0;
    public NodeConnection(Socket connection, CallBack CallBack, Processor Processor) throws IOException {
        this.callBack = CallBack;
        this.lidhja = connection;
        this.externalchanel = new ExternalChanel(this.lidhja);
        this.internalchanel = new InternalChanel(this.lidhja, this);
        Thread outputThread = new Thread(externalchanel);
        Thread inputThread   = new Thread(internalchanel);
        this.Processor = Processor;
        outputThread.start();
        inputThread.start();
    }
    public void shakeHands() {
        this.externalchanel.send("S_SHD");
        //System.out.println("Shake Hands Invoked");
    }
    public String getPlayerName() {
        return playerName;
    }
    public int getPlayerIndex() {
        return playerIndex;
    }
    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public void send (String Data) {
        this.externalchanel.send(Data);
    }
    public void inputRecived(String Input) {
        String Flag = Input.substring(0, 5);
        String Data = Input.substring(5);
        if(Flag.equals("C_SHD")) {
            //System.out.println("Shake Hands Successfull");
            //System.out.println("Requesting Name");
            this.externalchanel.send("S_RNM");
        } else if (Flag.equals("C_RNM")) {
            this.playerName = Data;
            //System.out.println("PlayerName Recived : " + Data);
            callBack.callBack(Flag,this);
        }
    }
    public void AddScore(int Score) {
        this.Score += Score;
    }
    public int getScore() {
        return this.Score;
    }
    public void CallBack(String Data, Object Caller) {
        Processor.Process(Data,this.callBack,this);
    }
}