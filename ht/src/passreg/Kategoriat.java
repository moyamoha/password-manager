/**
 * 
 */
package passreg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * <pre>
 * Luokkaa, joka kokoaa yhteen alkiot.
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   Kategoriat                          | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    |  - kategoria      | 
 * | - pitää yll� varsinaista kategoriarekisteri�,      |                   |
 * |     eli osaa lis�� ja poistaa kategorian           |                   | 
 * | - lukee ja kirjoittaa kategorioita tiedostoon      |                   | 
 * | - osaa etsi� ja lajitella                          |                   | 
 * |                                                    |                   |
 * |-------------------------------------------------------------------------
 * </pre>
 * @author Yahya
 * @version 1.3.2021
 *
 */
@SuppressWarnings("unused")
public class Kategoriat implements Iterable<Kategoria> {

    private final List<Kategoria> alkiot = new ArrayList<>();
    private static final String tiedostonNimi = "/kategoriat.dat";
    private boolean muutettu = false;
    
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
        
        Kategoria kg1 = new Kategoria("some");
        kg1.rekisteroi();
        
        Kategoria kg2 = new Kategoria("opiskelu");
        kg2.rekisteroi();
        
        Kategoria kg3 = new Kategoria("työ");
        kg3.rekisteroi();
        
        kgt.lisaa(kg1); kgt.lisaa(kg2); kgt.lisaa(kg3);
        
        for(Kategoria k : kgt) {
            k.tulosta(System.out);
        }
    }
    
    /**
     * @param kategoria lisättävä kategoria
     * @example
     * <pre name="test">
     *   Kategoriat kgt = new Kategoriat();
     *   Kategoria k1 = new Kategoria("ty�");
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
        muutettu = true;
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
     * @example
     * <pre name="test">
     *    Kategoriat kat = new Kategoriat();
     *    Kategoria k1 = new Kategoria("opiskelu"); 
     *    k1.rekisteroi();
     *    int n = k1.getTunnusNro();
     *    Kategoria k2 = new Kategoria("työ"); k2.rekisteroi();
     *    kat.getLkm() === 0;
     *    kat.anna(0); #THROWS IndexOutOfBoundsException
     *    kat.lisaa(k1);
     *    kat.anna(0).getNimi() === "opiskelu";
     *    kat.getLkm() === 1;
     *    kat.lisaa(k2);
     *    kat.anna(1).getNimi() === "työ";
     *    kat.getLkm() === 2;
     *    kat.poista(n);
     *    kat.getLkm() === 1;
     *    kat.poista(n+1);
     *    kat.getLkm() === 0;
     *    kat.anna(0); #THROWS IndexOutOfBoundsException 
     * </pre>
     */
    public void poista(int nro) {
        int lkm = this.getLkm();
        for (int i = 0; i < lkm; i++) {
            if (this.anna(i).getTunnusNro() == nro) {
                alkiot.remove(i);
                muutettu = true;
                lkm--;
            }
        }
    }
    
    /**
     * Palauttaa yksitt�inen kategoria tietystä indeksistä
     * @param i annettavan kategorian indeksi
     * @return Kategoria kyseisestä indeksistä
     * @throws IndexOutOfBoundsException jos väärästä indeksista haetaan
     * @example
     * <pre name="test">
     *   Kategoriat kgt = new Kategoriat();
     *   Kategoria kg1 = new Kategoria("ty�");
     *   kg1.rekisteroi();
     *   Kategoria kg2 = new Kategoria("muu");
     *   kg2.rekisteroi();
     *   Kategoria kg3 = new Kategoria("opiskelu");
     *   kg3.rekisteroi();
     *   kgt.lisaa(kg1);
     *   kgt.lisaa(kg2);    
     *   kgt.lisaa(kg3);    
     *   kgt.anna(0).getNimi()  === "ty�";
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
     * @param hakemisto hakemisto josta tiedosto l�ytyy
     * @example
     * <pre name="test">
     * #import java.io.File;
     * #import java.util.*;
     * #import java.io.*;
     *  Kategoriat kat = new Kategoriat();
     *  Kategoria k1 = new Kategoria("opiskelu"), k2 = new Kategoria("työ");
     *  k1.rekisteroi(); k2.rekisteroi();
     *  String hakemisto = "testi";
     *  File dir = new File(hakemisto);
     *  dir.mkdir();
     *  kat.lisaa(k1);
     *  kat.lisaa(k2);
     *  kat.tallenna(hakemisto);
     *  kat.lueTiedostosta(hakemisto);  // johon ladataan tiedot tiedostosta.
     *  Iterator<Kategoria> i = kat.iterator();
     *  Kategoria kTest = i.next();
     *  kTest.getNimi() === "opiskelu";
     *  Kategoria kTest2 = i.next();
     *  kTest2.getNimi()  === "työ";
     *  kat.tallenna(hakemisto);
     * </pre>
     */
    public void lueTiedostosta(String hakemisto) {
        File fTied = new File(hakemisto + tiedostonNimi);
        try {
            fTied.createNewFile();
        } catch (IOException e1) {
            //
        }
        String rivi = "";
        try (Scanner fi = new Scanner(new FileInputStream(fTied))) {
            while (fi.hasNextLine()) {
                rivi = fi.nextLine();
                Kategoria kategoria = new Kategoria();
                kategoria.parse(rivi);
                lisaa(kategoria);
            }
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
    
    
    /**
     * Tallennetaan kategoria tiedostoon. kesken
     * @param hakemisto tallennettavan tiedoston sijainti
     */
    public void tallenna(String hakemisto) {
        if (!muutettu) return;
        File fTied = new File(hakemisto + tiedostonNimi);
        try (PrintStream fo = new PrintStream(new FileOutputStream(fTied, false))) {
            for (Kategoria kategoria : this) {
                fo.println(kategoria.toString());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Tiedosto " + fTied.getAbsolutePath() + " ei löydy");
        }
    }

    /**
     * Palauttaa iteraattori kategorioiden läpikäymiseen.
     * @example
     * <pre name="test">
     *   Kategoriat kgt = new Kategoriat();
     *   Kategoria kg1 = new Kategoria("tyo");
     *   Kategoria kg2 = new Kategoria("muu");
     *   Kategoria kg3 = new Kategoria("opiskelu");
     *   Iterator<Kategoria> iter = kgt.iterator();
     *   iter.hasNext()  === false;
     *   iter.next(); #THROWS NoSuchElementException
     *   kgt.lisaa(kg1);
     *   iter.hasNext() === true;
     *   iter.next().getNimi() === "tyo";
     *   kgt.lisaa(kg2); 
     *   kgt.lisaa(kg3);
     *   iter.next()  === kg2;       
     *   iter.next()  === kg3;
     *   iter = kgt.iterator();
     *   iter.next() == iter.next() === false;
     *   iter.next().getNimi()  === "opiskelu";
     * </pre>
     */
    @Override
    public Iterator<Kategoria> iterator() {
        return new Iter();
    }
    
    /**
     * @author Yahya
     * @version 8.3.2021
     *
     */
    public class Iter implements Iterator<Kategoria> {
        
        private int kohdalla;
        
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }

        @Override
        public Kategoria next() {
            if (!hasNext()) throw new NoSuchElementException("Ei oo en��");
            return anna(kohdalla++);
        }
    }

    
    /**
     * @return true jos kategorioita on lisätty tai poistettu rekisteristä
     */
    public boolean onMuutettu() { return muutettu;}
    
    /**
     * @param b tieto siitä onko muutoksia tehty
     */
    public void setMuutettu(boolean b)   { muutettu = b; }

}

