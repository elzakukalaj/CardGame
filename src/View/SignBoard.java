package View;

import java.awt.*;

public class SignBoard implements LayoutManager {
    private int gap;
    boolean jacksMode = false;
    public SignBoard(int gap , boolean jacksMode) {
        this.gap = gap;
        this.jacksMode = jacksMode;
    }
    public SignBoard(int gap) {
        this(gap,false);
    }
    public SignBoard() {
        this(5,false);
    }
    @Override
    public void addLayoutComponent(String name, Component comp) {
        //System.out.println(name + " " + comp.toString());
    }
    @Override
    public void layoutContainer(Container parent) {
        if(jacksMode) {
            int[] row = new int[4];
            int v = 15;
            for(int i = 0 ;i < parent.getComponentCount();i++) {
                Card c = (Card) parent.getComponent(i);
                int x = (parent.getWidth() - (5 * (gap +  c.getWidth() )) + c.getWidth())  / 2;
                int y = ( (parent.getHeight())  - ((13 * v) + c.getHeight() )  )/2;
                c.setLocation(x + ((gap + c.getWidth()) * ( c.getCardData().merrShenjat() -1)) ,y + (v * row[c.getCardData().merrShenjat() - 1]));
                row[c.getCardData().merrShenjat() - 1] ++ ;
            }
        } else {
            for(int i = 0 ;i < parent.getComponentCount();i++) {
                Card c = (Card) parent.getComponent(i);
                int x = (parent.getWidth() - c.getWidth()) / 2;
                int y = (parent.getHeight() - c.getHeight()) /2;
                if(c.takeIndexTable() == 0) {
                    c.setLocation(x,y + c.getHeight() + gap);
                } else if(c.takeIndexTable() == 2) {
                    c.setLocation(x,y - (c.getHeight() + gap));
                } else if(c.takeIndexTable() == 1) {
                    c.setLocation(x  + c.getWidth() + gap ,y);
                } else if(c.takeIndexTable() == 3) {
                    c.setLocation(x  -( c.getWidth() + gap) ,y);
                }
            }
        }
    }
    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return null;
    }
    @Override
    public Dimension preferredLayoutSize(Container parent) {
        int dimx = parent.getWidth() / 2;
        int dimy = parent.getHeight()/2;
        return new Dimension(dimx,dimy);
    }
    @Override
    public void removeLayoutComponent(Component comp) {
    }
}
