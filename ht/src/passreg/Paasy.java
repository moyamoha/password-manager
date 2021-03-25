/**
 * 
 */
package passreg;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;
import kanta.Merkkijonot;
import kanta.Numerot;
import kanta.Tarkistukset;

/**
 * Rekisterin p‰‰sy, joka osaa itse huolehtia mm. tunnusnumerostaan
 * <pre>
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   P‰‰sy                               | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    |                   | 
 * | (- ei tied‰ passregista eik‰ k‰yttˆliittym‰st‰     |                   |
 * | - tiet‰‰ p‰‰syn kent‰t (otsikko, tunnus, puhnro    |                   |
 * |   , salasana jne.)                                 |                   | 
 * | - osaa tarkistaa tietyn kent‰n oikeellisuuden      |                   |
 * | - osaa muuttaa 1|gmail|..| - merkkijono            |                   |
 * |   p‰‰syn tiedoiksi                                 |                   | 
 * | - osaa antaa merkkijonona i:n kent‰n tiedot        |                   | 
 * | - osaa laittaa merkkijonon i:neksi kent‰ksi        |                   | 
 * |-------------------------------------------------------------------------
 * </pre>
 * @author Yahya
 * @version 17.2.2021
 */
public class Paasy implements Tietue, Cloneable{
    
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
     * T‰ytet‰‰n p‰‰sy gmail-tiedoilla. Tilap‰inen
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
     * Tulostetaan p‰‰syn tiedot
     * @param out tietovirta johon tulostetaan
     */
    @Override
    public void tulosta(PrintStream out) {
        out.println(" \t" + getTunnusNro() + this.otsikko);
        out.println(" \ttunnus: \t" + this.tunnus);
        out.println(" \ts‰hkˆposti: \t" + this.sPosti);
        out.println(" \tpuhelinnro: \t" + this.puhnro);
        out.println(" \tsalasana: \t" + this.salasana);
        out.println(" \turl: \t\t" + this.url);
        out.println(" \tlis‰‰ tietoa: \t" + this.info);
    }
    
    
    /**
     * Tulostetaan p‰‰syn tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Rekisterˆid‰‰n p‰‰sy
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
     * @return p‰‰syn tunnusnumero
     */
    @Override
    public int getTunnusNro() {
        return this.tunnusNro;
    }
    
    /**
     * Asettaa tunnusnumeron ja samalla varmistaa ett‰
     * seuraava numero on aina suurempi kuin t‰h‰n menness‰ suurin.
     * @param nr asetettava tunnusnumero
     */
    private void setTunnusNro(int nr) {
        tunnusNro = nr;
        if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;
    }
    
    /**
     * @return p‰‰syn otsikko
     */
    public String getOtsikko() {
        return this.otsikko;
    }
    
    
    /**
     * Palauttaa tiedon siit‰ mihin kategoriaan p‰‰sy kuuluu
     * @return kategorian tunnusNro
     */
    public int getKategoriaId() {
        return this.kID;
    }
    
    /**
     * Palauttaa rivi, jossa p‰‰syn tiedot muodsossa 1|2|gmail|abrkjIhejjf|ankka@gmail.com... 
     * @example
     * <pre name="test">
     *   Paasy p = new Paasy();
     *   p.parse(" 1  | 4|  soturi123 | ankka@yahoo.com");
     *   p.toString().startsWith("1|4|soturi123|ankka@yahoo.com") === true;
     * </pre>
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("");
        sb.append(tunnusNro+ "|"); 
        sb.append(kID + "|");
        String erotin = "";
        for (int k = ekaKentta(); k <= kenttaLkm(); k++) {
            sb.append(erotin);
            sb.append(anna(k));
            erotin = "|";
        }
        return sb.toString();
    }
    
    /**
     * Selvit‰‰ j‰senen tiedot | erotellusta merkkijonosta
     * Pit‰‰ huolen ett‰ seuraavaNro on suurempi kuin tuleva tunnusNro.
     * @param rivi josta j‰senen tiedot otetaan
     * 
     * @example
     * <pre name="test">
     *   Paasy p = new Paasy();
     *   p.parse("   3  |1 |  ankkaTHELion ");
     *   p.getTunnusNro() === 3;
     *   p.getKategoriaId() === 1;
     *   p.toString().startsWith("3|1|ankkaTHELion|") === true; // on enemm‰kin kuin 3 kentt‰‰, siksi loppu |
     *
     *   p.rekisteroi();
     *   int n = p.getTunnusNro();
     *   p.parse(""+(n+20));       // Otetaan merkkijonosta vain tunnusnumero
     *   p.rekisteroi();           // ja tarkistetaan ett‰ seuraavalla kertaa tulee yht‰ isompi
     *   p.getTunnusNro() === n+20+1;
     * </pre>
     */
    @Override
    public void parse( String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
        setKid(Mjonot.erota(sb, '|', getKategoriaId()));
        for (int i = ekaKentta(); i <= kenttaLkm(); i++) {
            aseta(i, Mjonot.erota(sb, '|'));
        }
    }

    /**
     * @param kID kategorian id, joka asetetaan t‰ll‰ p‰‰sylle
     */
    private void setKid(int kID) {
        if (kID >= 0) this.kID = kID;
        return;
    }

    @Override
    public String getView() {
        return getOtsikko();
    }

    /**
     * @param ehto mink‰ ehdon perusteella haetaan
     * @param kentta mik‰ on se ehdon tarkennus
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

    /**
     * @return kenttien lukum‰‰r‰n
     */
    @Override
    public int kenttaLkm() {
        return 7;
    }
    
    /**
     * @param k kent‰n j‰rjestys
     * @return k:nnes kent‰n arvo
     */
    @Override
    public String anna(int k) {
        switch (k) {
        case 0: return "" + kID;
        case 1: return otsikko;
        case 2: return tunnus;
        case 3: return sPosti;
        case 4: return puhnro;
        case 5: return salasana;
        case 6: return url;
        case 7: return info;
        default:
            return "ei mit‰‰n";
        }
    }

    /**
     * @param k kent‰n numero
     * @param arvo mik‰ tulee asetettua kent‰n arvoksi
     * @return virhe, jos arvo ei ole sopiva
     */
    @Override
    public String aseta(int k, String arvo) {
        String s = arvo.trim();
        switch (k) {
        case 0:
            return "ei voi asettaa noin";
        case 1:
            if (Tarkistukset.onValidiOtsikko(s))         { otsikko = s; return null; }
            return "Liian lyhyt otsikko tai v‰‰ri‰ kirjaimia ";
        case 2: 
            if (Tarkistukset.onValidiTunnus(s, 5, 30))   { tunnus = s; return null; }
            return "v‰‰r‰ tunnus";
        case 3: 
            if (Tarkistukset.onValidiEmail(s))           { sPosti = s; return null; }
            return "v‰‰r‰ s‰hkˆpostiosoite";
        case 4: 
            if (Tarkistukset.onValidiPuhelinNro(s))      { puhnro = s; return null; }
            return "v‰‰r‰ puhelinnumero";
        case 5: 
            if (Tarkistukset.onValidiSalasana(s, 5, 20)) { salasana = s; return null; }
            return "Salasanan vaadittu pituus on v‰lill‰ 5-20";
        case 6:
            url = s; return null;
         case 7:
            info = s; return null;
        default:
            return null;
        }
    }
    
    /**
     * @param k k:nnennen kent‰n numero
     * @param arvo kent‰n arvo/tuleva arvo
     * @return true jos on sopiva arvo
     */
    public boolean onValidi(int k, String arvo) {
        String s = arvo.trim();
        switch (k) {
        case 1:
            return Tarkistukset.onValidiOtsikko(s);
        case 2: 
            return Tarkistukset.onValidiTunnus(s, 5, 30);
        case 3: 
            return Tarkistukset.onValidiEmail(s);
        case 4: 
            return Tarkistukset.onValidiPuhelinNro(s);
        case 5: 
            return Tarkistukset.onValidiSalasana(s, 5, 20);
        case 6:
            return true;
         case 7:
            return true;
        default:
            return true;
        }
    }

    /**
     * @return ekan kent‰n numero
     */
    @Override
    public int ekaKentta() {
        return 1;
    }
    
    /**
     * @param p p‰‰sy johon verrataan
     * @return true jos p‰‰syjen kenttien arvot ovat samoja
     */
    public boolean equals(Paasy p) {
        if (getTunnusNro() != p.getTunnusNro()) return false;
        for (int i = 0; i <= kenttaLkm(); i++) {
            if (! anna(i).equals(p.anna(i))) return false;
        }
        return true;
    }
}
