/**
 * 
 */
package passreg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Luokkaa, joka kokoaa yhteen alkiot.
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   Kategoriat                          | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    |  - kategoria      | 
 * | - pit�� yll� varsinaista kategoriarekisteri�,      |                   |
 * |     eli osaa lis�t� ja poistaa kategorian          |                   | 
 * | - lukee ja kirjoittaa kategorioita tiedostoon      |                   | 
 * | - osaa etsi� ja lajitella                          |                   | 
 * |                                                    |                   |
 * |-------------------------------------------------------------------------
 * @author Yahya
 * @version 17.2.2021
 *
 */
@SuppressWarnings("unused")
public class Kategoriat {

    private final List<Kategoria> alkiot = new ArrayList<>();
    private String tiedostonNimi = "";
    
    /**
     * Alustetaan Kategoriat
     */
    public Kategoriat() {
        //alkiot = new ArrayList<Kategoria>();
    }
    
    /**
     * 
     * @param args ei k�yt�ss�
     */
    public static void main(String[] args) {
        //
        Kategoriat kgt = new Kategoriat();
        
        Kategoria kg1 = new Kategoria();
        kg1.rekisteroi();
        kg1.taytaSomeenTiedoilla();
        
        Kategoria kg2 = new Kategoria();
        kg2.rekisteroi();
        kg2.taytaSomeenTiedoilla();
        
        Kategoria kg3 = new Kategoria();
        kg3.rekisteroi();
        kg2.taytaSomeenTiedoilla();
        
        kgt.lisaa(kg1); kgt.lisaa(kg2); kgt.lisaa(kg3);
        for(int i = 0; i < kgt.getLkm(); i++) {
            kgt.anna(i).tulosta(System.out);
        }
    }
    
    /**
     * @param kategoria lis�tt�v� kategoria
     * Kategoriat kgt = new Kategoriat();
     */
    public void lisaa(Kategoria kategoria) {
        alkiot.add(kategoria);
    }
 
    
    /**
     * Palauttaa kategorioiden lukum��r�n
     * @return kategorioiden lkm
     */
    public int getLkm() {
        return alkiot.size();
    }
    
    /**
     * Poistaa kategoria, jolla tunnusnumerona <b>nro</b>
     * @param nro poistettavan kategorian tunnusnro
     * @return montako kategoria poistettiin
     */
    public int poista(int nro) {
        return 0;
    }
    
    /**
     * Palauttaa yksitt�inen kategoria tietyst� indeksist�
     * @param i annettavan kategorian indeksi
     * @return Kategoria kyseisest� indeksist�
     * @throws IndexOutOfBoundsException jos v��r�st� indeksista haetaan
     * @example
     * <pre name="test">
     *   Kategoriat kgt = new Kategoriat();
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
     *   kgt.lisaa(kg1);
     *   kgt.lisaa(kg2);    
     *   kgt.lisaa(kg3);    
     *   
     *   kgt.anna(0).getTunnusNro()  === 1;
     *   kgt.anna(1).getTunnusNro()  === 2;
     *   kgt.anna(2).getTunnusNro()  === 3;
     * </pre>
     */
    public Kategoria anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= getLkm()) {
            throw new IndexOutOfBoundsException("Laiton indeksi " + i);
        }
        return alkiot.get(i);
    }
    
    
    /**
     * Lukee kategorioiden tietoja tiedostosta
     * @param polku hakemisto josta tiedosto l�ytyy
     * @throws IOException jos lukemisessa tapahtuu vikaa
     */
    public void lueTiedostosta(String polku) throws IOException{
        tiedostonNimi = polku + "/alkiot.dat";
        //TODO: oikeaa tiedostonluku t�h�n
    }
    
    
    /**
     *  Tallettaa kategorioiden tiedot tiedostoon
     * @throws IOException jos kirjoittamisessa tapahtuu jokin vika
     */
    public void talleta() throws IOException {
        // TODO toimivaa tallennus t�h�n
    }

    /**
     * @return kaikki kategoriat
     */
    public List<Kategoria> getKategoriat() {
        // TODO Auto-generated method stub
        List<Kategoria> leikisti = new ArrayList<>();
        leikisti.add(new Kategoria("some"));
        leikisti.add(new Kategoria("ty�"));
        leikisti.add(new Kategoria("opiskelu"));
        leikisti.add(new Kategoria("banking"));
        return leikisti;
    }
    
    

}

