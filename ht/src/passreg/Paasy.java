/**
 * 
 */
package passreg;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;
import kanta.Merkkijonot;
import kanta.Numerot;

/**
 * Rekisterin p��sy, joka osaa itse huolehtia mm. tunnusnumerostaan
 * <pre>
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   P��sy                               | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    |                   | 
 * | (- ei tied� passregista eik� k�ytt�liittym�st�     |                   |
 * | - tiet�� p��syn kent�t (otsikko, tunnus, puhnro    |                   |
 * |   , salasana jne.)                                 |                   | 
 * | - osaa tarkistaa tietyn kent�n oikeellisuuden      |                   |
 * | - osaa muuttaa 1|gmail|..| - merkkijono            |                   |
 * |   p��syn tiedoiksi                                 |                   | 
 * | - osaa antaa merkkijonona i:n kent�n tiedot        |                   | 
 * | - osaa laittaa merkkijonon i:neksi kent�ksi        |                   | 
 * |-------------------------------------------------------------------------
 * </pre>
 * @author Yahya
 * @version 17.2.2021
 */
public class Paasy implements Tietue{
    
    private int         tunnusNro       = 0 ;
    private int         kID             = 0 ;
    private String      otsikko         = "";
    private String      tunnus          = "";
    private String      sPosti          = "";
    private String      puhnro          = "";
    private String      salasana        = "";
    private String      url             = "";
    private String      info            = "";
    
    private static int  seuraavaNro  = 1;

    /**
     * @param args ei k�yt�ss�
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
     * @param kID kategorian id
     */
    public Paasy(int kID) {
        this.kID = kID;
    }
    
    /**
     * oletus-muodostaja
     */
    public Paasy() {
        //
    }
    
    /**
     * T�ytet��n p��sy gmail-tiedoilla. Tilap�inen
     */
    public void taytaGmailTiedoilla() {
        this.otsikko = "gmail" + Numerot.rand(1, 100);
        this.tunnus  = Merkkijonot.generoiTunnus();
        this.sPosti  = this.tunnus + "@gmail.com";
        this.puhnro  = Merkkijonot.generoiPuhNro();
        this.salasana= Merkkijonot.generoiSalasana(Numerot.rand(5, 20), new boolean[] {true, false, true});
        this.url     = "www.google.com";
        this.info    = "gmail tili";
    }

    /**
     * Tulostetaan p��syn tiedot
     * @param out tietovirta johon tulostetaan
     */
    @Override
    public void tulosta(PrintStream out) {
        out.println(" \t" + getTunnusNro() + this.otsikko);
        out.println(" \ttunnus: \t" + this.tunnus);
        out.println(" \ts�hk�posti: \t" + this.sPosti);
        out.println(" \tpuhelinnro: \t" + this.puhnro);
        out.println(" \tsalasana: \t" + this.salasana);
        out.println(" \turl: \t\t" + this.url);
        out.println(" \tlis�� tietoa: \t" + this.info);
    }
    
    
    /**
     * Tulostetaan p��syn tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Rekister�id��n p��sy
     * @example
     * <pre name="test">
     *    Paasy gmail1 = new Paasy();
     *    Paasy gmail2 = new Paasy();
     *    gmail1.getTunnusNro()  === 0;
     *    gmail1.rekisteroi();
     *    int n1 = gmail1.getTunnusNro();
     *    gmail2.getTunnusNro() === 0;
     *    gmail2.rekisteroi();
     *    int n2 = gmail2.getTunnusNro();
     *    n2 == n1 + 1 === true;
     * </pre>
     */
    @Override
    public void rekisteroi() {
        this.tunnusNro = seuraavaNro;
        seuraavaNro++;
    }
    
    /**
     * @return p��syn tunnusnumero
     */
    @Override
    public int getTunnusNro() {
        return this.tunnusNro;
    }
    
    /**
     * Asettaa tunnusnumeron ja samalla varmistaa ett�
     * seuraava numero on aina suurempi kuin t�h�n menness� suurin.
     * @param nr asetettava tunnusnumero
     */
    private void setTunnusNro(int nr) {
        tunnusNro = nr;
        if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;
    }
    
    /**
     * @return p��syn otsikko
     */
    public String getOtsikko() {
        return this.otsikko;
    }
    
    
    /**
     * Palauttaa tiedon siit� mihin kategoriaan p��sy kuuluu
     * @return kategorian tunnusNro
     */
    public int getKategoriaId() {
        return this.kID;
    }
    
    /**
     * Palauttaa rivi, jossa p��syn tiedot muodsossa 1|2|gmail|abrkjIhejjf|ankka@gmail.com... 
     * @example
     * <pre name="test">
     *   Paasy p = new Paasy();
     *   p.parse(" 1  | 4|  soturi123 | ankka@yahoo.com");
     *   p.toString().startsWith("1|4|soturi123|ankka@yahoo.com") === true;
     * </pre>
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(tunnusNro); sb.append("|");
        sb.append(kID); sb.append("|");
        sb.append(otsikko); sb.append("|");
        sb.append(tunnus); sb.append("|");
        sb.append(sPosti); sb.append("|");
        sb.append(puhnro); sb.append("|");
        sb.append(salasana); sb.append("|");
        sb.append(url); sb.append("|");
        sb.append(info);
        return sb.toString();
    }
    
    /**
     * Selvit�� j�senen tiedot | erotellusta merkkijonosta
     * Pit�� huolen ett� seuraavaNro on suurempi kuin tuleva tunnusNro.
     * @param rivi josta j�senen tiedot otetaan
     * 
     * @example
     * <pre name="test">
     *   Paasy p = new Paasy();
     *   p.parse("   3  |1 |  ankkaTHELion ");
     *   p.getTunnusNro() === 3;
     *   p.getKategoriaId() === 1;
     *   p.toString().startsWith("3|1|ankkaTHELion|") === true; // on enemm�kin kuin 3 kentt��, siksi loppu |
     *
     *   p.rekisteroi();
     *   int n = p.getTunnusNro();
     *   p.parse(""+(n+20));       // Otetaan merkkijonosta vain tunnusnumero
     *   p.rekisteroi();           // ja tarkistetaan ett� seuraavalla kertaa tulee yht� isompi
     *   p.getTunnusNro() === n+20+1;
     * </pre>
     */
    @Override
    public void parse( String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
        setKid(Mjonot.erota(sb, '|', getKategoriaId()));
        otsikko = Mjonot.erota(sb, '|', otsikko);
        tunnus = Mjonot.erota(sb, '|', tunnus);
        sPosti = Mjonot.erota(sb, '|', sPosti);
        puhnro = Mjonot.erota(sb, '|', puhnro);
        salasana = Mjonot.erota(sb, '|', salasana);
        url = Mjonot.erota(sb, '|', url);
        info = Mjonot.erota(sb, '|', info);
    }

    /**
     * @param kID kategorian id, joka asetetaan t�ll� p��sylle
     */
    public void setKid(int kID) {
        if (kID >= 0) this.kID = kID;
        return;
    }

    @Override
    public String getView() {
        return getOtsikko();
    }

    /**
     * @param ehto mink� ehdon perusteella haetaan
     * @param kentta mik� on se ehdon tarkennus
     * @return true jos vastaa hakukriteerit
     */
    public boolean oletko(@SuppressWarnings("unused") String ehto, @SuppressWarnings("unused") String kentta) {
        return true;
    }
    
    /**
     * 
     */
    @Override
    public Paasy clone() throws CloneNotSupportedException {
        Paasy uusi = (Paasy) super.clone();
        return uusi;
    }
}
