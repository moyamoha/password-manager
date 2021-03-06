package kanta;
import java.util.*;

/**
 * 
 */

/**
 * @author Yahya
 * @version 22.2.2021
 *
 */
public class Strings {
    
    private static final char[] PIENET_KIRJAIMET  = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] ISO_KIRJAIMET     = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final char[] ERIKOIS_MERKIT    = "_?!></&%#.:;,*'^~+-\\\"=)([]{}|$?".toCharArray();
    private static final char[] NUMEROT           = "0123456789".toCharArray();
    
    // #import java.util.Arrays;
    
    /** 
     * Generoidaan merkkijono, jonka pituus on v?lilt?  <b>alarajaPituus</b> - <b>ylarajaPituus</b> ja merkit on valittu joukosta <b>charJoukko</b>
     * @param charJoukko char joukko, josta valitaan umpim?hk??n merkkej?
     * @param alarajaPituus generoituvan merkkijonon v?himm?ispituus
     * @param ylarajaPituus generoituvan merkkijonon enimm?ispituus
     * @return generoitu merkkijono
     * @example
     * <pre name="test">
     *    char[] merkit = new char[] {'a', 'b', 'c'};
     *    String g = generateMerkkiJono(merkit, 2, 10);
     *    (g.length() >= 2 && g.length() <= 10)  === true;
     *    g.indexOf("?")  === -1;
     * </pre>
     */
    public static String generateMerkkiJono(char[] charJoukko, int alarajaPituus, int ylarajaPituus) {
        int pituus = Numbers.rand(alarajaPituus, ylarajaPituus);
        return generateMerkkiJono(charJoukko, pituus);
    }
    
    /**
     * Generoidaan merkkijono, jonka pituus on <b>pituus</b> ja merkit on valittu joukosta <b>charJoukko</b>
     * @param charJoukko char joukko, josta valitaan umpim?hk??n merkkej?
     * @param pituus uuden merkkijonon pituus
     * @return generoitu merkkijono
     */
    public static String generateMerkkiJono(char[] charJoukko, int pituus) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pituus; i++) {
            int indeksi =  rand.nextInt(charJoukko.length);
            sb.append(charJoukko[indeksi]);
        }
        return sb.toString();
    }
    
    /**
     * Generoidaan tunnus. Tunnuksen pituus on v?lill? 7 - 10
     * @return generoitu tunnus
     * @example
     * <pre name="test">
     *   String tun = generoiTunnus();
     *   tun.length() <= 10  === true;
     * </pre>
     */
    public static String generoiTunnus() {
        char[] merkit = yhdista(PIENET_KIRJAIMET, ISO_KIRJAIMET);
        return generateMerkkiJono(merkit, 4, 7) + generateMerkkiJono(NUMEROT, 3);
    }
    
    /**
     * @param pituus generoidun salasanan pituus
     * @param arvot boolean taulukko, jossa alkioina <i>sis?lt?? numeroita</i>, <i>sis?lt?? erikoismerkkej?</i>
     * ja <i>sis?lt?? isoja kirjaimia</i>
     * @return uusi salasana
     * @example
     * <pre name="test">
     *   String sala = generoiSalasana(10, new boolean[] {false, false, true});
     *   sala.length() === 10;
     *   sala.contains("[0-9]")  === false;
     * </pre>
     */
    public static String generoiSalasana(int pituus, boolean[] arvot) {
        char[][] merkit = new char[3][];
        merkit[0] = NUMEROT; merkit[1] = ERIKOIS_MERKIT; merkit[2] = ISO_KIRJAIMET;
        
        char[] mukana = PIENET_KIRJAIMET;
        for (int i = 0; i < arvot.length; i++) {
            if (arvot[i] == true) {
                mukana = yhdista(mukana, merkit[i]);
            }
        }
        return generateMerkkiJono(mukana, pituus);       
    }
    
    /**
     * Generoi uusi satunnainen puhelinnumero
     * @return genroitu puhelin numero muodossa dddddddddd
     * @example
     * <pre name="test">
     *   String puh = generoiPuhNro();
     *   puh.startsWith("0");
     *   puh.contains("[a-zA-Z]") === false;
     * </pre>
     */
    public static String generoiPuhNro() {
        return "0" + generateMerkkiJono(NUMEROT, 9);
    }
    
    /**
     * Yhdist?? kaksi char-taulukkoa toisiinsa
     * @param eka ensimm?inen char-taulukko. T?m?n char taulukon alkiot sijoittuvat ensimm?isin? uudessa syntyneess? taulukossa
     * @param toka toinen char-taulukko. T?m?n char-taulukon alkiot sijoittuvat viimeisin? uudessa syntyneess? taulukossa
     * @return uusi char-taulukko muodossa [...eka..., ...toka...]
     * @example
     * <pre name="test">
     *    char[] PIENET_KIRJAIMET  = "abcdefghijklmnopqrstuvwxyz".toCharArray();
     *    char[] NUMEROT           = "0123456789".toCharArray();
     *    char[] yhdistetty = yhdista(PIENET_KIRJAIMET, NUMEROT);
     *    Arrays.toString(yhdistetty)  === "[a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9]";
     * </pre>
     */
    public static char[] yhdista(char[] eka , char[] toka) {
        StringBuilder sb = new StringBuilder();
        sb.append(eka); sb.append(toka);
        return sb.toString().toCharArray();
    }
    
    /**
     * @param mukanaOlevat boolean taulukko, joka m??rittelee mink?laisia merkkej? tulevat mukaan
     * @return uusi char taulukko
     */
    public static char[] yhdista(boolean[] mukanaOlevat) {
        char[] tulos = new char[] {};
        char[][] charTaul = {
                PIENET_KIRJAIMET, NUMEROT, ERIKOIS_MERKIT, PIENET_KIRJAIMET
        };
        
        for (int i = 0; i < mukanaOlevat.length; i++) {
            if (mukanaOlevat[i] == true) tulos = yhdista(tulos, charTaul[i]); 
        }
        return tulos;
        
    }
    
    /**
     * Generoidaan satunnainen nimi kategorioille
     * @return satunnainen kategorian nimi
     * @example
     * <pre name="test">
     *    String kNimi= generoiKNimi();
     *    (kNimi.length() < 11) && kNimi.length() >= 5  === true;
     *    kNimi.contains("[0-9A-Z]")  === false;
     *    kNimi.contains("[a-z]")  === true;
     * </pre>
     */
    public static String generoiKNimi() {
        return generateMerkkiJono(yhdista(PIENET_KIRJAIMET, new char[] {'?', '?'}), 5, 10);
    }
    
}
