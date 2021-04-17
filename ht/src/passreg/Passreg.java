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
    
    private Paasyt paasyt = new Paasyt();
    private Kategoriat kategoriat = new Kategoriat();
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
        if (tiedNimi != null) {
            this.tiedosto = tiedNimi;
            File hakemisto = new File(tiedNimi);
            if ( ! hakemisto.exists() ) hakemisto.mkdir();
        }
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
     *    Paasy gmail1 = new Paasy();
     *    gmail1.rekisteroi();
     *    gmail1.taytaGmailTiedoilla();
     *    pg.lisaa(gmail1);
     *    pg.getPaasytLkm()  === 1;
     *    Paasy gmail2 = new Paasy();
     *    gmail2.rekisteroi();
     *    gmail2.taytaGmailTiedoilla();
     *    pg.lisaa(gmail2);
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
    }

    
    /** 
     * Lis‰t‰‰n yksitt‰inen p‰‰sy rekisteriin
     * @param paasy lis‰tt‰v‰ p‰‰sy
     * @example
     * <pre name="test">
     *   Passreg passreg = new Passreg();
     *   Paasy p1 = new Paasy();
     *   p1.taytaGmailTiedoilla();
     *   p1.rekisteroi();
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
        if ( !onMuutettu() ) return;
        try {
            paasyt.tallenna(getTiedostonNimi());
        } catch (Exception e) {/*..*/}
        try {
            kategoriat.tallenna(getTiedostonNimi());
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
     *  Kategoria k1 = new Kategoria("opiskelu"); k1.rekisteroi();
     *  Kategoria k2 = new Kategoria("viihde");   k2.rekisteroi();
     *  int n1 = k1.getTunnusNro();
     *  int n2 = k2.getTunnusNro();
     *  Paasy p1 = new Paasy(n1);  p1.aseta(1, "gmail1");  
     *  Paasy p2 = new Paasy(n2);  p2.aseta(1, "gmail2");  
     *  Paasy p3 = new Paasy(n2);  p3.aseta(1, "gmail3");  
     *  Paasy p4 = new Paasy(n1);  p4.aseta(1, "gmail4");  
     *  Paasy p5 = new Paasy(n2);  p5.aseta(1, "gmail5"); 
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
     *  List<Paasy> pst = regis.getPaasyt(n1);
     *  Iterator<Paasy> it = pst.iterator();
     *  it.next() === p1;
     *  it.next() === p4;
     *  it.hasNext() === false;
     *  List<Paasy> loytyneet = regis.getPaasyt(n2);
     *  Iterator<Paasy> ih = loytyneet.iterator();
     *  ih.hasNext()  === true;
     *  ih.next() === p2;
     *  ih.next() === p3;
     *  ih.hasNext() === true;
     *  ih.next()  === p5;
     *  ih.next();  #THROWS NoSuchElementException
     *  File filePaasyt = new File(hakemisto + "/salasanat.dat");
     *  filePaasyt.delete() === true;
     *  File file_kategoriat = new File(hakemisto + "/kategoriat.dat");
     *  file_kategoriat.delete() === true;
     *  dir.delete() === true;
     * </pre> 
     */
    public void lueTiedostosta(String hakemisto) {
        Paasyt pst = new Paasyt();
        Kategoriat kat = new Kategoriat();
        setTiedostonNimi(hakemisto);
        try {
            pst.lueTiedostosta(getTiedostonNimi());
            kat.lueTiedostosta(getTiedostonNimi());
        } catch (Exception e) {/*..*/}
        paasyt = pst;
        kategoriat = kat;
        setMuutettu(false);
    }

    /**
     * Poistetaan kategoria, jonka tunnusnumero on v‰litetty parametrina.
     * Samalla poistetaan kaikki ne p‰‰syt, joiden kategorian id vastasi poistetun kategorian tunnusnumeroa
     * @param kID poistettavan kategorian tunnusnumero.
     * @example
     * <pre name="test">
     *    Passreg psg = new Passreg();
     *    Paasy p1 = new Paasy();
     *    p1.parse("1|1|gmail||||abcef");
     *    Paasy p2 = new Paasy();
     *    p2.parse("2|1|facebook|soturi123|||ghijkl");
     *    Paasy p3 = new Paasy(2);
     *    p3.aseta(1, "instagramm");
     *    psg.lisaa(p1); psg.lisaa(p2); psg.lisaa(p3);
     *    psg.getPaasytLkm() === 3;
     *    psg.annaPaasy(0).anna(1) === "gmail"; 
     *    Kategoria k1 = new Kategoria();
     *    k1.parse("1|tyo");
     *    Kategoria k2 = new Kategoria();
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
        kategoriat.poista(kID);
        paasyt.poistaKategorianPaasyt(kID);
    }

    /**
     * Palauttaa listana kaikki p‰‰syt jotka kuuluvat kategoriaan, jonka tunnusnumero on kID
     * @param kID kategorian tunnusnumero
     * @return lista kategoriaan
     * @example
     * <pre name="test">
     *   #import java.util.*;
     *    Passreg pss = new Passreg();
     *    Kategoria k = new Kategoria();
     *    int n = k.getTunnusNro();
     *    pss.lisaa(k);
     *    Paasy p1 = new Paasy(n); p1.taytaGmailTiedoilla();
     *    Paasy p2 = new Paasy(n); p2.taytaGmailTiedoilla();
     *    Paasy p3 = new Paasy(n+1); p3.taytaGmailTiedoilla();
     *    pss.lisaa(p1); pss.lisaa(p2); pss.lisaa(p3);
     *    List<Paasy> pst = pss.getPaasyt(n);
     *    Iterator<Paasy> i = pst.iterator();
     *    Paasy p = i.next();
     *    (p == p1 || p == p2) === true;
     *    i.hasNext() === true;
     *    i.next();
     *    i.hasNext() === false;
     *    i.next(); #THROWS NoSuchElementException
     * </pre>
     */
    public List<Paasy> getPaasyt(int kID) {
        List<Paasy> pst = new ArrayList<Paasy>();
        for (Paasy p : this.paasyt) {
            if (p.getKategoriaId() == kID) pst.add(p);
        }
        Collections.sort(pst, new Paasy.Vertailija(1)); // 
        return pst;
    }
    
    /**
     * @return true jos rekisteriin on tullut muutoksia
     */
    public boolean onMuutettu() {
        return paasyt.onMuutettu() || kategoriat.onMuutettu();
    }

    /**
     * @param b tieto siit‰ on muutoksia tehty rekisteriin
     */
    private void setMuutettu(boolean b) {
        paasyt.setMuutettu(b);
        kategoriat.setMuutettu(b);
    }

    /**
     * Haetaan t‰m‰n rekisterin p‰‰syt, jotka toteuttavat hakukriteerit
     * @param ehto mink‰ ehdon perusteella haetaan
     * @param kentta mik‰ on se ehdon tarkennus
     * @return kokoelma kaikista p‰‰syist‰, jotka toteuttavat hakuehdon
     * @example
     * <pre name="test">
     *   Paasy p1 = new Paasy();
     *   p1.aseta(1, "gmail3");
     *   Paasy p2 = new Paasy();
     *   p2.aseta(1, "gmail1");
     *   Paasy p3 = new Paasy();
     *   p3.aseta(1, "gmail2");
     *   Passreg psg = new Passreg();
     *   psg.lisaa(p1); psg.lisaa(p2); psg.lisaa(p3);
     *   Collection<Paasy> loytyneet = psg.etsi("gmail1", 1);
     *   Iterator<Paasy> i = loytyneet.iterator();
     *   i.next() === p2;
     *   loytyneet = psg.etsi("gmail2", 1);
     *   i = loytyneet.iterator();
     *   i.next() === p3;
     *   i.hasNext()  === false;
     * </pre>
     */
    public Collection<Paasy> etsi(String ehto, int kentta) {
        return paasyt.etsi(ehto, kentta);
    }

    /**
     * @param p korvattava tai lis‰tt‰v‰ p‰‰sy
     * @example
     * <pre name="test">
     *    Passreg psg = new Passreg();
     *    psg.onMuutettu() === false;
     *    Paasy p1 = new Paasy();
     *    p1.parse("1|1|gmail1");
     *    psg.korvaaTaiLisaa(p1);
     *    psg.onMuutettu() === true;
     *    psg.annaPaasy(0).anna(1) === "gmail1";
     *    p1.parse("1|2|facebook");
     *    psg.korvaaTaiLisaa(p1);
     *    psg.annaPaasy(0).anna(1) === "facebook";
     * </pre>
     */
    public void korvaaTaiLisaa(Paasy p) {
        paasyt.korvaaTaiLisaa(p);
    }

    /**
     * @param k korvattava tai lis‰tt‰v‰ kategoria
     * @example
     * <pre name="test">
     *    Passreg psg = new Passreg();
     *    Kategoria k1 = new Kategoria("tyo");
     *    Kategoria k2 = new Kategoria("opiskelu");
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
    public void korvaaTaiLisaa(Kategoria k) {
        kategoriat.korvaaTaiLisaa(k);
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
        return tiedosto;
    }
    
    /**
     * Palauttaa kaikki kategoriat j‰rjestettyn‰ listana
     * @return kaikki rekisterin kategoriat sortattuna
     * @example
     * <pre name="test">
     *   #import java.util.*;
     *    Passreg psg = new Passreg();
     *    Kategoria k1 = new Kategoria("tyo");
     *    Kategoria k2 = new Kategoria("opiskelu");
     *    psg.lisaa(k1); psg.lisaa(k2);
     *    List<Kategoria> katet = psg.annaKategoriat();
     *    Iterator<Kategoria> i = katet.iterator();
     *    i.next() === k2;
     *    i.hasNext() === true;
     *    i.next() === k1;
     *    i.hasNext() === false;
     * </pre>
     */
    public List<Kategoria> annaKategoriat() {
        return this.kategoriat.annaKategoriat();
    }
}
