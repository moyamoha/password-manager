/**
 * 
 */
package passreg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Luokkaa, joka kokoaa yhteen alkiot.
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   Kategoriat                          | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    |  - kategoria      | 
 * | - pitää yllä varsinaista kategoriarekisteriä,      |                   |
 * |     eli osaa lisää ja poistaa kategorian           |                   | 
 * | - lukee ja kirjoittaa kategorioita tiedostoon      |                   | 
 * | - osaa etsiä ja lajitella                          |                   | 
 * |                                                    |                   |
 * |-------------------------------------------------------------------------
 * @author Yahya
 * @version 1.3.2021
 *
 */
@SuppressWarnings("unused")
public class Kategoriat implements Iterable<Kategoria> {

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
     * @param args ei kÃ¤ytÃ¶ssÃ¤
     */
    public static void main(String[] args) {
        //
        Kategoriat kgt = new Kategoriat();
        
        Kategoria kg1 = new Kategoria("some");
        kg1.rekisteroi();
        
        Kategoria kg2 = new Kategoria("opiskelu");
        kg2.rekisteroi();
        
        Kategoria kg3 = new Kategoria("tyÃ¶");
        kg3.rekisteroi();
        
        kgt.lisaa(kg1); kgt.lisaa(kg2); kgt.lisaa(kg3);
        int lkm = kgt.getLkm();
        for(int i = 0; i < lkm; i++) {
            kgt.anna(i).tulosta(System.out);
        }
    }
    
    /**
     * @param kategoria lisÃ¤ttÃ¤vÃ¤ kategoria
     * @example
     * <pre name="test">
     *   Kategoriat kgt = new Kategoriat();
     *   Kategoria k1 = new Kategoria("työ");
     *   Kategoria k2 = new Kategoria("opiskelu");
     *   Kategoria k3 = new Kategoria("muu");
     *   kgt.getLkm()   === 0;
     *   kgt.lisaa(k1);
     *   kgt.getLkm()   === 1;
     *   kgt.lisaa(k2);
     *   kgt.getLkm()   === 2;
     *   kgt.lisaa(k3);
     *   kgt.getLkm()   === 3;
     * </pre>
     */
    public void lisaa(Kategoria kategoria) {
        alkiot.add(kategoria);
    }
 
    
    /**
     * Palauttaa kategorioiden lukumÃ¤Ã¤rÃ¤n
     * @return kategorioiden lkm
     */
    public int getLkm() {
        return alkiot.size();
    }
    
    /**
     * Poistaa kategoria, jolla tunnusnumerona <b>nro</b>
     * @param nro poistettavan kategorian tunnusnro
     */
    public void poista(int nro) {
        int lkm = this.getLkm();
        for (int i = 0; i < lkm; i++) {
            if (this.anna(i).getTunnusNro() == nro) {
                alkiot.remove(i);
                lkm--;
            }
        }
    }
    
    /**
     * Palauttaa yksittäinen kategoria tietystÃ¤ indeksistÃ¤
     * @param i annettavan kategorian indeksi
     * @return Kategoria kyseisestÃ¤ indeksistÃ¤
     * @throws IndexOutOfBoundsException jos vÃ¤Ã¤rÃ¤stÃ¤ indeksista haetaan
     * @example
     * <pre name="test">
     *   Kategoriat kgt = new Kategoriat();
     *   
     *   Kategoria kg1 = new Kategoria("työ");
     *   kg1.rekisteroi();
     *
     *   Kategoria kg2 = new Kategoria("muu");
     *   kg2.rekisteroi();
     *   
     *   Kategoria kg3 = new Kategoria("opiskelu");
     *   kg3.rekisteroi();
     *   
     *   kgt.lisaa(kg1);
     *   kgt.lisaa(kg2);    
     *   kgt.lisaa(kg3);    
     *   
     *   kgt.anna(0).getNimi()  === "työ";
     *   kgt.anna(1).getNimi()  === "muu";
     *   kgt.anna(2).getNimi()  === "opiskelu";
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
        //TODO: oikeaa tiedostonluku tÃ¤hÃ¤n
    }
    
    
    /**
     *  Tallettaa kategorioiden tiedot tiedostoon
     * @throws IOException jos kirjoittamisessa tapahtuu jokin vika
     */
    public void tallenna() throws IOException {
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

    @Override
    public Iterator<Kategoria> iterator() {
        // TODO Auto-generated method stub
        return new Iter();
    }
    
    /**
     * @author Yahya
     * @version 8.3.2021
     *
     */
    public class Iter implements Iterator<Kategoria> {
        
        int kohdalla;
        
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }

        @Override
        public Kategoria next() {
            if (!hasNext()) throw new NoSuchElementException("Ei oo enää");
            return anna(kohdalla++);
        }
        
    }

}

