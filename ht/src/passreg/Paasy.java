/**
 * 
 */
package passreg;

import java.io.OutputStream;
import java.io.PrintStream;

import kanta.Merkkijonot;
import kanta.Numerot;

/**
 * Rekisterin p‰‰sy, joka osaa itse huolehtia mm. tunnusnumerostaan
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   P‰‰sy                               | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    |                   | 
 * | (- ei tied‰ passregista eik‰ k‰yttˆliittym‰st‰     |                   |
 * | - tiet‰‰ p‰‰syn kent‰t (otsikko, tunnus, puhnro    |                   |
 * |   , salasana jne.)                                 |                   | 
 * | - osaa tarkistaa tietyn kent‰n oikeellisuuden      |                   |
 * |   (syntaksin)                                      |                   | 
 * | - osaa muuttaa 1|gmail|..| - merkkijonon           |                   |
 * |   p‰‰syn tiedoiksi                                 |                   | 
 * | - osaa antaa merkkijonona i:n kent‰n tiedot        |                   | 
 * | - osaa laittaa merkkijonon i:neksi kent‰ksi        |                   | 
 * |-------------------------------------------------------------------------
 * @author Yahya
 * @version 17.2.2021
 */
public class Paasy {
    
    private int         tunnusNro   = 0 ;
    @SuppressWarnings("unused")
    private int         kid         = 0 ;
    private String      otsikko     = "";
    private String      tunnus      = "";
    private String      email       = "";
    private String      password    = "";
    private String      puhnro      = "";
    private String      url         = "";
    private String      info        = "";
    
    private static int  seuraavaNro  = 1;

    /**
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        Paasy gmail = new Paasy();
        Paasy gmail2 = new Paasy();
        
        gmail.rekisteroi();
        gmail2.rekisteroi();
        
        gmail.tulosta(System.out);
        gmail.taytaGmailTiedoilla();
        gmail.tulosta(System.out);
        
        gmail2.tulosta(System.out);
        gmail2.taytaGmailTiedoilla();
        gmail2.tulosta(System.out);
    }
    
    /**
     * 
     */
    public void taytaGmailTiedoilla() {
        this.kid = 1;
        this.otsikko = "gmail" + Numerot.rand(1, 100);
        this.tunnus  = Merkkijonot.generoiTunnus();
        this.email   = this.tunnus + "@gmail.com";
        this.puhnro  = Merkkijonot.generoiPuhNro();
        this.password= Merkkijonot.generoiSalasana(Numerot.rand(5, 20), new boolean[] {true, false, true});
        this.url     = "www.google.com";
        this.info    = "gmail tili";
    }

    /**
     * 
     * @param out kohtdetietovirta
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", tunnusNro) + " " + this.otsikko);
        out.println(" tunnus: \t" + this.tunnus);
        out.println(" s‰hkˆposti: \t" + this.email);
        out.println(" puhelinnro: \t" + this.puhnro);
        out.println(" salasana: \t" + this.password);
        out.println(" url: \t\t" + this.url);
        out.println(" lis‰‰ tietoa: \t" + this.info);
    }
    
    
    /**
     * @param os kohdetietovirta
     */
    public void tulosta(OutputStream os) {
        tulosta(os);
    }
    
    
    /**
     * @return p‰‰syn saatu tunnusnumero
     * @example
     * <pre name="test">
     *    Paasy gmail1 = new Paasy();
     *    Paasy gmail2 = new Paasy();
     *    gmail1.getTunnusNro()  === 0;
     *    gmail1.rekisteroi();
     *    gmail1.getTunnusNro()  === 1; 
     *    gmail2.getTunnusNro()  === 0;
     *    gmail2.rekisteroi();
     *    gmail2.getTunnusNro()  === 2;
     * </pre>
     */
    public int rekisteroi() {
        this.tunnusNro = seuraavaNro;
        seuraavaNro++;
        return this.tunnusNro;
    }
    
    /**
     * @return p‰‰syn tunnusnumero
     */
    public int getTunnusNro() {
        return this.tunnusNro;
    }
    
    
    /**
     * @return p‰‰syn otsikko
     */
    public String getOtsikko() {
        return this.otsikko;
    }
    
    
    
}
