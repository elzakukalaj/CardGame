package View;

import Logic.CallBack;
import Logic.Logic;
import Logic.Sender;
import Nets.Connection;
import Logic.ClientProcessor;
import Logic.Processor;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class GameFrame extends JFrame implements CallBack, Sender {
    private static final long serialVersionUID = 1L;
    private Panel gPanel;
    private SidePanel sidePanel;
    private Connection lidhja;
    private String emriILojtarit;
    private int indeksi;
    private int lojtariAktual = 0;
    private int shenjatETabeleve = -1;
    public int merrShenjatETabelave() {
        return shenjatETabeleve;
    }
    public int  merrlojtarinAktual() {
        return this.lojtariAktual;
    }
    public void vendoslojtarinAktual(int i) {
        this.lojtariAktual = i;
    }
    public int merrIndeksin() {
        return this.indeksi;
    }
    @Override
    public void send(String Data) {
        this.lidhja.send(Data);
    }
    public GameFrame(String IP , int Port,String PlayerName) throws IOException {
        this.setBackground(Color.white);
        this.emriILojtarit = PlayerName;
        this.lidhja = new Connection(IP, Port, this, new ClientProcessor());
        this.setTitle(this.emriILojtarit);
        this.setSize(1366, 700);
        this.setLayout( new BorderLayout(5, 5) );
        this.gPanel = new Panel(this);
        gPanel.setBackground(Color.white);
        this.sidePanel = new SidePanel(this);
        this.sidePanel.setBackground(Color.white);
        this.add(sidePanel,BorderLayout.SOUTH);
        this.add(gPanel,BorderLayout.CENTER);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        System.out.println("Korniza u krijua!");
    }
    public Connection getConnection() {
        return lidhja;
    }
    public void callBack(String Data, Object Caller) {
        String Flag = Data.substring(0,5);
        String Msg = Data.substring(5);
        if(Flag.equals("S_RNM")) {
            lidhja.send("C_RNM" + this.emriILojtarit);
        }
        if(Flag.equals("S_SIN")) {
            this.indeksi = Integer.parseInt(Msg);
            //System.out.println("Index u ruajt");
        } else if(Flag.equals("S_PCN")) {
            int indx = Integer.parseInt(Msg.split(",")[0]);
            String name =(Msg.split(",")[1]);
            this.sidePanel.AddPlayer(name, indx);
        } else if(Flag.equals("S_PLS")) {
            System.out.println("Lista e lojtareve u pranua");
            String[] Players = Msg.split("-");
            for(int i =0;i<Players.length;i++) {
                try {
                    String name =(Players[i].split(",")[1]);
                    int indx = Integer.parseInt(Players[i].split(",")[0]);
                    this.sidePanel.AddPlayer(name, indx);
                } catch(Exception ex) {
                }
            }
            this.sidePanel.AddPlayer(this.emriILojtarit,0);
        } else if(Flag.equals("S_TBC")) {
        } else if (Flag.equals("S_CUP")) {
            int Indx = Integer.parseInt(Msg);
            this.lojtariAktual = Indx;
            this.sidePanel.SelectPlayer(Indx);
        } else if (Flag.equals("S_SGM")) {
            String[] Modes = Msg.split(",");
            ArrayList<Integer> modes = new ArrayList<Integer>();
            for(int i=0;i<Modes.length;i++)
                modes.add(new Integer(Integer.parseInt(Modes[i])));
            if(Logic.Dokk(this.gPanel.merrCard(), modes))
                send("C_GMA0");
            else
                send("C_GMA1");
        } else if (Flag.equals("S_CLS")) {
            this.gPanel.ReDistributeCards(Logic.DeSerializeCards(Msg));
        } else if (Flag.equals("C_CHT")) {
            this.lidhja.send("C_CHT" + this.emriILojtarit + ":" + Msg);
        } else if (Flag.equals("S_CHT")) {
            System.out.println("mesazh nga chati");
            this.sidePanel.AppendToChat(Msg);
        } else if (Flag.equals("S_CPD")) {
            System.out.println("Duke i procesuar letrat" + Msg);
            int indx = Integer.parseInt(Msg.split(",")[0]);
            int value = Integer.parseInt(Msg.split(",")[1]);
            int suit = Integer.parseInt(Msg.split(",")[2]);
            CardNotes cd = new CardNotes(value,suit);
            Card c = new Card(cd);
            c.placeIndexTable(indx);
            this.gPanel.shtoCard(c);
        } else if (Flag.equals("S_STS")) {
            this.shenjatETabeleve = Integer.parseInt(Msg);
        } else if (Flag.equals("S_PWN")) {
            this.shenjatETabeleve = -1;
            int indx = Integer.parseInt(Msg.split(",")[0]);
            String Name = Msg.split(",")[1];
            int score = Integer.parseInt(Msg.split(",")[2]);
            this.gPanel.tablePanel.removeAll();
            this.gPanel.tablePanel.repaint();
            this.gPanel.tablePanel.revalidate();
            this.sidePanel.getPlayersList().setSelectedIndex(-1);
            if(indx == this.indeksi)
                JOptionPane.showMessageDialog(null,"Ti fitove "  + this.emriILojtarit);
            else
                JOptionPane.showMessageDialog(null,   Name + " fitoj lojen");
        } else if (Flag.equals("S_ROE")) {
            JOptionPane.showMessageDialog(null, "Raundi perfundoj\n\n" + Msg);
        } else if (Flag.equals("S_GME")) {
            JOptionPane.showMessageDialog(null, "Loja perfundoj\n\n" + Msg);
            System.exit(0);
        }
    }
}