/**
 * 
 */
package kanta;

/**
 * @author Yahya
 * @version 25.2.2021
 *
 */
public class Tarkistukset {

    /**
     * @param args ei k�yt�ss�
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    }
    
    /**
     * Tarkistaa onko s�hk�posti oikein.
     * Oikeamuotoinen s�hk�posti on muodossa alkuosa@loppuosa
     * Alkuosassa saa olla vain pieni� kirjaimia, isoja kirjaimia, numeroita sek� . ja _
     * Loppuosassa saa olla vain pieni� kirjaimia ja v�hint��n yksi piste. Piste ei saa kuitenkaan olla 
     * viimeisen� merkkin�.
     * @param email k�sitelt�v� s�hk�posti
     * @return true jos s�hk�posti on syntaktisesti oikein
     * @example
     * <pre name="test">
     *   onValidiEmail("abc@gmail.com") === false;
     *   onValidiEmail("abcdef@edu.hel.fi") === true;
     *   onValidiEmail("@yahoo.")        === false;
     *   onValidiEmail("...@yahoo.com") === false;
     *   onValidiEmail("kkk11kk@hotmail.com")  === true;
     *   onValidiEmail("matti.meik�l�inen@gmail.com.") === false;
     *   onValidiEmail("matti.meik�l�inen@gmail.com")  === false;
     *   onValidiEmail("matti.meikalainen@gmail.com")  === true;
     * </pre>
     */
    public static boolean onValidiEmail(String email) {
        return email.matches("[a-z0-9A-z_.]{6,30}@[a-z.]+[^.]") || email.length() == 0;
    }
    
    /**
     * Tarkistaa onko k�ytt�j�tunnus oikein
     * @param tunnus k�sitelt�v� tunnus
     * @param minL montako merkki� saa olla v�himmill��n
     * @param maxL montako merkki� saa olla enimmill��n
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
        return tunnus.matches("[a-zA-Z0-9����]{" + minL + "," + maxL + "}") || tunnus.length() == 0;
    }
    
    /**
     * @param puhNro k�sitelt�v� puhelinnumero.
     * @return true jos puhelinnumero on oikein
     * @example
     * <pre name="test">
     *    onValidiPuhelinNro("0123456789")      === true;
     *    onValidiPuhelinNro(0093788351233")      
     * </pre>
     */
    public static boolean onValidiPuhelinNro(String puhNro) {
        return puhNro.matches("0{1}[1-9]{1}[1-9]{5,10}") || puhNro.length() == 0;
    }
    
    /**
     * @param salasana k�sitelt�v� salasana
     * @param minPituus montako merkkia saa olla enimmill��n
     * @param maxPituus montako merkki� pit�� olla v�hint��n
     * @return true jos salasana on oikeapituista
     */
    public static boolean onValidiSalasana(String salasana, int minPituus, int maxPituus) {
        return salasana.length() >= minPituus && salasana.length() <= maxPituus;
    }
    
    /**
     * @param otsikko k�sitelt�v� otsikko
     * @return true jos otsikko on oikein
     */
    public static boolean onValidiOtsikko(String otsikko) {
        //String eiSallitut = "_?!></&%#.:;,\\*'^~+-\\\\\"=)(\\[\\]{}|$�";
        return otsikko.length() > 0 && otsikko.matches(".*");
    }
    

}
