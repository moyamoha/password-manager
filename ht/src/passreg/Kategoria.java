/**
 * 
 */
package passreg;

import java.io.OutputStream;
import java.io.PrintStream;

import kanta.Merkkijonot;

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
 * @version 1.3.2021
 *
 */
public class Kategoria {
    
    private String nimi     = "";
    private int tunnusNro;
    
    private static int seuraavaNro = 1;
    

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Kategoria kg = new Kategoria("some");
        kg.tulosta(System.out);
    }
    
    /**
     * oletus-muodostaja
     */
    public Kategoria() {
        // Testauksen vuoksi. TODO: poistetaan
        this.nimi = Merkkijonot.generoiKNimi();
    }
    
    
    /**
     * Muodostaja kun parametrina kategorian nimi
     * @param inimi annettava nimi
     */
    public Kategoria(String inimi) {
        this.nimi = inimi;
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
        tulosta(new PrintStream(os));
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
     * @example
     * <pre name="test">
     *   Kategoria k1 = new Kategoria("opiskelu");
     *   k1.rekisteroi(); 
     *   Kategoria k2 = new Kategoria("työ");
     *   k2.rekisteroi();
     *   Kategoria k3 = new Kategoria("pankkijutut");
     *   k3.rekisteroi();
     *   
     *   k1.getTunnusNro()  === 1;
     *   k2.getTunnusNro()  === 2;
     *   k3.getTunnusNro()  === 3;
     * </pre>
     */
    public void rekisteroi() {
        this.tunnusNro = seuraavaNro;
        seuraavaNro++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getTunnusNro());
        sb.append("|");
        sb.append(getNimi());
        return sb.toString();
    }
    
    public void parse(String rivi) {
        //
    }
    
    
    
}
 