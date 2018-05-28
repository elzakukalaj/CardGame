package View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Images {
    private static String pathi = "E:\\Viti 3\\Programim i distribuar\\CardGame\\src\\Image\\";//"CardSuits" + File.separator;
    private static String tipi = ".png";
    private static boolean gati = false;
    private static BufferedImage spade;
    private static BufferedImage heart;
    private static BufferedImage diamond;
    private static BufferedImage tref;
    public static boolean eshteGati() {
        return gati;
    }
    public static BufferedImage merrDiamond() {
        return diamond;
    }
    public static BufferedImage merrtHeart() {
        return heart;
    }
    public static BufferedImage merrSpade() {
        return spade;
    }
    public static BufferedImage merrTref() {
        return tref;
    }
    public  static void BufferImages() throws IOException {
        spade = ImageIO.read(new File( pathi + "spade" + tipi  ));
        heart = ImageIO.read(new File  ( pathi + "heart" + tipi  ));
        diamond = ImageIO.read(new File( pathi + "diamond" + tipi  ));
        tref = ImageIO.read(new File   ( pathi + "tref" + tipi  ));
        gati = true;
    }
}