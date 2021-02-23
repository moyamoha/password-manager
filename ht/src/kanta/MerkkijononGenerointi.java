package kanta;
import java.util.Random;

/**
 * 
 */

/**
 * @author Yahya
 * @version 22.2.2021
 *
 */
public class MerkkijononGenerointi {
    
    private static final char[] PIENET_KIRJAIMET  = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] ISO_KIRJAIMET     = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final char[] ERIKOIS_MERKIT    = "_?!></&%#.:;,*'^~`¥+-\\\"=)([]{}|$Ä".toCharArray();
    private static final char[] NUMEROT           = "0123456789".toCharArray();
    
    
    /**
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        System.out.println(generoiTunnus());
        System.out.println(generoiSalasana(10, true, true, true));
        char[] merkit = yhdista(PIENET_KIRJAIMET, NUMEROT);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < merkit.length; i++) {
            sb.append(merkit[i]);
        }
        System.out.println(sb.toString());
    }
    
    
    /** 
     * Generoidaan merkkijono, jonka pituus on v‰lilt‰  <b>alarajaPituus</b> - <b>ylarajaPituus</b> ja merkit on valittu joukosta <b>charJoukko</b>
     * @param charJoukko char joukko, josta valitaan umpim‰hk‰‰n merkkej‰
     * @param alarajaPituus generoituvan merkkijonon v‰himm‰ispituus
     * @param ylarajaPituus generoituvan merkkijonon enimm‰ispituus
     * @return generoitu merkkijono
     */
    public static String generateMerkkiJono(char[] charJoukko, int alarajaPituus, int ylarajaPituus) {
        int pituus = Numerot.rand(alarajaPituus, ylarajaPituus);
        return generateMerkkiJono(charJoukko, pituus);
    }
    
    /**
     * Generoidaan merkkijono, jonka pituus on <b>pituus</b> ja merkit on valittu joukosta <b>charJoukko</b>
     * @param charJoukko char joukko, josta valitaan umpim‰hk‰‰n merkkej‰
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
     * Generoidaan tunnus. Tunnuksen pituus on v‰lill‰ 7 - 10
     * @return generoitu tunnus
     */
    public static String generoiTunnus() {
        char[] merkit = yhdista(PIENET_KIRJAIMET, ISO_KIRJAIMET);
        return generateMerkkiJono(merkit, 4, 7) + generateMerkkiJono(NUMEROT, 3);
    }
    
    /**
     * @param pituus generoidun salasanan pituus
     * @param onNumerot sis‰lt‰isikˆ uusi salasanan numeroita
     * @param onErikoiset sis‰lt‰isikˆ uusi salasan erikoismerkkej‰
     * @param onIsot sis‰lt‰isikˆ uusi salasana isoja kirjaimia
     * @return uusi salasana
     */
    public static String generoiSalasana(int pituus,    boolean onNumerot, boolean onErikoiset, boolean onIsot) {
        if (onNumerot && onErikoiset && onIsot)         return generateMerkkiJono(yhdista(PIENET_KIRJAIMET, ISO_KIRJAIMET, NUMEROT, ERIKOIS_MERKIT), pituus);
        else if(onNumerot && onIsot && ! onErikoiset)   return generateMerkkiJono(yhdista(PIENET_KIRJAIMET, ISO_KIRJAIMET, NUMEROT), pituus);
        else if (onNumerot && ! onIsot && !onErikoiset) return generateMerkkiJono(yhdista(PIENET_KIRJAIMET, NUMEROT), pituus);
        else if (!onNumerot && ! onIsot && onErikoiset) return generateMerkkiJono(yhdista(PIENET_KIRJAIMET, ERIKOIS_MERKIT), pituus);
        else if (!onNumerot && onIsot && onErikoiset)   return generateMerkkiJono(yhdista(PIENET_KIRJAIMET, ISO_KIRJAIMET, ERIKOIS_MERKIT), pituus);
        else if (!onNumerot && onIsot && !onErikoiset)  return generateMerkkiJono(yhdista(PIENET_KIRJAIMET, ISO_KIRJAIMET), pituus);
        else if (onNumerot  && !onIsot && onErikoiset)  return generateMerkkiJono(yhdista(PIENET_KIRJAIMET, NUMEROT, ERIKOIS_MERKIT), pituus);
        else return generateMerkkiJono(PIENET_KIRJAIMET, pituus);
    }
    
    /**
     * Generoi uusi satunnainen puhelinnumero
     * @return genroitu puhelin numero muodossa dddddddddd
     */
    public static String generoiPuhNro() {
        return "0" + generateMerkkiJono(NUMEROT, 9);
    }
    
    /**
     * Yhdist‰‰ kaksi char-taulukkoa toisiinsa
     * @param eka ensimm‰inen char-taulukko. T‰m‰n char taulukon alkiot sijoittuvat ensimm‰isin‰ uudessa syntyneess‰ taulukossa
     * @param toka toinen char-taulukko. T‰m‰n char-taulukon alkiot sijoittuvat viimeisin‰ uudessa syntyneess‰ taulukossa
     * @return uusi char-taulukko muodossa [...eka..., ...toka...]
     * @example
     * <pre name="test">
     *       char[] PIENET_KIRJAIMET  = "abcdefghijklmnopqrstuvwxyz".toCharArray();
     *       char[] NUMEROT           = "0123456789".toCharArray();
     *       yhdista(PIENET_KIRJAIMET, NUMEROT).equals("abcdefghijklmnopqrstuvwxyz0123456789".toCharArray()) === true;;
     * </pre>
     */
    public static char[] yhdista(char[] eka , char[] toka) {
        StringBuilder sb = new StringBuilder();
        sb.append(eka); sb.append(toka);
        return sb.toString().toCharArray();
        
    }
    
    /**
     * @param eka ensimm‰inen char-taulukko
     * @param toka toinen char-taulukko.
     * @param kolmas char-taulukko
     * @return uusi char-taulukko muodossa [...eka..., ...toka..., ...kolmas...]
     */
    public static char[] yhdista(char[] eka, char[] toka, char[] kolmas) {
        return yhdista(yhdista(eka, toka), kolmas);
    }
    
    
    /**
     * @param eka ensimm‰inen char-taulukko
     * @param toka toinen char-taulukko.
     * @param kolmas char-taulukko
     * @param neljas char-taulukko
     * @return uusi char-taulukko muodossa [...eka..., ...toka..., ...kolmas..., ...neljas...]
     */
    public static char[] yhdista(char[] eka, char[] toka, char[] kolmas, char[] neljas) {
        return yhdista(yhdista(eka, toka, kolmas), neljas);
    }
}
