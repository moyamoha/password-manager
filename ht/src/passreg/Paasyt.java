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
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * <pre>
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   Paasyt                              | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    | - Paasy           | 
 * | - pitää yllä varsinaista paasyrekisteriä, eli osaa |                   | 
 * |   lisätä ja poistaa pääsyn                         |                   | 
 * | - lukee ja kirjoittaa pääsyjä tiedostoon           |                   | 
 * | - osaa etsiä ja lajitella                          |                   | 
 * |-------------------------------------------------------------------------
 * </pre>
 * @author Yahya
 * @version 17.2.2021
 *
 */
public class Paasyt implements Iterable<Paasy> {
    
    private static int MAX_KOKO = 6; 
    private Paasy[] alkiot;
    private int lkm;
    private boolean muutettu = false;
    private static final String tiedostonNimi = "/salasanat.dat";
    
    /**
     * Oletus-muodostaja
     */
    public Paasyt() {
        this.alkiot = new Paasy[MAX_KOKO];
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Paasyt paasyt = new Paasyt();
        
        Paasy gmail1 = new Paasy();
        gmail1.rekisteroi();
        gmail1.taytaGmailTiedoilla();
        
        
        Paasy gmail2 = new Paasy();
        gmail2.rekisteroi();
        gmail2.taytaGmailTiedoilla();
        
        Paasy gmail3 = new Paasy();
        gmail3.rekisteroi();
        gmail3.taytaGmailTiedoilla();
        
        paasyt.lisaa(gmail1); paasyt.lisaa(gmail2);
        paasyt.lisaa(gmail3);
        
        for (int i = 0; i < paasyt.getLkm() ; i++) {
            paasyt.alkiot[i].tulosta(System.out);
            System.out.println();
        }
       
        Paasy gmail4 = new Paasy();
        gmail4.rekisteroi();
        gmail4.taytaGmailTiedoilla();
        
        gmail4.tulosta(System.out);
        
        paasyt.lisaa(gmail4);
    }
    
    
    /**
     * Lisätään yksittäinen pääsy tietorakenteeseen
     * @param paasy lisättävä pääsy
     * @example
     * <pre name="test">
     *   Paasyt pst = new Paasyt();
     *   Paasy p1 = new Paasy();
     *   Paasy p2 = new Paasy();
     *   pst.getLkm() === 0;
     *   pst.anna(1); #THROWS IndexOutOfBoundsException
     *   pst.lisaa(p1);
     *   pst.anna(0) === p1;
     *   pst.getLkm() === 1;
     *   pst.lisaa(p2);
     *   pst.anna(2); #THROWS IndexOutOfBoundsException
     *   pst.getLkm()  === 2;
     *   pst.anna(1) === p2;
     * </pre>
     */
    public void lisaa(Paasy paasy) {        
        if (getLkm() >= alkiot.length) {
            luoJaKopioi(); 
            lisaa(paasy);
        }
        else {
            this.alkiot[lkm++] = paasy;
            muutettu = true; 
        }
    }
    

    /**
     * Kun pääsyt tulee täyteen kutsutaan tätä. Luodaan uusi taulukko, jonka tilaa on kaksinkertainen. 
     * Samalla kopioidaan alkiot uuteen taulukkoon ja Tuhotaan vanha taulukko.
     */
    private final void luoJaKopioi() {
        MAX_KOKO = 2 * MAX_KOKO;
        Paasy[] apuTaul = new Paasy[MAX_KOKO];
        for (int i = 0; i < getLkm(); i++) {
            apuTaul[i] = this.alkiot[i];
        }
        this.alkiot = apuTaul;
        apuTaul = null;
    }
    

    /**
     * @return pääsyjen lukumäärä
     */
    public int getLkm() {
        return this.lkm;
    }
    
    
    /**
     * Palautetaan tiety pääsyn viite sen indeksin perusteella
     * @param i pääsyn indeksi taulukossa <b>alkiot</b>
     * @return viite Pääsy-olioon
     * @throws IndexOutOfBoundsException jos indeksi ei ole sopiva
     * @example
     * <pre name="test">
     * #THROWS IndexOutOfBoundsException
     *   Paasyt pst = new Paasyt();
     *   pst.getLkm()  === 0;
     *   Paasy gmail1 = new Paasy();
     *   gmail1.rekisteroi();
     *   gmail1.taytaGmailTiedoilla();
     *   pst.lisaa(gmail1);
     *   pst.getLkm()  === 1;
     *   Paasy gmail2 = new Paasy();
     *   gmail2.rekisteroi();
     *   gmail2.taytaGmailTiedoilla();
     *   pst.lisaa(gmail2);
     *   pst.getLkm()  === 2;
     *   pst.anna(2); #THROWS IndexOutOfBoundsException
     * </pre>
     */
    public Paasy anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i) {
            throw new IndexOutOfBoundsException("Huono indeksi: " + i);
        }
        return this.alkiot[i];
    }
    
    /**
     * Lukee pääsyjen tietoja tiedostosta
     * @param hakemisto hakemisto josta tiedosto l�ytyy
     * @example
     * <pre name="test">
     * #import java.io.File;
     * #import java.util.*;
     * #import java.io.*;
     *  Paasyt pst = new Paasyt();
     *  Paasy p1 = new Paasy(1), p2 = new Paasy(2);
     *  p1.taytaGmailTiedoilla();
     *  p2.taytaGmailTiedoilla();
     *  String hakemisto = "testi";
     *  File fTied = new File(hakemisto);
     *  fTied.mkdir();
     *  pst.lisaa(p1);
     *  pst.lisaa(p2);
     *  pst.tallenna(hakemisto);
     *  pst.lueTiedostosta(hakemisto);  // johon ladataan tiedot tiedostosta.
     *  Iterator<Paasy> i = pst.iterator();
     *  Paasy pTest = i.next();
     *  pTest.getKategoriaId() === 1;
     *  Paasy pTest2 = i.next();
     *  pTest2.getKategoriaId()  === 2;
     *  pst.tallenna(hakemisto);
     * </pre>
     */
    public void lueTiedostosta(String hakemisto) {
        File fTied = new File(hakemisto + tiedostonNimi);
        try {
            fTied.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String rivi = "";
        try (Scanner fi = new Scanner(new FileInputStream(fTied))) {
            while (fi.hasNextLine()) {
                rivi = fi.nextLine();
                Paasy paasy = new Paasy();
                paasy.parse(rivi);
                lisaa(paasy);
            }
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
    
    
    /**
     * Tallennetaan pääsyt tiedostoon. kesken
     * @param hakemisto tallennettavan tiedoston nimi
     */
    public void tallenna(String hakemisto) {
        if (!muutettu) return;
        File fTied = new File(hakemisto + tiedostonNimi);
        try (PrintStream fo = new PrintStream(new FileOutputStream(fTied, false))) {
            for (Paasy p : this) {
                fo.println(p.toString());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Tiedosto " + fTied.getAbsolutePath() + " ei löydy");
        }
        
    }
    
    /**
     * poistaa tietty pääsy
     * @param nro poistettavan pääsyn tunnusNro
     * @example
     * <pre name="test">
     *    Paasyt pst = new Paasyt();
     *    Paasy p1 = new Paasy();
     *    p1.taytaGmailTiedoilla();
     *    p1.rekisteroi();
     *    Paasy p2 = new Paasy();
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
        for (int i = 0; i < getLkm(); i++) {
            if (alkiot[i] == null) continue;
            if (alkiot[i].getTunnusNro() == nro) {
                alkiot[i] = null;
                muutettu = true;
                shiftToLeft(i);
                lkm--;
            }
        }
    }
    
    private final void shiftToLeft(int i) {
        int p = i;
        for (int j = p + 1; j < getLkm() ; j++ ,p++) {
            alkiot[p] = alkiot[j];
        }
    }
    
    /** 
     * Palauttaa iteraattori pääsyjen läpikäymiseen
     * @example
     * <pre name="test">
     *  #import java.util.*;
     *   Paasyt pst = new Paasyt();
     *   Paasy p1 = new Paasy();
     *   Paasy p2 = new Paasy();
     *   Iterator<Paasy> i = pst.iterator();
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
    public Iterator<Paasy> iterator() {
        return new Iter();
    }
    
    /**
     * @author Yahya
     * @version 9.3.2021
     * 
     */
    public class Iter implements Iterator<Paasy> {
        
        int kohdalla;

        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }

        @Override
        public Paasy next() {
            if (kohdalla >= getLkm()) throw new NoSuchElementException(" Ei oo enää!");
            return anna(kohdalla++);
        }
 
    }

    /**
     * @param kID kategorian id
     */
    public void poistaKategorianPaasyt(int kID) {
        for (int i = 0; i < getLkm(); i++) {
            if (alkiot[i] == null) continue;
            if (alkiot[i].getKategoriaId() == kID) {
                alkiot[i] = null;
                muutettu = true;
            }
        }
        for (int i = 0; i < getLkm(); i++) {
            if (alkiot[i] == null) {
                shiftToLeft(i);
                lkm--;
            }
        }
    }
    
    /**
     * @return true jos pääsyjä on lisätty tai poistettu kategoriasta
     */
    public boolean onMuutettu() { return muutettu; }

    /**
     * @param b tieto siitä on muutoksia tehty
     */
    public void setMuutettu(boolean b) {
        muutettu = b;
    }

}
