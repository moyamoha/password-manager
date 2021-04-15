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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Tietorakennetyyppinen luokka, joka kokoaa yhteen ohjelman kategoriat.
 * Osaa lis‰t‰/poistaa kategorioita sek‰ tallentaa niit‰ tiedostoon/lukea tiedostosta
 * @author Yahya
 * @version 1.3.2021
 * @see passreg.Kategoria
 */
public class Kategoriat implements Iterable<Kategoria> {

    private List<Kategoria> alkiot = new ArrayList<>();
    private static final String TIEDOSTON_NIMI = "/kategoriat.dat";
    private boolean muutettu = false;
    
    /**
     * Alustetaan Kategoriat
     */
    public Kategoriat() {
        //alkiot = new ArrayList<Kategoria>();
    }
    
    /**
     * 
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        //
        Kategoriat kgt = new Kategoriat();
        
        Kategoria kg1 = new Kategoria("some");
        kg1.rekisteroi();
        
        Kategoria kg2 = new Kategoria("opiskelu");
        kg2.rekisteroi();
        
        Kategoria kg3 = new Kategoria("tyo");
        kg3.rekisteroi();
        
        kgt.lisaa(kg1); kgt.lisaa(kg2); kgt.lisaa(kg3);
        
        for(Kategoria k : kgt) {
            k.tulosta(System.out);
        }
    }
    
    /**
     * @param kategoria lis√§tt√§v√§ kategoria
     * @example
     * <pre name="test">
     *   Kategoriat kgt = new Kategoriat();
     *   Kategoria k1 = new Kategoria("tyo");
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
     * Palauttaa kategorioiden lukum√§√§r√§n
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
     *    Kategoria k2 = new Kategoria("tyo"); k2.rekisteroi();
     *    kat.getLkm() === 0;
     *    kat.anna(0); #THROWS IndexOutOfBoundsException
     *    kat.lisaa(k1);
     *    kat.anna(0).getNimi() === "opiskelu";
     *    kat.getLkm() === 1;
     *    kat.lisaa(k2);
     *    kat.anna(1).getNimi() === "tyo";
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
     * Palauttaa yksittÔøΩinen kategoria tietyst√§ indeksist√§
     * @param i annettavan kategorian indeksi
     * @return Kategoria kyseisest√§ indeksist√§
     * @throws IndexOutOfBoundsException jos v√§√§r√§st√§ indeksista haetaan
     * @example
     * <pre name="test">
     *   Kategoriat kgt = new Kategoriat();
     *   Kategoria kg1 = new Kategoria("tyo");
     *   kg1.rekisteroi();
     *   Kategoria kg2 = new Kategoria("muu");
     *   kg2.rekisteroi();
     *   Kategoria kg3 = new Kategoria("opiskelu");
     *   kg3.rekisteroi();
     *   kgt.lisaa(kg1);
     *   kgt.lisaa(kg2);    
     *   kgt.lisaa(kg3);    
     *   kgt.anna(0).getNimi()  === "tyo";
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
     * @param hakemisto hakemisto josta tiedosto lÔøΩytyy
     * @example
     * <pre name="test">
     * #import java.io.File;
     * #import java.util.*;
     * #import java.io.*;
     *  Kategoriat kat = new Kategoriat();
     *  Kategoria k1 = new Kategoria("opiskelu"), k2 = new Kategoria("tyo");
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
     *  kTest2.getNimi()  === "tyo";
     *  kat.tallenna(hakemisto);
     * </pre>
     */
    public void lueTiedostosta(String hakemisto) {
        File fTied = new File(hakemisto + TIEDOSTON_NIMI);
        try {
            fTied.createNewFile();
        } catch (IOException e1) { /*..*/ }
        String rivi = "";
        try (Scanner fi = new Scanner(new FileInputStream(fTied))) {
            while (fi.hasNextLine()) {
                rivi = fi.nextLine();
                Kategoria kategoria = new Kategoria();
                kategoria.parse(rivi);
                lisaa(kategoria);
            }
        }
        catch (FileNotFoundException e) { /*..*/ }
    }
    
    
    /**
     * Tallennetaan kategoria tiedostoon. kesken
     * @param hakemisto tallennettavan tiedoston sijainti
     */
    public void tallenna(String hakemisto) {
        if (!muutettu) return;
        File fTied = new File(hakemisto + TIEDOSTON_NIMI);
        try (PrintStream fo = new PrintStream(new FileOutputStream(fTied, false))) {
            for (Kategoria kategoria : this) {
                fo.println(kategoria.toString());
            }
        } catch (FileNotFoundException e) { /*..*/ }
    }

    /**
     * Palauttaa iteraattori kategorioiden l√§pik√§ymiseen.
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
     *   Kategoria k = iter.next();
     *   k === kg1;
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
     * @version 6.4.2021
     * Iteraattori kategorioiden l‰pik‰ymiseen
     */
    public class Iter implements Iterator<Kategoria> {

        private int kohdalla;
        
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }

        @Override
        public Kategoria next() {
            if (kohdalla >= getLkm()) throw new NoSuchElementException(" Ei oo en‰‰!");
            return anna(kohdalla++);
        }
        
    }

    
    /**
     * @return true jos kategorioita on lis√§tty tai poistettu rekisterist√§
     */
    public boolean onMuutettu() { return muutettu;}
    
    /**
     * @param b tieto siit√§ onko muutoksia tehty
     */
    public void setMuutettu(boolean b)   { muutettu = b; }

    /**
     * @param k korvattava tai lis‰tt‰v‰ kategoria
     * @example
     * <pre name="test">
     *    Kategoriat kat = new Kategoriat();
     *    Kategoria k1 = new Kategoria("tyo");
     *    Kategoria k2 = new Kategoria("opiskelu");
     *    kat.onMuutettu() === false;
     *    kat.lisaa(k1);
     *    kat.lisaa(k2);
     *    kat.setMuutettu(false);
     *    kat.anna(0).getNimi() === "tyo";
     *    k1.parse("1|viihde");
     *    kat.korvaaTaiLisaa(k1);
     *    kat.anna(0).getNimi() === "viihde";
     *    kat.onMuutettu() === true;
     * </pre>
     */
    public void korvaaTaiLisaa(Kategoria k) {
        if (k == null) return;
        for (int i = 0; i < getLkm(); i++) {
            if (alkiot.get(i).getTunnusNro() == k.getTunnusNro()) {
                alkiot.set(i, k);
                muutettu = true;
                return;
            }
        }
        lisaa(k);
    }

    /**
     * @return Palauttaa kaikki kategoriat sortattuna otsikon perusteella
     * @example
     * <pre name="test">
     *   #import java.util.*;
     *    Kategoriat kat = new Kategoriat();
     *    Kategoria k1 = new Kategoria("tyo");
     *    Kategoria k2 = new Kategoria("opiskelu");
     *    kat.lisaa(k1); kat.lisaa(k2);
     *    List<Kategoria> katet = kat.annaKategoriat();
     *    Iterator<Kategoria> i = katet.iterator();
     *    i.next() === k2;
     *    i.hasNext() === true;
     *    i.next() === k1;
     *    i.hasNext() === false;
     * </pre>
     */
    public List<Kategoria> annaKategoriat() {
        List<Kategoria> kategoriat = new ArrayList<>(getLkm());
        for (Kategoria k : this) {
            kategoriat.add(k);
        }
        Collections.sort(kategoriat);
        return kategoriat;
    }

}

