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
 * Tietorakennetyyppinen luokka, joka kokoaa yhteen ohjelman groups.
 * Osaa lis‰t‰/poistaa kategorioita sek‰ tallentaa niit‰ tiedostoon/lukea tiedostosta
 * @author Yahya
 * @version 1.3.2021
 * @see passreg.Group
 */
public class Groups implements Iterable<Group> {

    private List<Group> content = new ArrayList<>();
    private static final String FILE_NAME = "/groups.dat";
    private boolean changed = false;
    
    /**
     * Alustetaan Groups
     */
    public Groups() {
        //content = new ArrayList<Group>();
    }
    
    /**
     * 
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        //
        Groups groups = new Groups();
        
        Group kg1 = new Group("some");
        kg1.register();
        
        Group kg2 = new Group("opiskelu");
        kg2.register();
        
        Group kg3 = new Group("tyo");
        kg3.register();
        
        groups.add(kg1); groups.add(kg2); groups.add(kg3);
        
        for(Group k : groups) {
            k.tulosta(System.out);
        }
    }
    
    /**
     * @param group lis√§tt√§v√§ kategoria
     * @example
     * <pre name="test">
     *   Groups groups = new Groups();
     *   Group k1 = new Group("tyo");
     *   Group k2 = new Group("opiskelu");
     *   Group k3 = new Group("muu");
     *   groups.getLkm()   === 0;
     *   groups.lisaa(k1);
     *   groups.getLkm()   === 1;
     *   groups.lisaa(k2);
     *   groups.getLkm()   === 2;
     *   groups.lisaa(k3);
     *   groups.getLkm()   === 3;
     * </pre>
     */
    public void add(Group group) {
        content.add(group);
        changed = true;
    }
 
    
    /**
     * Palauttaa kategorioiden lukum√§√§r√§n
     * @return kategorioiden lkm
     */
    public int getLkm() {
        return content.size();
    }
    
    /**
     * Poistaa kategoria, jolla tunnusnumerona <b>nro</b>
     * @param nro poistettavan kategorian tunnusnro
     * @example
     * <pre name="test">
     *    Groups kat = new Groups();
     *    Group k1 = new Group("opiskelu"); 
     *    k1.rekisteroi();
     *    int n = k1.getTunnusNro();
     *    Group k2 = new Group("tyo"); k2.rekisteroi();
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
                content.remove(i);
                changed = true;
                lkm--;
            }
        }
    }
    
    /**
     * Palauttaa yksittÔøΩinen kategoria tietyst√§ indeksist√§
     * @param i annettavan kategorian indeksi
     * @return Group kyseisest√§ indeksist√§
     * @throws IndexOutOfBoundsException jos v√§√§r√§st√§ indeksista haetaan
     * @example
     * <pre name="test">
     *   Groups groups = new Groups();
     *   Group kg1 = new Group("tyo");
     *   kg1.rekisteroi();
     *   Group kg2 = new Group("muu");
     *   kg2.rekisteroi();
     *   Group kg3 = new Group("opiskelu");
     *   kg3.rekisteroi();
     *   groups.lisaa(kg1);
     *   groups.lisaa(kg2);    
     *   groups.lisaa(kg3);    
     *   groups.anna(0).getNimi()  === "tyo";
     *   groups.anna(1).getNimi()  === "muu";
     *   groups.anna(2).getNimi()  === "opiskelu";
     * </pre>
     */
    public Group anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= getLkm()) {
            throw new IndexOutOfBoundsException("Laiton indeksi " + i);
        }
        return content.get(i);
    }
    
    
    /**
     * Lukee kategorioiden tietoja tiedostosta
     * @param hakemisto hakemisto josta tiedosto lÔøΩytyy
     * @example
     * <pre name="test">
     * #import java.io.File;
     * #import java.util.*;
     * #import java.io.*;
     *  Groups kat = new Groups();
     *  Group k1 = new Group("opiskelu"), k2 = new Group("tyo");
     *  k1.rekisteroi(); k2.rekisteroi();
     *  String hakemisto = "testi";
     *  File dir = new File(hakemisto);
     *  dir.mkdir();
     *  kat.lisaa(k1);
     *  kat.lisaa(k2);
     *  kat.tallenna(hakemisto);
     *  kat.lueTiedostosta(hakemisto);  // johon ladataan tiedot tiedostosta.
     *  Iterator<Group> i = kat.iterator();
     *  Group kTest = i.next();
     *  kTest.getNimi() === "opiskelu";
     *  Group kTest2 = i.next();
     *  kTest2.getNimi()  === "tyo";
     *  kat.tallenna(hakemisto);
     * </pre>
     */
    public void lueTiedostosta(String hakemisto) {
        File fTied = new File(hakemisto + FILE_NAME);
        try {
            fTied.createNewFile();
        } catch (IOException e1) { /*..*/ }
        String rivi = "";
        try (Scanner fi = new Scanner(new FileInputStream(fTied))) {
            while (fi.hasNextLine()) {
                rivi = fi.nextLine();
                Group group = new Group();
                group.parse(rivi);
                add(group);
            }
        }
        catch (FileNotFoundException e) { /*..*/ }
    }
    
    
    /**
     * Tallennetaan kategoria tiedostoon. kesken
     * @param hakemisto tallennettavan tiedoston sijainti
     */
    public void tallenna(String hakemisto) {
        if (!changed) return;
        File fTied = new File(hakemisto + FILE_NAME);
        try (PrintStream fo = new PrintStream(new FileOutputStream(fTied, false))) {
            for (Group group : this) {
                fo.println(group.toString());
            }
        } catch (FileNotFoundException e) { /*..*/ }
    }

    /**
     * Palauttaa iteraattori kategorioiden l√§pik√§ymiseen.
     * @example
     * <pre name="test">
     *   Groups groups = new Groups();
     *   Group kg1 = new Group("tyo");
     *   Group kg2 = new Group("muu");
     *   Group kg3 = new Group("opiskelu");
     *   Iterator<Group> iter = groups.iterator();
     *   iter.hasNext()  === false;
     *   iter.next(); #THROWS NoSuchElementException
     *   groups.lisaa(kg1);
     *   iter.hasNext() === true;
     *   Group k = iter.next();
     *   k === kg1;
     *   groups.lisaa(kg2); 
     *   groups.lisaa(kg3);
     *   iter.next()  === kg2;       
     *   iter.next()  === kg3;
     *   iter = groups.iterator();
     *   iter.next() == iter.next() === false;
     *   iter.next().getNimi()  === "opiskelu";
     * </pre>
     */
    @Override
    public Iterator<Group> iterator() {
        return new Iter();
    }
    
    /**
     * @author Yahya
     * @version 6.4.2021
     * Iteraattori kategorioiden l‰pik‰ymiseen
     */
    public class Iter implements Iterator<Group> {

        private int kohdalla;
        
        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }

        @Override
        public Group next() {
            if (kohdalla >= getLkm()) throw new NoSuchElementException(" Ei oo en‰‰!");
            return anna(kohdalla++);
        }
        
    }

    
    /**
     * @return true jos kategorioita on lis√§tty tai poistettu rekisterist√§
     */
    public boolean onMuutettu() { return changed;}
    
    /**
     * @param b tieto siit√§ onko muutoksia tehty
     */
    public void setMuutettu(boolean b)   { changed = b; }

    /**
     * @param k korvattava tai lis‰tt‰v‰ kategoria
     * @example
     * <pre name="test">
     *    Groups kat = new Groups();
     *    Group k1 = new Group("tyo");
     *    Group k2 = new Group("opiskelu");
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
    public void korvaaTaiLisaa(Group k) {
        if (k == null) return;
        for (int i = 0; i < getLkm(); i++) {
            if (content.get(i).getTunnusNro() == k.getTunnusNro()) {
                content.set(i, k);
                changed = true;
                return;
            }
        }
        add(k);
    }

    /**
     * @return Palauttaa kaikki groups sortattuna otsikon perusteella
     * @example
     * <pre name="test">
     *   #import java.util.*;
     *    Groups kat = new Groups();
     *    Group k1 = new Group("tyo");
     *    Group k2 = new Group("opiskelu");
     *    kat.lisaa(k1); kat.lisaa(k2);
     *    List<Group> katet = kat.annagroups();
     *    Iterator<Group> i = katet.iterator();
     *    i.next() === k2;
     *    i.hasNext() === true;
     *    i.next() === k1;
     *    i.hasNext() === false;
     * </pre>
     */
    public List<Group> annagroups() {
        List<Group> groups = new ArrayList<>(getLkm());
        for (Group k : this) {
            groups.add(k);
        }
        Collections.sort(groups);
        return groups;
    }

}

