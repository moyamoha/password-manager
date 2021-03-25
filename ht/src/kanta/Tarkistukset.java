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
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    }
    
    /**
     * Tarkistaa onko sähköposti oikein.
     * Oikeamuotoinen sähköposti on muodossa alkuosa@loppuosa
     * Alkuosassa saa olla vain pieniä kirjaimia, isoja kirjaimia, numeroita sekä . ja _
     * Loppuosassa saa olla vain pieniä kirjaimia ja vähintään yksi piste. Piste ei saa kuitenkaan olla 
     * viimeisenä merkkinä.
     * @param email käsiteltävä sähköposti
     * @return true jos sähköposti on syntaktisesti oikein
     * @example
     * <pre name="test">
     *   onValidiEmail("abc@gmail.com") === false;
     *   onValidiEmail("abcdef@edu.hel.fi") === true;
     *   onValidiEmail("@yahoo.")        === false;
     *   onValidiEmail("...@yahoo.com") === false;
     *   onValidiEmail("kkk11kk@hotmail.com")  === true;
     *   onValidiEmail("matti.meikäläinen@gmail.com.") === false;
     *   onValidiEmail("matti.meikäläinen@gmail.com")  === false;
     *   onValidiEmail("matti.meikalainen@gmail.com")  === true;
     * </pre>
     */
    public static boolean onValidiEmail(String email) {
        return email.matches("[a-z0-9A-z_.]{6,30}@[a-z.]+[^.]") || email.length() == 0;
    }
    
    /**
     * Tarkistaa onko käyttäjätunnus oikein
     * @param tunnus käsiteltävä tunnus
     * @param minL montako merkkiä saa olla vähimmillään
     * @param maxL montako merkkiä saa olla enimmillään
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
        return tunnus.matches("[a-zA-Z0-9äÄöÖ]{" + minL + "," + maxL + "}") || tunnus.length() == 0;
    }
    
    /**
     * @param puhNro käsiteltävä puhelinnumero.
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
     * @param salasana käsiteltävä salasana
     * @param minPituus montako merkkia saa olla enimmillään
     * @param maxPituus montako merkkiä pitää olla vähintään
     * @return true jos salasana on oikeapituista
     */
    public static boolean onValidiSalasana(String salasana, int minPituus, int maxPituus) {
        return salasana.length() >= minPituus && salasana.length() <= maxPituus;
    }
    
    /**
     * @param otsikko käsiteltävä otsikko
     * @return true jos otsikko on oikein
     */
    public static boolean onValidiOtsikko(String otsikko) {
        //String eiSallitut = "_?!></&%#.:;,\\*'^~+-\\\\\"=)(\\[\\]{}|$€";
        return otsikko.length() > 0 && otsikko.matches(".*");
    }
    

}
