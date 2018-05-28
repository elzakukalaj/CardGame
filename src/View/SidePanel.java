package View;

import Logic.CallBack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SidePanel extends JPanel implements KeyListener {
    private JTextField dergoTekstin;
    private JTextArea  hapesiaeChatit;
    private CallBack callBack;
    private JScrollPane chatScroll;
    private JList listaELojtareve;
    private DefaultListModel emriILojtareve;
    public SidePanel(CallBack CallBack) {
        this.callBack = CallBack;
        this.setLayout(new BorderLayout(5,5));
        this.setPreferredSize(new Dimension(180, 400));
        this.dergoTekstin = new JTextField();
        this.dergoTekstin.addKeyListener(this);
        this.dergoTekstin.setPreferredSize(new Dimension(180,30));
        this.dergoTekstin.setBackground(Color.LIGHT_GRAY);
        this.dergoTekstin.setForeground(Color.BLACK);
        this.dergoTekstin.setBorder(null);
        this.dergoTekstin.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 14));
        this.hapesiaeChatit = new JTextArea();
        this.hapesiaeChatit.setEditable(false);
        this.hapesiaeChatit.setBackground(Color.DARK_GRAY);
        this.hapesiaeChatit.setForeground(Color.orange);
        this.hapesiaeChatit.setLineWrap(true);
        this.hapesiaeChatit.setFont(new Font("Times New Roman", Font.CENTER_BASELINE, 14));
        this.chatScroll = new JScrollPane(hapesiaeChatit);
        this.chatScroll.setBorder(null);
        this.chatScroll.getVerticalScrollBar().setBackground(Color.black);
        this.emriILojtareve  = new DefaultListModel();
        this.listaELojtareve = new JList(emriILojtareve);
        this.listaELojtareve.setPreferredSize(new Dimension(200, 80));
        this.listaELojtareve.setBorder(null);
        this.listaELojtareve.setBackground(Color.DARK_GRAY);
        this.listaELojtareve.setForeground(Color.white);
        this.listaELojtareve.setSelectionForeground(Color.DARK_GRAY);
        this.listaELojtareve.setSelectionBackground(Color.orange);
        this.add(chatScroll,BorderLayout.CENTER);
        this.add(listaELojtareve,BorderLayout.NORTH);
        this.add(dergoTekstin,BorderLayout.SOUTH);
    }
    public void AppendToChat(String Str) {
        this.hapesiaeChatit.append(Str + "\n");
        this.hapesiaeChatit.setCaretPosition(hapesiaeChatit.getText().length());
    }
    public void AddPlayer(String Name , int indx) {
        this.emriILojtareve.addElement(Name);
        this.repaint();
    }
    public void SelectPlayer(int indx) {
        this.listaELojtareve.setSelectedIndex(indx);
        this.repaint();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == e.VK_ENTER) {
            this.callBack.callBack("C_CHT" + dergoTekstin.getText(), this);
            dergoTekstin.setText("");
        }
    }
    public JList getPlayersList() {
        return listaELojtareve;
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}
