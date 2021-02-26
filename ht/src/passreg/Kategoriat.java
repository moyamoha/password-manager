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
 * | - pitää yllä varsinaista kategoriarekisteriä,      |                   |
 * |     eli osaa lisätä ja poistaa kategorian          |                   | 
 * | - lukee ja kirjoittaa kategorioita tiedostoon      |                   | 
 * | - osaa etsiä ja lajitella                          |                   | 
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
     * @param args ei käytössä
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
     * @param kategoria lisättävä kategoria
     * Kategoriat kgt = new Kategoriat();
     */
    public void lisaa(Kategoria kategoria) {
        alkiot.add(kategoria);
    }
 
    
    /**
     * Palauttaa kategorioiden lukumäärän
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
     * Palauttaa yksittäinen kategoria tietystä indeksistä
     * @param i annettavan kategorian indeksi
     * @return Kategoria kyseisestä indeksistä
     * @throws IndexOutOfBoundsException jos väärästä indeksista haetaan
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
     * @param polku hakemisto josta tiedosto löytyy
     * @throws IOException jos lukemisessa tapahtuu vikaa
     */
    public void lueTiedostosta(String polku) throws IOException{
        tiedostonNimi = polku + "/alkiot.dat";
        //TODO: oikeaa tiedostonluku tähän
    }
    
    
    /**
     *  Tallettaa kategorioiden tiedot tiedostoon
     * @throws IOException jos kirjoittamisessa tapahtuu jokin vika
     */
    public void talleta() throws IOException {
        // TODO toimivaa tallennus tähän
    }

    /**
     * @return kaikki kategoriat
     */
    public List<Kategoria> getKategoriat() {
        // TODO Auto-generated method stub
        List<Kategoria> leikisti = new ArrayList<>();
        leikisti.add(new Kategoria("some"));
        leikisti.add(new Kategoria("työ"));
        leikisti.add(new Kategoria("opiskelu"));
        leikisti.add(new Kategoria("banking"));
        return leikisti;
    }
    
    

}

