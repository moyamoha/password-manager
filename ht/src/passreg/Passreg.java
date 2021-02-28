/**
 * 
 */
package passreg;

import java.io.IOException;
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
    
    /**
     * 
     */
    public Passreg() {
        // ei viel‰ mit‰‰n
    }
    
    /**
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
       Passreg passreg = new Passreg();
       
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
     * @param nro viite poistettavaan/poistettavien p‰‰syjen numeroon
     * @return montako p‰‰sy‰ poistettiin
     */
    public int poistaPaasy(int nro) {
        return nro;
        //TODO 7 vaiheessa
    }

    
    /**
     * @param paasy lis‰tt‰v‰ p‰‰sy
     * @example
     * <pre name="test">
     *   Passreg passreg = new Passreg();
     *   
     *   Kategoria kg1 = new Kategoria("some");
     *   kg1.rekisteroi();
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
     * @param i p‰‰syn indeksi
     * @return viite kyseiseen p‰‰syyn
     */
    public Paasy annaPaasy(int i) {
        return paasyt.anna(i);
    }
    

    /**
     * Tallettaa rekisterin tiedot tiedostoon. kesken
     * @throws IOException jos kirjoittamisessa sattuu virheit‰
     */
    public void talleta() throws IOException {
        paasyt.talleta();
       // TODO: yrit‰ tallettaa toinen vaikka toinen ep‰onnistuisi
    }
    
    /**
     * Lukee rekisterin tiedot tiedostosta
     * @param nimi hakemiston polku
     * @throws IOException jos lukeminen ei onnistu
     */
    public void lueTiedostosta(String nimi) throws IOException {
         paasyt.lueTiedostosta(nimi);
    }
}
