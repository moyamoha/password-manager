/**
 * 
 */
package passreg;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Comparator;

import fi.jyu.mit.ohj2.Mjonot;
import kanta.Strings;
import kanta.Numbers;
import kanta.Checkings;

/**
 * Rekisterin p‰‰sy, joka osaa itse huolehtia mm. tunnusnumerostaan. 
 * P‰‰syll‰ pit‰‰ olla v‰hint‰‰n otsikko, jokin k‰ytt‰j‰tunnusta vastaava kentt‰ ja salasana.
 * Lis‰ksi jokainen p‰‰sy kuuluu ainoastaan yhteen kategoriaan eik‰ se pysty muuttamaan
 * kategoriaansa kerran kun se on jo luotu.
 * @see passreg.Group
 * @author Yahya
 * @version 17.2.2021
 */
public class Entry implements ShowableData, Cloneable{
    
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
        Entry gmail = new Entry();
        Entry gmail2 = new Entry();
        
        gmail.register();
        gmail2.register();
        
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
    public Entry(int kID) {
        this.kID = kID;
    }
    
    /**
     * oletus-muodostaja
     */
    public Entry() {
        //
    }
    
    /**
     * T‰ytet‰‰n p‰‰sy gmail-tiedoilla. Tilap‰inen
     */
    public void taytaGmailTiedoilla() {
        this.otsikko = "gmail" + Numbers.rand(1, 100);
        this.tunnus  = Strings.generoiTunnus();
        this.sPosti  = this.tunnus + "@gmail.com";
        this.puhnro  = Strings.generoiPuhNro();
        this.salasana= Strings.generoiSalasana(Numbers.rand(5, 20), new boolean[] {true, false, true});
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
     *    Entry gmail1 = new Entry();
     *    Entry gmail2 = new Entry();
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
    public void register() {
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
     * 
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
     *   Entry p = new Entry();
     *   p.parse(" 1  | 4|  soturi123 | ankkaAku");
     *   p.toString().startsWith("1|4|soturi123|ankkaAku") === true;
     *   p.parse("   2");
     *   p.getTunnusNro()  === 2;
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
     *   Entry p = new Entry();
     *   p.parse("   3  |1 |  ankkaTHELion ");
     *   p.getTunnusNro() === 3;
     *   p.getKategoriaId() === 1;
     *   p.toString().startsWith("3|1|ankkaTHELion|") === true; // on enemm‰kin kuin 3 kentt‰‰, siksi loppu |
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
     * Tehdaan identiteetti klooni paasysta
     * @return uusi kloonattu paasy
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException 
     *   Entry p = new Entry();
     *   p.parse("   3  | 2|  Ankka Aku   | 123");
     *   Entry kopio = p.clone();
     *   kopio.toString() === p.toString();
     *   p.parse("   4  | 2|  toinen gmail   | 123sKfe");
     *   kopio.toString().equals(p.toString()) === false;
     * </pre>
     */
    @Override
    public Entry clone() throws CloneNotSupportedException {
        Entry uusi = (Entry) super.clone();
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
     * @example
     * <pre name="test">
     *   Entry p = new Entry();
     *   p.parse("1|  2|  gmail| | ankkaaku@gmail.com");
     *   p.anna(1) === "gmail";
     *   p.anna(2) === "";
     *   p.anna(3) === "ankkaaku@gmail.com";
     *   p.parse("|||||kkkl123");
     *   p.anna(3) === ""; 
     * </pre>
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
     * @example
     * <pre name="test">
     *   Entry p = new Entry();
     *   p.parse("1|  2|  gmail| | ankkaaku@gmail.com");
     *   p.anna(1) === "gmail";
     *   p.anna(2) === "";
     *   p.aseta(1, "toinen gmail");
     *   p.anna(1)  === "toinen gmail";
     *   p.aseta(3, "x@yahoo.com") === "v‰‰r‰ s‰hkˆpostiosoite";
     *   p.anna(3) === "ankkaaku@gmail.com";
     * </pre>
     */
    @Override
    public String aseta(int k, String arvo) {
        String s = arvo.trim();
        switch (k) {
        case 0:
            return "ei voi asettaa noin";
        case 1:
            if (Checkings.onValidiOtsikko(s))         { otsikko = s; return null; }
            return "Otsikko ei voi olla tyhj‰!";
        case 2: 
            if (Checkings.onValidiTunnus(s, 5, 30))   { tunnus = s; return null; }
            return "v‰‰r‰ tunnus";
        case 3: 
            if (Checkings.onValidiEmail(s))           { sPosti = s; return null; }
            return "v‰‰r‰ s‰hkˆpostiosoite";
        case 4: 
            if (Checkings.onValidiPuhelinNro(s))      { puhnro = s; return null; }
            return "v‰‰r‰ puhelinnumero";
        case 5: 
            if (Checkings.onValidiSalasana(s, 5, 20)) { salasana = s; return null; }
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
     * Tarkistaa onko p‰‰syn tietty kentt‰ 
     * @param k k:nnennen kent‰n numero
     * @param arvo kent‰n arvo/tuleva arvo
     * @return true jos on sopiva arvo
     * @example
     * <pre name="test">
     *   Entry p = new Entry();
     *   p.parse("1|1|facebook||akuAnkka@gmail.com||12345k||");
     *   p.onValidi(1, p.anna(1)) === true;
     *   p.onValidi(2, p.anna(2)) === true;
     *   p.onValidi(5, p.anna(5)) === true;
     *   p.onValidi(3, "akuankka")  === false; 
     * </pre>
     */
    public boolean onValidi(int k, String arvo) {
        String s = arvo.trim();
        switch (k) {
        case 1:
            return Checkings.onValidiOtsikko(s);
        case 2: 
            return Checkings.onValidiTunnus(s, 5, 30);
        case 3: 
            return Checkings.onValidiEmail(s);
        case 4: 
            return Checkings.onValidiPuhelinNro(s);
        case 5: 
            return Checkings.onValidiSalasana(s, 5, 20);
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
     * @example
     * <pre name="test">
     *   Entry p1 = new Entry();
     *   p1.parse("   3 | 1 | gmail   | soturi123");
     *   Entry p2 = new Entry();
     *   p2.parse("   3 | 1 | gmail   | soturi123");
     *   Entry p3 = new Entry();
     *   p3.parse("   3 | 1 | gmail   | soturi124");
     *   p1.equals(p2) === true;
     *   p2.equals(p1) === true;
     *   p1.equals(p3) === false;
     *   p3.equals(p2) === false;
     * </pre>
     */
    public boolean equals(Entry p) {
        if (getTunnusNro() != p.getTunnusNro()) return false;
        for (int i = 0; i <= kenttaLkm(); i++) {
            if (! anna(i).equals(p.anna(i))) return false;
        }
        return true;
    }
    
    /**
     * @param k k:nnes kentta
     * @return kentan nimi
     * @example
     * <pre name="test">
     *   Entry p = new Entry();
     *   p.getKysymys(1) === "Otsikko";
     *   p.getKysymys(1) == "otsikko" === false;
     *   p.getKysymys(4) === "Puhelin numero";
     *   p.getKysymys(10) === null;
     * </pre>
     */
    public String getKysymys(int k) {
        switch (k) {
        case 1: return "Otsikko";
        case 2: return "Tunnus";
        case 3: return "S‰hkˆposti";
        case 4: return "Puhelin numero";
        case 5: return "Salasana";
        case 6: return "URL";
        case 7: return "Info";
        default: return null;
        }
    }
    
    /**
     * @author Yahya
     * @version 31.3.2021
     * P‰‰syjen vertailija-luokka
     * @example
     * <pre name="test">
     *  #import passreg.Entry.Vertailija;
     *   Entry p1 = new Entry();
     *   p1.parse("1|1|gmail2|henk.gmail");
     *   Entry p2 = new Entry();
     *   p2.parse("2|1|gmail1|tyogmail");
     *   Vertailija vrt = new Vertailija(1);
     *   int tulos = vrt.compare(p1, p2); 
     *   tulos > 0 === true;
     * </pre>
     */
    public static class Vertailija implements Comparator<Entry>{
        private int k;
        
        /**
         * @param kentta kentt‰, jonka mukaan vertaillaan
         */
        public Vertailija(int kentta) {
            k = kentta;
        }
        
        @Override
        public int compare(Entry o1, Entry o2) {
            return o1.anna(k).compareTo(o2.anna(k));
        }
    }
    
}
