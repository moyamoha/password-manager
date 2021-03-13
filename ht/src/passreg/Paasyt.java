/**
 * 
 */
package passreg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   Jasenet                             | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    | - Paasy           | 
 * | - pitää yllä varsinaista paasyrekisteriä, eli osaa |                   | 
 * |   lisätä ja poistaa pääsyn                         |                   | 
 * | - lukee ja kirjoittaa pääsyjä tiedostoon           |                   | 
 * | - osaa etsiä ja lajitella                          |                   | 
 * |                                                    |                   | 
 * |-------------------------------------------------------------------------
 * @author Yahya
 * @version 17.2.2021
 *
 */
public class Paasyt implements Iterable<Paasy> {
    
    private static int MAX_KOKO = 6; 
    private Paasy[] alkiot;
    private int lkm;
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
        
        for (int i = 0; i < paasyt.alkiot.length; i++) {
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
     */
    public void lisaa(Paasy paasy) {        
        if (getLkm() >= alkiot.length) {
            luoJaKopioi(); 
            lisaa(paasy);
        }
        else this.alkiot[lkm++] = paasy;
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
     */
    public void lueTiedostosta(String hakemisto) {
        File fTied = new File(hakemisto + tiedostonNimi);
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
     * @param paasy pääsy, jonka kategorian id muutetaan
     * @param kID asetettavan kategorian id
     */
    public void setKid(Paasy paasy, int kID) {
        paasy.setKid(kID);
    }
    

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
     * @param kid poistetun kategorian id
     * @param uusiKID uusi kategorian id
     */
    public void paivitakID(int kid, int uusiKID) {
        for (Paasy p : this) {
            if (p.getKategoriaId() == kid) {
                p.setKid(uusiKID);
            }
        }
    }
}
