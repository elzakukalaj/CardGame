package Logic;

import Nets.NodeConnection;

public class ProcessorConnection implements Processor{
    private CallBack callBack;
    private Sender sender;
    public ProcessorConnection() {}
    public ProcessorConnection(CallBack callBack,Sender Sender) {
        this.callBack = callBack;
        this.sender = Sender;
    }
    public void Process (String Data) {
        Process(Data,this.callBack,this.sender);
    }
    public void Process(String Data, CallBack callBack,Sender Sender) {
        String Flag = Data.substring(0, 5);
        String Msg = Data.substring(5);
        if(Flag.equals("C_SHD")) {
            //System.out.println("Shake Hands Successfull");
            //System.out.println("Requesting Name");
            Sender.send("S_RNM");
        } else if (Flag.equals("C_RNM")) {
            ((NodeConnection)Sender).setPlayerName(Msg);
            //System.out.println("PlayerName Recived : " + Msg);
            callBack.callBack(Flag,Sender);
        } else if (Flag.equals("C_CHT")) {
            callBack.callBack(Data, Sender);
            //System.out.println("Chat Msg Recived " +Msg );
        } else if (Flag.equals("C_CPD")) {
            callBack.callBack(Data, Sender);
            //System.out.println("Card Recived " +Msg );
        } else if (Flag.equals("C_GMA")) {
            callBack.callBack(Data, Sender);
        } else if (Flag.equals("C_SGM")) {
            callBack.callBack(Data, Sender);
        }
    }
}
