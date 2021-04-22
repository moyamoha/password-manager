/**
 * 
 */
package passreg;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Luokka, joka huolehtii p‰‰syjen ja kategorioiden v‰lisest‰ yhteistyˆst‰, 
 * sek‰ osaa lukea/kirjoittaa niiden tiedot tiedostoon.
 * @author Yahya
 * @version 18.2.2021
 */
public class Passreg {
    
    private Entries entries = new Entries();
    private Groups groups = new Groups();
    private String location = "";
    
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
       
       Entry gmail1 = new Entry();
       gmail1.register();
       gmail1.taytaGmailTiedoilla();
       
       
       Entry gmail2 = new Entry();
       gmail2.register();
       gmail2.taytaGmailTiedoilla();
       
       Entry gmail3 = new Entry();
       gmail3.register();
       gmail3.taytaGmailTiedoilla();
       
       passreg.lisaa(gmail1); passreg.lisaa(gmail2);
       passreg.lisaa(gmail3);
       
       Group kg1 = new Group("Some");
       kg1.register();
       passreg.add(kg1);
       
       for (int i = 0; i < passreg.getEntriesSize(); i++) {
           passreg.annaPaasy(i).tulosta(System.out);
       }
       
       for (Group kg : passreg.groups) {
           kg.tulosta(System.out);
       }
    }
    
    /**
     * @param tiedNimi rekisterin tiedostojen sijainti
     */
    public void setTiedostonNimi(String tiedNimi) {
        if (tiedNimi != null) {
            this.location = tiedNimi;
            File hakemisto = new File(tiedNimi);
            if ( ! hakemisto.exists() ) hakemisto.mkdir();
        }
    }
    
    /**
     * @return rekisterin sijainti
     */
    private String getLocation() { return this.location; }
    
    
    /**
     * @return rekisterin p‰‰sym‰‰r‰
     * @example
     * <pre name="test">
     *    Passreg pg = new Passreg();
     *    pg.getPaasytLkm()  === 0;
     *    Entry gmail1 = new Entry();
     *    gmail1.rekisteroi();
     *    gmail1.taytaGmailTiedoilla();
     *    pg.lisaa(gmail1);
     *    pg.getPaasytLkm()  === 1;
     *    Entry gmail2 = new Entry();
     *    gmail2.rekisteroi();
     *    gmail2.taytaGmailTiedoilla();
     *    pg.lisaa(gmail2);
     *    pg.getPaasytLkm()  === 2;
     * </pre>
     */
    public int getEntriesSize() {
        return entries.getLkm();
    }
    
    /**
     * @return rekisterin kategorioiden m‰‰r‰
     */
    public int getKategoriatLkm() {
        return groups.getLkm();
    }
    
    /**
     * @param nro viite poistettavaan/poistettavien p‰‰syjen numeroon
     */
    public void poistaPaasy(int nro) {
        entries.poista(nro);
    }

    
    /** 
     * Lis‰t‰‰n yksitt‰inen p‰‰sy rekisteriin
     * @param entry lis‰tt‰v‰ p‰‰sy
     * @example
     * <pre name="test">
     *   Passreg passreg = new Passreg();
     *   Entry p1 = new Entry();
     *   p1.taytaGmailTiedoilla();
     *   p1.rekisteroi();
     *   passreg.getPaasytLkm()  === 0;
     *   passreg.lisaa(p1);
     *   passreg.getPaasytLkm()  === 1;
     * </pre>
     */
    public void lisaa(Entry entry) {
        entries.lisaa(entry);
    }
    
    /**
     * @param group lis‰tt‰v‰ kategoria
     * @example
     * <pre name="test">
     *   Passreg passreg = new Passreg();
     *   Group k1 = new Group("some");
     *   k1.rekisteroi();
     *   Group k2 = new Group("opiskelu");
     *   k2.rekisteroi();
     *   Group k3 = new Group("tyˆ");
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
    public void add(Group group) {
        groups.add(group);
    }
    
    
    /**
     * Palauttaa p‰‰sy indeksissa <b>i</b>
     * @param i p‰‰syn indeksi
     * @return viite kyseiseen p‰‰syyn
     */
    public Entry annaPaasy(int i) {
        return entries.give(i);
    }
    
    /**
     * Palauttaa viite pyydettyyn kategoriaan, joka sijaitsee indeksissa <b>i</b>
     * @param i kategorian indeksi
     * @return pyydetty kategoria
     */
    public Group annaKategoria(int i) {
        return groups.anna(i);
    }
    

    /**
     * Tallettaa rekisterin tiedot tiedostoon. kesken
     */
    public void tallenna() {
        if ( !onMuutettu() ) return;
        try {
            entries.tallenna(getLocation());
        } catch (Exception e) {/*..*/}
        try {
            groups.tallenna(getLocation());
        } catch (Exception e) {/*..*/}
        setMuutettu(false);
    }
    
    /**
     * Lukee rekisterin tiedot tiedostosta
     * @param hakemisto hakemiston polku
     * @example
     * <pre name="test">
     * #import java.io.*;
     * #import java.util.*;
     *  Passreg regis = new Passreg();
     *  Group k1 = new Group("opiskelu"); k1.rekisteroi();
     *  Group k2 = new Group("viihde");   k2.rekisteroi();
     *  int n1 = k1.getTunnusNro();
     *  int n2 = k2.getTunnusNro();
     *  Entry p1 = new Entry(n1);  p1.aseta(1, "gmail1");  
     *  Entry p2 = new Entry(n2);  p2.aseta(1, "gmail2");  
     *  Entry p3 = new Entry(n2);  p3.aseta(1, "gmail3");  
     *  Entry p4 = new Entry(n1);  p4.aseta(1, "gmail4");  
     *  Entry p5 = new Entry(n2);  p5.aseta(1, "gmail5"); 
     *  String hakemisto = "testi";
     *  File dir = new File(hakemisto);
     *  dir.mkdir();  
     *  regis.lueTiedostosta(hakemisto); 
     *  regis.lisaa(p1);
     *  regis.lisaa(p2);
     *  regis.lisaa(p3);
     *  regis.lisaa(k1);
     *  regis.lisaa(p4);
     *  regis.lisaa(k2);
     *  regis.lisaa(p5);
     *  regis.tallenna();
     *  List<Entry> pst = regis.getPaasyt(n1);
     *  Iterator<Entry> it = pst.iterator();
     *  it.next() === p1;
     *  it.next() === p4;
     *  it.hasNext() === false;
     *  List<Entry> loytyneet = regis.getPaasyt(n2);
     *  Iterator<Entry> ih = loytyneet.iterator();
     *  ih.hasNext()  === true;
     *  ih.next() === p2;
     *  ih.next() === p3;
     *  ih.hasNext() === true;
     *  ih.next()  === p5;
     *  ih.next();  #THROWS NoSuchElementException
     *  File filePaasyt = new File(hakemisto + "/salasanat.dat");
     *  filePaasyt.delete() === true;
     *  File file_kategoriat = new File(hakemisto + "/groups.dat");
     *  file_kategoriat.delete() === true;
     *  dir.delete() === true;
     * </pre> 
     */
    public void lueTiedostosta(String hakemisto) {
        Entries pst = new Entries();
        Groups kat = new Groups();
        setTiedostonNimi(hakemisto);
        try {
            pst.lueTiedostosta(getLocation());
            kat.lueTiedostosta(getLocation());
        } catch (Exception e) {/*..*/}
        entries = pst;
        groups = kat;
        setMuutettu(false);
    }

    /**
     * Poistetaan kategoria, jonka tunnusnumero on v‰litetty parametrina.
     * Samalla poistetaan kaikki ne p‰‰syt, joiden kategorian id vastasi poistetun kategorian tunnusnumeroa
     * @param kID poistettavan kategorian tunnusnumero.
     * @example
     * <pre name="test">
     *    Passreg psg = new Passreg();
     *    Entry p1 = new Entry();
     *    p1.parse("1|1|gmail||||abcef");
     *    Entry p2 = new Entry();
     *    p2.parse("2|1|facebook|soturi123|||ghijkl");
     *    Entry p3 = new Entry(2);
     *    p3.aseta(1, "instagramm");
     *    psg.lisaa(p1); psg.lisaa(p2); psg.lisaa(p3);
     *    psg.getPaasytLkm() === 3;
     *    psg.annaPaasy(0).anna(1) === "gmail"; 
     *    Group k1 = new Group();
     *    k1.parse("1|tyo");
     *    Group k2 = new Group();
     *    k2.parse("2|opiskelu");
     *    psg.lisaa(k1); psg.lisaa(k2);
     *    psg.getKategoriatLkm() === 2;
     *    psg.poistaKategoria(1);
     *    psg.getKategoriatLkm() === 1;
     *    psg.getPaasytLkm() === 1;
     *    psg.annaPaasy(0) === p3;
     *    psg.annaKategoria(0) === k2;
     * </pre>
     */
    public void poistaKategoria(int kID) {
        groups.poista(kID);
        entries.poistaKategorianPaasyt(kID);
    }

    /**
     * Palauttaa listana kaikki p‰‰syt jotka kuuluvat kategoriaan, jonka tunnusnumero on kID
     * @param kID kategorian tunnusnumero
     * @return lista kategoriaan
     * @example
     * <pre name="test">
     *   #import java.util.*;
     *    Passreg pss = new Passreg();
     *    Group k = new Group();
     *    int n = k.getTunnusNro();
     *    pss.lisaa(k);
     *    Entry p1 = new Entry(n); p1.taytaGmailTiedoilla();
     *    Entry p2 = new Entry(n); p2.taytaGmailTiedoilla();
     *    Entry p3 = new Entry(n+1); p3.taytaGmailTiedoilla();
     *    pss.lisaa(p1); pss.lisaa(p2); pss.lisaa(p3);
     *    List<Entry> pst = pss.getPaasyt(n);
     *    Iterator<Entry> i = pst.iterator();
     *    Entry p = i.next();
     *    (p == p1 || p == p2) === true;
     *    i.hasNext() === true;
     *    i.next();
     *    i.hasNext() === false;
     *    i.next(); #THROWS NoSuchElementException
     * </pre>
     */
    public List<Entry> getPaasyt(int kID) {
        List<Entry> pst = new ArrayList<Entry>();
        for (Entry p : this.entries) {
            if (p.getKategoriaId() == kID) pst.add(p);
        }
        Collections.sort(pst, new Entry.Vertailija(1)); // 
        return pst;
    }
    
    /**
     * @return true jos rekisteriin on tullut muutoksia
     */
    public boolean onMuutettu() {
        return entries.onMuutettu() || groups.onMuutettu();
    }

    /**
     * @param b tieto siit‰ on muutoksia tehty rekisteriin
     */
    private void setMuutettu(boolean b) {
        entries.setMuutettu(b);
        groups.setMuutettu(b);
    }

    /**
     * Haetaan t‰m‰n rekisterin p‰‰syt, jotka toteuttavat hakukriteerit
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
     *   Passreg psg = new Passreg();
     *   psg.lisaa(p1); psg.lisaa(p2); psg.lisaa(p3);
     *   Collection<Entry> loytyneet = psg.etsi("gmail1", 1);
     *   Iterator<Entry> i = loytyneet.iterator();
     *   i.next() === p2;
     *   loytyneet = psg.etsi("gmail2", 1);
     *   i = loytyneet.iterator();
     *   i.next() === p3;
     *   i.hasNext()  === false;
     * </pre>
     */
    public Collection<Entry> etsi(String ehto, int kentta) {
        return entries.etsi(ehto, kentta);
    }

    /**
     * @param p korvattava tai lis‰tt‰v‰ p‰‰sy
     * @example
     * <pre name="test">
     *    Passreg psg = new Passreg();
     *    psg.onMuutettu() === false;
     *    Entry p1 = new Entry();
     *    p1.parse("1|1|gmail1");
     *    psg.korvaaTaiLisaa(p1);
     *    psg.onMuutettu() === true;
     *    psg.annaPaasy(0).anna(1) === "gmail1";
     *    p1.parse("1|2|facebook");
     *    psg.korvaaTaiLisaa(p1);
     *    psg.annaPaasy(0).anna(1) === "facebook";
     * </pre>
     */
    public void korvaaTaiLisaa(Entry p) {
        entries.korvaaTaiLisaa(p);
    }

    /**
     * @param k korvattava tai lis‰tt‰v‰ kategoria
     * @example
     * <pre name="test">
     *    Passreg psg = new Passreg();
     *    Group k1 = new Group("tyo");
     *    Group k2 = new Group("opiskelu");
     *    psg.onMuutettu() === false;
     *    psg.lisaa(k1);
     *    psg.lisaa(k2);
     *    psg.annaKategoria(0).getNimi() === "tyo";
     *    k1.parse("1|viihde");
     *    psg.korvaaTaiLisaa(k1);
     *    psg.annaKategoria(0).getNimi() === "viihde";
     *    psg.onMuutettu() === true;
     * </pre>
     */
    public void korvaaTaiLisaa(Group k) {
        groups.korvaaTaiLisaa(k);
    }

    /**
     * Palautetaan rekisterin nimi
     * @return rekisterin nimi
     * @example
     * <pre name="test">
     *   Passreg psg = new Passreg();
     *   psg.setTiedostonNimi("toinenTesti");
     *   psg.getName() === "toinenTesti";
     * </pre>
     */
    public String getName() {
        return location;
    }
    
    /**
     * Palauttaa kaikki groups j‰rjestettyn‰ listana
     * @return kaikki rekisterin groups sortattuna
     * @example
     * <pre name="test">
     *   #import java.util.*;
     *    Passreg psg = new Passreg();
     *    Group k1 = new Group("tyo");
     *    Group k2 = new Group("opiskelu");
     *    psg.lisaa(k1); psg.lisaa(k2);
     *    List<Group> katet = psg.annaKategoriat();
     *    Iterator<Group> i = katet.iterator();
     *    i.next() === k2;
     *    i.hasNext() === true;
     *    i.next() === k1;
     *    i.hasNext() === false;
     * </pre>
     */
    public List<Group> annaKategoriat() {
        return this.groups.annagroups();
    }
}
