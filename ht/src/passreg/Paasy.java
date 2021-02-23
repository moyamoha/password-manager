/**
 * 
 */
package passreg;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.regex.Pattern;

import kanta.MerkkijononGenerointi;
import kanta.Numerot;

/**
 * @author Yahya
 * @version 17.2.2021
 *
 */
@SuppressWarnings("unused")
public class Paasy {
    //TODO: toteutus tähän
    
    private int         tunnusNro   = 0;
    private int         kid         = 0;
    private String      otsikko     = "";
    private String      tunnus      = "";
    private String      email       = "";
    private String      password    = "";
    private String      puhnro      = "";
    private String      url         = "";
    private String      info        = "";
    
    private static int  seuraavaNro  = 1;

    /**
     * @param args ei käytössä
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
        // TODO Auto-generated method stub
        this.otsikko = "gmail" + Numerot.rand(1, 100);
        this.tunnus  = MerkkijononGenerointi.generoiTunnus();
        this.email   = this.tunnus + "@gmail.com";
        this.puhnro  = MerkkijononGenerointi.generoiPuhNro();
        this.password= MerkkijononGenerointi.generoiSalasana(Numerot.rand(5, 20), true, false, true);
        this.url     = "www.google.com";
        this.info    = "gmail tili";
    }

    /**
     * 
     * @param out kohtdetietovirta
     */
    public void tulosta(PrintStream out) {
        // TODO Auto-generated method stub
        out.println(String.format("%03d", tunnusNro) + " " + this.otsikko);
        out.println(" tunnus: \t" + this.tunnus);
        out.println(" sähköposti: \t" + this.email);
        out.println(" puhelinnro: \t" + this.puhnro);
        out.println(" salasana: \t" + this.password);
        out.println(" url: \t\t" + this.url);
        out.println(" lisää tietoa: \t" + this.info);
    }
    
    
    /**
     * @param os kohdetietovirta
     */
    public void tulosta(OutputStream os) {
        tulosta(os);
    }
    
    
    /**
     * @return pääsyn saatu tunnusnumero
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
     * 
     * @return pääsyn tunnusnumero
     */
    public int getTunnusNro() {
        return this.tunnusNro;
    }
    
    
    
}
