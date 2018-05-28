package View;

import Logic.Logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Panel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    JPanel cardsPanel,tablePanel;
    ArrayList<CardNotes> cards;
    GameFrame gameFrame;
    private boolean button_u_klikua = false;
    JButton shperndajButton = new JButton("Gati per loje");
    public void klikoniKarten(ActionEvent e) {
        Card cTmp = (Card)e.getSource();
        this.cardsPanel.remove(cTmp);
        this.cardsPanel.repaint();
        this.tablePanel.repaint();
        this.revalidate();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(button_u_klikua && gameFrame.merrlojtarinAktual() == gameFrame.merrIndeksin()) {
            Card cTmp = (Card)e.getSource();
            if(gameFrame.merrShenjatETabelave() == -1 || cTmp.getCardData().merrShenjat() == gameFrame.merrShenjatETabelave()|| !Logic.has(gameFrame.merrShenjatETabelave(), this.merrCard())) {
                this.cardsPanel.remove(cTmp);
                this.cardsPanel.repaint();
                this.tablePanel.repaint();
                this.revalidate();
                gameFrame.send("C_CPD"+ cTmp.toString());
                Logic.sleep(1000);
                gameFrame.vendoslojtarinAktual(-1);
            }
        } else if(e.getActionCommand().equals("Gati per loje")) {
            button_u_klikua = true;
            this.remove(shperndajButton);
            this.add(cardsPanel,BorderLayout.NORTH);
            this.cardsPanel.repaint();
            this.tablePanel.repaint();
            this.revalidate();
        }
    }
    public Panel(GameFrame gmfrm ) {
        this.gameFrame = gmfrm;
        this.setLayout(new BorderLayout(5, 5));
        this.cardsPanel = new JPanel();
        this.cardsPanel.setBackground(Color.DARK_GRAY);
        this.cardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER,3,10));
        this.tablePanel = new JPanel();
        this.tablePanel.setBackground(Color.DARK_GRAY);
        this.tablePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
        shperndajButton.addActionListener(this);
        this.add(shperndajButton,BorderLayout.NORTH);
        //this.add(cardsPanel,BorderLayout.NORTH);
        this.add(tablePanel,BorderLayout.CENTER);
        System.out.println("Paneli i lojes u krijua");
    }
    public void ReDistributeCards(ArrayList<CardNotes> cards) {
        this.cardsPanel.removeAll();
        this.cards = cards;
        for(CardNotes card : this.cards) {
            Card c = new Card(card);
            this.cardsPanel.add(c);
            c.addActionListener(this);
        }
        this.revalidate();
    }
    public JPanel merrKartenePanelit() {
        return cardsPanel;
    }
    public void shtoCard(Card card) {
        this.tablePanel.add(card);
        tablePanel.repaint();
        tablePanel.revalidate();
        this.repaint();
        this.revalidate();
        System.out.println("Karta u shtua");
    }
    public ArrayList<CardNotes> merrCard(){
        ArrayList<CardNotes> cards = new ArrayList<CardNotes>();
        for(int i = 0;i<cardsPanel.getComponentCount();i++) {
            if(cardsPanel.getComponent(i) instanceof Card) {
                cards.add(((Card)cardsPanel.getComponent(i)).getCardData());
            }
        }
        return cards;
    }
}