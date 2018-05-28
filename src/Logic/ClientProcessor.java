package Logic;

public class ClientProcessor implements Processor {
    private CallBack callBack;
    private Sender sender;

    public ClientProcessor() {

    }
    public ClientProcessor(CallBack callBack,Sender Sender) {
        this.callBack = callBack;
        this.sender = Sender;
    }
    public void Process (String Data) {
        Process(Data, this.callBack, this.sender);
    }
    public void Process(String Data, CallBack callBack,Sender Sender) {
        String Flag = Data.substring(0,5);
        String Msg = Data.substring(5);
        if (Flag.equals("S_SHD")) {
            Sender.send("C_SHD");
        } else if(Flag.equals("S_RNM")) {
            callBack.callBack(Flag, Sender);
        } else if(Flag.equals("S_SIN")) {
            //System.out.println("Indeksi im " +Msg );
            callBack.callBack(Data, Sender);
        } else if(Flag.equals("S_PCN")) {
            //System.out.println("Lojtari i lidhur : " + Msg);
            callBack.callBack(Data, Sender);
        } else if(Flag.equals("S_PLS")) {
            String[] Players = Msg.split("-");
            for(int i =0;i<Players.length;i++)//System.out.println(Players[i]);
                callBack.callBack(Data, Sender);
        } else if (Flag.equals("S_TBC")) {
            callBack.callBack("S_TBC", this);
            //System.out.println("Tabela u kompletua\nDuke filluar loja");
        } else if (Flag.equals("S_CUP")) {
            callBack.callBack(Data, this);
            //System.out.println("Lojtari u ndryshua" + Msg);
        } else if(Flag.equals("S_CLS")) {
            callBack.callBack(Data, Sender);
        } else if (Flag.equals("S_STH")) {
            String Modes = "";
            while(Modes.length() ==0) {
                Modes = "1";//JOptionPane.showInputDialog("Enter Game Modes Spearated by ',' \n\b 1-Jacks\n2-King Of Hearts\n3-Queens\n4-Diamonds\n5-Suns");
            }
            if (Modes.length() == 1)Modes += ",";
            Sender.send("C_SGM" + Modes);
        } else if (Flag.equals("S_SGM")) {
            callBack.callBack(Data, Sender);
            //System.out.println("Checking Game Modes" + Msg);
        } else if (Flag.equals("S_CHT")) {
            callBack.callBack(Data, Sender);
        } else if (Flag.equals("S_CPD")) {
            callBack.callBack(Data, Sender);
            //System.out.println("Card Recived" + Msg);
        } else if (Flag.equals("S_STS")) {
            callBack.callBack(Data, Sender);
            //System.out.println("Table Suite Recived" + Msg);
        } else if (Flag.equals("S_PWN")) {
            callBack.callBack(Data, Sender);
            //System.out.println("Player Won " + Msg);
        } else if (Flag.equals("S_ROE")) {
            callBack.callBack(Data, Sender);
            //System.out.println("Round Ended" + Msg);
        } else if (Flag.equals("S_GME")) {
            callBack.callBack(Data, Sender);
            //System.out.println("Game Ended" + Msg);
        }
    }
}