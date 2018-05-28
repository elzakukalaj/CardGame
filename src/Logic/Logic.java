package Logic;

import View.CardNotes;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Logic {
    private static ArrayList<CardNotes> generateCards() {
        ArrayList<CardNotes>  cards = new ArrayList<CardNotes>();
        for(int i = 0 ; i < 4 ; i ++) {
            for (int j = 0; j < 13; j++) {
                cards.add( new CardNotes(j+2,i+1));
            }
        }
        return cards;
    }
    public  static ArrayList<ArrayList<CardNotes>> generateCardOfPlayers() {
        ArrayList<ArrayList<CardNotes>> playersSets = new ArrayList<ArrayList<CardNotes>>();
        ArrayList<CardNotes> allCards  = generateCards();
        boolean [] selected = new boolean[52];
        for(int i = 0 ;i<52; i ++)
            selected[i]= false;
        Random rnd = new Random(new Date().getTime());
        int choice = rnd.nextInt(52);
        for(int i = 0; i < 4 ; i ++ ) {
            ArrayList<CardNotes> tmp = new ArrayList<CardNotes>();
            for(int j = 0 ; j<13 ; j++) {
                while(selected[choice]) {
                    choice = rnd.nextInt(52);
                }
                tmp.add(allCards.get(choice));
                selected[choice]= true;
            }
            playersSets.add(SortToSuits(fastSort(tmp)));
        }
        return playersSets;
    }
    private static ArrayList<CardNotes> fastSort(ArrayList<CardNotes> lst) {
        if(lst.size() <=1)
            return lst;
        ArrayList<CardNotes> less = new ArrayList<CardNotes>();
        ArrayList<CardNotes> more = new ArrayList<CardNotes>();
        CardNotes me = lst.get(0);
        for(int i = 1 ; i < lst.size() ; i++) {
            if   (me.merrValue() > lst.get(i).merrValue())
                less.add(lst.get(i));
            else
                more.add(lst.get(i));
        }
        more = fastSort(more);
        less = fastSort(less);
        more.add(me);
        more.addAll(less);
        return (more);
    }
    public static ArrayList<CardNotes> SortToSuits(ArrayList<CardNotes> unSorted) {
        // Dirty Sorting Better algorithm needed.
        ArrayList<CardNotes> Sorted = new ArrayList<CardNotes>();
        int indeks = 0;
        for(int i = 0 ; i < 4; i ++) {
            for(int j = 0 ; j < unSorted.size();j++) {
                if((i + 1) == unSorted.get(j).merrShenjat()) {
                    Sorted.add( unSorted.get(j));
                }
            }
            indeks++;
        }
        return Sorted;
    }
    public static ArrayList<CardNotes> DeSerializeCards(String StrCards) {
        ArrayList<CardNotes> Cards = new ArrayList<CardNotes>();
        String[] clst = StrCards.split("-");
        for(int i = 0 ; i<clst.length;i++) {
            int value = Integer.parseInt(clst[i].split(",")[0]);
            int suit = Integer.parseInt(clst[i].split(",")[1]);
            Cards.add(new CardNotes(value, suit));
        }
        return Cards;
    }
    public static String SerializeCards(ArrayList<CardNotes> Cards) {
        String strCards = "";
        for(CardNotes card : Cards) {
            strCards += "" + card.merrValue() + "," + card.merrShenjat()+ "-";
        }
        return strCards;
    }
    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch(Exception ex) {
        }
    }
    public static boolean has(int suit , ArrayList<CardNotes> cards ) {
        boolean result = false;
        for(CardNotes card :cards) {
            result = result || (card.merrShenjat() ==  suit);
        }
        return result;
    }
    public static boolean Dokk(ArrayList<CardNotes> cards , ArrayList<Integer> Modes) {
        //King of Hearts
        if(Modes.contains(new Integer(2))){
            int heartCount = 0;
            boolean hasKing = false;
            for(CardNotes card :cards)
                if(card.merrShenjat() == 2) {
                heartCount ++;
                hasKing = (card.merrValue()==13);
            }
            if(heartCount<= 2 && hasKing)
                return true;
        }
        //Suns
        if(Modes.contains(new Integer(5))) {
            int points = 0;
            for(CardNotes card :cards) {
                if(card.merrValue()> 10)
                    points += card.merrValue()-10;
            }
            if(points >=16)
                return true;
        }
        return false;
    }
    public static int HandWinner(int tableSuit , CardNotes[] Cards) {
        int max = 0;
        int indx =0;
        for(int i = 0 ; i<Cards.length;i++) {
            if(Cards[i].merrShenjat() == tableSuit) {
                if(Cards[i].merrValue() > max) {
                    max = Cards[i].merrValue();
                    indx = i;
                }
            }
        }
        return indx;
    }
}