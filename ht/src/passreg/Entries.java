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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import fi.jyu.mit.ohj2.WildChars;

/**
 * Tietorakenne, joka yll‰pit‰‰ p‰‰syj‰ ja osaa lis‰t‰, poista, etsi‰ ja lajitella. 
 * Osaa lukea tiedostosta sek‰ kirjoittaa tiedostoon.
 * @author Yahya
 * @version 17.2.2021
 */
public class Entries implements Iterable<Entry> {
    
    private static int maxSize = 3; 
    private Entry[] content;
    private int lkm;
    private boolean changed = false;
    private static final String FILE_NAME = "/salasanat.dat";
    
    /**
     * Oletus-muodostaja
     */
    public Entries() {
        this.content = new Entry[maxSize];
    }
    
    /**
     * @param args ei k‰yt√∂ss‰
     */
    public static void main(String[] args) {
        Entries entries = new Entries();
        
        Entry gmail1 = new Entry();
        gmail1.register();
        gmail1.taytaGmailTiedoilla();
        
        
        Entry gmail2 = new Entry();
        gmail2.register();
        gmail2.taytaGmailTiedoilla();
        
        Entry gmail3 = new Entry();
        gmail3.register();
        gmail3.taytaGmailTiedoilla();
        
        entries.lisaa(gmail1); entries.lisaa(gmail2);
        entries.lisaa(gmail3);
        
        for (int i = 0; i < entries.getLkm() ; i++) {
            entries.content[i].tulosta(System.out);
            System.out.println();
        }
       
        Entry gmail4 = new Entry();
        gmail4.register();
        gmail4.taytaGmailTiedoilla();
        
        gmail4.tulosta(System.out);
        
        entries.lisaa(gmail4);
        int luku = 0x23;
        System.out.println(luku);
    }
    
    
    /**
     * Lis‰t‰‰n yksitt‰inen p‰‰sy tietorakenteeseen
     * @param entry lis‰tt‰v‰ p‰‰sy
     * @example
     * <pre name="test">
     *   Entries pst = new Entries();
     *   Entry p1 = new Entry();
     *   Entry p2 = new Entry();
     *   Entry p3 = new Entry(4);
     *   pst.getLkm() === 0;
     *   pst.anna(1); #THROWS IndexOutOfBoundsException
     *   pst.lisaa(p1);
     *   pst.anna(0) === p1;
     *   pst.getLkm() === 1;
     *   pst.lisaa(p2);
     *   pst.lisaa(p3);
     *   pst.getLkm()  === 3;
     *   pst.anna(1) === p2;
     *   pst.anna(2).getKategoriaId()  === 4;
     *   Entry p4 = new Entry(2);
     *   pst.lisaa(p4); // Ei pit‰isi heitt‰‰ poikkeusta
     * </pre>
     */
    public void lisaa(Entry entry) {        
        if (getLkm() >= content.length) {
            luoJaKopioi(); 
            lisaa(entry);
        }
        else {
            this.content[lkm++] = entry;
            changed = true; 
        }
    }
    
    /**
     * Kun p‰‰syt tulee t‰yteen kutsutaan t‰t‰. Luodaan uusi taulukko, jonka tilaa on kaksinkertainen. 
     * Samalla kopioidaan content uuteen taulukkoon ja Tuhotaan vanha taulukko.
     */
    private final void luoJaKopioi() {
        maxSize = 2 * maxSize;
        Entry[] apuTaul = new Entry[maxSize];
        for (int i = 0; i < getLkm(); i++) {
            apuTaul[i] = this.content[i];
        }
        this.content = apuTaul;
        apuTaul = null;
    }
    

    /**
     * @return p‰‰syjen lukum‰‰r‰
     */
    public int getLkm() {
        return this.lkm;
    }
    
    
    /**
     * Palautetaan tiety p‰‰syn viite sen indeksin perusteella
     * @param i p‰‰syn indeksi taulukossa <b>content</b>
     * @return viite P‰‰sy-olioon
     * @throws IndexOutOfBoundsException jos indeksi ei ole sopiva
     * @example
     * <pre name="test">
     * #THROWS IndexOutOfBoundsException
     *   Entries pst = new Entries();
     *   pst.getLkm()  === 0;
     *   Entry gmail1 = new Entry();
     *   gmail1.rekisteroi();
     *   gmail1.taytaGmailTiedoilla();
     *   pst.lisaa(gmail1);
     *   pst.getLkm()  === 1;
     *   Entry gmail2 = new Entry();
     *   gmail2.rekisteroi();
     *   gmail2.taytaGmailTiedoilla();
     *   pst.lisaa(gmail2);
     *   pst.getLkm()  === 2;
     *   pst.anna(2); #THROWS IndexOutOfBoundsException
     * </pre>
     */
    public Entry give(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i) {
            throw new IndexOutOfBoundsException("Huono indeksi: " + i);
        }
        return this.content[i];
    }
    
    /**
     * Lukee p‰‰syjen tietoja tiedostosta
     * @param hakemisto hakemisto josta tiedosto lÔøΩytyy
     * @example
     * <pre name="test">
     * #import java.io.File;
     * #import java.util.*;
     * #import java.io.*;
     *  Entries pst = new Entries();
     *  Entry p1 = new Entry(1), p2 = new Entry(2);
     *  p1.taytaGmailTiedoilla();
     *  p2.taytaGmailTiedoilla();
     *  String hakemisto = "testi";
     *  File fTied = new File(hakemisto);
     *  fTied.mkdir();
     *  pst.lisaa(p1);
     *  pst.lisaa(p2);
     *  pst.tallenna(hakemisto);
     *  pst.lueTiedostosta(hakemisto);  // johon ladataan tiedot tiedostosta.
     *  Iterator<Entry> i = pst.iterator();
     *  Entry pTest = i.next();
     *  pTest.getKategoriaId()   === 1;
     *  Entry pTest2 = i.next();
     *  pTest2.getKategoriaId()  === 2;
     *  pst.tallenna(hakemisto);
     * </pre>
     */
    public void lueTiedostosta(String hakemisto) {
        File fTied = new File(hakemisto + FILE_NAME);
        try {
            fTied.createNewFile();
        } catch (IOException e1) {/*..*/}
        String rivi = "";
        try (Scanner fi = new Scanner(new FileInputStream(fTied))) {
            while (fi.hasNextLine()) {
                rivi = fi.nextLine();
                Entry entry = new Entry();
                entry.parse(rivi);
                lisaa(entry);
            }
        }
        catch (FileNotFoundException e) {/*..*/}
        changed = false;
    }
    
    
    /**
     * Tallennetaan p‰‰syt tiedostoon. kesken
     * @param hakemisto tallennettavan tiedoston nimi
     */
    public void tallenna(String hakemisto) {
        if (!changed) return;
        File fTied = new File(hakemisto + FILE_NAME);
        try (PrintStream fo = new PrintStream(new FileOutputStream(fTied, false))) {
            for (Entry p : this) {
                fo.println(p.toString());
            }
        } catch (FileNotFoundException e) {/*..*/}
        changed = false;
        
    }
    
    /**
     * poistaa tietty p‰‰sy
     * @param nro poistettavan p‰‰syn tunnusNro
     * @example
     * <pre name="test">
     *    Entries pst = new Entries();
     *    Entry p1 = new Entry();
     *    p1.taytaGmailTiedoilla();
     *    p1.rekisteroi();
     *    Entry p2 = new Entry();
     *    p2.taytaGmailTiedoilla();
     *    p2.rekisteroi();
     *    pst.getLkm()  === 0;
     *    pst.lisaa(p1);
     *    pst.lisaa(p2);
     *    pst.getLkm()  === 2;
     *    pst.poista(p1.getTunnusNro());
     *    pst.getLkm()  === 1;
     *    pst.poista(p2.getTunnusNro());
     *    pst.getLkm()  === 0;
     *    pst.anna(0); #THROWS IndexOutOfBoundsException
     *    pst.anna(1); #THROWS IndexOutOfBoundsException
     * </pre>
     */
    public void poista(int nro) {
        if (getLkm() == 1 && give(0).getTunnusNro() == nro) {
            content[0] = null;
            lkm = 0;
            return;
        }
        for (int i = 0; i < getLkm(); i++) {
            if (content[i] == null) continue;
            if (content[i].getTunnusNro() == nro) {
                content[i] = null;
                changed = true;
                swap(i);
                lkm--;
            }
        }
    }
    
    private void swap(int i) {
        int last = getLkm();
        Entry p = give(last - 1);
        content[i] = p;
        content[last - 1] = null;
    }
    
    /** 
     * Palauttaa iteraattori p‰‰syjen l‰pik‰ymiseen
     * @example
     * <pre name="test">
     *  #import java.util.*;
     *   Entries pst = new Entries();
     *   Entry p1 = new Entry();
     *   Entry p2 = new Entry();
     *   Iterator<Entry> i = pst.iterator();
     *   i.hasNext()  === false;
     *   i.next(); #THROWS NoSuchElementException
     *   pst.lisaa(p1);
     *   i.next() === p1;
     *   i.hasNext() === false;
     *   pst.lisaa(p2);
     *   i.hasNext() === true;
     *   i.next()    === p2;
     *   i.next(); #THROWS NoSuchElementException
     * </pre>
     */
    @Override
    public Iterator<Entry> iterator() {
        return new Iter();
    }
    
    /**
     * @author Yahya
     * @version 9.3.2021
     *  Iteraattori p‰‰syjen l‰pik‰ymiseen
     */
    public class Iter implements Iterator<Entry> {
        
        private int kohdalla;

        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }

        @Override
        public Entry next() {
            if (kohdalla >= getLkm()) throw new NoSuchElementException(" Ei oo en‰‰!");
            return give(kohdalla++);
        }
 
    }

    /**
     * Poistetaan kaikki p‰‰syt, joilla on sama kategorian id.
     * @param kID kategorian id
     * @example
     * <pre name="test">
     *    Entries pst = new Entries();
     *    Entry p1 = new Entry();
     *    p1.parse("1|1|gmail||||abcef");
     *    Entry p2 = new Entry();
     *    p2.parse("2|1|facebook|soturi123|||ghijkl");
     *    Entry p3 = new Entry(2);
     *    p3.aseta(1, "instagramm");
     *    pst.lisaa(p1); pst.lisaa(p2); pst.lisaa(p3);
     *    pst.getLkm() === 3;
     *    pst.anna(0).anna(1) === "gmail"; 
     *    pst.poistaKategorianPaasyt(1);
     *    pst.getLkm() === 1;
     *    pst.anna(0) === p3;
     * </pre>
     */
    public void poistaKategorianPaasyt(int kID) {
        int i = 0;
        while (i < getLkm()) {
            Entry p = give(i);
            if (p.getKategoriaId() == kID) {
                poista(p.getTunnusNro());
            }
            else i++;
        }
    }
    
    /**
     * @return true jos p‰‰syj‰ on lis‰tty tai poistettu kategoriasta
     */
    public boolean onMuutettu() { return changed; }

    /**
     * @param b tieto siit‰ on muutoksia tehty
     */
    public void setMuutettu(boolean b) {
        changed = b;
    }

    /**
     * Haetaan p‰‰syt, jotka toteuttavat hakukriteerit
     * @param ehto mink‰ ehdon perusteella haetaan
     * @param kentta mik‰ on se ehdon tarkennus
     * @return kokoelma kaikista p‰‰syist‰, jotka toteuttavat hakuehdon
     * @example
     * <pre name="test">
     *   Entry p1 = new Entry();
     *   p1.aseta(1, "gmail3");
     *   Entry p2 = new Entry();
     *   p2.aseta(1, "gmail1");
     *   Entry p3 = new Entry();
     *   p3.aseta(1, "gmail2");
     *   Entries pst = new Entries();
     *   pst.lisaa(p1); pst.lisaa(p2); pst.lisaa(p3);
     *   Collection<Entry> loytyneet = pst.etsi("gmail1", 1);
     *   Iterator<Entry> i = loytyneet.iterator();
     *   i.next() === p2;
     *   loytyneet = pst.etsi("gmail2", 1);
     *   i = loytyneet.iterator();
     *   i.next() === p3;
     *   i.hasNext()  === false;
     * </pre>
     */
    public Collection<Entry> etsi(String ehto, int kentta) {
        List<Entry> loytyneet = new ArrayList<Entry>(); 
        for (Entry p : this) { 
            if (WildChars.onkoSamat(p.anna(kentta), ehto + "*" )) loytyneet.add(p);   
        } 
        Collections.sort(loytyneet, new Entry.Vertailija(kentta)); 
        return loytyneet;
    }

    /**
     * @param p korvattava tai lis‰tt‰v‰ p‰‰sy
     * @example
     * <pre name="test">
     *    Entries pst = new Entries();
     *    pst.onMuutettu() === false;
     *    Entry p1 = new Entry();
     *    p1.parse("1|1|gmail1");
     *    pst.korvaaTaiLisaa(p1);
     *    pst.onMuutettu() === true;
     *    pst.anna(0).anna(1) === "gmail1";
     *    p1.parse("1|2|facebook");
     *    pst.korvaaTaiLisaa(p1);
     *    pst.anna(0).anna(1) === "facebook";
     * </pre>
     */
    public void korvaaTaiLisaa(Entry p) {
        if (p == null) return;
        int nro = p.getTunnusNro();
        for (int i = 0; i < getLkm(); i++) {
            if (content[i].getTunnusNro() == nro) {
                content[i] = p;
                changed = true;
                return;
            }
        }
        lisaa(p);
    }

}
