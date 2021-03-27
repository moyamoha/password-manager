/**
 * 
 */
package passreg;

import java.io.OutputStream;
import java.io.PrintStream;
import fi.jyu.mit.ohj2.Mjonot;
import kanta.Tarkistukset;

/**
 * <pre>
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   Kategoria                           | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    |                   | 
 * | (- ei tied� kerhosta, eik� k�ytt�liittym�st�)      |                   | 
 * | - tiet�� kategorian kent�t                         |                   | 
 * | - osaa tarkistaa tietyn kent�n oikeellisuuden      |                   |
 * |   syntaksin)                                       |                   | 
 * | - osaa muuttaa 4|some - merkkijonon                |                   |
 * |   kategorian tiedoiksi ja p�invastoin              |                   | 
 * | - osaa antaa merkkijonona i:n kent�n tiedot        |                   | 
 * | - osaa laittaa merkkijonon i:neksi kent�ksi        |                   | 
 * |-------------------------------------------------------------------------
 * </pre>
 * @author Yahya
 * @version 1.3.2021
 *
 */
public class Kategoria implements Tietue, Cloneable {
    
    private String nimi     = "";
    private int tunnusNro;
    
    private static int seuraavaNro = 1;
    

    /**
     * @param args ei k�yt�ss�
     */
    public static void main(String[] args) {
        Kategoria kg = new Kategoria("some");
        kg.tulosta(System.out); // tulostaa 0 some
        int nro = kg.getTunnusNro();
        System.out.println(nro);  // tulostaa 0
        kg.rekisteroi();
        System.out.println(kg.getTunnusNro()); // tulostaa 1
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
    public Kategoria(String inimi) {
        this.nimi = inimi;
    }
    
    /**
     * Tulostetaan kategoria kohdetietovirtaan
     * @param out kohdetietovirta
     */
    @Override
    public void tulosta(PrintStream out) {
        out.println(getTunnusNro() + " " + this.getNimi());
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
    @Override
    public int getTunnusNro() {
        return this.tunnusNro;
    }
    

    /**
     * Rekister�id��n kategoria. Samalla kasvatetaan seuraavaNro yhdell�.
     * @example
     * <pre name="test">
     *   Kategoria k1 = new Kategoria("opiskelu");
     *   k1.rekisteroi(); 
     *   Kategoria k2 = new Kategoria("ty�");
     *   k2.rekisteroi();
     *   Kategoria k3 = new Kategoria("pankkijutut");
     *   k3.rekisteroi();
     *   int n1 = k1.getTunnusNro();
     *   int n2 = k2.getTunnusNro();
     *   int n3 = k3.getTunnusNro();
     *   n3 > n2 === true;
     *   n2 = n1 +1 ;
     *   n1 < n2 && n2 < n3 === true;
     * </pre>
     */
    @Override
    public void rekisteroi() {
        this.tunnusNro = seuraavaNro;
        seuraavaNro++;
    }

    /**
     * Palauttaa rivi, jossa kategorian tiedot muodsossa 1|ty� 
     * @example
     * <pre name="test">
     *   Kategoria k = new Kategoria();
     *   k.parse(" 1  | opiskelu");
     *   k.toString() === "1|opiskelu";
     *   k.getTunnusNro() === 1;
     * </pre>
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append(tunnusNro + "|"); 
        String erotin = "";
        for (int k = ekaKentta(); k <= kenttaLkm(); k++) {
            sb.append(erotin);
            sb.append(anna(k));
            erotin = "|";
        }
        return sb.toString();
    }
    
    /**
     * Selvit�� kategorian tiedot | erotellusta merkkijonosta
     * Pit�� huolen ett� seuraavaNro on suurempi kuin tuleva tunnusNro.
     * @param rivi josta j�senen tiedot otetaan
     * @example
     * <pre name="test">
     *   Kategoria k = new Kategoria();
     *   k.parse("1    | opiskelu");
     *   k.getTunnusNro() === 1;
     *   k.getNimi()      === "opiskelu";
     *   k.toString()     === "1|opiskelu"
     *
     *   k.rekisteroi();
     *   int n = k.getTunnusNro();
     *   k.parse(""+(n+20));       // Otetaan merkkijonosta vain tunnusnumero
     *   k.rekisteroi();           // ja tarkistetaan ett� seuraavalla kertaa tulee yht� isompi
     *   k.getTunnusNro() === n+20+1;
     * </pre>
     */
    @Override
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
        for (int i = ekaKentta(); i <= kenttaLkm(); i++) {
            aseta(i, Mjonot.erota(sb, '|'));
        }
    }

    /**
     * Asettaa tunnusnumeron ja samalla varmistaa ett�
     * seuraava numero on aina suurempi kuin t�h�n menness� suurin.
     * @param nr asetettava tunnusnumero
     */
    private void setTunnusNro(int nro) {
        tunnusNro = nro;
        if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;
    }

    @Override
    public String getView() {
        return getNimi();
    }
    
    /**
     * @return 1 eli ensimm�isen kent�n j�rjestysluku
     */
    @Override
    public int ekaKentta() { return 1; }
    
    /**
     * @return 1 koska t�ll� on pelk�st��n yksi kentt�
     */
    @Override
    public int kenttaLkm() { return 1; }
    
    
    @Override
    public String aseta(int k, String arvo) {
        String s = arvo.trim();
        switch (k) {
        case 1:
            if (Tarkistukset.onValidiOtsikko(arvo)) {nimi = s; return null; }
            return "v��r� nimi";
        default:
            return null;
        }
        
    }

    @Override
    public String anna(int k) {
        switch (k) {
        case 0:
            return "" + getTunnusNro();
        case 1:
            return nimi;
        default:
            return null;
        }
    }
    
    @Override
    public Kategoria clone() throws CloneNotSupportedException {
        Kategoria k;
        k = (Kategoria) super.clone();
        return k;
    }
    
    /**
     * @param kat kategoria johon verrataan
     * @return true jos molemmat samoja
     */
    public boolean equals(Kategoria kat) {
        if (kat.getTunnusNro() != this.getTunnusNro()) return false;
        for (int k = ekaKentta(); k <= kenttaLkm(); k++) {
            if ( ! anna(k).equals(kat.anna(k))) return false;
        }
        return true;
    }
    
    /**
     * @param k k:nnes kentta
     * @return kentan nimi
     */
    public String getKysymys(int k) {
        switch (k) {
        case 1: return "Nimi";
        default: return null;
        }
    }
    
}
 