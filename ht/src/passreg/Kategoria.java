/**
 * 
 */
package passreg;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   Kategoria                           | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    |                   | 
 * | (- ei tiedä kerhosta, eikä käyttöliittymästä)      |                   | 
 * | - tietää kategorian kentät                         |                   | 
 * | - osaa tarkistaa tietyn kentän oikeellisuuden      |                   |
 * |   syntaksin)                                       |                   | 
 * | - osaa muuttaa 4|some - merkkijonon                |                   |
 * |   kategorian tiedoiksi ja päinvastoin              |                   | 
 * | - osaa antaa merkkijonona i:n kentän tiedot        |                   | 
 * | - osaa laittaa merkkijonon i:neksi kentäksi        |                   | 
 * |                                                    |                   | 
 * |-------------------------------------------------------------------------
 * @author Yahya
 * @version 17.2.2021
 *
 */
public class Kategoria {
    
    private String nimi;
    private int tunnusNro;
    
    private static int seuraavaNro = 1;
    

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Kategoria kg = new Kategoria();
        kg.rekisteroi();
        kg.taytaSomeenTiedoilla();
        
        kg.tulosta(System.out);
    }
    
    /**
     * oletus-muodostaja
     */
    public Kategoria() {
        // 
    }
    
    
    /**
     * Muodostaja kun parametrina kategorian nimi
     * @param inimi annettava nimi
     */
    public Kategoria(@SuppressWarnings("unused") String inimi) {
        // TODO: toteutus tähän
        this.nimi = inimi;
    }
    
    /**
     * Malli kategoria "some (sosiaalinen media)". <i>valiaikainen</i>
     * @example
     * <pre name="test">
     *   Kategoria kg = new Kategoria();
     *   kg.rekisteroi();
     *   kg.taytaSomeenTiedoilla();
     *   
     *   kg.getNimi()  === "some";
     * </pre>
     */
    public void taytaSomeenTiedoilla() {
        this.nimi = "some";
    }
    
    /**
     * Tulostetaan kategoria kohdetietovirtaan
     * @param out kohdetietovirta
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", getTunnusNro()) + " " + this.getNimi());
    }
    
    
    /**
     * Tulostetaan Kategorian tiedot kohdetietovirtaan
     * @param os kohdetietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(os);
    }
    
    
    /**
     * @return kategorian nimi
     */
    public  String getNimi() {
        return this.nimi;
    }
    

    /**
     * @return kategorian tunnusNro
     */
    public int getTunnusNro() {
        return this.tunnusNro;
    }
    

    /**
     * Rekisteröidään kategoria. Samalla kasvatetaan seuraavaNro yhdellä.
     */
    public void rekisteroi() {
        this.tunnusNro = seuraavaNro;
        seuraavaNro++;
    }
}
