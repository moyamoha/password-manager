/**
 * 
 */
package passreg;

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
    private String tiedosto = "";
    
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
       
       Kategoria kg1 = new Kategoria("Some");
       kg1.rekisteroi();
       passreg.lisaa(kg1);
       
       for (int i = 0; i < passreg.getPaasytLkm(); i++) {
           passreg.annaPaasy(i).tulosta(System.out);
       }
       
       for (Kategoria kg : passreg.kategoriat) {
           kg.tulosta(System.out);
       }
    }
    
    /**
     * @param tiedNimi rekisterin tiedostojen sijainti
     */
    public void setTiedostonNimi(String tiedNimi) {
        if (tiedNimi != null) this.tiedosto = tiedNimi;
    }
    
    /**
     * @return rekisterin sijainti
     */
    private String getTiedostonNimi() { return this.tiedosto; }
    
    
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
     * @return rekisterin kategorioiden m‰‰r‰
     */
    public int getKategoriatLkm() {
        return kategoriat.getLkm();
    }
    
    /**
     * @param nro viite poistettavaan/poistettavien p‰‰syjen numeroon
     */
    public void poistaPaasy(int nro) {
        paasyt.poista(nro);
        //TODO 7 vaiheessa
    }
    
    /**
     * @param p p‰‰sy, jonka kategoriaID asetetaan
     * @param id asetettava kategoriaID
     */
    public void setkID(Paasy p, int id) {
        paasyt.setKid(p, id);
    }

    
    /** 
     * Lis‰t‰‰n yksitt‰inen p‰‰sy rekisteriin
     * @param paasy lis‰tt‰v‰ p‰‰sy
     * @example
     * <pre name="test">
     *   Passreg passreg = new Passreg();
     *   
     *   Paasy p1 = new Paasy();
     *   p1.taytaGmailTiedoilla();
     *   p1.rekisteroi();
     *   
     *   passreg.getPaasytLkm()  === 0;
     *   passreg.lisaa(p1);
     *   passreg.getPaasytLkm()  === 1;
     * </pre>
     */
    public void lisaa(Paasy paasy) {
        paasyt.lisaa(paasy);
    }
    
    /**
     * @param kategoria lis‰tt‰v‰ kategoria
     * @example
     * <pre name="test">
     *   Passreg passreg = new Passreg();
     *   Kategoria k1 = new Kategoria("some");
     *   k1.rekisteroi();
     *   Kategoria k2 = new Kategoria("opiskelu");
     *   k2.rekisteroi();
     *   Kategoria k3 = new Kategoria("tyˆ");
     *   k3.rekisteroi();
     *   passreg.getKategoriatLkm() === 0;
     *   passreg.annaKategoria(0); #THROWS IndexOutOfBoundsException
     *   passreg.lisaa(k1);
     *   passreg.getKategoriatLkm() === 1;
     *   passreg.annaKategoria(0).getNimi() === "some";
     *   passreg.lisaa(k2);
     *   passreg.getKategoriatLkm() === 2;
     *   passreg.annaKategoria(1).getNimi()  === "opiskelu";
     *   passreg.lisaa(k3);
     *   passreg.getKategoriatLkm() === 3;
     *   passreg.annaKategoria(2).getNimi()  === "tyˆ";
     *   passreg.annaKategoria(3); #THROWS IndexOutOfBoundsException
     * </pre>
     */
    public void lisaa(Kategoria kategoria) {
        kategoriat.lisaa(kategoria);
    }
    
    
    /**
     * Palauttaa p‰‰sy indeksissa <b>i</b>
     * @param i p‰‰syn indeksi
     * @return viite kyseiseen p‰‰syyn
     */
    public Paasy annaPaasy(int i) {
        return paasyt.anna(i);
    }
    
    /**
     * Palauttaa viite pyydettyyn kategoriaan, joka sijaitsee indeksissa <b>i</b>
     * @param i kategorian indeksi
     * @return pyydetty kategoria
     */
    public Kategoria annaKategoria(int i) {
        return kategoriat.anna(i);
    }
    

    /**
     * Tallettaa rekisterin tiedot tiedostoon. kesken
     */
    public void tallenna() {
        String virhe = "";
        try {
            paasyt.tallenna(getTiedostonNimi());
        } catch (Exception e) {
            virhe += e.getMessage();
        }
        try {
            kategoriat.tallenna(getTiedostonNimi());
        } catch (Exception e) {
            virhe += e.getMessage();
        }
        System.out.println(virhe);
    }
    
    /**
     * Lukee rekisterin tiedot tiedostosta
     * @param hakemisto hakemiston polku
     */
    public void lueTiedostosta(String hakemisto) {
         setTiedostonNimi(hakemisto);
         paasyt.lueTiedostosta(getTiedostonNimi());
         kategoriat.lueTiedostosta(getTiedostonNimi());
    }

    /**
     * Poistetaan kategoria, jonka tunnusnumero on v‰litetty parametrina.
     * Samalla p‰ivitet‰‰n kaikki ne p‰‰syt, joiden kategorian id vastasi poistetun kategorian tunnusnumeroa
     * @param tunnusNro poistettavan kategorian tunnusnumero. 
     */
    public void poistaKategoria(int tunnusNro) {
        kategoriat.poista(tunnusNro);
        paasyt.paivitakID(tunnusNro, 0);
    }
}
