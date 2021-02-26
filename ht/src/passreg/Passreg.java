/**
 * 
 */
package passreg;

import java.io.IOException;
import java.util.List;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   Passreg                             | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    | - Paasyt          | 
 * | - huolehtii Paasyt ja Kategoriat - luokkien        | - Kategoriat      | 
 * |   v‰lisest‰ yhteistyˆst‰ ja v‰litt‰‰ n‰it‰ tietoja | - Paasy           | 
 * |   pyydett‰ess‰                                     | - Kategoria       | 
 * | - lukee ja kirjoittaa passregin tiedostoon         |                   | 
 * |    pyyt‰m‰ll‰ apua avustajiltaan                   |                   | 
 * |-------------------------------------------------------------------------
 * @author Yahya
 * @version 18.2.2021
 * 
 */
public class Passreg {
    
    private final Paasyt paasyt = new Paasyt();
    private final Kategoriat kategoriat = new Kategoriat();
    
    
    /**
     * 
     */
    public Passreg() {
        // ei viel‰ mit‰‰n
    }
    
    /**
     * @param args e
     */
    public static void main(String[] args) {
       Passreg passreg = new Passreg();
       
       Kategoria kg1 = new Kategoria();
       kg1.rekisteroi();
       kg1.taytaSomeenTiedoilla();
       
       Kategoria kg2 = new Kategoria();
       kg2.rekisteroi();
       kg2.taytaSomeenTiedoilla();
       
       Kategoria kg3 = new Kategoria();
       kg3.rekisteroi();
       kg2.taytaSomeenTiedoilla();
       
       passreg.lisaa(kg1);
       passreg.lisaa(kg2);
       passreg.lisaa(kg3);
       
       Paasy gmail1 = new Paasy();
       gmail1.rekisteroi();
       gmail1.taytaGmailTiedoilla();
       
       
       Paasy gmail2 = new Paasy();
       gmail2.rekisteroi();
       gmail2.taytaGmailTiedoilla();
       
       Paasy gmail3 = new Paasy();
       gmail3.rekisteroi();
       gmail3.taytaGmailTiedoilla();
       
       passreg.lisaa(gmail1); passreg.lisaa(gmail2);
       passreg.lisaa(gmail3);
       
       for (int i = 0; i < passreg.getPaasytLkm(); i++) {
           passreg.annaPaasy(i).tulosta(System.out);
       }
    }
    
    
    /**
     * @return rekisterin p‰‰sym‰‰r‰
     * @example
     * <pre name="test">
     *    Passreg pg = new Passreg();
     *    pg.getPaasytLkm()  === 0;
     *    
     *    Paasy gmail1 = new Paasy();
     *    gmail1.rekisteroi();
     *    gmail1.taytaGmailTiedoilla();
     *    pg.lisaa(gmail1);
     *    
     *    pg.getPaasytLkm()  === 1;
     *     
     *    Paasy gmail2 = new Paasy();
     *    gmail2.rekisteroi();
     *    gmail2.taytaGmailTiedoilla();
     *    pg.lisaa(gmail2);
     *    
     *    pg.getPaasytLkm()  === 2;
     * </pre>
     */
    public int getPaasytLkm() {
        return paasyt.getLkm();
    }
    
    /**
     * @return rekisterin kategorian lukum‰‰r‰
     * @example
     * <pre name="test">
     *   Passreg passreg = new Passreg();
     *   
     *   Kategoria kg1 = new Kategoria();
     *   kg1.rekisteroi();
     *   kg1.taytaSomeenTiedoilla();
     *
     *   Kategoria kg2 = new Kategoria();
     *   kg2.rekisteroi();
     *   kg2.taytaSomeenTiedoilla();
     *   
     *   Kategoria kg3 = new Kategoria();
     *   kg3.rekisteroi();
     *   kg2.taytaSomeenTiedoilla();
     *   
     *   passreg.getKategoriatLkm()  === 0;
     *   passreg.lisaa(kg1);
     *   passreg.getKategoriatLkm()  === 1;
     *   passreg.lisaa(kg2);    
     *   passreg.getKategoriatLkm()  === 2;
     *   passreg.lisaa(kg3);
     *   passreg.getKategoriatLkm()  === 3;
     * </pre>  
     */
    public int getKategoriatLkm() {
        return kategoriat.getLkm();
    }
    
    /**
     * @return rekisterin kategoriat
     */
    public List<Kategoria> getKategoriat(){
        return this.kategoriat.getKategoriat();
    }
    
    /**
     * @param nro viite poistettavaan/poistettavien p‰‰syjen numeroon
     * @return montako p‰‰sy‰ poistettiin
     */
    public int poistaPaasy(@SuppressWarnings("unused") int nro) {
        return 0;
        //TODO 7 vaiheessa
    }
    
    
    /**
     * @param nro viite poistettavaan/poistettavien p‰‰syjen numeroon
     * @return montako kategoriaa poistettiin
     */
    public int PoistaKategoria(@SuppressWarnings("unused") int nro) {
        return 0;
    }
    
    
    /**
     * @param paasy lis‰tt‰v‰ p‰‰sy
     * @example
     * <pre name="test">
     *   Passreg passreg = new Passreg();
     *   
     *   Kategoria kg1 = new Kategoria();
     *   kg1.rekisteroi();
     *   kg1.taytaSomeenTiedoilla();
     *   
     *   passreg.getKategoriatLkm()  === 0;
     *   passreg.lisaa(kg1);
     *   passreg.annaKategoria(0).getNimi()  === "some";
     * </pre>
     */
    public void lisaa(Paasy paasy) {
        paasyt.lisaa(paasy);
    }
    
    
    /**
     * @param kategoria lis‰tt‰v‰ kategoria
     */
    public void lisaa(Kategoria kategoria) {
        kategoriat.lisaa(kategoria);
    }
    
    
    /**
     * @param i p‰‰syn indeksi
     * @return viite kyseiseen p‰‰syyn
     */
    public Paasy annaPaasy(int i) {
        return paasyt.anna(i);
    }
    
    
    /**
     * @param i kategorian indeksi
     * @return viite kyseiseen kategoriaan
     */
    public Kategoria annaKategoria(int i) {
        return kategoriat.anna(i);
    }
    
    /**
     * Tallettaa rekisterin tiedot tiedostoon. kesken
     * @throws IOException jos kirjoittamisessa sattuu virheit‰
     */
    public void talleta() throws IOException {
        paasyt.talleta();
        kategoriat.talleta();
       // TODO: yrit‰ tallettaa toinen vaikka toinen ep‰onnistuisi
    }
    
    /**
     * Lukee rekisterin tiedot tiedostosta
     * @param nimi hakemiston polku
     * @throws IOException jos lukeminen ei onnistu
     */
    public void lueTiedostosta(String nimi) throws IOException {
         paasyt.lueTiedostosta(nimi);
         kategoriat.lueTiedostosta(nimi);
    }
}
