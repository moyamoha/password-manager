/**
 * 
 */
package kanta;

/**
 * @author Yahya
 * @version 25.2.2021
 * Tarkistamiseen liittyvi? yleismaailmallisia metodia
 */
public class Checkings {
    
    /**
     * Tarkistaa onko s?hk?posti oikein.
     * Oikeamuotoinen s?hk?posti on muodossa alkuosa@loppuosa
     * Alkuosassa saa olla vain pieni? kirjaimia, isoja kirjaimia, numeroita sek? . ja _
     * Loppuosassa saa olla vain pieni? kirjaimia ja v?hint??n yksi piste. Piste ei saa kuitenkaan olla 
     * viimeisen? merkkin?.
     * @param email k?sitelt?v? s?hk?posti
     * @return true jos s?hk?posti on syntaktisesti oikein
     * @example
     * <pre name="test">
     *   onValidiEmail("abc@gmail.com") === false;
     *   onValidiEmail("abcdef@edu.hel.fi") === true;
     *   onValidiEmail("@yahoo.")        === false;
     *   onValidiEmail("...@yahoo.com") === false;
     *   onValidiEmail("kkk11kk@hotmail.com")  === true;
     *   onValidiEmail("matti.meik?l?inen@gmail.com.") === false;
     *   onValidiEmail("matti.meik?l?inen@gmail.com")  === false;
     *   onValidiEmail("matti.meikalainen@gmail.com")  === true;
     * </pre>
     */
    public static boolean onValidiEmail(String email) {
        return email.matches("[a-z0-9A-z_.]{6,30}@[a-z.]+[^.]") || email.length() == 0;
    }
    
    /**
     * Tarkistaa onko k?ytt?j?tunnus oikein
     * @param tunnus k?sitelt?v? tunnus
     * @param minL montako merkki? saa olla v?himmill??n
     * @param maxL montako merkki? saa olla enimmill??n
     * @return true jos tunnus on syntaktisesti oikein
     * @example
     * <pre name="test">
     *    onValidiTunnus("soturi123", 6, 30)  === true;
     *    onValidiTunnus("?!jdkds", 2, 10)    === false;
     *    onValidiTunnus("123UpQz", 10, 14)   === false;
     *    onValidiTunnus("MattiM" , 6, 12)    === true;
     * </pre>
     */
    public static boolean onValidiTunnus(String tunnus, int minL , int maxL) {
        return tunnus.matches("[a-zA-Z0-9????]{" + minL + "," + maxL + "}") || tunnus.length() == 0;
    }
    
    /**
     * @param puhNro k?sitelt?v? puhelinnumero.
     * @return true jos puhelinnumero on oikein
     * @example
     * <pre name="test">
     *    onValidiPuhelinNro("0123456789")      === true;
     *    onValidiPuhelinNro("0093788351233")    === true;
     *    onValidiPuhelinNro("00abc")            === false;
     * </pre>
     */
    public static boolean onValidiPuhelinNro(String puhNro) {
        return puhNro.matches("[0-9]*");
    }
    
    /**
     * @param salasana k?sitelt?v? salasana
     * @param minPituus montako merkkia saa olla enimmill??n
     * @param maxPituus montako merkki? pit?? olla v?hint??n
     * @return true jos salasana on oikeapituista
     * @example
     * <pre name="test">
     *    onValidiSalasana("abcde", 5, 20) === true;
     *    onValidiSalasana("", 5, 20)      === false;
     * </pre>
     */
    public static boolean onValidiSalasana(String salasana, int minPituus, int maxPituus) {
        return salasana.length() >= minPituus && salasana.length() <= maxPituus;
    }
    
    /**
     * @param otsikko kasiteltava otsikko
     * @return true jos otsikko on v?hint??n yhden merkin pituinen
     * @example
     * <pre name="test">
     *    onValidiOtsikko("") === false;
     *    onValidiOtsikko("    ") === false;
     *    onValidiOtsikko(".")    === true;
     *    onValidiOtsikko("abc")  === true;
     * </pre>
     */
    public static boolean onValidiOtsikko(String otsikko) {
        return !otsikko.isBlank();
    }

}
